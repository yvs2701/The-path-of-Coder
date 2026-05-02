import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Extracts audio tracks from all video files in a given directory using the
 * system-installed {@code ffmpeg} binary. Structured around a
 * {@link #read read} &rarr; {@link #process process} &rarr; {@link #write write}
 * pipeline.
 *
 * <p>By default the tool runs in {@link ExtractionMode#STREAM_COPY STREAM_COPY}
 * mode: it calls {@code ffprobe} on each source file to detect the audio codec,
 * derives a compatible container extension from it, then remuxes with
 * {@code -acodec copy} — no re-encoding, no quality loss.
 *
 * <p>Re-encoding to a fixed format ({@link ExtractionMode#MP3 MP3} or
 * {@link ExtractionMode#M4A M4A}) is still available for cases where a uniform
 * output format is required.
 *
 * <p><b>Command-line usage:</b>
 * <pre>
 *   java ExtractAudioFromVideoDirectory &lt;input-folder&gt; &lt;output-folder&gt; [copy|mp3|m4a]
 *
 *   input-folder  – directory containing video files
 *   output-folder – directory where audio files are written (created if absent)
 *   mode          – copy (default), mp3, or m4a
 * </pre>
 */
public class ExtractAudioFromVideoDirectory {

    /**
     * List of supported video file extensions (lowercase, without leading dot) used
     * to filter the input directory. This is not an exhaustive list of all formats
     * that ffmpeg can read. Files with extensions not in this set are ignored
     * silently, even if ffmpeg could process them.
     */
    private static final Set<String> VIDEO_EXTENSIONS = Set.of(
            "mp4", "mkv", "avi", "mov", "wmv", "flv", "webm", "m4v", "ts", "mpeg", "mpg");

    /**
     * Maps {@code ffprobe} {@code codec_name} values to the file extension of
     * the container that natively holds that codec without re-encoding. Codecs
     * absent from this map fall back to {@link #FALLBACK_EXTENSION} ({@code .mka}),
     * which accepts virtually any codec.
     */
    private static final Map<String, String> CODEC_TO_EXTENSION = Map.ofEntries(
            Map.entry("aac", "m4a"),
            Map.entry("mp3", "mp3"),
            Map.entry("opus", "opus"),
            Map.entry("vorbis", "ogg"),
            Map.entry("flac", "flac"),
            Map.entry("alac", "m4a"),
            Map.entry("ac3", "ac3"),
            Map.entry("eac3", "eac3"),
            Map.entry("dts", "dts"),
            Map.entry("pcm_s16le", "wav"),
            Map.entry("pcm_s24le", "wav"),
            Map.entry("pcm_f32le", "wav"));

    /**
     * Output container extension used when the source audio codec is not present
     * in {@link #CODEC_TO_EXTENSION}. Matroska audio ({@code .mka}) accepts
     * stream-copy of virtually any codec.
     */
    private static final String FALLBACK_EXTENSION = "mka";


    /**
     * Controls whether audio is remuxed as-is or re-encoded to a fixed format.
     *
     * <ul>
     *   <li>{@code STREAM_COPY} — copies the audio bitstream without decoding or
     *       re-encoding. Fastest option; preserves original quality exactly.
     *       The output container is derived automatically from the source codec
     *       via {@link #CODEC_TO_EXTENSION}.</li>
     *   <li>{@code MP3} — re-encodes to MPEG-1 Audio Layer III using
     *       {@code libmp3lame} at variable-bitrate quality level 2 (~190 kbps).</li>
     *   <li>{@code M4A} — re-encodes to AAC in an MPEG-4 audio container at
     *       192 kbps CBR.</li>
     * </ul>
     */
    enum ExtractionMode {
        STREAM_COPY,
        MP3,
        M4A;

        /**
         * Resolves an {@code ExtractionMode} from a case-insensitive string token.
         *
         * @param value one of {@code "copy"}, {@code "mp3"}, or {@code "m4a"}
         * @return the corresponding {@code ExtractionMode} constant
         * @throws IllegalArgumentException if {@code value} does not match any
         *                                  known token
         */
        static ExtractionMode fromString(String value) {
            return switch (value.toLowerCase()) {
                case "copy" -> STREAM_COPY;
                case "mp3" -> MP3;
                case "m4a" -> M4A;
                default -> throw new IllegalArgumentException("Unknown mode: " + value);
            };
        }
    }

    /**
     * Immutable descriptor for a single audio-extraction task. Carries all
     * information needed by {@link #process} and {@link #write} so that neither
     * stage needs to re-derive paths or probe the file system.
     *
     * @param inputFile  absolute path to the source video file
     * @param outputFile absolute path to the audio file to be created; the
     *                   extension is already resolved to match the codec/mode
     * @param mode       the extraction mode that governs the ffmpeg command
     *                   built by {@link #process}
     */
    record ExtractionJob(Path inputFile, Path outputFile, ExtractionMode mode) {
    }

    private static final boolean isWindowsOS = isWindows();

    /**
     * Command-line entry point. Parses arguments, validates the input directory,
     * and delegates to {@link #run}.
     *
     * <p>Expected arguments:
     * <ol>
     *   <li>{@code args[0]} — path to the directory containing source video files</li>
     *   <li>{@code args[1]} — path to the directory where audio files will be written</li>
     *   <li>{@code args[2]} <i>(optional)</i> — extraction mode: {@code copy} (default),
     *       {@code mp3}, or {@code m4a}</li>
     * </ol>
     *
     * <p>Exits with status {@code 1} if fewer than two arguments are supplied or
     * if {@code args[0]} is not an existing directory.
     *
     * @param args command-line arguments as described above
     * @throws IOException          if an I/O error occurs while listing files or
     *                              spawning subprocesses
     * @throws InterruptedException if the current thread is interrupted while
     *                              waiting for {@code ffprobe} or {@code ffmpeg}
     *                              to finish
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 2) {
            System.err.println("Usage: java AudioExtractor <input-folder> <output-folder> [copy|mp3|m4a]");
            System.exit(1);
        }

        Path inputDir = Paths.get(args[0]);
        Path outputDir = Paths.get(args[1]);
        ExtractionMode mode = args.length >= 3
                ? ExtractionMode.fromString(args[2])
                : ExtractionMode.STREAM_COPY; // default mode

        if (!Files.isDirectory(inputDir)) {
            System.err.println("Input path is not a directory: " + inputDir);
            System.exit(1);
        }

        run(inputDir, outputDir, mode);
    }

    /**
     * Drives the full {@link #read read} &rarr; {@link #process process} &rarr;
     * {@link #write write} pipeline sequentially for every video file found in
     * {@code inputDir}.
     *
     * <p>This method is the Spring Batch insertion point: replace the
     * {@code for}-loop with a chunk-oriented step backed by an
     * {@code ItemReader} calling {@link #read}, an {@code ItemProcessor} calling
     * {@link #process}, and an {@code ItemWriter} calling {@link #write}.
     *
     * <p>Prints a summary of succeeded and failed extractions to stdout on
     * completion. If no eligible video files are found, a notice is printed and
     * the method returns without error.
     *
     * @param inputDir  directory to scan for source video files; must exist and
     *                  be a directory
     * @param outputDir directory where extracted audio files are written; created
     *                  automatically if absent
     * @param mode      extraction mode applied to every job in this run
     * @throws IOException          if directory listing or subprocess I/O fails
     * @throws InterruptedException if the current thread is interrupted while
     *                              waiting for a subprocess to finish
     */
    public static void run(Path inputDir, Path outputDir, ExtractionMode mode)
            throws IOException, InterruptedException {

        List<ExtractionJob> jobs = read(inputDir, outputDir, mode);

        if (jobs.isEmpty()) {
            System.out.println("No processable video files found in: " + inputDir);
            return;
        }

        System.out.printf("Found %d video file(s). Mode: %s%n", jobs.size(), mode);

        int success = 0, failure = 0;
        for (ExtractionJob job : jobs) {
            if (write(job))
                success++;
            else
                failure++;
        }

        System.out.printf("%nDone. Success: %d  Failed: %d%n", success, failure);
    }

    /**
     * Scans {@code inputDir} and returns one {@link ExtractionJob} per video
     * file found, skipping subdirectories and non-video files silently.
     *
     * <p>For {@link ExtractionMode#STREAM_COPY STREAM_COPY} mode, {@code ffprobe}
     * is invoked here — during the read phase — to detect each file's audio
     * codec and resolve a compatible output container extension. Performing the
     * probe in {@code read()} keeps {@link #process} and {@link #write} free of
     * any file-inspection logic. Files whose probe fails are logged to stderr and
     * excluded from the returned list.
     *
     * <p>For {@link ExtractionMode#MP3 MP3} and {@link ExtractionMode#M4A M4A}
     * modes the output extension is fixed and no probing is performed.
     *
     * @param inputDir  directory to scan; must exist and be a directory
     * @param outputDir directory under which output file paths are constructed;
     *                  does not need to exist at call time
     * @param mode      determines whether {@code ffprobe} is called and what
     *                  output extension is assigned to each job
     * @return an unordered list of {@link ExtractionJob} instances, one per
     *         eligible video file; empty if no video files are found
     * @throws IOException          if listing {@code inputDir} fails at the OS level
     * @throws InterruptedException if the current thread is interrupted while
     *                              waiting for an {@code ffprobe} subprocess
     */
    public static List<ExtractionJob> read(Path inputDir, Path outputDir, ExtractionMode mode)
            throws IOException, InterruptedException {

        File[] files = inputDir.toFile().listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .filter(f -> VIDEO_EXTENSIONS.contains(extension(f.getName())))
                .map(f -> buildJob(f.toPath(), outputDir, mode))
                .filter(job -> job != null)
                .collect(Collectors.toList());
    }

    /**
     * Constructs a single {@link ExtractionJob} for the given source file.
     *
     * <p>Resolves the output file extension based on {@code mode}: fixed for
     * {@code MP3} and {@code M4A}; dynamically probed via {@link #resolveExtension}
     * for {@code STREAM_COPY}. Returns {@code null} if extension resolution fails
     * (e.g. {@code ffprobe} is not on {@code PATH}), causing the caller to skip
     * this file.
     *
     * @param inputFile source video file
     * @param outputDir directory in which the output audio file will be placed
     * @param mode      extraction mode controlling codec and container selection
     * @return a fully populated {@link ExtractionJob}, or {@code null} if
     *         {@code ffprobe} fails for this file in {@code STREAM_COPY} mode
     */
    private static ExtractionJob buildJob(Path inputFile, Path outputDir, ExtractionMode mode) {
        String baseName = stripExtension(inputFile.getFileName().toString());
        String ext;
        try {
            ext = switch (mode) {
                case MP3 -> "mp3";
                case M4A -> "m4a";
                case STREAM_COPY -> resolveExtension(inputFile);
            };
        } catch (IOException | InterruptedException e) {
            System.err.printf("ffprobe failed for %s: %s - skipping%n",
                    inputFile.getFileName(), e.getMessage());
            return null;
        }
        Path outputFile = outputDir.resolve(baseName + "." + ext);
        return new ExtractionJob(inputFile, outputFile, mode);
    }

    /**
     * Executes the {@code ffmpeg} command produced by {@link #process} and writes
     * the resulting audio file to {@link ExtractionJob#outputFile()}.
     *
     * <p>Creates all missing parent directories of the output file before
     * launching {@code ffmpeg}. The process inherits stdin, stdout, and stderr
     * from the JVM so that ffmpeg's progress output is visible in the terminal.
     *
     * @param job the extraction job to execute; {@link #process} is called
     *            internally to obtain the command — callers do not need to call
     *            it separately
     * @return {@code true} if {@code ffmpeg} exited with code {@code 0};
     *         {@code false} if it exited with a non-zero code, in which case an
     *         error line is also printed to stderr
     * @throws IOException          if the output directory cannot be created or
     *                              the {@code ffmpeg} process cannot be started
     * @throws InterruptedException if the current thread is interrupted while
     *                              waiting for the {@code ffmpeg} process to exit
     */
    public static boolean write(ExtractionJob job) throws IOException, InterruptedException {
        Files.createDirectories(job.outputFile().getParent());
        List<String> command = process(job);

        System.out.printf("Extracting [%s]: %s → %s%n",
                job.mode(),
                job.inputFile().getFileName(),
                job.outputFile().getFileName());

        Process proc = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .inheritIO()
                .start();

        int exitCode = proc.waitFor();
        if (exitCode != 0) {
            System.err.printf("ffmpeg failed (exit %d) for: %s%n", exitCode, job.inputFile());
        }
        return exitCode == 0;
    }

    /**
     * Translates an {@link ExtractionJob} into the {@code ffmpeg} command-line
     * token list that {@link #write} will execute.
     *
     * <p>The command always includes {@code -vn} to discard the video stream and
     * {@code -y} to overwrite the output file without prompting. The codec
     * arguments vary by mode:
     * <ul>
     *   <li>{@code STREAM_COPY} &rarr; {@code -acodec copy}</li>
     *   <li>{@code MP3}         &rarr; {@code -acodec libmp3lame -q:a 2}</li>
     *   <li>{@code M4A}         &rarr; {@code -acodec aac -b:a 192k}</li>
     * </ul>
     *
     * <p>The {@code ffmpeg} binary name is {@code ffmpeg.exe} on Windows and
     * {@code ffmpeg} elsewhere, resolved from the system {@code PATH}.
     *
     * @param job the extraction job describing input path, output path, and mode;
     *            must not be {@code null}
     * @return an unmodifiable {@link List} of command tokens ready to be passed
     *         to {@link ProcessBuilder}
     */
    public static List<String> process(ExtractionJob job) {
        String ffmpeg = isWindowsOS ? "ffmpeg.exe" : "ffmpeg";
        List<String> codecArgs = switch (job.mode()) {
            case STREAM_COPY -> List.of("-acodec", "copy");
            case MP3 -> List.of("-acodec", "libmp3lame", "-q:a", "2");
            case M4A -> List.of("-acodec", "aac", "-b:a", "192k");
        };

        var command = new java.util.ArrayList<String>();
        command.add(ffmpeg);
        command.addAll(List.of("-i", job.inputFile().toString()));
        command.add("-vn");
        command.addAll(codecArgs);
        command.addAll(List.of("-y", job.outputFile().toString()));
        return Collections.unmodifiableList(command);
    }

    /**
     * Determines the appropriate output file extension for a stream-copy
     * extraction by probing the source file's audio codec and looking it up in
     * {@link #CODEC_TO_EXTENSION}.
     *
     * <p>If the detected codec has no entry in the map, {@link #FALLBACK_EXTENSION}
     * ({@code mka}) is returned and a notice is printed to stdout.
     *
     * @param file path to the source video file to probe; must exist and be
     *             readable
     * @return a lowercase file extension string (without leading dot) compatible
     *         with the file's audio codec for stream-copy remuxing; never
     *         {@code null}
     * @throws IOException          if launching the {@code ffprobe} subprocess fails
     * @throws InterruptedException if the current thread is interrupted while
     *                              waiting for {@code ffprobe} to exit
     */
    private static String resolveExtension(Path file) throws IOException, InterruptedException {
        String codec = probeAudioCodec(file);
        String ext = CODEC_TO_EXTENSION.getOrDefault(codec, FALLBACK_EXTENSION);
        if (ext.equals(FALLBACK_EXTENSION) && !codec.isEmpty()) {
            System.out.printf("No known container for codec '%s' in %s — using .%s%n",
                    codec, file.getFileName(), FALLBACK_EXTENSION);
        }
        return ext;
    }

    /**
     * Invokes {@code ffprobe} to retrieve the {@code codec_name} of the first
     * audio stream ({@code a:0}) in the given file.
     *
     * <p>The command issued is:
     * <pre>
     *   ffprobe -v error -select_streams a:0 -show_entries stream=codec_name
     *           -of default=noprint_wrappers=1:nokey=1 &lt;file&gt;
     * </pre>
     * Only stdout is read; stderr is left separate so ffprobe's own diagnostics
     * do not pollute the codec value.
     *
     * @param file path to the media file to inspect; must exist and be readable
     * @return the lowercase {@code codec_name} string reported by {@code ffprobe}
     *         (e.g. {@code "aac"}, {@code "mp3"}, {@code "opus"}), or an empty
     *         string if {@code ffprobe} produces no output or the file has no
     *         audio stream
     * @throws IOException          if the {@code ffprobe} process cannot be started
     * @throws InterruptedException if the current thread is interrupted while
     *                              waiting for {@code ffprobe} to exit
     */
    private static String probeAudioCodec(Path file) throws IOException, InterruptedException {
        String ffprobe = isWindowsOS ? "ffprobe.exe" : "ffprobe";
        List<String> command = List.of(
                ffprobe,
                "-v", "error",
                "-select_streams", "a:0",
                "-show_entries", "stream=codec_name",
                "-of", "default=noprint_wrappers=1:nokey=1",
                file.toString());

        Process proc = new ProcessBuilder(command)
                .redirectErrorStream(false)
                .start();

        String codec;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(proc.getInputStream()))) {
            codec = reader.lines()
                    .map(String::trim)
                    .filter(l -> !l.isEmpty())
                    .findFirst()
                    .orElse("");
        }

        proc.waitFor();
        return codec.toLowerCase();
    }

    /**
     * Extracts the lowercase file extension from a filename.
     *
     * @param filename filename string, with or without a path prefix
     * @return the substring after the last {@code '.'}, lowercased; or an empty
     *         string if the filename contains no {@code '.'}
     */
    private static String extension(String filename) {
        int dot = filename.lastIndexOf('.');
        return dot >= 0 ? filename.substring(dot + 1).toLowerCase() : "";
    }

    /**
     * Removes the extension (the last {@code '.'} and everything after it) from
     * a filename.
     *
     * @param filename filename string to strip
     * @return the filename without its extension; returns the original string
     *         unchanged if it contains no {@code '.'}
     */
    private static String stripExtension(String filename) {
        int dot = filename.lastIndexOf('.');
        return dot >= 0 ? filename.substring(0, dot) : filename;
    }

    /**
     * Detects whether the current JVM is running on a Windows host by inspecting
     * the {@code os.name} system property.
     *
     * @return {@code true} if {@code os.name} contains {@code "win"}
     *         (case-insensitive); {@code false} otherwise
     */
    private static boolean isWindows() {
        boolean isWindowsOS = false;
        try {
            isWindowsOS = System.getProperty("os.name", "").toLowerCase().contains("win");
        } catch (SecurityException e) {
            System.err.println("Unable to determine OS name due to security restrictions: " + e.getMessage());
        }
        return isWindowsOS;
    }
}
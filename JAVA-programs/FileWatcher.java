import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class FileWatcher {
    public static void main(String[] args) {
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream("./file.txt"))) {
            boolean threadRunning = true;

            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_ITALIC_GREEN = "\033[3m\u001B[32m";
            int noEditCount = 0;

            while (threadRunning) {
                if (reader.available() > 0) {
                    System.out.print((char) reader.read());
                    noEditCount = 0;
                } else {
                    if (noEditCount == 6) {
                        // print styled text for no edits every minute (6*10 seconds)
                        System.out.println("\n" + ANSI_ITALIC_GREEN + "[no new edit]" + ANSI_RESET);
                        noEditCount = 0;
                    }
                    noEditCount++;
                    try {
                        // try catch was needed to handle: "Unhandled exception type
                        // InterruptedException" in Thread.sleep(long ms) method
                        java.lang.Thread.sleep(10000); // watches every 10 seconds
                    } catch (InterruptedException e) {
                        threadRunning = false;
                        System.err.println(e);
                    } finally {
                        // not needed since try with resources has been implemented
                        // reader.close();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found !");
        } catch (IOException e) {
            // or just use throws IOException but it will ismply propagate this checked
            // exception
            // to the calling function so here it won't make any difference
            e.printStackTrace();
        }
    }
}
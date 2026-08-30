import csv
import re
from urllib.parse import parse_qs, urlparse
from bs4 import BeautifulSoup, Tag

def attribute_extractor(tag: Tag, attribute: str) -> str:
    return tag.get(attribute, '').__str__() if tag and tag.has_attr(attribute) else ''

def text_content_extractor(tag: Tag) -> str:
    # Video titles/channel names in the new YouTube markup are wrapped across
    # multiple lines/spans, so collapse all internal whitespace into single spaces.
    return re.sub(r'\s+', ' ', tag.get_text(' ', strip=True)) if tag else ''

def extract_video_info(video: Tag) -> tuple[str, str, str, str]:
    title = ''
    channel = ''
    video_url = ''
    thumb_url = ''

    # Extract Video Title
    # <a class="ytLockupMetadataViewModelTitle" href="/watch?v=...">
    title_tag = video.find('a', class_='ytLockupMetadataViewModelTitle')
    title = text_content_extractor(title_tag)

    # Extract Channel/Uploader Name
    # <yt-content-metadata-view-model> with the channel(s) in the
    # first ".ytContentMetadataViewModelMetadataRow". For collab videos with
    # multiple channels, YouTube renders plain text ("A and B") with no <a>
    # tags, so we grab the row's full text rather than looking for a link.
    channel_row = video.select_one(
        'yt-content-metadata-view-model .ytContentMetadataViewModelMetadataRow'
    )
    channel = text_content_extractor(channel_row)

    # Extract Video URL
    if title_tag and title_tag.has_attr('href'):
        link_href = attribute_extractor(title_tag, 'href')

        parsed_url = urlparse(link_href)
        query_params = parse_qs(parsed_url.query)

        # YouTube hrefs are typically relative like: /watch?v=<VIDEO_ID>...
        if 'v' in query_params:
            # Reconstruct a clean URL using only the 'v' parameter if it exists
            video_id = query_params['v'][0]
            video_url = f"https://www.youtube.com/watch?v={video_id}"
        else:
            # Fallback for unexpected URL structures
            video_url = f"https://www.youtube.com{link_href}" if link_href.startswith('/') else link_href

    # Extract Thumbnail Link
    img_tag = video.find('img')
    thumb_src = attribute_extractor(img_tag, 'src') if img_tag else ''

    # Match YouTube image URL structure: https://i.ytimg.com/vi/<VIDEO_ID>/...
    if thumb_src:
        video_id = thumb_src.split('/vi/')[1].split('/')[0] if '/vi/' in thumb_src else None
        thumb_url = f"https://i.ytimg.com/vi/{video_id}/maxresdefault.jpg" if video_id else thumb_src

    return title, channel, video_url, thumb_url

def extract_yt_liked_videos(html_file_path: str, output_csv_path: str) -> None:
    """
    Navigate to the "Liked Videos" page on YouTube, scroll all the way down to load all liked videos then use
    inspect element on theis page to copy the HTML content of the HTML tag which stores the list of videos.
    Then paste it in a file and use this script to extract the video titles, channel names, and thumbnail links into a CSV file.

    YouTube "Liked Videos" HTML structure (as of Aug 2026 frontend redesign)
    ------------------------------------------------------------------------
    Each video row is a <yt-lockup-view-model> element. Column extraction sources:

    Video Title
        ```html
        <a class="ytLockupMetadataViewModelTitle" href="/watch?v=<ID>...">
            <span>Title text (wrapped across multiple lines/spans)</span>
        </a>
        ```
        - No `title` attribute anymore; text must be pulled from the element's
        visible content and whitespace-normalized.

    Channel/Uploader Name
        ```html
        <yt-content-metadata-view-model>
            <div class="ytContentMetadataViewModelMetadataRow">
                <a href="/@handle">Channel Name</a>   <!-- single channel -->
                OR plain text "ChannelA and ChannelB" <!-- collab videos, no <a> -->
            </div>
            <div class="ytContentMetadataViewModelMetadataRow">
                views • upload date
            </div>
        </yt-content-metadata-view-model>
        ```
        - Always read the FIRST metadata row as raw text (not by finding an <a>),
        since collab/multi-channel videos render as plain text with no link.

    Video URL
        Derived from the title `<a>`'s href: `"/watch?v=<ID>&list=LL&index=N&pp=..."`
        - Parse query params and keep only `v` to build a clean
        https://www.youtube.com/watch?v=<ID> URL.

    Thumbnail Link
        `<img src="https://i.ytimg.com/vi/<ID>/hqdefault.jpg?sqp=...&rs=...">`
        - Extract the video ID between "/vi/" and the next "/", then rebuild a
        clean maxresdefault.jpg URL (drops the tracking query params).
    """
    with open(html_file_path, 'r', encoding='utf-8') as f:
        soup = BeautifulSoup(f, 'html.parser')

    videos = soup.find_all('yt-lockup-view-model')

    with open(output_csv_path, 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['Video Title', 'Channel/Uploader Name', 'Video URL', 'Thumbnail Link'])

        for video in videos:
            title, channel, video_url, thumb = extract_video_info(video)
            if title or channel or video_url or thumb: # Only write if at least one piece of information is present
                writer.writerow([title, channel, video_url, thumb])

if __name__ == "__main__":
    extract_yt_liked_videos(
        '/Users/yvs2701/Codeplayground/The_path_of_coder/Utility Scripts/ytLikedVideos.html',
        '/Users/yvs2701/Codeplayground/The_path_of_coder/Utility Scripts/YouTube Liked Videos.csv'
    )

import csv
from bs4 import BeautifulSoup, Tag

def attribute_extractor(tag: Tag, attribute: str) -> str:
    return tag.get(attribute, '').__str__() if tag and tag.has_attr(attribute) else ''

def text_content_extractor(tag: Tag) -> str:
    return tag.text.__str__() if tag else ''

def extract_video_info(video: Tag) -> tuple[str, str, str, str]:
    # Extract Video Title
    title_tag = video.find('a', id='video-title')
    title = attribute_extractor(title_tag, 'title') if title_tag else ''

    # Extract Channel/Uploader Name
    channel_tag = video.select_one('ytd-channel-name yt-formatted-string#text')
    channel = text_content_extractor(channel_tag) if channel_tag else ''

    # Extract Video URL
    video_url = ''
    if title_tag and title_tag.has_attr('href'):
        href = attribute_extractor(title_tag, 'href')
        # YouTube hrefs are typically relative (e.g., /watch?v=...)
        video_url = f"https://www.youtube.com{href}" if href.startswith('/') else href

    # Extract Thumbnail Link
    img_tag = video.find('img')
    thumb = attribute_extractor(img_tag, 'src') if img_tag else ''

    return title, channel, video_url, thumb

def extract_yt_liked_videos(html_file_path: str, output_csv_path: str) -> None:
    """
    Navigate to the "Liked Videos" page on YouTube, scroll all the way down to load all liked videos then use
    inspect element on theis page to copy the HTML content of the HTML tag which stores the list of videos.
    Then paste it in a file and use this script to extract the video titles, channel names, and thumbnail links into a CSV file.
    """
    with open(html_file_path, 'r', encoding='utf-8') as f:
        soup = BeautifulSoup(f, 'html.parser')

    videos = soup.find_all('ytd-playlist-video-renderer')

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

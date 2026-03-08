import csv
from urllib.parse import parse_qs, urlparse
from bs4 import BeautifulSoup, Tag

def attribute_extractor(tag: Tag, attribute: str) -> str:
    return tag.get(attribute, '').__str__() if tag and tag.has_attr(attribute) else ''

def text_content_extractor(tag: Tag) -> str:
    return tag.text.__str__() if tag else ''

def extract_video_info(video: Tag) -> tuple[str, str, str, str]:
    title = ''
    channel = ''
    video_url = ''
    thumb_url = ''

    # Extract Video Title
    title_tag = video.find('a', id='video-title')
    title = attribute_extractor(title_tag, 'title') if title_tag else ''

    # Extract Channel/Uploader Name
    channel_tag = video.select_one('ytd-channel-name yt-formatted-string#text')
    channel = text_content_extractor(channel_tag) if channel_tag else ''

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

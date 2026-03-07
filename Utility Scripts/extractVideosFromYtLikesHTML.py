import csv
from bs4 import BeautifulSoup

def extract_video_info(video: str) -> tuple[str, str, str]:
    # Extract Video Title
    title_tag = video.find('a', id='video-title')
    title = title_tag.get('title', '').replace(',', '-') if title_tag else ''

    # Extract Channel/Uploader Name
    channel_tag = video.select_one('ytd-channel-name yt-formatted-string#text')
    channel = channel_tag.text.replace(',', '-') if channel_tag else ''

    # Extract Thumbnail Link
    img_tag = video.find('img')
    thumb = img_tag.get('src', '').replace(',', '-') if img_tag else ''

    return title, channel, thumb

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
        writer.writerow(['Video Title', 'Channel/Uploader Name', 'Thumbnail Link'])

        for video in videos:
            title, channel, thumb = extract_video_info(video)
            if title or channel or thumb:
                writer.writerow([title, channel, thumb])

if __name__ == "__main__":
    extract_yt_liked_videos(
        '/Users/yvs2701/Codeplayground/The_path_of_coder/Utility Scripts/ytLikedVideos.html',
        '/Users/yvs2701/Codeplayground/The_path_of_coder/Utility Scripts/YouTube Liked Videos.csv'
    )

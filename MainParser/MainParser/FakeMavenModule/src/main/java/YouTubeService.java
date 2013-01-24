import java.net.URL;

public class YouTubeService
{
    public YouTubeService(String clientID)
    {

    }

    public VideoEntry getEntry(URL url, Class<VideoEntry> videoEntryClass)
    {
        return new VideoEntry();
    }

    public VideoFeed getFeed(URL url, Class<VideoFeed> videoEntry)
    {
        return new VideoFeed();
    }
}

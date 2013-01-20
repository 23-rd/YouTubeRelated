import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 @author Tsykal Alexandr
 @version 1.0
 class created specifically for tests
 */

public class FakeYouTubeConnector
{
    /**
     * YouTubeService open connection to YouTube using clientID
     * VideoEntry open video canal
     * String linked - get Href and go to received link
     * @return write RelatedVideo Attribute in console
     */
    public void fakeConnection(String clientID) throws MalformedURLException {
        YouTubeService service = new YouTubeService(clientID);
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"), VideoEntry.class);
        if (videoEntry.getRelatedVideosLink() != null)
        {
            String linked = videoEntry.getRelatedVideosLink().getHref().toString();
            VideoFeed videoFeed = service.getFeed(new URL(linked), VideoFeed.class);
            for(VideoEntry entry : videoFeed.getEntries() )
            {
                System.out.println("Title: " + entry.getTitle().getPlainText().toString());
                System.out.println("Link: " + entry.getHtmlLink().getHref());
                if (entry.getRating()!=null)
                    System.out.println("Rating: " + entry.getRating().getAverage());
            }
        }
    }
}


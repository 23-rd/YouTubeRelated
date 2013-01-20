import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 @author Tsykal Alexandr
 @version 1.0
 */

public class YouTubeConnector
{
    /**
     * @param clientID need to initiate client on YouTube
     */
    String clientID = String.valueOf(System.currentTimeMillis());

    /**
     * @input string receive in console
     * @return code of the YouTube video
     */
    private String urlRecovery(String url)
    {
        Pattern p = Pattern.compile("&\\S*$");
        Matcher m = p.matcher(url);
        String temp = m.replaceFirst("");
        Pattern pattern = Pattern.compile("/|watch|v=|&|#|!|feature=player_embedded");
        String[] split = pattern.split(temp);
        return split[split.length-1];
    }

    /**
     * @input related video link
     * @return link without additional parameters
     */
    private String linkRecovery(String link)
    {
        Pattern p1 = Pattern.compile("&\\S*$");
        Matcher m1 = p1.matcher(link);
        String temp1 = m1.replaceFirst("");
        return temp1;
    }

    /**
     * YouTubeService open connection to YouTube using clientID
     * VideoEntry open video canal
     * String linked - get Href and go to received link
     * @return write RelatedVideo Attribute in console
     */
    public void getRelatedVideos(String url) throws IOException, ServiceException
    {
        String finalUrl = "http://gdata.youtube.com/feeds/api/videos/" + urlRecovery(url);
        YouTubeService service = new YouTubeService(clientID);
        VideoEntry videoEntry = service.getEntry(new URL(finalUrl), VideoEntry.class);
        if (videoEntry.getRelatedVideosLink() != null)
        {
            String linked = videoEntry.getRelatedVideosLink().getHref().toString();
            VideoFeed videoFeed = service.getFeed(new URL(linked), VideoFeed.class);
            for(VideoEntry entry : videoFeed.getEntries() )
            {
                System.out.println("Title: " + entry.getTitle().getPlainText().toString());
                System.out.println("Link: " + linkRecovery(entry.getHtmlLink().getHref().toString()));
                if (entry.getRating()!=null)
                System.out.println("Rating: " + entry.getRating().getAverage().toString());
            }
        }
    }
}


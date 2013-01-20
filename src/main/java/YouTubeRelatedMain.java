import com.google.gdata.util.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

/**
@author Tsykal Alexandr
@version 1.0
 */
public class YouTubeRelatedMain {

    /**
     * @value command line
    */
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static YouTubeConnector youTubeConnector = new YouTubeConnector();

    public static FakeYouTubeConnector fakeYouTubeConnector = new FakeYouTubeConnector();

    /**
     * @exception IOException input string is not valid
     * @exception MalformedURLException input string is not URL
     * @exception ServiceException module cannot connect to YouTube Service
     * take link and start connection to YouTube
     */
    public static YouTubeRelatedMain init() throws IOException, ServiceException {
        System.out.println("Please enter youtube link:");
        String url = in.readLine();
        try
        {
            youTubeConnector.getRelatedVideos(url);
        }
        catch (MalformedURLException e) {
            System.out.println("Invalid URL, please retry");
            YouTubeRelatedMain youTubeRelated = YouTubeRelatedMain.init();
        } catch (ServiceException e) {
            fakeYouTubeConnector.fakeConnection(String.valueOf(System.currentTimeMillis()));
        } catch (IOException e) {
            System.out.println("Invalid URL, please retry");
            YouTubeRelatedMain youTubeRelated = YouTubeRelatedMain.init();
        }
        return new YouTubeRelatedMain();
    }

    public static void main(String[] args) throws IOException, ServiceException {
        init();
    }
}

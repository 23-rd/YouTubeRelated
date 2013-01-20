import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 16.01.13
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */

public class YouTubeRelatedTest
{
    /*Проверка доступности сервера*/
    @Test
    public void testservice()
    {
        assertNotNull(new YouTubeService(String.valueOf(System.currentTimeMillis())));
    }

    /*Проверка перехода по ссылке*/
    @Test
    public void testVideoEntry() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        assertNotNull(service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class));
    }

    /*Проверка существования связанных видео(ссылок)*/
    @Test
    public void testRelatedLinks() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class);
        assertNotNull(videoEntry.getRelatedVideosLink());
    }

    /*Проверка получения связанных видео(ссылок)*/
    @Test
    public void testVideoHref() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class);
        assertNotNull(videoEntry.getRelatedVideosLink().getHref());
    }

    /*Проверка видео канала*/
    @Test
    public void testVideoFeed() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class);
        assertNotNull(service.getFeed(new URL(videoEntry.getRelatedVideosLink().getHref()), VideoFeed.class));
    }

    /*Получение связных видео*/
    @Test
    public void testRelatedVideo() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class);
        VideoFeed videoFeed = service.getFeed(new URL(videoEntry.getRelatedVideosLink().getHref()), VideoFeed.class);
        assertNotNull(videoFeed.getEntries());
    }

    /*Получение ссылок на связные видео*/
    @Test
    public void testRelatedVideoLinks() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class);
        VideoFeed videoFeed = service.getFeed(new URL(videoEntry.getRelatedVideosLink().getHref()), VideoFeed.class);
        VideoEntry[] videoEntry1 = videoFeed.getEntries();
        assertNotNull(videoEntry1[0].getHtmlLink().getHref());
    }

    /*Получение названия связных видео*/
    @Test
    public void testRelatedVideoTitles() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class);
        VideoFeed videoFeed = service.getFeed(new URL(videoEntry.getRelatedVideosLink().getHref()), VideoFeed.class);
        VideoEntry[] videoEntry1 = videoFeed.getEntries();
        assertNotNull(videoEntry1[0].getHtmlLink().getHref());
    }

    /*Получение рейтинга связных видео*/
    @Test
    public void testRelatedVideoRating() throws MalformedURLException
    {
        YouTubeService service = new YouTubeService(String.valueOf(System.currentTimeMillis()));
        VideoEntry videoEntry = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/videos/5KQDQAxle5Q"),
                VideoEntry.class);
        VideoFeed videoFeed = service.getFeed(new URL(videoEntry.getRelatedVideosLink().getHref()), VideoFeed.class);
        VideoEntry[] videoEntry1 = videoFeed.getEntries();
        assertNotNull(videoEntry1[0].getRating().getAverage());
    }

}

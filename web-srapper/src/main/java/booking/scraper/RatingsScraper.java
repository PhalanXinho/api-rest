package booking.scraper;

import booking.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import static booking.Api.hotelName;

public class RatingsScraper {
    public Map<String, String> getRatings() {
        Map<String, String> ratingsMap = new HashMap<>();
        Document document = new Utils().getHtmlDocument(new Utils().getHotelHref(hotelName));
        Elements ratings = document.getElementsByClass("ccff2b4c43 cfc0860887");
        get(ratingsMap, ratings);
        return ratingsMap;
    }

    private void get(Map<String, String> ratingsMap, Elements ratings) {
        for (Element rating : ratings) {
            String ratingTitle = rating.getElementsByClass("d6d4671780").text();
            String ratingMark = rating.getElementsByClass("ee746850b6 b8eef6afe1").text();
            ratingsMap.put(ratingTitle, ratingMark);
        }
    }
}

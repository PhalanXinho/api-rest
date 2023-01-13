package booking.scraper;

import booking.Utils;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

import static booking.Api.hotelName;

public class MainPageScraper {
    public Map<String, String> getHotel() {
        Map<String, String> hotel = new HashMap<>();
        Document document = new Utils().getHtmlDocument(new Utils().getHotelHref(hotelName));
        String url = document.getElementsByClass("bui_breadcrumb__link_masked").select("a").attr("href");
        put(hotel, url);
        return hotel;
    }

    private String getName() {
        return new Utils().parseName(new Utils().getHotelHref(hotelName));
    }

    private String getLabel(String url) {
        return new Utils().parseLabel(url);
    }

    private String getSid(String url) {
        return new Utils().parseSid(url);
    }

    private void put(Map<String, String> hotel, String url) {
        hotel.put("name", getName());
        hotel.put("label", getLabel(url));
        hotel.put("sid", getSid(url));
        hotel.put("url", url);
    }
}

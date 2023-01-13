package booking.scraper;

import booking.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static booking.Api.hotelName;

public class ServicesScraper {
    public Map<String, List<String>> getServices() {
        Map<String, List<String>> servicesMap = new HashMap<>();
        Document document = new Utils().getHtmlDocument(new Utils().getHotelHref(hotelName));
        Elements elements = document.getElementsByClass("hotel-facilities-group");
        get(servicesMap, elements);
        return servicesMap;
    }

    private void get(Map<String, List<String>> servicesMap, Elements elements) {
        for (Element element : elements) {
            List<String> serviceList = new ArrayList<>();
            String title = element.getElementsByClass("bui-title__text hotel-facilities-group__title-text").text();
            Elements services = element.getElementsByClass("bui-list__description");
            for (Element service : services) {
                serviceList.add(service.getElementsByClass("bui-list__description").text());
            }
            servicesMap.put(title, serviceList);
        }
    }
}

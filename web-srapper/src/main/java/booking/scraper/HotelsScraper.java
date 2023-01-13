package booking.scraper;

import booking.model.Hotels;
import booking.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static booking.Api.hotelUbication;

public class HotelsScraper {
    public List<Hotels> getHotels() {
        String hotelsNamesUrl = "https://www.booking.com/searchresults.es.html?ss=" + hotelUbication;
        Document document = new Utils().getHtmlDocument(hotelsNamesUrl);
        Elements elements = document.getElementsByClass("a826ba81c4 fe821aea6c fa2f36ad22 afd256fc79 d08f526e0d ed11e24d01 ef9845d4b3 da89aeb942");
        List<Hotels> hotelsList = new ArrayList<>();
        for (Element element : elements) {
            Hotels hotels = new Hotels();
            get(element, hotels);
            hotelsList.add(hotels);
        }
        return hotelsList;
    }

    private void getHotelName(Element element, Hotels hotels) {
        hotels.setName(element.getElementsByClass("fcab3ed991 a23c043802").text());
    }

    private void getHotelUbication(Element element, Hotels hotels) {
        hotels.setUbication(element.getElementsByClass("f4bd0794db b4273d69aa").first().text());
    }

    private void getHotelRating(Element element, Hotels hotels) {
        hotels.setRating(element.getElementsByClass("b5cd09854e d10a6220b4").text());
    }

    private void getHotelDescription(Element element, Hotels hotels) {
        hotels.setDescription(element.getElementsByClass("d8eab2cf7f").text());
    }

    private void get(Element element, Hotels hotels) {
        getHotelName(element, hotels);
        getHotelUbication(element, hotels);
        getHotelRating(element, hotels);
        getHotelDescription(element, hotels);
    }
}

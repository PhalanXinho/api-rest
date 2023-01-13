package booking.scraper;

import booking.model.Ubication;
import booking.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static booking.Api.hotelName;

public class UbicationScraper {
    public Ubication getUbications() {
        Ubication ubication = new Ubication();
        Document document = new Utils().getHtmlDocument(new Utils().getHotelHref(hotelName));
        get(document, ubication);
        return ubication;
    }

    private void getDirection(Document document, Ubication ubication) {
        String direction = document.getElementsByClass("\n" + "hp_address_subtitle\n" + "js-hp_address_subtitle\n" + "jq_tooltip\n").text();
        ubication.setDirection(direction);
    }

    private void getTelephone(Document document, Ubication ubication) {
        Elements hotelName = document.getElementsByClass("d2fee87262 pp-header__title");
        String searchURL = Utils.GOOGLE_SEARCH_URL + "?q=" + hotelName.text();
        Document googleSearch = new Utils().getHtmlDocument(searchURL);
        String hotelTelephone = googleSearch.getElementsByClass("LrzXr zdqRlf kno-fv").text();
        ubication.setTelephone(hotelTelephone);
    }

    private void getCoordinates(Document document, Ubication ubication) {
        Elements coordinates = document.getElementsByClass("hotel-sidebar-map");
        String coords = coordinates.select("a").attr("data-atlas-latlng");
        ubication.setCoords(coords);
    }

    private void get(Document document, Ubication ubication) {
        getDirection(document, ubication);
        getTelephone(document, ubication);
        getCoordinates(document, ubication);
    }
}

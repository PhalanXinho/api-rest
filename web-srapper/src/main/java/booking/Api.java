package booking;

import booking.model.Comments;
import booking.model.Hotels;
import booking.model.Ubication;
import booking.scraper.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class Api {
    public static String hotelName = "";
    public static String hotelUbication = "";

    public void askForHotelName() {
        hotelName = new Utils().requestHotelName();
    }

    public void askForHotelUbication() {
        hotelUbication = new Utils().requestHotelUbication();
    }
    
    public void start() {
        askForHotelName();
        askForHotelUbication();
        runGet();
    }

    private void runGet() {
        get("hotels", (req, res) -> {
            String queryParameter = req.queryParams("ubication");
            if (!queryParameter.equals(hotelUbication)) {
                res.status(400);
                return "Misspelled Address, check the input value in the command line";
            }
            return getHotels();
        });
        get("hotels/:name", (req, res) -> {
            req.params(new Utils().parseName(new Utils().getHotelHref(hotelName)));
            return getUbication();
        });
        get("hotels/:name/services", (req, res) -> {
            req.params(new Utils().parseName(new Utils().getHotelHref(hotelName)));
            return getService();
        });
        get("hotels/:name/comments", (req, res) -> {
            req.params(new Utils().parseName(new Utils().getHotelHref(hotelName)));
            return getComments();
        });
        get("hotels/:name/ratings", (req, res) -> {
            req.params(new Utils().parseName(new Utils().getHotelHref(hotelName)));
            return getRatings();
        });
        get("hotels/:name/details", (req, res) -> {
            req.params(new Utils().parseName(new Utils().getHotelHref(hotelName)));
            return getHotelDetails();
        });
    }

    public String getUbication() {
        UbicationScraper ubicationScraper = new UbicationScraper();
        Ubication ubication = ubicationScraper.getUbications();
        String json = new Gson().toJson(ubication);
        return new Utils().jsonFormatter(json);
    }

    public String getService() {
        ServicesScraper servicesScraper = new ServicesScraper();
        Map<String, List<String>> services = servicesScraper.getServices();
        String json = new Gson().toJson(services);
        return new Utils().jsonFormatter(json);
    }

    public String getComments() {
        CommentsScraper commentsScraper = new CommentsScraper();
        List<Comments> comments = commentsScraper.getComments();
        String json = new Gson().toJson(comments);
        return new Utils().jsonFormatter(json);
    }

    public String getRatings() {
        RatingsScraper ratingsScraper = new RatingsScraper();
        Map<String, String> ratings = ratingsScraper.getRatings();
        String json = new Gson().toJson(ratings);
        return new Utils().jsonFormatter(json);
    }

    public String getHotels() {
        HotelsScraper hotelsScraper = new HotelsScraper();
        List<Hotels> hotels = hotelsScraper.getHotels();
        String json = new Gson().toJson(hotels);
        return new Utils().jsonFormatter(json);
    }

    public String getHotelDetails() throws JsonProcessingException {
        MainPageScraper mainPageScraper = new MainPageScraper();
        Map<String, String> hotel = mainPageScraper.getHotel();
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(hotel);
    }
}

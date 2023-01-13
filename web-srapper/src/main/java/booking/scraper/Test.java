package booking.scraper;

import booking.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        Utils utils = new Utils();
        Map<String, String> hotel = new HashMap<>();
        Document document = utils.getHtmlDocument("https://www.booking.com/hotel/es/paradiselagotauritomogan.es.html");
        String url = document.getElementsByClass("bui-header__logo").select("a").attr("href");
        String name = utils.parseName("https://www.booking.com/hotel/es/paradiselagotauritomogan.es.html");
        String label = utils.parseLabel(url);
        String id = utils.parseSid(url);
        hotel.put("name", name);
        hotel.put("label", label);
        hotel.put("id", id);
        hotel.put("url", url);
        ObjectMapper mapper = new ObjectMapper();
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hotel));

        JsonElement jsonElement = new JsonParser().parse(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hotel));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        System.out.println(jsonObject.get("name").getAsString());
    }

}

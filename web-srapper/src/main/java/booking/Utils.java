package booking;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

    public String getUrlName(String url) {
        String regex = "(?<=/es/).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return "No name in URL";
        }
    }

    public Document getHtmlDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return document;
    }

    public String jsonFormatter(String jsonString) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(jsonString));
    }

    public String getHotelHref(String hotelName) {
        String searchURL = Utils.GOOGLE_SEARCH_URL + "?q=booking+" + hotelName;
        Document document = getHtmlDocument(searchURL);
        return document.getElementsByClass("yuRUbf").select("a").attr("href");
    }

    public String requestHotelName() {
        System.out.print("Enter hotel name: ");
        return new Scanner(System.in).nextLine().replaceAll(" ", "_");
    }

    public String requestHotelUbication() {
        System.out.print("Enter hotel ubication: ");
        return new Scanner(System.in).nextLine();
    }

    public String parseName(String url) {
        String nameRegex = "(?<=/es/).*(?=.es)";
        Pattern pattern = Pattern.compile(nameRegex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    public String parseLabel(String url) {
        String labelRegex = ".*label=([^&|\\n|\\t\\s]+)";
        Pattern pattern = Pattern.compile(labelRegex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public String parseSid(String url) {
        String labelRegex = ".*sid=([^&|\\n|\\t\\s]+)";
        Pattern pattern = Pattern.compile(labelRegex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}

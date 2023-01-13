package booking.scraper;

import booking.model.Comments;
import booking.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static booking.Api.hotelName;

public class CommentsScraper {
    public List<Comments> getComments() {
        String hotelUrlName = new Utils().getUrlName(new Utils().getHotelHref(hotelName));
        String reviewsUrl = "https://www.booking.com/reviews/es/hotel/" + hotelUrlName + "?page=1&rows=25&";
        Document reviewsHtml = new Utils().getHtmlDocument(reviewsUrl);
        Elements reviews = reviewsHtml.getElementsByClass("review_item clearfix ");
        List<Comments> totalComments = new ArrayList<>();
        for (Element review : reviews) {
            Comments comments = new Comments();
            List<String> tagsList = new ArrayList<>();
            get(review, comments, tagsList);
            totalComments.add(comments);
        }
        return totalComments;
    }

    private void getCommentsAuthor(Element review, Comments comments) {
        comments.setAuthor(review.getElementsByClass("reviewer_name").text());
    }

    private void getCommentsStayDate(Element review, Comments comments) {
        comments.setStayDate(review.getElementsByClass("review_staydate ").text().replaceAll("Se alojó en ", ""));
    }

    private void getCommentsCountry(Element review, Comments comments) {
        comments.setCountry(review.getElementsByClass("reviewer_country").text());
    }

    private void getCommentsMark(Element review, Comments comments) {
        comments.setMark(review.getElementsByClass("review-score-badge").text());
    }

    private void getCommentsTitle(Element review, Comments comments) {
        comments.setTitle(review.getElementsByClass("review_item_header_content\n" + " review_item_header_scoreword\n").text());
    }

    private void getCommentsTags(Element review, Comments comments, List<String> tagsList) {
        Elements reviewTags = review.getElementsByClass("review_item_info_tags");
        for (Element reviewTag : reviewTags) {
            Elements tags = reviewTag.getElementsByClass("review_info_tag ");
            for (Element tag : tags) {
                tagsList.add(tag.text().replaceAll("•", ""));
            }
        }
        comments.setTags(tagsList);
    }

    private void getPositiveComments(Element review, Comments comments) {
        comments.setPositiveReview(review.getElementsByClass("review_pos ").text());
    }

    private void getNegativeComments(Element review, Comments comments) {
        comments.setNegativeReview(review.getElementsByClass("review_neg ").text());
    }

    private void get(Element review, Comments comments, List<String> tagsList) {
        getCommentsAuthor(review, comments);
        getCommentsStayDate(review, comments);
        getCommentsCountry(review, comments);
        getCommentsMark(review, comments);
        getCommentsTitle(review, comments);
        getCommentsTags(review, comments, tagsList);
        getPositiveComments(review, comments);
        getNegativeComments(review, comments);
    }
}

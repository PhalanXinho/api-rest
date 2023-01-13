package booking.model;

import java.util.List;

public class Comments {
    public String author;
    public String stayDate;
    public String country;
    public String mark;
    public String title;
    public List<String> tags;
    public String positiveReview;
    public String negativeReview;

    public String getAuthor() {
        return author;
    }

    public String getStayDate() { return stayDate; }

    public String getCountry() {
        return country;
    }

    public String getMark() {
        return mark;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getPositiveReview() {
        return positiveReview;
    }

    public String getNegativeReview() {
        return negativeReview;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStayDate(String stayDate) {this.stayDate = stayDate; }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setPositiveReview(String positiveReview) {
        this.positiveReview = positiveReview;
    }

    public void setNegativeReview(String negativeReview) {
        this.negativeReview = negativeReview;
    }
}

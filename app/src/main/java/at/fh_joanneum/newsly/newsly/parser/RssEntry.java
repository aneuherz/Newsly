package at.fh_joanneum.newsly.newsly.parser;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by aneuh on 29.04.2017.
 */

public class RssEntry {
    private String title;
    private String author;
    private String link;
    private String description;
    private Date pubDate;

    public RssEntry(String title, String author, String link, String description, Date pubDate) {
        this.title = title;
        this.author = author;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "RssEntry{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubDate=" + pubDate +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFormattedDateAndAuthor() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());

        if (getPubDate() != null) {
            return dateFormat.format(getPubDate()) + ", " + getAuthor();
        }

        return "";
    }
}

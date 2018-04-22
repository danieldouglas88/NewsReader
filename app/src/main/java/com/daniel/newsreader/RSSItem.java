package com.daniel.newsreader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSSItem {
    private String title = null;
    private String description = null;
    private String link = null;
    private String pubDate = null;

    private SimpleDateFormat dateOutFormat = new SimpleDateFormat("EEE (MMM d)");
    private SimpleDateFormat dateInFormat  = new SimpleDateFormat("yyyy-MM-dd");

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public SimpleDateFormat getDateOutFormat() {
        return dateOutFormat;
    }

    public void setDateOutFormat(SimpleDateFormat dateOutFormat) {
        this.dateOutFormat = dateOutFormat;
    }

    public SimpleDateFormat getDateinFormat() {
        return dateInFormat;
    }

    public void setDateinFormat(SimpleDateFormat dateinFormat) {
        this.dateInFormat = dateinFormat;
    }

    public String getPubDateFormatted(){

    try{
        if(pubDate != null){
            Date date = dateInFormat.parse(pubDate);
            String pubDatFormatted = dateOutFormat.format(date);
            return pubDatFormatted;
        } else {
            return "No date in the feed";
        }
    }catch(ParseException e){
        return "No date in the feed";
    }
}
}
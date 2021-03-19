package com.luna.application.entity;

import java.util.Date;

public class ImageItem {

    private String id;

    private String Content;

    private String url;

    private int    drawable;

    private Date   date;

    private String title;

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public ImageItem(String id, String content, int drawable, Date date, String title) {
        this.id = id;
        Content = content;
        this.drawable = drawable;
        this.date = date;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ImageItem{" +
            "id='" + id + '\'' +
            ", Content='" + Content + '\'' +
            ", url='" + url + '\'' +
            ", drawable=" + drawable +
            ", date=" + date +
            ", title='" + title + '\'' +
            '}';
    }
}

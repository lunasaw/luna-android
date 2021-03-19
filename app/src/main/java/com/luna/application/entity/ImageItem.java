package com.luna.application.entity;

import java.util.Date;

public class ImageItem {

    private int    id;

    private String Content;

    private String url;

    private Date   date;

    private String title;

    public ImageItem(int id, String content, String url, Date date, String title) {
        this.id = id;
        Content = content;
        this.url = url;
        this.date = date;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

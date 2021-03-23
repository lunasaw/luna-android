package com.luna.application.entity;

import java.util.Date;

public class GoodsDO {

    private int id;

    private String content;

    private Date   date;

    private String title;

    private int    price;

    public GoodsDO(int id, String content, Date date, String title, int price) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.title = title;
        this.price = price;
    }

    public GoodsDO(String title, String content, Date date, int price) {
        this.content = content;
        this.date = date;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public GoodsDO setId(int id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public GoodsDO setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public GoodsDO setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GoodsDO setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public GoodsDO setPrice(int price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsDO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}

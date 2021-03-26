package com.luna.application.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class NewsDO implements Serializable {

    private String uniquekey;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻时间
     */
    private String date;
    /**
     * 新闻分类
     */
    private String category;
    /**
     * 新闻来源
     */
    private String authorName;
    /**
     * 新闻访问链接
     */
    private String url;
    /**
     * 新闻图片链接
     */
    private String thumbnailPicS;
    /**
     * 是否有详细内容
     */
    private String isContent;

    public NewsDO() {}

    public NewsDO(String uniquekey, String title, String date, String category, String authorName, String url,
        String thumbnailPicS, String isContent) {
        this.uniquekey = uniquekey;
        this.title = title;
        this.date = date;
        this.category = category;
        this.authorName = authorName;
        this.url = url;
        this.thumbnailPicS = thumbnailPicS;
        this.isContent = isContent;
    }

    public String getId() {
        return uniquekey;
    }

    public NewsDO setId(String uniquekey) {
        this.uniquekey = uniquekey;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsDO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDate() {
        return date;
    }

    public NewsDO setDate(String date) {
        this.date = date;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public NewsDO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public NewsDO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public NewsDO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getThumbnailPicS() {
        return thumbnailPicS;
    }

    public NewsDO setThumbnailPicS(String thumbnailPicS) {
        this.thumbnailPicS = thumbnailPicS;
        return this;
    }

    public String getIsContent() {
        return isContent;
    }

    public NewsDO setIsContent(String isContent) {
        this.isContent = isContent;
        return this;
    }
}

package com.luna.application.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class NewsDTO {

    @JSONField(name = "data")
    private List<NewsDO> listNews;

    private int          page;

    private int          pageSize;

    public List<NewsDO> getListNews() {
        return listNews;
    }

    public NewsDTO setListNews(List<NewsDO> listNews) {
        this.listNews = listNews;
        return this;
    }

    public int getPage() {
        return page;
    }

    public NewsDTO setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public NewsDTO setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}

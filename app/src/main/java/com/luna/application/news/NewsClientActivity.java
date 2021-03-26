package com.luna.application.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.luna.application.R;
import com.luna.application.api.ApiKey;
import com.luna.application.entity.NewsDO;
import com.luna.application.entity.NewsDTO;
import com.luna.application.utils.HttpUtil;
import com.luna.application.views.ListViewActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsClientActivity extends AppCompatActivity {

    public static final String NEWS        = "NewsClient";
    private ListView           listView;
    private List<NewsDO>       list        = new ArrayList<>();
    NewsAdapter                newsAdapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_client_layout);
        listView = findViewById(R.id.news);
        newsAdapter = new NewsAdapter(this, list);
        listView.setAdapter(newsAdapter);
        getNewsList("top", 1, 10);
        // 点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsDO newsDO = list.get(i);
                Intent intent = new Intent(NewsClientActivity.this, WebViewActivity.class);
                intent.putExtra("news", newsDO);
                startActivity(intent);
            }
        });
    }

    public void getNewsList(String type, int page, int pageSize) {
        get(getUrl(type, page, pageSize));
    }

    private void get(String url) {
        HttpUtil.get(this, url, json -> {
            getParse(json);
        });
    }

    private void getParse(String json) {
        String result = JSONObject.parseObject(json).getString("result");
        NewsDTO newsDTO = JSON.parseObject(result, NewsDTO.class);
        List<NewsDO> listNews = newsDTO.getListNews();
        Log.i(NEWS, "getParse: " + JSON.toJSONString(listNews));
        newsAdapter.notifyDataSetChanged();
        list.clear();
        list.addAll(listNews);
        newsAdapter.notifyDataSetChanged();
    }

    private String getUrl(String type, int page, int pageSize) {
        return String.format(ApiKey.NEWS, type, page, pageSize, ApiKey.KEY_THREE);
    }
}

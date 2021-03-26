package com.luna.application.news;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.luna.application.R;
import com.luna.application.api.ApiKey;
import com.luna.application.entity.GoodsDO;
import com.luna.application.entity.NewsDO;
import com.luna.application.entity.NewsDTO;
import com.luna.application.utils.HttpUtil;
import com.luna.application.warehouse.WarehouseAdapter;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private Context            context;
    private LayoutInflater     layoutInflater;
    public static List<NewsDO> list;

    public NewsAdapter(Context context, List<NewsDO> listNews) {
        this.context = context;
        list = listNews;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        public TextView  title, time, category, from;
        public ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        NewsDO newsDO = list.get(i);
        Log.i("TAG", "getView: " + JSON.toJSONString(newsDO));
        if (view == null) {
            view = layoutInflater.inflate(R.layout.news_item_layout, null);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.tx_news_title);
            holder.time = view.findViewById(R.id.tx_news_time);
            holder.category = view.findViewById(R.id.tx_news_category);
            holder.from = view.findViewById(R.id.news_from);
            holder.img = view.findViewById(R.id.tx_news_img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.title.setText(newsDO.getTitle());
        holder.time.setText(newsDO.getDate());
        holder.category.setText(newsDO.getCategory());
        holder.from.setText(newsDO.getAuthorName());
        Glide.with(context).load(newsDO.getThumbnailPicS()).into(holder.img);
        return view;
    }
}

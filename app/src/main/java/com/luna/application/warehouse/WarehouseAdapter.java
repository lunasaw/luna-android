package com.luna.application.warehouse;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.luna.application.R;
import com.luna.application.entity.GoodsDO;
import com.luna.application.utils.DateUtil;
import com.luna.application.views.exam.ExamAdapter;

import java.util.Date;
import java.util.List;

public class WarehouseAdapter extends BaseAdapter {
    private Context        context;

    private LayoutInflater layoutInflater;

    private List<GoodsDO>  goodsDOS;

    public WarehouseAdapter(Context context, List<GoodsDO> goodsDOS) {
        this.context = context;
        this.goodsDOS = goodsDOS;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return goodsDOS.size();
    }

    @Override
    public Object getItem(int i) {
        return goodsDOS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder {
        public TextView title, content, date, price;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        GoodsDO goodsDO = goodsDOS.get(i);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.goods_item_layout, null);

            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.goods_title);
            holder.date = view.findViewById(R.id.goods_time);
            holder.content = view.findViewById(R.id.goods_content);
            holder.price = view.findViewById(R.id.goods_price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.title.setText("商品名称：" + goodsDO.getTitle());
        holder.content.setText("商品介绍：" + goodsDO.getContent());
        holder.date.setText("上架时间：" + DateUtil.dateToString(goodsDO.getDate(), DateUtil.DatePattern.ONLY_DAY));
        holder.price.setText("商品价格：" + goodsDO.getPrice());
        return view;
    }
}

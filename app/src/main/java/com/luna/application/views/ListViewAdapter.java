package com.luna.application.views;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.luna.application.R;
import com.luna.application.entity.ImageItemDO;
import com.luna.application.utils.DateUtil;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    public static final String LIST_VIEW = "listView";
    private Context            mContext;
    private LayoutInflater     mLayoutInflater;
    private int                size;
    private List<ImageItemDO>  list;

    public ListViewAdapter(Context context, List<ImageItemDO> list) {
        this.mContext = context;
        this.size = list.size();
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView  tvTitle, tvTime, tvContent;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        ImageItemDO imageItemDO = list.get(i);

        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.iv);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            holder.tvTime = view.findViewById(R.id.tv_time);
            holder.tvContent = view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.tvTitle.setText(imageItemDO.getTitle());
        holder.tvTime.setText(DateUtil.dateToString(imageItemDO.getDate(), DateUtil.DatePattern.ONLY_DAY));
        holder.tvContent.setText(imageItemDO.getContent());
        holder.imageView.setImageResource(imageItemDO.getDrawable());
        return view;
    }
}

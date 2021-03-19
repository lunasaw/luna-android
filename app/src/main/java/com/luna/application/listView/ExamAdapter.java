package com.luna.application.listView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.luna.application.R;
import com.luna.application.entity.ImageItem;
import com.luna.application.utils.DateUtil;
import com.luna.application.utils.ToastUtil;

import java.util.List;

public class ExamAdapter extends BaseAdapter {

    public static final String LIST_VIEW = "listView";
    private Context            context;
    private LayoutInflater     layoutInflater;
    private List<String>       list;

    public ExamAdapter(Context context, List<String> listStr) {
        this.context = context;
        this.list = listStr;
        layoutInflater = LayoutInflater.from(context);
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

    static class ViewHolder {
        public TextView textView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        String s = list.get(i);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_exam_layout, null);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.text_exam);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.textView.setText("数据" + s);
        return view;
    }
}

package com.luna.application.listView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.common.collect.Lists;
import com.luna.application.R;
import com.luna.application.entity.ImageItem;
import com.luna.application.utils.DateUtil;
import com.luna.application.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    public static final int INT = 20;
    private ListView        listView;

    private List<ImageItem> imageItems;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        listView = findViewById(R.id.lv_1);
        imageItems = imageItemList();
        listView.setAdapter(new ListViewAdapter(ListViewActivity.this, imageItems));

        // 点击事件
        listView.setOnItemClickListener(new Onclick());

        // 长按事件
        listView.setOnItemLongClickListener(new LongOnclick());
    }

    class Onclick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
            builder.setTitle("请回答：").setMessage("你喜欢" + imageItems.get(i).getTitle() + "吗？")
                .setIcon(imageItems.get(i).getDrawable())
                .setPositiveButton("喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showMsg(ListViewActivity.this, "真的吗？");
                    }
                }).setNeutralButton("还行", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showMsg(ListViewActivity.this, "你确定吗？");
                    }
                }).setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showMsg(ListViewActivity.this, "为什么呢？");
                    }
                }).show();
            Toast.makeText(ListViewActivity.this, "点击 pos: " + i, Toast.LENGTH_SHORT).show();
        }
    }

    class LongOnclick implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(ListViewActivity.this, "长按 pos: " + i, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<ImageItem> imageItemList() {
        ArrayList<ImageItem> list = Lists.newArrayList();
        list.add(new ImageItem("qq", "QQ", R.drawable.qq,
            DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "QQ"));
        list.add(new ImageItem("jd", "京东购物", R.drawable.jd,
            DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "京东"));
        list.add(new ImageItem("qq_dizhu", "欢乐斗地主", R.drawable.qq_dizhu,
            DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "欢乐斗地主"));
        list.add(new ImageItem("sina", "新浪微博", R.drawable.sina,
            DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "sina"));
        list.add(new ImageItem("tmall", "天猫购物", R.drawable.tmall,
            DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "天猫购物"));
        list.add(new ImageItem("uc", "UC浏览器", R.drawable.uc,
            DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "UC浏览器"));
        list.add(new ImageItem("weixin", "微信 连接你我", R.drawable.weixin,
            DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "微信"));
        return list;
    }
}

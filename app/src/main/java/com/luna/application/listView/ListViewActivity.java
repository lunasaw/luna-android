package com.luna.application.listView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;
import com.luna.application.entity.ImageItem;
import com.luna.application.utils.DateUtil;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    public static final int INT = 10;
    private ListView        listView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        listView = findViewById(R.id.lv_1);
        List<ImageItem> imageItemList = new ArrayList<>();
        for (int i = 0; i < INT; i++) {
            ImageItem imageItem =
                new ImageItem(i, "面朝大海，春暖花开", "https://www.isczy.tk/luna-image-bed/img/20210319100148.jpg",
                    DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY), "安卓应用开发");
            imageItemList.add(imageItem);
        }
        listView.setAdapter(new ListViewAdapter(ListViewActivity.this, imageItemList));

        // 点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "点击 pos: " + i, Toast.LENGTH_SHORT).show();
            }
        });

        // 长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "长按 pos: " + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}

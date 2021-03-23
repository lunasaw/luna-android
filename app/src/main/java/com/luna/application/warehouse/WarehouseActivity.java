package com.luna.application.warehouse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;
import com.luna.application.dao.GoodsDAO;
import com.luna.application.entity.GoodsDO;
import com.luna.application.utils.DateUtil;
import com.luna.application.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class WarehouseActivity extends AppCompatActivity {

    private ListView      listView;

    private List<GoodsDO> GoodsDOS;

    private GoodsDAO      goodsDAO;

    private Button        operation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warehouse_layout);

        listView = findViewById(R.id.goods);
        GoodsDOS = itemList();
        operation = findViewById(R.id.operation_goods);
        operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WarehouseActivity.this, OperationActivity.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(new WarehouseAdapter(WarehouseActivity.this, GoodsDOS));

        // 点击事件
        listView.setOnItemClickListener(new WarehouseActivity.Onclick());

        // 长按事件
        listView.setOnItemLongClickListener(new LongOnclick());
    }

    class Onclick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder builder = new AlertDialog.Builder(WarehouseActivity.this);
            builder.setTitle("请回答：").setMessage("你喜欢" + GoodsDOS.get(i).getTitle() + "吗？")
                .setPositiveButton("喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showMsg(WarehouseActivity.this, "真的吗？");
                    }
                }).setNeutralButton("还行", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showMsg(WarehouseActivity.this, "你确定吗？");
                    }
                }).setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showMsg(WarehouseActivity.this, "为什么呢？");
                    }
                }).show();
            Toast.makeText(WarehouseActivity.this, "点击 pos: " + i, Toast.LENGTH_SHORT).show();
        }
    }

    class LongOnclick implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(WarehouseActivity.this, "长按 pos: " + i, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private static List<GoodsDO> list =new ArrayList<>();
    static {
        GoodsDO goodsDO = new GoodsDO("香蕉", "美味好吃香甜可口美味好吃香甜可口", DateUtil.stringToDate("2021-3-23", DateUtil.DatePattern.ONLY_DAY), 10);
        GoodsDO goodsDO1 = new GoodsDO("橘子", "美味好吃", DateUtil.stringToDate("2021-3-23", DateUtil.DatePattern.ONLY_DAY), 10);
        GoodsDO goodsDO2 = new GoodsDO("苹果", "美味好吃", DateUtil.stringToDate("2021-3-23", DateUtil.DatePattern.ONLY_DAY), 10);
        GoodsDO goodsDO3 = new GoodsDO("手机", "1999交个朋友1999交个朋友1999交个朋友1999交个朋友", DateUtil.stringToDate("2021-3-23", DateUtil.DatePattern.ONLY_DAY), 1999);
        GoodsDO goodsDO4 = new GoodsDO("方便面", "美味时刻享受", DateUtil.stringToDate("2021-3-23", DateUtil.DatePattern.ONLY_DAY), 11);
        list.add(goodsDO);
        list.add(goodsDO1);
        list.add(goodsDO2);
        list.add(goodsDO3);
        list.add(goodsDO4);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<GoodsDO> itemList() {
        goodsDAO = new GoodsDAO(this);
        for (GoodsDO goodsDO : list) {
            goodsDAO.insert(goodsDO);
        }
        list.removeAll(list);
        return goodsDAO.query();
    }
}

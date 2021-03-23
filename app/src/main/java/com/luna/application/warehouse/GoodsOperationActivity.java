package com.luna.application.warehouse;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;
import com.luna.application.dao.GoodsDAO;
import com.luna.application.entity.GoodsDO;
import com.luna.application.utils.DateUtil;

public class GoodsOperationActivity extends AppCompatActivity {

    private EditText title, content, date, price, goodsEdit;

    private Button   insert, update, delete, search;

    private GoodsDAO goodsDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_operation_layout);
        initview();
        goodsDAO = new GoodsDAO(this);
    }

    class Onclick implements View.OnClickListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.goods_insert:
                    // 增
                    insert();
                    break;
                case R.id.goods_delete:
                    remove();
                    // 删
                    break;
                case R.id.goods_update:
                    update();
                    // 改
                    break;
                case R.id.goods_search:
                    // 查
                    query();
                    break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void update() {
        String title = this.title.getText().toString();
        String content = this.content.getText().toString();
        String date = this.date.getText().toString();
        String price = this.price.getText().toString();
        GoodsDO goodsDO = new GoodsDO(title, content, DateUtil.stringToDate(date, DateUtil.DatePattern.ONLY_DAY),
                Integer.parseInt(price));
        Log.i("TAG", "update: " + goodsDO);
        goodsDAO.update(goodsDO);
    }

    private void remove() {
        String title = this.goodsEdit.getText().toString();
        goodsDAO.delete(title);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void query() {
        String title = this.goodsEdit.getText().toString();
        GoodsDO query = goodsDAO.query(title);
        this.title.setText(query.getTitle());
        this.content.setText(query.getContent());
        this.date.setText(DateUtil.dateToString(query.getDate(), DateUtil.DatePattern.ONLY_DAY));
        this.price.setText(String.valueOf(query.getPrice()));
        Log.i("TAG", "query: " + query);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insert() {
        String title = this.title.getText().toString();
        String content = this.content.getText().toString();
        String date = this.date.getText().toString();
        String price = this.price.getText().toString();
        GoodsDO goodsDO = new GoodsDO(title, content, DateUtil.stringToDate(date, DateUtil.DatePattern.ONLY_DAY),
            Integer.parseInt(price));
        Log.i("TAG", "insert: " + goodsDO);
        goodsDAO.insert(goodsDO);
    }

    private void initview() {
        title = findViewById(R.id.goods_title);
        content = findViewById(R.id.goods_content);
        date = findViewById(R.id.goods_time);
        price = findViewById(R.id.goods_price);
        goodsEdit = findViewById(R.id.goods_edit);

        insert = findViewById(R.id.goods_insert);
        update = findViewById(R.id.goods_update);
        delete = findViewById(R.id.goods_delete);
        search = findViewById(R.id.goods_search);
        Onclick onclick = new Onclick();
        insert.setOnClickListener(onclick);
        delete.setOnClickListener(onclick);
        search.setOnClickListener(onclick);
        update.setOnClickListener(onclick);
    }
}

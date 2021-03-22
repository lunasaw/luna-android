package com.luna.application.searchphone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luna.application.R;
import com.luna.application.api.ApiKey;
import com.luna.application.utils.HttpUtil;
import com.luna.application.utils.HttpUtils;
import com.luna.application.utils.ToastUtil;

public class SearchPhoneActivity extends AppCompatActivity {

    private EditText etPhone;
    private Button   btnSubmit;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_phone_layout);

        etPhone = findViewById(R.id.et_phone);
        btnSubmit = findViewById(R.id.btn_submit);
        textView = findViewById(R.id.tv_pro);
        textView1 = findViewById(R.id.tv_city);
        textView2 = findViewById(R.id.tv_areacode);
        textView3 = findViewById(R.id.tv_zip);
        textView4 = findViewById(R.id.tv_company);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                get(getUrl(phone));
            }
        });

    }

    private void get(String url) {
        HttpUtil.get(this, url, new HttpUtil.OnHttpResponseListener() {
            @Override
            public void onGetString(String json) {
                getParse(json);
                Log.i("TAG", "onGetString: " + json);
            }
        });
    }

    private void getParse(String json) {
        PhoneInfo result = JSON.parseObject(JSONObject.parseObject(json).getString("result"), PhoneInfo.class);
        if (result != null) {
            textView.setText("省份：" + result.getProvince());
            textView1.setText("城市：" + result.getCity());
            textView2.setText("地区码：" + result.getAreacode());
            textView3.setText("邮编：" + result.getZip());
            textView4.setText("所属公司：" + result.getCompany());
        } else {
            ToastUtil.showMsg(SearchPhoneActivity.this, "电话不能为空");
        }
    }

    private String getUrl(String phone) {
        return String.format(ApiKey.SEARCH_PHONE, phone, ApiKey.KEY_TWO);
    }
}
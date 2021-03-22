package com.luna.application.searchphone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;
import com.luna.application.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchPhoneActivity extends AppCompatActivity {

    private EditText etPhone;
    private Button btnSubmit;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private String key = "45377b7f6660c4f780b28d843d5e3da6";
    private String url = "http://apis.juhe.cn/mobile/get?phone=%s&key=%s";

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
                String endUrl = getUrl(phone);
                getEnd(endUrl);
            }
        });




    }

    private void getEnd(String endUrl) {

        HttpUtil.getUrl2Net(this, endUrl, new HttpUtil.OnHttpRepsonLinstener() {
            @Override
            public void onGetString(String json) {
                getParse(json);
            }
        });
    }

    private void getParse(String json)  {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject result = jsonObject.getJSONObject("result");

            String province = result.getString("province");
            String city = result.getString("city");
            String areacode = result.getString("areacode");
            String zip = result.getString("zip");
            String company = result.getString("company");

            textView.setText("省份："+province);
            textView1.setText("城市："+city);
            textView2.setText("地区码："+areacode);
            textView3.setText("邮编："+zip);
            textView4.setText("所属公司："+company);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String getUrl(String phone) {
        String mUrl = String.format(url, phone, key);
        Log.e("TAG",mUrl);

        return mUrl;
    }
}
package com.luna.application.bitrh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.luna.application.R;
import com.luna.application.api.ApiKey;
import com.luna.application.utils.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

public class BirthHoroscope extends AppCompatActivity {

    private EditText etYear;
    private EditText etMonth;
    private EditText etDay;
    private EditText etHour;
    private Button   btnCommit;
    private TextView tvFive;
    private TextView tvEight;
    private TextView tvStar;
    private TextView tvAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);

        etYear = findViewById(R.id.et_year);
        etMonth = findViewById(R.id.et_month);
        etDay = findViewById(R.id.et_day);
        etHour = findViewById(R.id.et_hour);
        btnCommit = findViewById(R.id.btn_commit);
        tvFive = findViewById(R.id.tv_five);
        tvEight = findViewById(R.id.tv_eight);
        tvStar = findViewById(R.id.tv_star);
        tvAnimal = findViewById(R.id.tv_animal);

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = etYear.getText().toString();
                String month = etMonth.getText().toString();
                String day = etDay.getText().toString();
                String hour = etHour.getText().toString();
                String endUrl = getUrl(year, month, day, hour);
                getEnd(endUrl);
            }
        });
    }

    private void getEnd(String endUrl) {
        HttpUtil.get(this, endUrl, new HttpUtil.OnHttpResponseListener() {
            @Override
            public void onGetString(String json) {
                Log.e("TAG", json);
                parseJson(json);
            }
        });
    }

    private String getUrl(String year, String month, String day, String hour) {
        return String.format(ApiKey.BIRTH_COMPUTER, year, month, day, hour, ApiKey.KEY_ONE);
    }

    public void parseJson(String json) {
        Log.i("TAG", "parseJson: " + json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject resultObj = jsonObject.getJSONObject("result");
            String animal = resultObj.getString("Animal");
            String star = resultObj.getString("astro");

            // 获取八字
            JSONObject eightAllObj = resultObj.getJSONObject("eightAll");
            JSONArray eightArray = eightAllObj.getJSONArray("eight");
            String eight = "";
            for (int i = 0; i < eightArray.length(); i++) {
                eight = eight + " " + eightArray.getString(i);
            }

            // 获取五行
            JSONObject fiveAllObj = resultObj.getJSONObject("fiveAll");
            JSONArray fiveArray = fiveAllObj.getJSONArray("five");

            String five = "";
            for (int i = 0; i < fiveArray.length(); i++) {
                five = five + " " + fiveArray.getString(i);
            }

            tvAnimal.setText(animal);
            tvStar.setText(star);
            tvFive.setText(five);
            tvEight.setText(eight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

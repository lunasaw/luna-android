package com.luna.application.picture;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;
import com.luna.application.api.ApiKey;
import com.luna.application.utils.HttpUtil;
public class OrCreateActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText  editText;
    private Button    button;
    private String    url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.or_create_layout);
        imageView = findViewById(R.id.or_img);
        editText = findViewById(R.id.or_edit);
        button = findViewById(R.id.or_btn);
        button.setOnClickListener(new Onclick());
        Log.i("TAG", "onCreate: " + url);


    }

    class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            url = getUrl("2", "00b7ee", "90", "5", editText.getText().toString());
            get(url);
        }
    }

    private void get(String url) {
        HttpUtil.get(this, url, new HttpUtil.OnHttpResponseListener() {
            @Override
            public void onGetString(String json) {
                parseJson(json);
                Log.i("TAG", "onGetString: " + json);
            }
        });
    }

    private void parseJson(String json) {

    }

    private String getUrl(String type, String fgcolor, String width, String margin, String text) {
        return String.format(ApiKey.QR_CODE, type, fgcolor, width, margin, text, ApiKey.KEY_ONE);
    }
}

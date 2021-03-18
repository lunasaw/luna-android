package com.luna.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity {

    private Button button_1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginfo_layout);
        Log.e("log_test", "onCreate: LogActivity");
        button_1 = findViewById(R.id.log_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("log_test", "onClick: log_1");
                Toast.makeText(LogActivity.this, "log_1被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class Onclick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i("log_test", "onClick: log_1");
            Toast.makeText(LogActivity.this, "log_1被点击了", Toast.LENGTH_SHORT).show();
        }
    }
}

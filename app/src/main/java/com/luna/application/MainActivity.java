package com.luna.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button diDiButton;

    private Button calculateButton;

    private Button loginButton;

    private Button heroButton;

    private Button logButton;

    private Button dialogButton;

    private Button fileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        diDiButton = findViewById(R.id.didi);
        calculateButton = findViewById(R.id.calculate);
        loginButton = findViewById(R.id.login);
        heroButton = findViewById(R.id.hero);
        logButton = findViewById(R.id.log);
        dialogButton = findViewById(R.id.dialog);
        fileButton = findViewById(R.id.file);
        setListeners();
    }

    /**
     * +
     * 监听器方法
     */
    private void setListeners() {
        OnClick onClick = new OnClick();
        diDiButton.setOnClickListener(onClick);
        calculateButton.setOnClickListener(onClick);
        loginButton.setOnClickListener(onClick);
        heroButton.setOnClickListener(onClick);
        logButton.setOnClickListener(onClick);
        dialogButton.setOnClickListener(onClick);
        fileButton.setOnClickListener(onClick);
    }

    /**
     * 实现监听器接口
     */
    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                // 跳转到DiDiLayout演示页面
                case R.id.didi:
                    intent = new Intent(MainActivity.this, DiDiActivity.class);
                    break;
                // 跳转到calculateLayout演示页面
                case R.id.calculate:
                    intent = new Intent(MainActivity.this, CalculateActivity.class);
                    break;
                // 跳转到LoginLayout演示页面
                case R.id.login:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
                // 跳转到HeroLayout演示页面
                case R.id.hero:
                    intent = new Intent(MainActivity.this, HeroActivity.class);
                    break;
                // 跳转到LogLayout演示页面
                case R.id.log:
                    intent = new Intent(MainActivity.this, LogActivity.class);
                    break;
                case R.id.dialog:
                    intent = new Intent(MainActivity.this, DialogActivity.class);
                    break;
                case R.id.file:
                    intent = new Intent(MainActivity.this, FileActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}

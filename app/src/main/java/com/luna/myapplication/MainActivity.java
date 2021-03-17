package com.luna.myapplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diDiButton = findViewById(R.id.didi);
        calculateButton = findViewById(R.id.calculate);
        loginButton = findViewById(R.id.login);
        heroButton = findViewById(R.id.hero);
        setListeners();
    }


    /**+
     * 监听器方法
     */
    private void setListeners() {
        OnClick onClick = new OnClick();
        diDiButton.setOnClickListener(onClick);
        calculateButton.setOnClickListener(onClick);
        loginButton.setOnClickListener(onClick);
        heroButton.setOnClickListener(onClick);
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
            }
            startActivity(intent);
        }
    }
}


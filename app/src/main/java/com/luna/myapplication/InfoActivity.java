package com.luna.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_layout);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String sex = intent.getStringExtra("gender");

        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvPassword = findViewById(R.id.tv_password);
        TextView tvSex = findViewById(R.id.tv_sex);
        //给textview控件动态设置值的方法
        tvUsername.setText("用户名："+name);
        tvPassword.setText("密码:"+password);
        tvSex.setText("性别："+sex);
    }
}

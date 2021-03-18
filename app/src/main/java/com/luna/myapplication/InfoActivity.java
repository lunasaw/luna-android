package com.luna.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.luna.myapplication.entity.UserDO;
import com.luna.myapplication.utils.ShareUtils;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_layout);
        Intent intent = getIntent();
        UserDO userDO = (UserDO)intent.getSerializableExtra("userInfo");
        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvPassword = findViewById(R.id.tv_password);
        TextView tvSex = findViewById(R.id.tv_sex);
        TextView remember = findViewById(R.id.is_remember);
        // 给textview控件动态设置值的方法
        tvUsername.setText("用户名：" + userDO.getUserName());
        tvPassword.setText("密码:" + userDO.getPassword());
        tvSex.setText("性别：" + userDO.getGender());
        remember.setText("是否记住密码：" + ShareUtils.getInstance(InfoActivity.this).getBoolean("remember"));
    }
}

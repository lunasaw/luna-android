package com.luna.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.luna.myapplication.entity.UserDO;
import com.luna.myapplication.utils.ShareUtils;

public class LoginActivity extends Activity {

    private Button     login;

    private EditText   username;

    private EditText   password;

    private RadioGroup gender;

    private CheckBox   remember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        login = findViewById(R.id.btn_login);
        gender = findViewById(R.id.gender);
        remember = findViewById(R.id.is_remember);
        ShareUtils instance = ShareUtils.getInstance(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        UserDO userDO =
            new UserDO(ShareUtils.getKey("username"), ShareUtils.getKey("password"), ShareUtils.getKey("gender"));
        username.setText(userDO.getUserName());
        password.setText(userDO.getPassword());
        setSex(userDO.getGender());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String word = password.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(word)) {
                    Toast.makeText(LoginActivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                    intent.putExtra("userInfo", new UserDO(name, word, getSex()));
                    if (remember.isChecked()) {
                        ShareUtils.putString("username", name);
                        ShareUtils.putString("password", word);
                        ShareUtils.putString("gender", getSex());
                        ShareUtils.putBoolean("remember", true);
                    } else {
                        ShareUtils.putString("username", "");
                        ShareUtils.putString("password", "");
                        ShareUtils.putBoolean("remember", false);
                    }
                    startActivity(intent);
                }
            }
        });
    }

    private String getSex() {
        String sex = "";
        for (int i = 0; i < gender.getChildCount(); i++) {
            RadioButton rb = (RadioButton)gender.getChildAt(i);
            if (rb.isChecked()) {
                if (rb.getId() == R.id.gender_man) {
                    sex = "男";
                } else {
                    sex = "女";
                }
                break;
            }
        }
        return sex;
    }

    private void setSex(String sex) {
        if ("男".equals(sex)) {
            RadioButton radioButton = findViewById(R.id.gender_man);
            radioButton.setChecked(true);
        } else if ("女".equals(sex)) {
            RadioButton radioButton = findViewById(R.id.gender_wowan);
            radioButton.setChecked(true);
        }
    }
}

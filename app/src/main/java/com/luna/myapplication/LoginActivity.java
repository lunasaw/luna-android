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
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends Activity {

    private Button   login;

    private EditText username;

    private EditText password;

    private RadioGroup gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login);
        gender = findViewById(R.id.gender);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.username);
                String name = username.getText().toString();
                password = findViewById(R.id.password);
                String word = password.getText().toString();
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(word)) {
                    Toast.makeText(LoginActivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                    intent.putExtra("username", name);
                    intent.putExtra("password", word);
                    intent.putExtra("gender",getSex());
                    startActivity(intent);
                }
            }
        });
    }

    private String getSex(){
        String sex = "";
        for (int i = 0; i < gender.getChildCount(); i++) {
            RadioButton rb = (RadioButton) gender.getChildAt(i);
            if (rb.isChecked()){
                if (rb.getId() == R.id.gender_man){
                    sex="男";
                }else {
                    sex="女";
                }
                break;
            }
        }
        return  sex;
    }
}



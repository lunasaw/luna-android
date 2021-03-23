package com.luna.application.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.luna.application.R;
import com.luna.application.dao.UserDAO;
import com.luna.application.entity.UserDO;
import com.luna.application.utils.HashUtils;
import com.luna.application.utils.ShareUtils;
import com.luna.application.utils.ToastUtil;

public class LoginActivity extends Activity {

    private Button     login;

    private EditText   username;

    private EditText   password;

    private RadioGroup gender;

    private CheckBox   remember;

    private UserDAO    userDAO;

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
        userDAO = new UserDAO(this);
        final UserDO userDO =
            new UserDO(instance.getKey("username"), instance.getKey("password"), instance.getKey("gender"));
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
                    UserDO user = new UserDO(name, HashUtils.SHA512(word), getSex());
                    UserDO query = userDAO.query(user);
                    if (query != null) {
                        intent.putExtra("userInfo", query);
                        if (remember.isChecked()) {
                            ShareUtils.putString("userInfo", JSON.toJSONString(query));
                            Log.i("login", "onCreate: " + JSON.toJSONString(query));
                        } else {
                            ShareUtils.putString("username", "");
                            ShareUtils.putString("password", "");
                            instance.putBoolean("remember", false);
                        }
                        startActivity(intent);
                        ToastUtil.showMsg(LoginActivity.this, "登陆成功");
                    } else {
                        ToastUtil.showMsg(LoginActivity.this, "用户不存在");
                    }
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

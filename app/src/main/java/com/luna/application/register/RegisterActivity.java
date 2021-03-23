package com.luna.application.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.common.hash.Hashing;
import com.luna.application.MainActivity;
import com.luna.application.R;
import com.luna.application.database.dao.UserDAO;
import com.luna.application.entity.UserDO;
import com.luna.application.login.LoginActivity;
import com.luna.application.utils.HashUtils;
import com.luna.application.utils.ToastUtil;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private Button     register, update, remove;

    private EditText   username;

    private EditText   password;

    private EditText   ensurePassword;

    private RadioGroup gender;

    private UserDAO    userDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        ensurePassword = findViewById(R.id.ensure_password);
        register = findViewById(R.id.btn_register);
        update = findViewById(R.id.update_info);
        remove = findViewById(R.id.remove_info);
        userDAO = new UserDAO(this);
        setListeners();
    }

    private void setListeners() {
        Onclick onclick = new Onclick();
        register.setOnClickListener(onclick);
        update.setOnClickListener(onclick);
        remove.setOnClickListener(onclick);
    }

    class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_register:
                    // 注册成功跳转登陆页面
                    register();
                    break;
                case R.id.update_info:
                    update();
                    break;
                case R.id.remove_info:
                    remove();
                    break;
            }
        }
    }

    private void update() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String gender = getSex();
        String ensure = this.ensurePassword.getText().toString();
        if (userDAO.query(username).size() == 0) {
            ToastUtil.showMsg(this, "用户不存在");
            return;
        }
        if (password.equals(ensure)) {
            boolean update = userDAO.update(new UserDO(username, HashUtils.SHA512(password), gender));
            ToastUtil.showMsg(this, update ? "更新成功" : "更新失败");
        }
    }

    private void remove() {
        String username = this.username.getText().toString();
        String ensure = this.ensurePassword.getText().toString();
        if (userDAO.query(username).size() == 0) {
            ToastUtil.showMsg(this, "用户不存在");
            return;
        }
        if (password.equals(ensure)) {
            boolean delete = userDAO.delete(username);
            ToastUtil.showMsg(this, delete ? "删除成功" : "删除失败");
        }
    }

    private void register() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String gender = getSex();
        String ensure = this.ensurePassword.getText().toString();
        // 1。密码匹配 2。查询用户不存在 后插入
        if (userDAO.query(username).size() != 0) {
            ToastUtil.showMsg(this, "用户名已存在");
            return;
        }
        if (password.equals(ensure)) {
            boolean insert = userDAO.insert(new UserDO(username, HashUtils.SHA512(password), gender));
            if (insert) {
                ToastUtil.showMsg(this, "注册成功");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                ToastUtil.showMsg(this, "注册失败");
            }
        }
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

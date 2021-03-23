package com.luna.application.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;

public class RegisterActivity extends AppCompatActivity {

    private Button     register, update, remove;

    private EditText   username;

    private EditText   password;

    private EditText   ensurePassword;

    private RadioGroup gender;

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
        setListeners();
    }

    private void setListeners() {
        Onclick onclick = new Onclick();
        register.setOnClickListener(onclick);
        update.setOnClickListener(onclick);
        remove.setOnClickListener(onclick);
    }

    class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_register:

                case R.id.update_info:

                case R.id.remove_info:
            }
        }
    }
}

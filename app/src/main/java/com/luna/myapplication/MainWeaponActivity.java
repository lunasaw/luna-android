package com.luna.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Map;

public class MainWeaponActivity extends Activity implements View.OnClickListener{

    private Button     goldButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainweapon_layout);
        goldButton = findViewById(R.id.gold);
        goldButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("info", new ItemInfo(100, 20, 20));
        setResult(1, intent);
        finish();
    }
}

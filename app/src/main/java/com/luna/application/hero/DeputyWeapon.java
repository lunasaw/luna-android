package com.luna.application.hero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.luna.application.R;
import com.luna.application.hero.ItemInfo;

public class DeputyWeapon extends Activity {
    private Button iron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deputyweapon_layout);

        iron = findViewById(R.id.iron);

        iron.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("info", new ItemInfo(50, 10, 20));
            setResult(0, intent);
            finish();
        });
    }
}

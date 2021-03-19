package com.luna.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeputyWeapon extends Activity {
    private Button iron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deputyweapon_layout);

        iron = findViewById(R.id.iron);
        iron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("info", new ItemInfo(50, 10, 20));
                setResult(0, intent);
                finish();
            }
        });

    }
}
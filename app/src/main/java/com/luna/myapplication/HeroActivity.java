package com.luna.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HeroActivity extends Activity {

    private Button     mainWeapon;

    private Button     deputyWeapon;

    public TextView    life;

    public TextView    damage;

    public TextView    agile;

    public ProgressBar lifeBar;

    public ProgressBar agileBar;

    public ProgressBar damageBar;

    @Override
    protected void onResume() {
        System.out.println("切换了");
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_layout);
        life = findViewById(R.id.life_num);
        damage = findViewById(R.id.damage_num);
        agile = findViewById(R.id.agile_num);
        init();

        mainWeapon = findViewById(R.id.main_weapon);
        mainWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeroActivity.this, MainWeaponActivity.class);
                startActivityForResult(intent,1);
            }
        });

        deputyWeapon = findViewById(R.id.deputy_weapon);
        deputyWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeroActivity.this, DeputyWeapon.class);
                startActivityForResult(intent,1);
            }
        });

    }

    private void init() {
        lifeBar = (ProgressBar)findViewById(R.id.life_value_bar);
        damageBar = (ProgressBar)findViewById(R.id.damage_value_bar);
        agileBar = (ProgressBar)findViewById(R.id.agile_value_bar);
        lifeBar.setMax(1000); // 设置最大值1000
        damageBar.setMax(1000);
        agileBar.setMax(1000);
    }

    // 回调方法，接受调转页面回调传递过来的值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == resultCode) {
            ItemInfo info = (ItemInfo)data.getSerializableExtra("info");
            // 更新ProgressBar的值
            updateProgress(info);
        }
    }

    // 更新ProgressBar的值
    private void updateProgress(ItemInfo info) {
        int progress1 = lifeBar.getProgress();
        int progress2 = damageBar.getProgress();
        int progress3 = agileBar.getProgress();
        lifeBar.setProgress(progress1 + info.getLife());
        damageBar.setProgress(progress2 + info.getDamage());
        agileBar.setProgress(progress3 + info.getAgile());
        life.setText(lifeBar.getProgress() + "");
        damage.setText(damageBar.getProgress() + "");
        agile.setText(agileBar.getProgress() + "");
    }

}

class ItemInfo implements Serializable {
    public int life;

    public int damage;

    public int agile;

    public ItemInfo(int life, int damage, int agile) {
        this.life = life;
        this.damage = damage;
        this.agile = agile;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAgile() {
        return agile;
    }

    public void setAgile(int agile) {
        this.agile = agile;
    }
}

class UserInfo {

    public static Map<String, Integer> infoList = new HashMap<>();
}

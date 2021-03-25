package com.luna.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.luna.application.calculate.CalculateActivity;
import com.luna.application.didi.DiDiActivity;
import com.luna.application.dialog.DialogActivity;
import com.luna.application.file.FileActivity;
import com.luna.application.fragment.FragmentActivity;
import com.luna.application.hero.HeroActivity;
import com.luna.application.sensor.SensorActivity;
import com.luna.application.utils.NotificationsUtils;
import com.luna.application.views.exam.ExamActivity;
import com.luna.application.views.ListViewActivity;
import com.luna.application.login.LoginActivity;
import com.luna.application.picture.OrCreateActivity;
import com.luna.application.register.RegisterActivity;
import com.luna.application.smartphone.SearchPhoneActivity;
import com.luna.application.birth.BirthHoroscope;
import com.luna.application.warehouse.WarehouseActivity;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    private Button diDiButton;

    private Button calculateButton;

    private Button loginButton;

    private Button heroButton;

    private Button logButton;

    private Button registerButton;

    private Button dialogButton;

    private Button fileButton;

    private Button listViewButton;

    private Button listViewExamButton;

    private Button birthButton;

    private Button orCreateButton;

    private Button searchPhoneButton;

    private Button warehouseButton;

    private Button fragmentButton;

    private Button sensorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        diDiButton = findViewById(R.id.didi);
        calculateButton = findViewById(R.id.calculate);
        loginButton = findViewById(R.id.login);
        heroButton = findViewById(R.id.hero);
        logButton = findViewById(R.id.log);
        dialogButton = findViewById(R.id.dialog);
        fileButton = findViewById(R.id.file);
        listViewButton = findViewById(R.id.listView);
        listViewExamButton = findViewById(R.id.listViewExam);
        birthButton = findViewById(R.id.birth);
        orCreateButton = findViewById(R.id.orCreate);
        searchPhoneButton = findViewById(R.id.searchPhone);
        registerButton = findViewById(R.id.register);
        warehouseButton = findViewById(R.id.warehouse);
        fragmentButton = findViewById(R.id.fragment);
        sensorButton = findViewById(R.id.sensor);
        setListeners();

        if (NotificationsUtils.isNotificationEnabled(this)) {
            Log.e(TAG, "onCreate: 通知权限 已开启");
            setBasicSetup(1);
            setBasicSetup(4);
        } else {
            Log.e(TAG, "onCreate: 通知权限 未开启");
            // 提示用户去设置，跳转到应用信息界面
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }
    }

    /**
     * +
     * 监听器方法
     */
    private void setListeners() {
        OnClick onClick = new OnClick();
        diDiButton.setOnClickListener(onClick);
        calculateButton.setOnClickListener(onClick);
        loginButton.setOnClickListener(onClick);
        heroButton.setOnClickListener(onClick);
        logButton.setOnClickListener(onClick);
        dialogButton.setOnClickListener(onClick);
        fileButton.setOnClickListener(onClick);
        listViewButton.setOnClickListener(onClick);
        listViewExamButton.setOnClickListener(onClick);
        birthButton.setOnClickListener(onClick);
        orCreateButton.setOnClickListener(onClick);
        searchPhoneButton.setOnClickListener(onClick);
        registerButton.setOnClickListener(onClick);
        warehouseButton.setOnClickListener(onClick);
        fragmentButton.setOnClickListener(onClick);
        sensorButton.setOnClickListener(onClick);
    }

    /**
     * 实现监听器接口
     */
    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                // 跳转到DiDiLayout演示页面
                case R.id.didi:
                    intent = new Intent(MainActivity.this, DiDiActivity.class);
                    break;
                // 跳转到calculateLayout演示页面
                case R.id.calculate:
                    intent = new Intent(MainActivity.this, CalculateActivity.class);
                    break;
                // 跳转到LoginLayout演示页面
                case R.id.login:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
                // 跳转到HeroLayout演示页面
                case R.id.hero:
                    intent = new Intent(MainActivity.this, HeroActivity.class);
                    break;
                // 跳转到LogLayout演示页面
                case R.id.log:
                    intent = new Intent(MainActivity.this, LogActivity.class);
                    break;
                // 跳转到DialogLayout演示页面
                case R.id.dialog:
                    intent = new Intent(MainActivity.this, DialogActivity.class);
                    break;
                // 跳转到fileLayout演示页面
                case R.id.file:
                    intent = new Intent(MainActivity.this, FileActivity.class);
                    break;
                // 跳转到listViewLayout演示页面
                case R.id.listView:
                    intent = new Intent(MainActivity.this, ListViewActivity.class);
                    break;
                // 跳转到listViewExamLayout演示页面
                case R.id.listViewExam:
                    intent = new Intent(MainActivity.this, ExamActivity.class);
                    break;
                // 跳转到birthExamLayout演示页面
                case R.id.birth:
                    intent = new Intent(MainActivity.this, BirthHoroscope.class);
                    break;
                // 跳转到orCreate演示页面
                case R.id.orCreate:
                    intent = new Intent(MainActivity.this, OrCreateActivity.class);
                    break;
                // 跳转到searchPhone演示页面
                case R.id.searchPhone:
                    intent = new Intent(MainActivity.this, SearchPhoneActivity.class);
                    break;
                case R.id.register:
                    intent = new Intent(MainActivity.this, RegisterActivity.class);
                    break;
                case R.id.warehouse:
                    intent = new Intent(MainActivity.this, WarehouseActivity.class);
                    break;
                case R.id.fragment:
                    intent = new Intent(MainActivity.this, FragmentActivity.class);
                    break;
                case R.id.sensor:
                    intent = new Intent(MainActivity.this, SensorActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    private static final String TAG = "MainActivity";

    /**
     * 1-2-3-4
     * 增、删、改、查
     */
    public void setBasicSetup(int type) {
        if (type == 1) {
            // 设置别名（新的调用会覆盖之前的设置）
            JPushInterface.setAlias(this, 0, "luna");
            // 设置标签（同上）
            JPushInterface.setTags(this, 0, setUserTags());
        } else if (type == 2) {
            // 删除别名
            JPushInterface.deleteAlias(this, 0);
            // 删除指定标签
            JPushInterface.deleteTags(this, 0, setUserTags());
            // 删除所有标签
            JPushInterface.cleanTags(this, 0);
        } else if (type == 3) {
            // 增加tag用户量(一般都是登录成功设置userid为目标，在别处新增加比较少见)
            JPushInterface.addTags(this, 0, setUserTags());
        } else if (type == 4) {
            // 查询所有标签
            JPushInterface.getAllTags(this, 0);
            // 查询别名
            JPushInterface.getAlias(this, 0);
            // 查询指定tag与当前用户绑定的状态（MyJPushMessageReceiver获取）
            JPushInterface.checkTagBindState(this, 0, "luna");
            // 获取注册id
            JPushInterface.getRegistrationID(this);
        }
    }

    /**
     * 标签用户
     */
    private static Set<String> setUserTags() {
        // 添加3个标签用户（获取登录userid较为常见）
        Set<String> tags = new HashSet<>();
        tags.add("luna");
        tags.add("luna_nov");
        tags.add("luna_ly");
        return tags;
    }
}

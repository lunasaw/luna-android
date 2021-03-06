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
import com.luna.application.news.NewsClientActivity;
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

    private Button clientButton;

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
        clientButton = findViewById(R.id.client);
        setListeners();

        if (NotificationsUtils.isNotificationEnabled(this)) {
            Log.e(TAG, "onCreate: ???????????? ?????????");
            setBasicSetup(1);
            setBasicSetup(4);
        } else {
            Log.e(TAG, "onCreate: ???????????? ?????????");
            // ???????????????????????????????????????????????????
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }
    }

    /**
     * +
     * ???????????????
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
        clientButton.setOnClickListener(onClick);
    }

    /**
     * ?????????????????????
     */
    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                // ?????????DiDiLayout????????????
                case R.id.didi:
                    intent = new Intent(MainActivity.this, DiDiActivity.class);
                    break;
                // ?????????calculateLayout????????????
                case R.id.calculate:
                    intent = new Intent(MainActivity.this, CalculateActivity.class);
                    break;
                // ?????????LoginLayout????????????
                case R.id.login:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
                // ?????????HeroLayout????????????
                case R.id.hero:
                    intent = new Intent(MainActivity.this, HeroActivity.class);
                    break;
                // ?????????LogLayout????????????
                case R.id.log:
                    intent = new Intent(MainActivity.this, LogActivity.class);
                    break;
                // ?????????DialogLayout????????????
                case R.id.dialog:
                    intent = new Intent(MainActivity.this, DialogActivity.class);
                    break;
                // ?????????fileLayout????????????
                case R.id.file:
                    intent = new Intent(MainActivity.this, FileActivity.class);
                    break;
                // ?????????listViewLayout????????????
                case R.id.listView:
                    intent = new Intent(MainActivity.this, ListViewActivity.class);
                    break;
                // ?????????listViewExamLayout????????????
                case R.id.listViewExam:
                    intent = new Intent(MainActivity.this, ExamActivity.class);
                    break;
                // ?????????birthExamLayout????????????
                case R.id.birth:
                    intent = new Intent(MainActivity.this, BirthHoroscope.class);
                    break;
                // ?????????orCreate????????????
                case R.id.orCreate:
                    intent = new Intent(MainActivity.this, OrCreateActivity.class);
                    break;
                // ?????????searchPhone????????????
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
                case R.id.client:
                    intent = new Intent(MainActivity.this, NewsClientActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    private static final String TAG = "MainActivity";

    /**
     * 1-2-3-4
     * ?????????????????????
     */
    public void setBasicSetup(int type) {
        if (type == 1) {
            // ??????????????????????????????????????????????????????
            JPushInterface.setAlias(this, 0, "luna");
            // ????????????????????????
            JPushInterface.setTags(this, 0, setUserTags());
        } else if (type == 2) {
            // ????????????
            JPushInterface.deleteAlias(this, 0);
            // ??????????????????
            JPushInterface.deleteTags(this, 0, setUserTags());
            // ??????????????????
            JPushInterface.cleanTags(this, 0);
        } else if (type == 3) {
            // ??????tag?????????(??????????????????????????????userid??????????????????????????????????????????)
            JPushInterface.addTags(this, 0, setUserTags());
        } else if (type == 4) {
            // ??????????????????
            JPushInterface.getAllTags(this, 0);
            // ????????????
            JPushInterface.getAlias(this, 0);
            // ????????????tag?????????????????????????????????MyJPushMessageReceiver?????????
            JPushInterface.checkTagBindState(this, 0, "luna");
            // ????????????id
            JPushInterface.getRegistrationID(this);
        }
    }

    /**
     * ????????????
     */
    private static Set<String> setUserTags() {
        // ??????3??????????????????????????????userid???????????????
        Set<String> tags = new HashSet<>();
        tags.add("luna");
        tags.add("luna_nov");
        tags.add("luna_ly");
        return tags;
    }
}

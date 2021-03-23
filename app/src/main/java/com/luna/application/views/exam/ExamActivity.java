package com.luna.application.views.exam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.R;
import com.luna.application.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private ListView     listView;

    private List<String> listStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_exam_layout);
        listView = findViewById(R.id.lv_exam);
        listStr = getListData();
        listView.setAdapter(new ExamAdapter(ExamActivity.this, listStr));
        // 点击事件
        listView.setOnItemClickListener(new Onclick());
    }

    class Onclick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ExamActivity.this);
            builder.setTitle("提示：").setMessage("你选择的数据是数据【" + i + "】")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showMsg(ExamActivity.this, "好的？");
                    }
                }).show();
            Toast.makeText(ExamActivity.this, "点击 pos: " + i, Toast.LENGTH_SHORT).show();
        }
    }

    public static List<String> getListData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        return list;
    }
}

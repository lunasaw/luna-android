package com.luna.application;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luna.application.utils.ToastUtil;

public class DialogActivity extends AppCompatActivity {

    private Button mBtnDialog1, mBtnDialog2, mBtnDialog3, mBtnDialog4, mBtnDialog5, mBtnDialog6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        mBtnDialog1 = findViewById(R.id.btn_diolog1);
        mBtnDialog2 = findViewById(R.id.btn_diolog2);
        mBtnDialog3 = findViewById(R.id.btn_diolog3);
        mBtnDialog4 = findViewById(R.id.btn_diolog4);
        mBtnDialog5 = findViewById(R.id.btn_diolog5);
        mBtnDialog6 = findViewById(R.id.btn_diolog6);
        OnClick onClick = new OnClick();
        mBtnDialog1.setOnClickListener(onClick);
        mBtnDialog2.setOnClickListener(onClick);
        mBtnDialog3.setOnClickListener(onClick);
        mBtnDialog4.setOnClickListener(onClick);
        mBtnDialog5.setOnClickListener(onClick);
        mBtnDialog6.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_diolog1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                    builder.setTitle("请回答：").setMessage("你喜欢计算机吗？").setIcon(R.drawable.username)
                        .setPositiveButton("喜欢", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ToastUtil.showMsg(DialogActivity.this, "真的吗？");
                            }
                        }).setNeutralButton("还行", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ToastUtil.showMsg(DialogActivity.this, "你确定吗？");
                            }
                        }).setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ToastUtil.showMsg(DialogActivity.this, "为什么呢？");
                            }
                        }).show();
                    break;
                case R.id.btn_diolog2:
                    final String[] array2 = new String[] {"男", "女"};
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(DialogActivity.this);
                    builder2.setTitle("请选择性别").setItems(array2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ToastUtil.showMsg(DialogActivity.this, array2[i]);
                        }
                    }).show();
                    break;
                case R.id.btn_diolog3:
                    final String[] array3 = new String[] {"男", "女"};
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(DialogActivity.this);
                    builder3.setTitle("请选择性别").setSingleChoiceItems(array3, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ToastUtil.showMsg(DialogActivity.this, array3[i]);
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false).show();
                    break;
                case R.id.btn_diolog4:
                    final String[] array4 = new String[] {"唱歌", "跳舞", "写代码"};
                    final boolean[] isCheckd = new boolean[] {false, false, true};
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(DialogActivity.this);
                    builder4.setTitle("选择兴趣")
                        .setMultiChoiceItems(array4, isCheckd, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                ToastUtil.showMsg(DialogActivity.this, array4[i] + ": " + isCheckd[i]);
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                    break;
                case R.id.btn_diolog5:
                    AlertDialog.Builder builder5 = new AlertDialog.Builder(DialogActivity.this);
                    View loginview = LayoutInflater.from(DialogActivity.this).inflate(R.layout.login_layout, null);
                    EditText username = loginview.findViewById(R.id.username);
                    EditText password = loginview.findViewById(R.id.password);
                    Button btnLogin = loginview.findViewById(R.id.btn_login);
                    btnLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("dialog", "onClick: builder5 事件触发");
                        }
                    });
                    builder5.setTitle("请先登录").setView(loginview).show();
                    break;
                case R.id.btn_diolog6:
                    ProgressDialog progressDialog = new ProgressDialog(DialogActivity.this);
                    // 设置提示信息
                    progressDialog.setMessage("正在加载");
                    // 设置弹窗标题
                    progressDialog.setTitle("进度条");
                    // 设置弹窗图标
                    progressDialog.setIcon(R.drawable.username);
                    // 能够返回
                    progressDialog.setCancelable(true);
                    // 点击外部返回
                    progressDialog.setCanceledOnTouchOutside(true);
                    // 设置进度条
                    progressDialog.setProgress(100);
                    // 设置进度条是否明确
                    progressDialog.setIndeterminate(true);
                    // 设置进度条样式
                    // 环形精度条 ProgressDialog.STYLE_SPINNER
                    // 水平样式的进度条 ProgressDialog.STYLE_HORIZONTAL
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // progressDialog.dismiss();
                                Toast.makeText(DialogActivity.this, "你点击了弹窗的按钮", Toast.LENGTH_LONG).show();
                            }
                        });
                    progressDialog.show();
            }
        }
    }
}

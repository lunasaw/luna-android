package com.luna.application;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class FileActivity extends Activity {

    private Button   save;

    private TextView text;

    private Button   get, clean;

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_layout);
        save = findViewById(R.id.btn_file_2);
        text = findViewById(R.id.btn_file_0);
        editText = findViewById(R.id.btn_file_1);
        get = findViewById(R.id.btn_file_3);
        clean = findViewById(R.id.btn_file_4);
        save.setOnClickListener(new Onclick());
        get.setOnClickListener(new Onclick());
    }

    class Onclick implements View.OnClickListener {

        public static final String SDCARD_TEST = "/data/data/com.luna.application/files/";
        public static final String FILE_NAME   = "file.txt";

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_file_2:
                    try {
                        FileOutputStream outputStream = null;
                        String s = editText.getText().toString();
                        Log.i("file", "onClick: " + s);
                        if (TextUtils.isEmpty(s)) {
                            outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
                            outputStream.write(editText.getText().toString().getBytes());
                        } else {
                            outputStream = openFileOutput(FILE_NAME, MODE_APPEND);
                            outputStream.write(editText.getText().toString().getBytes());
                        }
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case R.id.btn_file_3:
                    try {
                        String s = loadTextFile(openFileInput(FILE_NAME));
                        Log.i("file", "onClick: " + s);
                        text.setText(s);
                        editText.setText(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case R.id.btn_file_4:
                    editText.setText("");
            }
        }
    }

    private String loadTextFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        return new String(byteArrayOutputStream.toByteArray(), "UTF8");
    }

    /**
     * 将字符串写入到文本文件中
     * 
     * @param strcontent
     * @param filePath
     * @param fileName
     */
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        // 生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    /**
     * 生成文件
     * 
     * @param filePath
     * @param fileName
     * @return
     */
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            Log.i("file", "makeFilePath: " + file.getName());
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 生成文件夹
     * 
     * @param filePath
     */
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                Log.i("file", "makeRootDirectory: " + filePath);
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }
}

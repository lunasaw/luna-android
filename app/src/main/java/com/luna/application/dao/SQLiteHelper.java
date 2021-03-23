package com.luna.application.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DB = "luna-sqlite";

    /**
     * 构造器
     * 
     * @param context 上下文
     * @param name 数据库名
     * @param factory 目的创建consumer对象
     * @param version 版本
     */
    public SQLiteHelper(@Nullable Context context, @Nullable String name,
        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB, null, 1);
    }

    /**
     * 创建的时候执行操作 初始化语句
     * 
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "create table if not exists tb_user("
                + "id integer primary key autoincrement,"
                + "username text,"
                + "password text,"
                + "gender text)");
    }

    /**
     * 版本升级的时候调用 onUpgrade方法就是每次数据库版本升级时调用
     * 
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

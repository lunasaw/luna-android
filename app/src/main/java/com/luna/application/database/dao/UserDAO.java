package com.luna.application.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.luna.application.entity.UserDO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static final String USER_DAO = "UserDAO";
    public static final String TB_USER  = "tb_user";
    private SQLiteDatabase     sqLiteDatabase;

    public UserDAO(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }

    public boolean insert(UserDO userDO) {
        Log.i(USER_DAO, "insert: " + userDO);
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", userDO.getUserName());
        contentValues.put("password", userDO.getPassword());
        contentValues.put("gender", userDO.getGender());
        return sqLiteDatabase.insert(TB_USER, null, contentValues) != -1;
    }

    public List<UserDO> query(String username) {
        Log.i(USER_DAO, "query: " + username);
        Cursor cursor =
            sqLiteDatabase.query(TB_USER, new String[] {"id", "username", "password", "gender"}, "username=? ",
                new String[] {username}, null, null, null);
        List<UserDO> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(new UserDO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return users;
    }

    public List<UserDO> query() {
        Cursor cursor =
            sqLiteDatabase.query(TB_USER, new String[] {"id", "username", "password", "gender"},
                null,
                null, null, null, null);
        List<UserDO> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(new UserDO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        Log.i(USER_DAO, "query: " + users);
        return users;
    }

    public List<UserDO> query(UserDO userDO) {
        Cursor cursor =
            sqLiteDatabase.query(TB_USER, new String[] {"id", "username", "password", "gender"},
                "username=? and password=?",
                new String[] {userDO.getUserName(), userDO.getPassword()}, null, null, null);
        List<UserDO> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(new UserDO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        Log.i(USER_DAO, "query: " + users);
        return users;
    }

    public boolean delete(String username) {
        Log.i(USER_DAO, "delete: " + username);
        return sqLiteDatabase.delete(TB_USER, "username=? ", new String[] {username}) >= 1;
    }

    public boolean update(UserDO userDO) {
        Log.i(USER_DAO, "update: " + userDO);
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", userDO.getUserName());
        contentValues.put("password", userDO.getPassword());
        contentValues.put("gender", userDO.getGender());
        return sqLiteDatabase.update(TB_USER, contentValues, "username=? ", new String[] {userDO.getUserName()}) == 1;
    }
}

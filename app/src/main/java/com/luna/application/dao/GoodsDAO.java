package com.luna.application.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.luna.application.entity.GoodsDO;
import com.luna.application.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {

    public static final String    GOODS_DAO = "GoodsDAO";
    public static final String    TB_GOODS  = "tb_goods";
    private static SQLiteDatabase sqLiteDatabase;

    public GoodsDAO(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean insert(GoodsDO goodsDO) {
        Log.i(GOODS_DAO, "insert: " + goodsDO);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", goodsDO.getTitle());
        contentValues.put("content", goodsDO.getContent());
        contentValues.put("price", goodsDO.getPrice());
        contentValues.put("date", DateUtil.dateToString(goodsDO.getDate(), DateUtil.DatePattern.ONLY_DAY));
        return sqLiteDatabase.insert(TB_GOODS, null, contentValues) != -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public GoodsDO query(String title) {
        Log.i(GOODS_DAO, "query: " + title);
        Cursor cursor =
            sqLiteDatabase.query(TB_GOODS, new String[] {"id", "content", "date", "title", "price"}, "title=?",
                new String[] {title}, null, null, null);
        List<GoodsDO> goods = new ArrayList<>();
        while (cursor.moveToNext()) {
            goods.add(new GoodsDO(cursor.getInt(0), cursor.getString(1),
                DateUtil.stringToDate(cursor.getString(2), DateUtil.DatePattern.ONLY_DAY), cursor.getString(3),
                cursor.getInt(4)));
        }
        Log.i("query", "query: " + goods.get(0));
        return goods.size() != 0 ? goods.get(0) : null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<GoodsDO> query() {
        Cursor cursor =
            sqLiteDatabase.query(TB_GOODS, new String[] {"id", "content", "date", "title", "price"},
                null,
                null, null, null, null);
        List<GoodsDO> goods = new ArrayList<>();
        while (cursor.moveToNext()) {
            goods.add(new GoodsDO(cursor.getInt(0), cursor.getString(1),
                DateUtil.stringToDate(cursor.getString(2), DateUtil.DatePattern.ONLY_DAY), cursor.getString(3),
                cursor.getInt(4)));
        }
        Log.i(GOODS_DAO, "query: " + goods);
        return goods;
    }

    public boolean delete(String title) {
        Log.i(GOODS_DAO, "delete: " + title);
        return sqLiteDatabase.delete(TB_GOODS, "title=? ", new String[] {title}) >= 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean update(GoodsDO GoodsDO) {
        Log.i(GOODS_DAO, "update: " + GoodsDO);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", GoodsDO.getTitle());
        contentValues.put("content", GoodsDO.getContent());
        contentValues.put("date", DateUtil.dateToString(GoodsDO.getDate(), DateUtil.DatePattern.ONLY_DAY));
        contentValues.put("price", GoodsDO.getPrice());
        return sqLiteDatabase.update(TB_GOODS, contentValues, "title=? ", new String[] {GoodsDO.getTitle()}) == 1;
    }
}

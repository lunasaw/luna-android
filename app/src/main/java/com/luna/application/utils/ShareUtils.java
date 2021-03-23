package com.luna.application.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtils extends Activity {

    public static final String              USER_SHARE = "user.share";
    private Context                         context;

    private static ShareUtils               shareUtils;

    private static SharedPreferences        sharedPreferences;

    private static SharedPreferences.Editor editor;

    private ShareUtils(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(USER_SHARE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 保存字符串
     * 
     * @param key
     * @param value
     * @return
     */
    public static boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public String getKey(String key) {
        return sharedPreferences.getString(key, "");
    }

    public  boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public  boolean putBoolean(String key, boolean b) {
        return editor.putBoolean(key, b).commit();
    }

    public static ShareUtils getInstance(Context context) {
        if (shareUtils == null) {
            shareUtils = new ShareUtils(context);
        }
        return shareUtils;
    }
}

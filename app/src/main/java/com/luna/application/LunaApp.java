package com.luna.application;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;

public class LunaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

}

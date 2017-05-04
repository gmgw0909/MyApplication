package com.example.lena.myapplication.base;

import android.app.Application;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/5/4
 */

public class BaseApplication extends Application {

    private static BaseApplication myApp = null;

    public static BaseApplication getMyApplication() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }
}

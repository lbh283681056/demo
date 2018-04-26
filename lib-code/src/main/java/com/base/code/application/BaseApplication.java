package com.base.code.application;

import android.app.Application;

/**
 * Created by linbinghuang on 2016/12/9.
 */
public class BaseApplication extends Application{
    protected static BaseApplication baseApplication;
    public static BaseApplication getInstance() {
        return baseApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }
}

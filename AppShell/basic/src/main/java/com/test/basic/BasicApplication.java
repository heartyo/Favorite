package com.test.basic;

import android.app.Application;

public class BasicApplication extends Application {

    final static boolean is_app = BuildConfig.is_app;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}

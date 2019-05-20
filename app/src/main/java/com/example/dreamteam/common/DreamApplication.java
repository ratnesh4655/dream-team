package com.example.dreamteam.common;

import android.app.Application;

public class DreamApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceUtil.init(this);
    }
}

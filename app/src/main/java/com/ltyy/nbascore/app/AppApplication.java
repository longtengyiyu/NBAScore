package com.ltyy.nbascore.app;

import android.app.Application;

import com.ltyy.nbascore.api.Api;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Api.create();
    }
}

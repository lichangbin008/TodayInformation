package com.lcb.todayinformation.base;

import android.app.Application;

import com.lcb.todayinformation.base.crash.CrashProtectManager;

/**
 * Created by ${lichangbin} on 2020/2/6.
 */
public class TodayInfomationApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance(this).init();
    }
}

package com.lcb.todayinformation.base;

import android.app.Application;

import com.lcb.todayinformation.base.crash.CrashProtectManager;
import com.lcb.todayinformation.base.helper.ContextHelper;

/**
 * Created by ${lichangbin} on 2020/2/6.
 */
public class TodayInfomationApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashProtectManager.getInstance(this).init();

        // 全局Context获取类
        ContextHelper.getInstance().init(this);
    }
}

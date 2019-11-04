package com.task.tools;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by ${lichangbin} on 2019/11/4.
 */

public class ThreadUtil {

    // 主线程的Handler
    private final static Handler MAIN = new Handler(Looper.getMainLooper());

    public static void postMainThread(final Runnable runnable) {
        MAIN.post(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

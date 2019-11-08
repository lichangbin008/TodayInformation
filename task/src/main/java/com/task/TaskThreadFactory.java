package com.task;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * Created by ${lichangbin} on 2019/11/5.
 */

public class TaskThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable r) {
        return new Thread(r,"task_thread_pool");
    }
}

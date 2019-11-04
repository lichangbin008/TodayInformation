package com.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by ${lichangbin} on 2019/11/4.
 */

public class TaskScheduler {

    interface ITaskSchedulerType {
        int SUBMIT_TASK = 0;
    }

    private static TaskScheduler instance;
    private final Handler handler;

    private TaskScheduler() {
        // 用于消息调度的线程
        HandlerThread handlerThread = new HandlerThread("task-thread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            // HandlerThread handleMessage运行在子线程
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case ITaskSchedulerType.SUBMIT_TASK:
                        doSubmitTask((AsyncTaskInstance) msg.obj);
                        break;
                }
                return false;
            }
        });

        // 创建一个线程池
    }

    private void doSubmitTask(AsyncTaskInstance instance) {
        executor.submit(instance);
    }


    public static TaskScheduler getInstance() {
        if (instance == null) {
            instance = new TaskScheduler();
        }
        return instance;
    }

    public void submit(AsyncTaskInstance instance) {
        handler.sendMessage(handler.obtainMessage(ITaskSchedulerType.SUBMIT_TASK, instance));
    }
}

package com.task;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public class TaskHelper {

    public static void submitTask(ITaskBackground iTaskBackground, ITaskCallback iTaskCallback) {
        AsyncTaskInstance instance = AsyncTaskInstance.getInstance(iTaskBackground,iTaskCallback);
        // 构建线程池管理器
//        exec.execute(instance);
        TaskScheduler.getInstance().submit(instance);
    }
}

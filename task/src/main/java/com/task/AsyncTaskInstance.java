package com.task;

import android.support.annotation.NonNull;

import com.task.tools.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by ${lichangbin} on 2019/11/4.
 */

public class AsyncTaskInstance<Result> extends FutureTask<Result> {

    private final ITaskBackground iTaskBackground;
    private final ITaskCallback iTaskCallback;

    public AsyncTaskInstance(final ITaskBackground<Result> iTaskBackground, ITaskCallback<Result> iTaskCallback) {
        super(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return iTaskBackground.onBackground();
            }
        });
        this.iTaskBackground = iTaskBackground;
        this.iTaskCallback = iTaskCallback;
    }

    @Override
    protected void done() {
        if (iTaskCallback != null) {
            onComplete();
        }
    }

    @Override
    protected void setException(final Throwable t) { // 获取FutureTask执行过程中的异常
        super.setException(t);
        if (iTaskCallback!=null){
            ThreadUtil.postMainThread(new Runnable() {
                @Override
                public void run() {
                    iTaskCallback.onException(t);
                }
            });
        }
    }

    private void onComplete() {
        try {
            final Object object = get();
            if (object != null) {
                // 确保运行到主线程中
                ThreadUtil.postMainThread(new Runnable() {
                    @Override
                    public void run() {
                        iTaskCallback.onComplete(object);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AsyncTaskInstance getInstance(ITaskBackground iTaskBackground, ITaskCallback iTaskCallback) {
        return new AsyncTaskInstance(iTaskBackground, iTaskCallback);
    }
}

package com.task;

import android.support.annotation.NonNull;

import com.task.tools.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by ${lichangbin} on 2019/11/4.
 */

public class AsyncTaskInstance extends FutureTask {

    private final ITaskBackground iTaskBackground;
    private final ITaskCallback iTaskCallback;

    public AsyncTaskInstance(final ITaskBackground iTaskBackground, ITaskCallback iTaskCallback) {
        super(new Callable() {
            @Override
            public Object call() throws Exception {
                return iTaskBackground.onBackground();
            }
        });
        this.iTaskBackground = iTaskBackground;
        this.iTaskCallback = iTaskCallback;
    }

    public static AsyncTaskInstance getInstance(ITaskBackground iTaskBackground, ITaskCallback iTaskCallback) {
        return new AsyncTaskInstance(iTaskBackground, iTaskCallback);
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
                        iTaskCallback.onSuccess(object);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

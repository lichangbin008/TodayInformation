package com.task;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public interface ITaskCallback {

    void onSuccess(Object o);

    void onException(Throwable throwable);
}

package com.task;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public interface ITaskCallback<Result> {

    void onComplete(Result o);

    void onException(Throwable throwable);
}

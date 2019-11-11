package com.lcb.http.result;

/**
 * Created by ${lichangbin} on 2019/11/9.
 */

public interface IResultCallBack<T> {

    void onSuccess(IResult<T> t);

    void onFailed(IResult t);
}

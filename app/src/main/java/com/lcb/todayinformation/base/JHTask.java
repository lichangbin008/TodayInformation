package com.lcb.todayinformation.base;

import com.lcb.http.result.IResult;
import com.lcb.http.result.IResultCallBack;
import com.lcb.http.result.Result;
import com.task.LfTask;

/**
 * Created by ${lichangbin} on 2019/11/9.
 */

public abstract class JHTask<T> extends LfTask<IResult<T>> implements IResultCallBack<T> {

    @Override
    public void onComplete(IResult<T> iResult) {
        if (iResult != null) {
            if (iResult.isSuccess()) {
                onSuccess(iResult);
            } else {
                onFailed(Result.failed(Result.CODE_404)); // 1次失败
            }
        } else {
            onFailed(Result.failed(Result.CODE_504));// 2次失败
        }
    }

    @Override
    public void onFailed(IResult t) {
        // 可以做成统一错误码处理
        switch (t.getCode()){
            case Result.CODE_404:
                break;
            case Result.CODE_504:
                break;
            case Result.CODE_505:
                break;
        }
    }

    @Override
    public void onException(Throwable throwable) {
        onFailed(Result.failed(Result.CODE_505));// 3次失败
    }

}

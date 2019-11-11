package com.lcb.http.result;

/**
 * Created by ${lichangbin} on 2019/11/6.
 */

public class Result<T> implements IResult {

    public final static int CODE_200 = 200;

    public final static int CODE_404 = 404;

    public final static int CODE_504 = 504;

    public final static int CODE_505 = 505;

    protected T data;

    protected int code;

    protected boolean success;

    public static IResult failed(int code) {
        Result result = new Result();
        result.code = code;
        result.success = false;
        return result;
    }

    public static IResult success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = CODE_200;
        result.success = true;
        return result;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public T data() {
        return data;
    }
}

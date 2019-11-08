package com.lcb.http.result;

/**
 * Created by ${lichangbin} on 2019/11/6.
 */

public class Result<T> implements IResult {

    public static int CODE_200 = 200;

    public static int CODE_404 = 404;

    protected T data;

    protected int code;

    public static IResult failed() {
        Result result = new Result();
        result.code = CODE_404;
        return result;
    }

    public static IResult success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = CODE_200;
        return result;
    }
}

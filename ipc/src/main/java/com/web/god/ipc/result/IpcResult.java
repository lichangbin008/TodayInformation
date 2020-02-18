package com.web.god.ipc.result;

/**
 * Created by ${lichangbin} on 2020/2/16.
 */
public class IpcResult implements IResult {

    private String data;

    private int code;

    private boolean success;

    public static IResult getErrorResult() {
        IpcResult result = new IpcResult();
        result.success = false;
        return result;
    }

    public static IResult getSuccessResult(String data) {
        IpcResult result = new IpcResult();
        result.success = true;
        result.data = data;
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
    public String data() {
        return data;
    }
}

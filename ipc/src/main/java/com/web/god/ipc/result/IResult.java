package com.web.god.ipc.result;

/**
 * Created by ${lichangbin} on 2020/2/16.
 */
public interface IResult {

    boolean isSuccess();

    int getCode();

    String data();
}

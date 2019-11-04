package com.lcb.http.okhttp;

import com.lcb.http.IRequest;
import com.lcb.http.request.call.ICall;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/11/2.
 */

public class OkHttpCall implements ICall {

    private Call call;

    public OkHttpCall(IRequest request, Call call) {
        this.call = call;
    }

    @Override
    public Object execute() {
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

package com.lcb.http.okhttp;

import com.lcb.http.IRequest;
import com.lcb.http.request.call.ICall;
import com.lcb.http.response.IResponse;
import com.lcb.http.result.IResult;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/11/2.
 */

public class OkHttpCall implements ICall {

    private IRequest request;
    private Call call;

    public OkHttpCall(IRequest request, Call call) {
        this.call = call;
        this.request = request;
    }

    @Override
    public IResponse execute() {
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpResponse okHttpResponse = new OkHttpResponse(response);
        return response;
    }

    @Override
    public IRequest getRequest() {
        return request;
    }
}

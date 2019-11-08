package com.lcb.http.okhttp;

import com.lcb.http.response.IResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/11/5.
 */

public class OkHttpResponse implements IResponse {

    private Response response;

    public OkHttpResponse(Response response) {
        this.response = response;
    }

    @Override
    public String getBodyStr() {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

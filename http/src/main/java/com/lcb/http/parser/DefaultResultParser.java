package com.lcb.http.parser;

import com.google.gson.Gson;
import com.lcb.http.IRequest;
import com.lcb.http.response.IResponse;
import com.lcb.http.result.IResult;
import com.lcb.http.result.Result;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/11/6.
 */

public class DefaultResultParser implements IParser {

    private static DefaultResultParser instance;

    private Gson gson;

    public DefaultResultParser() {
        gson = new Gson();
    }

    public static IParser getInstance() {
        if (instance == null) {
            instance = new DefaultResultParser();
        }
        return instance;
    }

    @Override
    public IResult parseResponse(IRequest request, IResponse response) {
        Type type = request.getType();
        String bodyStr = response.getBodyStr();
        Object object = gson.fromJson(bodyStr,type);
        if (object ==null){
            return Result.failed();
        }else {
            return Result.success(object);
        }
    }
}

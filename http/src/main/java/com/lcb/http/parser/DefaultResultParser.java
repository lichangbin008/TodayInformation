package com.lcb.http.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.lcb.http.request.IRequest;
import com.lcb.http.response.IResponse;
import com.lcb.http.result.IResult;
import com.lcb.http.result.Result;

import java.lang.reflect.Type;

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
        Log.e("parseResponse","parseResponse");
        Type type = request.getType();
        String bodyStr = response.getBodyStr();
        Object object = gson.fromJson(bodyStr,type);
        return Result.success(object);
    }
}

package com.lcb.http;

import android.util.Log;

import com.lcb.http.request.IRequest;
import com.lcb.http.parser.IParser;
import com.lcb.http.request.call.ICall;
import com.lcb.http.response.IResponse;
import com.lcb.http.result.IResult;

/**
 * Created by ${lichangbin} on 2019/11/2.
 */

public abstract class HttpScheduler {

    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call) {
        Log.e("HttpScheduler","execute");
        // IResponse和IResult进行一个转换
        IResponse response = call.execute();
        IRequest request = call.getRequest();
        IParser parser = request.getParser();
        return parser.parseResponse(request,response);
    }
}

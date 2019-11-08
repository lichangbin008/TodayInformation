package com.lcb.http;

import com.lcb.http.result.IResult;

import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class LfHttpServer {

    protected <T> IResult<T> execute(IRequest request, Map<String, Object> params) {
        return HttpHelper.execute(request, params);
    }
}

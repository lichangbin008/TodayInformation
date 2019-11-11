package com.lcb.http;

import com.lcb.http.okhttp.OkHttpScheduler;
import com.lcb.http.request.IRequest;
import com.lcb.http.request.call.ICall;
import com.lcb.http.result.IResult;

import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class HttpHelper {

    private volatile static HttpScheduler httpScheduler;

    public static HttpScheduler getHttpScheduler() {
        if (httpScheduler == null) {
            synchronized (HttpHelper.class) {
                if (httpScheduler == null) {
                    httpScheduler = new OkHttpScheduler();
                }
            }
        }
        return httpScheduler;
    }

    // TODO: 2019/11/1  带重构
    protected static <T> IResult<T> execute(IRequest request, Map<String, Object> params) {

        request.setParams(params);

        ICall call = getHttpScheduler().newCall(request);

        return getHttpScheduler().execute(call);
    }
}

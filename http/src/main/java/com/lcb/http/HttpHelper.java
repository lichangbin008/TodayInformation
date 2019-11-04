package com.lcb.http;

import com.lcb.http.okhttp.OkHttpScheduler;
import com.lcb.http.request.HttpScheduler;
import com.lcb.http.request.call.ICall;

import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class HttpHelper {

    private volatile static HttpScheduler httpScheduler;

    public static HttpScheduler getHttpScheduler() {
        if (httpScheduler == null) {
            synchronized (HttpScheduler.class) {
                if (httpScheduler == null) {
                    httpScheduler = new OkHttpScheduler();
                }
            }
        }
        return httpScheduler;
    }

    // TODO: 2019/11/1  带重构
    protected static Object execute(IRequest request, Map<String, Object> params) {

        request.setParams(params);

        ICall call = getHttpScheduler().newCall(request);

        Object object = getHttpScheduler().execute(call);
        return object;
    }
}

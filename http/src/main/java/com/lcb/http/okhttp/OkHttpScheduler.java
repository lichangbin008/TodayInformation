package com.lcb.http.okhttp;

import com.lcb.http.IRequest;
import com.lcb.http.annotation.RequestMothod;
import com.lcb.http.request.HttpScheduler;
import com.lcb.http.request.call.ICall;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ${lichangbin} on 2019/11/2.
 */

public class OkHttpScheduler extends HttpScheduler {

    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest request) {
        Map<String, Object> params = request.getParams();
        int requsetMethod = request.getRequestMethod();
        Request.Builder requestBuilder = new Request.Builder();
        switch (requsetMethod) {
            case RequestMothod.Get:
                // 拼接Get请求的url host+path
                StringBuilder sbUrl = new StringBuilder(request.getHost().getHost());
                sbUrl.append(request.getPath());
                HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(sbUrl.toString()).newBuilder();

                if (params != null && params.size() > 0) {
                    Iterator<Map.Entry<String,Object>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String,Object> next = iterator.next();
                        // TODO: 2019/11/2  带重构 涉及到对象如何转换成String 字符串
                        httpUrlBuilder.addQueryParameter(next.getKey(),String.valueOf(next.getValue()));
                    }
                }
                requestBuilder.get().url(httpUrlBuilder.build());
                break;
            case RequestMothod.Post:
                break;
        }
        Request okHttpRequest = requestBuilder.build();
        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(request,call);
        return okHttpCall;
    }

    @Override
    public Object execute(ICall call) {
        return call.execute();
    }


    private OkHttpClient getClient() {
        if (client ==null){
            client = new OkHttpClient();
        }
        return client;
    }
}

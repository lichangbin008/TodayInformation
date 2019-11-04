package com.lcb.todayinformation.base;

import com.lcb.http.IRequest;
import com.lcb.http.annotation.RequestMothod;
import com.lcb.http.request.LfRequest;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class JHRequest extends LfRequest{

    public static IRequest sendHttp(String path, @RequestMothod int requestMethod){
        JHRequest request = new JHRequest();
        request.host = IHostManager.jhHost;
        request.requestMethod = requestMethod;
        request.path = path;
        return request;
    }
}

package com.lcb.todayinformation.base;

import com.lcb.http.IRequest;
import com.lcb.http.annotation.RequestMothod;
import com.lcb.http.parser.DefaultResultParser;
import com.lcb.http.request.LfRequest;

import java.lang.reflect.Type;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class JHRequest extends LfRequest {

    public static IRequest sendHttp(String path, @RequestMothod int requestMethod, Type type) {
        JHRequest request = new JHRequest();
        request.host = IHostManager.jhHost;
        request.requestMethod = requestMethod;
        request.path = path;
        request.type = type;
        request.resultParser = DefaultResultParser.getInstance();
        return request;
    }
}

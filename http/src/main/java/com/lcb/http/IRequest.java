package com.lcb.http;

import com.lcb.http.request.IHost;

import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public interface IRequest {

    void setParams(Map<String, Object> params);

    Map<String, Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();

}

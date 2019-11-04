package com.lcb.http.request;

import com.lcb.http.IRequest;
import com.lcb.http.annotation.RequestMothod;

import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class LfRequest implements IRequest {

    protected IHost host;

    @RequestMothod
    protected int requestMethod;

    private Map<String, Object> params;

    protected String path;

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }
}

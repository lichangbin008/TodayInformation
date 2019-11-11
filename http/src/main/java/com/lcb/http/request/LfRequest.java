package com.lcb.http.request;

import com.lcb.http.annotation.RequestMethod;
import com.lcb.http.parser.IParser;
import com.lcb.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class LfRequest implements IRequest {

    protected IHost host;

    @RequestMethod
    protected int requestMethod;

    private Map<String, Object> params;

    protected String path;

    protected Type type;

    protected IParser resultParser;

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

    @Override
    public IParser getParser() {
        return resultParser;
    }

    @Override
    public Type getType() {
        return type;
    }
}

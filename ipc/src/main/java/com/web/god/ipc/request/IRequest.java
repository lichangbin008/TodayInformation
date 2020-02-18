package com.web.god.ipc.request;

import com.web.god.ipc.ICallback;

/**
 * Created by ${lichangbin} on 2020/2/16.
 */
public interface IRequest extends Comparable<IRequest>{

    void setParams(String params);

    String getParams();

    String getRequestKey();

    void addCallback(ICallback callback);

    ICallback getCallback();

    long getAddTime();
}

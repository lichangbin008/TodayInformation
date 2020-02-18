package com.web.god.ipc.request;

import android.support.annotation.NonNull;

import com.web.god.ipc.ICallback;

/**
 * Created by ${lichangbin} on 2020/2/16.
 */
public class IpcRequest implements IRequest {

    private long mTime;
    private String requestKey;
    private String params;
    private ICallback callback;

    public IpcRequest() {
        mTime = System.currentTimeMillis();
    }

    public IpcRequest(@NonNull String requestKey) {
        this.requestKey = requestKey;
    }

    @Override
    public void setParams(@NonNull String params) {
        this.params = params;
    }

    @Override
    public String getParams() {
        return params;
    }

    @Override
    public String getRequestKey() {
        return requestKey;
    }

    @Override
    public void addCallback(ICallback callback) {
        this.callback = callback;
    }

    @Override
    public ICallback getCallback() {
        return callback;
    }

    @Override
    public long getAddTime() {
        return this.mTime;
    }

    @Override
    public int compareTo(@NonNull IRequest o) {
        return (int) (this.getAddTime() - o.getAddTime());
    }
}

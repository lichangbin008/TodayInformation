package com.web.god.ipc.response;

import com.web.god.ipc.IClientInterface;

/**
 * Created by ${lichangbin} on 2020/2/19.
 */
public class BaseResponse {

    public String requestKey;
    public String params;
    public IClientInterface iClientInterface;

    public BaseResponse(String requestKey, String params, IClientInterface iClientInterface) {
        this.requestKey = requestKey;
        this.params = params;
        this.iClientInterface = iClientInterface;
    }
}
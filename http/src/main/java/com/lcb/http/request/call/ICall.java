package com.lcb.http.request.call;

import com.lcb.http.request.IRequest;
import com.lcb.http.response.IResponse;

/**
 * Created by ${lichangbin} on 2019/11/2.
 */

public interface ICall {
    IResponse execute();
    IRequest getRequest();
}

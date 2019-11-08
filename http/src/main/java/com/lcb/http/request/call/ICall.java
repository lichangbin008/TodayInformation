package com.lcb.http.request.call;

import com.lcb.http.IRequest;
import com.lcb.http.response.IResponse;
import com.lcb.http.result.IResult;

/**
 * Created by ${lichangbin} on 2019/11/2.
 */

public interface ICall {
    IResponse execute();
    IRequest getRequest();
}

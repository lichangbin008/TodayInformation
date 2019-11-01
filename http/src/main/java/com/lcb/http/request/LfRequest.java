package com.lcb.http.request;

import com.lcb.http.IRequest;
import com.lcb.http.annotation.RequestMothod;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class LfRequest implements IRequest {

    protected IHost host;

    @RequestMothod
    protected int requestMethod;
}

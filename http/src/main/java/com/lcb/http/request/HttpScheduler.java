package com.lcb.http.request;

import com.lcb.http.IRequest;
import com.lcb.http.request.call.ICall;

/**
 * Created by ${lichangbin} on 2019/11/2.
 */

public abstract class HttpScheduler {
    public abstract ICall newCall(IRequest request);

    public abstract Object execute(ICall call);
}

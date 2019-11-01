package com.lcb.http;

import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class LfHttpTask {

    protected Object execute(IRequest request, Map<String, Object> params) {
        return HttpHelper.execute(request, params);
    }
}

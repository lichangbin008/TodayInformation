package com.lcb.todayinformation.main.shanghai.module;

import com.lcb.http.LfHttpServer;
import com.lcb.http.result.IResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public class ShanghaiDetailHttpTask<T> extends LfHttpServer {

    public IResult<T> getXiaoHuaList(String sort, String page, String pagesize) {
        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        params.put("page", page);
        params.put("pagesize", pagesize);
        params.put("time", "" + System.currentTimeMillis() / 1000);
        params.put("key", "51280ee59e42e870feb0012ea7fc5628");
        return super.execute(IShanghaiDetailRequest.xiaoHuaRequest, params);
    }
}

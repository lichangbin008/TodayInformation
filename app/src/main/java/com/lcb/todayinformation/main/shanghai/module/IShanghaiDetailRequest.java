package com.lcb.todayinformation.main.shanghai.module;

import com.lcb.http.IRequest;
import com.lcb.http.annotation.RequestMothod;
import com.lcb.todayinformation.base.JHRequest;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public interface IShanghaiDetailRequest {

    IRequest xiaoHuaRequest = JHRequest.sendHttp("/joke/content/list.php", RequestMothod.Get);
}

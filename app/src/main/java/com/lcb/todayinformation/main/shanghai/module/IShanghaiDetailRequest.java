package com.lcb.todayinformation.main.shanghai.module;

import com.lcb.http.request.IRequest;
import com.lcb.http.annotation.RequestMethod;
import com.lcb.todayinformation.base.JHRequest;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDetailBean;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public interface IShanghaiDetailRequest {

    IRequest xiaoHuaRequest = JHRequest.sendHttp("/joke/content/list.php", RequestMethod.Get, ShanghaiDetailBean.class);
}

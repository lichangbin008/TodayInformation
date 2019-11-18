package com.lcb.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/11/6.
 */

public class ShanghaiDetailBean {

    public int error_code;

    public String reason;

    public XiaoHuaListBean result;

    public static class XiaoHuaListBean{
        public ArrayList<XiaoHuaBean> data;
    }

    public static class XiaoHuaBean{
        public String content;

        public String hashId;

        public String unixtime;

        public String updatetime;
    }
}

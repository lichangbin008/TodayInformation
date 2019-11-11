package com.lcb.todayinformation.base;

import com.lcb.http.request.host.IHost;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

public interface IHostManager {
    IHost jhHost = new IHost() {

        @Override
        public String getHost() {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath() {
            return "/joke/content/list.php";
        }
    };
}

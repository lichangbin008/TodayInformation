package com.lcb.todayinformation.main.shanghai.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.lcb.todayinformation.main.shanghai.module.ShanghaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public class GetXiaoHuaTask extends AsyncTask<Object, Object, Object> {

    @Override
    protected Object doInBackground(Object[] params) { // 运行在子线程中
        Object desc = new ShanghaiDetailHttpTask().getXiaoHuaList((String) params[0], (String) params[1], (String) params[2]);
        return desc;
    }

    @Override
    protected void onPostExecute(Object o) { // 运行在主线程中，用于更新数据
        super.onPostExecute(o);
        Response response = (Response)o;
//        Log.e("GetXiaoHuaTask", "response:" + response.body().toString());
        try {
            Log.e("GetXiaoHuaTask", "response:" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

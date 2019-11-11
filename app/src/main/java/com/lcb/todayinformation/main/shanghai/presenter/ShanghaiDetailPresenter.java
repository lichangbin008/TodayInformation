package com.lcb.todayinformation.main.shanghai.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.lcb.http.result.IResult;
import com.lcb.todayinformation.base.BasePresenter;
import com.lcb.todayinformation.base.JHTask;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.lcb.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.lcb.todayinformation.main.shanghai.module.ShanghaiDetailHttpTask;
import com.lcb.todayinformation.splash.SplashTimerPresenter;
import com.mvp.mvp.base.BaseMvpPresenter;
import com.task.LfTask;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.IView> implements IShanghaiDetailContract.IPresenter {

    public ShanghaiDetailPresenter(IShanghaiDetailContract.IView view) {
        super(view);
    }

    @Override
    protected IShanghaiDetailContract.IView getEmptyView() {
        return IShanghaiDetailContract.emptyView;
    }

    @Override
    public void getNetData() {
        // 1.数据的结果解析怎么来做
        // 2.相同任务的去重工作
//        submitTask(new LfTask() {
//            @Override
//            public Object onBackground() { //运行在子线程
//                IResult desc = new ShanghaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
//
//                return null;
//            }
//
//            @Override
//            public void onSuccess(Object o) { // 在主线程
//                Log.e("getNetData", "onSuccess:" + Thread.currentThread().getName());
//                //获取网络结果
//                Log.e("getNetData", o.toString());
////                Response response = (Response) o;
////                try {
////                    Log.e("getNetData", response.body().string());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                Log.e("getNetData", throwable.toString());
//            }
//        });

        /**
         *  架构师的必备条件
         1、合理利用继承关系
         2、合理利用抽象编程
         3、合理利用泛型传递数据
         4、合理利用一些设计模式
         */
        submitTask(new JHTask<ShanghaiDetailBean>() {

            @Override
            public IResult<ShanghaiDetailBean> onBackground() {
                return new ShanghaiDetailHttpTask<ShanghaiDetailBean>().getXiaoHuaList("desc", "1", "1");
            }

            @Override
            public void onSuccess(IResult<ShanghaiDetailBean> t) {
                Log.e("onSuccess", "onSuccess");
                ShanghaiDetailBean data = t.data();
//                Log.e("onSuccess", data.error_code + data.reason);
                Gson gson = new Gson();
                String s = gson.toJson(data);
                Log.e("onSuccess", s);
            }
        });
    }
}

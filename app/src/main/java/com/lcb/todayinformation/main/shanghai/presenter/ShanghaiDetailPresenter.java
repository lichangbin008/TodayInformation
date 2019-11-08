package com.lcb.todayinformation.main.shanghai.presenter;

import android.util.Log;

import com.lcb.http.result.IResult;
import com.lcb.todayinformation.base.BasePresenter;
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
        submitTask(new LfTask() {
            @Override
            public Object onBackground() { //运行在子线程
                IResult desc = new ShanghaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");

                return null;
            }

            @Override
            public void onSuccess(Object o) { // 在主线程
                Log.e("getNetData", "onSuccess:" + Thread.currentThread().getName());
                //获取网络结果
                Log.e("getNetData", o.toString());
//                Response response = (Response) o;
//                try {
//                    Log.e("getNetData", response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onException(Throwable throwable) {
                Log.e("getNetData", throwable.toString());
            }
        });
    }
}

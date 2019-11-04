package com.lcb.todayinformation.main.shanghai.presenter;

import com.lcb.todayinformation.base.BasePresenter;
import com.lcb.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.lcb.todayinformation.main.shanghai.module.ShanghaiDetailHttpTask;
import com.mvp.mvp.base.BaseMvpPresenter;
import com.task.LfTask;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.IView> implements IShanghaiDetailContract.IPresenter{

    public ShanghaiDetailPresenter(IShanghaiDetailContract.IView view) {
        super(view);
    }

    @Override
    protected IShanghaiDetailContract.IView getEmptyView() {
        return IShanghaiDetailContract.emptyView;
    }

    @Override
    public void getNetData() {
        submitTask(new LfTask() {
            @Override
            public Object onBackground() { //运行在子线程
                return new ShanghaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
            }

            @Override
            public void onSuccess(Object o) { // 在主线程
                //获取网络结果
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }
}

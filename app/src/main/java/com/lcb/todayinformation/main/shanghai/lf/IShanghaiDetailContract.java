package com.lcb.todayinformation.main.shanghai.lf;

import android.support.v4.app.Fragment;

import com.lcb.todayinformation.main.IMainActivityContract;
import com.mvp.mvp.ILifeCircle;
import com.mvp.mvp.IMvpView;
import com.mvp.mvp.MvpControler;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public interface IShanghaiDetailContract {

    interface IView extends IMvpView {

    }

    interface IPresenter extends ILifeCircle {

        void getNetData();
    }

    IShanghaiDetailContract.IView emptyView = new IShanghaiDetailContract.IView() {


        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}

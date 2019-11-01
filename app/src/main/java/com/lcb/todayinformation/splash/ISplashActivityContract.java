package com.lcb.todayinformation.splash;

import com.mvp.mvp.ILifeCircle;
import com.mvp.mvp.IMvpView;
import com.mvp.mvp.MvpControler;

/**
 * Created by ${lichangbin} on 2019/10/22.
 */

public interface ISplashActivityContract {

    interface IView extends IMvpView {
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

    IView emptyView = new IView() {
        @Override
        public void setTvTimer(String timer) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}

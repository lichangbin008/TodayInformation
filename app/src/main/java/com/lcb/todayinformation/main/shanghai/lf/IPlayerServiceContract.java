package com.lcb.todayinformation.main.shanghai.lf;

import android.content.Context;

import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.mvp.mvp.ILifeCircle;
import com.mvp.mvp.IMvpView;
import com.mvp.mvp.MvpControler;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public interface IPlayerServiceContract {

    interface IView extends IMvpView {


    }

    interface IPresenter extends ILifeCircle {

        void bindService(Context context);

        void playOrPaused();
    }

    IPlayerServiceContract.IView emptyView = new IPlayerServiceContract.IView() {




        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}

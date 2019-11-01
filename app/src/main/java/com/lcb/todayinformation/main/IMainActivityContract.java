package com.lcb.todayinformation.main;

import android.support.v4.app.Fragment;

import com.mvp.mvp.ILifeCircle;
import com.mvp.mvp.IMvpView;
import com.mvp.mvp.MvpControler;

/**
 * Created by ${lichangbin} on 2019/10/22.
 */

public interface IMainActivityContract {

    interface IView extends IMvpView {

        void showFragment(Fragment fragment);

        void addFragment(Fragment fragment);

        void hideFragment(Fragment fragment);
    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckIndex();

        void replaceFragment(int fragmentIndex);

        int getTopPosition();

        int getBottomPosition();

        int getCurrentCheckId();
    }

    IView emptyView = new IView() {
        @Override
        public void showFragment(Fragment fragment) {

        }

        @Override
        public void addFragment(Fragment fragment) {

        }

        @Override
        public void hideFragment(Fragment fragment) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}

package com.lcb.todayinformation;

import com.lcb.todayinformation.mvp.IMvpView;
import com.lcb.todayinformation.mvp.ISplashActivityContract;
import com.lcb.todayinformation.mvp.base.BaseMvpPresenter;

/**
 * Created by ${lichangbin} on 2019/10/18.
 */

public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.IView> implements ISplashActivityContract.IPresenter {


    private CountDownTimer countDownTimer;

    public SplashTimerPresenter(ISplashActivityContract.IView view) {
        super(view);
    }


    public void initTimer() {
        countDownTimer = new CountDownTimer(5, new CountDownTimer.ICountDownTimeHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒");
            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");
            }
        });
        countDownTimer.start();
    }

    public void cancel() {
        countDownTimer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }

    @Override
    protected ISplashActivityContract.IView getEmptyView() {
        // 防止空指针
        return ISplashActivityContract.emptyView;
    }
}

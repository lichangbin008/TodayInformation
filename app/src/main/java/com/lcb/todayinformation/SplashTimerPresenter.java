package com.lcb.todayinformation;

/**
 * Created by ${lichangbin} on 2019/10/18.
 */

public class SplashTimerPresenter {

    private final SplashActivity activity;
    private CountDownTimer countDownTimer;

    public SplashTimerPresenter(SplashActivity activity){
        this.activity = activity;
    }

    public void initTimer() {
        countDownTimer = new CountDownTimer(5, new CountDownTimer.ICountDownTimeHandler() {
            @Override
            public void onTicker(int time) {
                activity.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                activity.setText("跳过");
            }
        });
        countDownTimer.start();
    }

    public void cancel(){
        countDownTimer.cancel();
    }
}

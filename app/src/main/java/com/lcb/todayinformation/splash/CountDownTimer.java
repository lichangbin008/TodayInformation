package com.lcb.todayinformation.splash;

import android.os.Handler;
import android.widget.TextView;

/**
 * Created by ${lichangbin} on 2019/10/18.
 */

public class CountDownTimer implements Runnable {


    private int time;

    private int countDownTime;

    private Handler handler;

    private ICountDownTimeHandler iCountDownTimeHandler;

    private boolean isRun;

    public CountDownTimer(int time, ICountDownTimeHandler iCountDownTimeHandler) {
        this.time = time;
        countDownTime = time;
        handler = new Handler();
        this.iCountDownTimeHandler = iCountDownTimeHandler;
    }

    public void start() {
        isRun = true;
        handler.post(this);
    }

    public void cancel() {
        isRun = false;
        handler.removeCallbacks(this);
    }


    @Override
    public void run() {
        if (isRun) {
            if (iCountDownTimeHandler != null) {
                iCountDownTimeHandler.onTicker(countDownTime);
            }
            if (countDownTime == 0) {
                cancel();
                if (iCountDownTimeHandler != null) {
                    iCountDownTimeHandler.onFinish();
                }
            } else {
                countDownTime = time--;
                handler.postDelayed(this, 1000);
            }
        }
    }

    public interface ICountDownTimeHandler {

        void onTicker(int time);

        void onFinish();
    }
}

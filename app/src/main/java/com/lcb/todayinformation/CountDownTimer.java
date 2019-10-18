package com.lcb.todayinformation;

import android.os.Handler;

/**
 * Created by ${lichangbin} on 2019/10/18.
 */

public class CountDownTimer {


    private int timer;

    private Handler handler;

    public void CountDownTimer(int timer){
        this.timer = timer;
        handler = new Handler();
    }

    public void start(){
        if (timer == 5){

        }else if (timer == 0){

        }
    }

    public void finish(){

    }
}

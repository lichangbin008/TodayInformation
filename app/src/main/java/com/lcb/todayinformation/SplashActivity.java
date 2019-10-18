package com.lcb.todayinformation;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    private SplashTimerPresenter timerPresenter;

    private VideoView vvSplashPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        initTimerPresenter();
    }

    private void initView() {
//        tvSplash
        vvSplashPlayer = (VideoView) findViewById(R.id.vv_splash_player);
        vvSplashPlayer.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        vvSplashPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vvSplashPlayer.start();
            }
        });
        vvSplashPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vvSplashPlayer.start();
            }
        });
    }

    private void initTimerPresenter() {
        timerPresenter = new SplashTimerPresenter();
        timerPresenter.initTimer();
    }
}

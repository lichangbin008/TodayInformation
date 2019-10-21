package com.lcb.todayinformation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ViewInject(mainlayout = R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @BindView(R.id.vv_splash_player)
    SplashVideoView vvSplashPlayer;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;

    private SplashTimerPresenter timerPresenter;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);


//        initView();
        initVideoView();
        initListener();
//        initTimer();
        initTimerPresenter();
    }

    private void initTimer() {

    }

    private void initVideoView() {
        vvSplashPlayer.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        vvSplashPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vvSplashPlayer.start();
            }
        });
    }

    private void initListener() {
        vvSplashPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vvSplashPlayer.start();
            }
        });
    }

    private void initTimerPresenter() {
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerPresenter.cancel();
    }

    @OnClick(R.id.tv_count_down)
    public void onClick() {
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
    }

    public void setText(String s) {
        tvCountDown.setText(s);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

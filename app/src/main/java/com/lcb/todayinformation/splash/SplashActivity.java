package com.lcb.todayinformation.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseActivity;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.MainActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainlayout = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.IView {

    @BindView(R.id.vv_splash_player)
    SplashVideoView vvSplashPlayer;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;

    private ISplashActivityContract.IPresenter timerPresenter;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void afterBindView() {
        initVideoView();
        initListener();
        initTimerPresenter();
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

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        timerPresenter.cancel();
//    }

    @OnClick(R.id.tv_count_down)
    public void onClick() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setTvTimer(String timer) {
        tvCountDown.setText(timer);
    }
}

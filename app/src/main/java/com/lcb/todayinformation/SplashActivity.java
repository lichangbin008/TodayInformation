package com.lcb.todayinformation;

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


        initView();

        initTimerPresenter();
    }

    private void initView() {
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
        countDownTimer = new CountDownTimer(5, new CountDownTimer.ICountDownTimeHandler() {
            @Override
            public void onTicker(int time) {
                tvCountDown.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                tvCountDown.setText("跳过");
            }
        });
        countDownTimer.start();
    }

    private void initTimerPresenter() {
        timerPresenter = new SplashTimerPresenter();
        timerPresenter.initTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    @OnClick(R.id.tv_count_down)
    public void onClick() {
//        switch ()
    }
}

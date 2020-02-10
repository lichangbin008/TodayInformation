package com.newa.today.player.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.newa.today.player.state.PlayerState;

import java.io.IOException;

/**
 * Created by ${lichangbin} on 2020/2/7.
 */
public class GoogleMediaPlayer implements IPlayer, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnInfoListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnBufferingUpdateListener {

    private MediaPlayer mMediaPlayer;

    private IPlayerListener mPlayerListener;

    public GoogleMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnInfoListener(this);
        mMediaPlayer.setOnVideoSizeChangedListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
    }

    @Override
    public void release() {

    }

    @Override
    public void prepare(Context context, String url) {
        // medilaPlayer播放系统资源时只能用 uri的方式
        try {
            mMediaPlayer.setDataSource(context, Uri.parse(url));
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPlayerListener(IPlayerListener listener) {
        this.mPlayerListener = listener;
    }

    @Override
    public void paused() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            setPlayerState(PlayerState.PAUSED);
        }
    }

    @Override
    public void reStart() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            setPlayerState(PlayerState.STARTED);
        }
    }

    //播放器准备好后的回调
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        setPlayerState(PlayerState.STARTED);
    }

    private void setPlayerState(PlayerState state) {
        if (mPlayerListener != null) {
            mPlayerListener.playerStateChanged(state);
        }
    }

    //播放器如果遇到错误 会回调到Java层
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e("GoogleMediaPlayer", "what = " + what + " extra = " + extra);
        return false;
    }

    //播放完成后的监听
    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    //播放卡顿的一些信息
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    //横竖屏切换 视频大小改变回调
    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }

    //播放器的缓冲监听，用于一些UI展示，缓冲进度条
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }
}

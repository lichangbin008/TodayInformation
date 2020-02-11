package com.newa.today.player.player;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.newa.today.player.source.IPlayerSource;
import com.newa.today.player.state.PlayerState;

/**
 * Created by ${lichangbin} on 2020/2/11.
 */
public class ExoMediaPlayer implements IPlayer, Player.EventListener {

    private IPlayerListener mPlayerListener;

    private SimpleExoPlayer mExoPlayer;

    public ExoMediaPlayer(Context context){
        // 创建播放器
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(context);
        mExoPlayer.addListener(this);
    }

    @Override
    public void release() {

    }

    @Override
    public void prepare(Context context, IPlayerSource iPlayerSource) {
        // 准备资源去播放
        DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(iPlayerSource.getResId()));
        RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(context);
        try {
            rawResourceDataSource.open(dataSpec);
        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
            e.printStackTrace();
        }
        Uri uri = rawResourceDataSource.getUri();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, ""));
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        mExoPlayer.prepare(videoSource);
        mExoPlayer.setPlayWhenReady(true);
        setPlayerState(PlayerState.STARTED);
    }

    private void setPlayerState(PlayerState state) {
        if (mPlayerListener != null) {
            mPlayerListener.playerStateChanged(state);
        }
    }

    @Override
    public void setPlayerListener(IPlayerListener listener) {
        this.mPlayerListener = listener;
    }

    @Override
    public void paused() {
        mExoPlayer.setPlayWhenReady(false);
        setPlayerState(PlayerState.PAUSED);
    }

    @Override
    public void reStart() {
        mExoPlayer.setPlayWhenReady(true);
        setPlayerState(PlayerState.STARTED);
    }
}

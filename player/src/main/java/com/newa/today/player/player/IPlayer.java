package com.newa.today.player.player;

import android.content.Context;

import com.newa.today.player.source.IPlayerSource;

/**
 * Created by ${lichangbin} on 2020/2/7.
 */
public interface IPlayer {

    // 释放播放器
    void release();

    // 准备播放器
    void prepare(Context context, IPlayerSource playerSource);

    // 设置Player监听
    void setPlayerListener(IPlayerListener listener);

    // 暂停
    void paused();

    // 重新播放
    void reStart();
}

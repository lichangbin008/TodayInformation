package com.newa.today.player.state;

/**
 * Created by ${lichangbin} on 2020/2/7.
 */
public enum PlayerState {

    /*播放器处于未初始化*/
    IDLE,

    /*正在准备*/
    PREPARING,

    /* 准备完成*/
    PREPARED,

    /*播放中 */
    STARTED,

    /*暂停状态*/
    PAUSED,

    /* 停止状态*/
    STOPPED,

    /* 完成状态*/
    COMPLETED,

    /*停止状态*/
    END,

    /* 错误状态*/
    ERROR
}

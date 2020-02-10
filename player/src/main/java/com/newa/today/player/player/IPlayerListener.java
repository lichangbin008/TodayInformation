package com.newa.today.player.player;

import com.newa.today.player.state.PlayerState;

/**
 * Created by ${lichangbin} on 2020/2/7.
 */
public interface IPlayerListener {

    void playerStateChanged(PlayerState state);
}
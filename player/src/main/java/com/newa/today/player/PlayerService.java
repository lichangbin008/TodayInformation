package com.newa.today.player;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.newa.today.player.player.IPlayer;
import com.newa.today.player.player.IPlayerListener;
import com.newa.today.player.player.PlayerFactory;
import com.newa.today.player.source.IPlayerSource;
import com.newa.today.player.state.PlayerState;

import java.util.concurrent.Semaphore;

/**
 * Created by ${lichangbin} on 2020/2/7.
 */
public class PlayerService extends Service implements IPlayerListener {

    private PlayerState mState = PlayerState.IDLE;
    private IPlayer mPlayer;
    private PlayerFactory mPlayerFactory;

    @Override
    public void playerStateChanged(PlayerState state) {
        this.mState = state;
    }

    public class PlayerBinder extends Binder {

        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }

    /**
     * 在startService有作用，start多次就会调用多次，一般用来给service传递数据
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 不管start还是bind，也不管调用几次，只会启动一次，常用来做全局初始化操作
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void playOrPause(IPlayerSource playerSource, Context context) {
        switch (mState) {
            case IDLE:
                // 初始化播放器去播放
                if (mPlayer != null) {
                    mPlayer.release();
                }
                if (mPlayerFactory == null) {
                    mPlayerFactory = new PlayerFactory();
                }
                if (mPlayer == null) {
                    mPlayer = PlayerFactory.createPlayer(context);
                }

                if (mPlayer == null) {
                    // 播放器创建失败
                    return;
                }
                // 拿到播放器去播放
                mPlayer.setPlayerListener(this);
                mPlayer.prepare(context, playerSource);
                break;
            case STARTED:
                // 暂停播放
                if (mPlayer != null) {
                    mPlayer.paused();
                }
                break;
            case PAUSED:
                // 继续播放
                if (mPlayer != null) {
                    mPlayer.reStart();
                }
                break;
//            case PREPARING:
//                // 初始化播放器去播放
//                break;


//            case IDLE:
//                // 初始化播放器去播放
//                break;
//            case IDLE:
//                // 初始化播放器去播放
//                break;
//            case IDLE:
//                // 初始化播放器去播放
//                break;
//            case IDLE:
//                // 初始化播放器去播放
//                break;
            default:
                break;

        }
    }

}

package com.lcb.todayinformation.main.shanghai.presenter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BasePresenter;
import com.lcb.todayinformation.base.helper.ContextHelper;
import com.lcb.todayinformation.main.shanghai.lf.IPlayerServiceContract;
import com.newa.today.player.PlayerService;
import com.newa.today.player.source.RawPlayerSource;

/**
 * Created by ${lichangbin} on 2019/11/3.
 */

public class PlayerServicePresenter extends BasePresenter<IPlayerServiceContract.IView> implements IPlayerServiceContract.IPresenter {

    private PlayerService playerService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // IOC数据回调和Service连接成功后调用
            PlayerService.PlayerBinder binder = (PlayerService.PlayerBinder) service;
            playerService = binder.getService();

            playOrPaused();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // IOC数据回调和Service断开连接后调用
            if (playerService != null) {
                playerService.unbindService(mConnection);
                playerService = null;
            }
        }
    };

    public PlayerServicePresenter(IPlayerServiceContract.IView view) {
        super(view);
    }

    @Override
    protected IPlayerServiceContract.IView getEmptyView() {
        return IPlayerServiceContract.emptyView;
    }

    @Override
    public void bindService(Context context) {
        if (playerService != null) {
            playOrPaused();
        } else {
            Intent intent = new Intent(context, PlayerService.class);
            context.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        }

    }

    @Override
    public void playOrPaused() {
        if (playerService != null) {
            playerService.playOrPause(
                    new RawPlayerSource().setPath(ContextHelper.getInstance().getApplicationContext().getPackageName(), R.raw.minyao),
                    ContextHelper.getInstance().getApplicationContext());

        }
    }
}

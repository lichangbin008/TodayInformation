package com.newa.today.player.player;

import android.content.Context;
import android.media.MediaPlayer;

import com.newa.today.player.tool.DataSourceUtil;

/**
 * Created by ${lichangbin} on 2020/2/7.
 * 播放器工厂类
 */
public class PlayerFactory {

    /**
     * 一般工厂设计模式：可以创建用户想要的播放器
     *
     * @return
     */
    public static IPlayer createPlayer(Context context) {
        // 读取配置
        int playerType = DataSourceUtil.getMetaDataFromApp(context);
        switch (playerType){
            case IPlayerType.MEDIAPLAYERTYPE:
                return new GoogleMediaPlayer();
        }
        return null;
    }
}

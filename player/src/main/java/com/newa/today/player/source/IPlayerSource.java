package com.newa.today.player.source;

/**
 * Created by ${lichangbin} on 2020/2/7.
 */
public interface IPlayerSource {

    void setUrl(String url);

    String getUrl();

    int getResId();

    void setResId(int resId);
}

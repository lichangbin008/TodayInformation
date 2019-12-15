package com.lcb.todayinformation.main.hangzhou.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lcb.todayinformation.R;
import com.web.god.refresh.BaseRefreshManager;

/**
 * Created by ${lichangbin} on 2019/12/8.
 */

public class MeiTuanRefreshManager extends BaseRefreshManager {

    private ImageView imageView;

    public MeiTuanRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        View view = layoutInflater.inflate(R.layout.meituan_header_refresh_layout, null, false);
        imageView = view.findViewById(R.id.loading);
        return view;
    }

    @Override
    public void downRefresh() { // 只会触发一次

    }

    @Override
    public void releaseRefresh() {
        // 会变成美团的吉祥物
        imageView.setImageResource(R.drawable.mei_tuan_loading_pre);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void iddle() {
        imageView.clearAnimation();
        imageView.setImageResource(R.mipmap.pull_image);
        imageView.setScaleX(0);
        imageView.setScaleY(0);
    }

    @Override
    public void refreshing() {
// 会变成美团的吉祥物
        imageView.setImageResource(R.drawable.mei_tuan_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void downRefreshPercent(float percent) {
        imageView.setScaleX(percent);
        imageView.setScaleY(percent);
    }
}

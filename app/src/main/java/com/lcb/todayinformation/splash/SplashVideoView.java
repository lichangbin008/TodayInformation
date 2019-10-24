package com.lcb.todayinformation.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by ${lichangbin} on 2019/10/18.
 */

public class SplashVideoView extends VideoView {

    public SplashVideoView(Context context) {
        super(context);
    }

    public SplashVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplashVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // widthMeasureSpec 包含两个主要的内容，1、测量模式，2、测量大小
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }
}

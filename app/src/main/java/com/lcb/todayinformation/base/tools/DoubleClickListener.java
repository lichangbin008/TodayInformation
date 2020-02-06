package com.lcb.todayinformation.base.tools;

import android.view.View;

/**
 * Created by ${lichangbin} on 2020/2/6.
 */
public class DoubleClickListener implements View.OnClickListener {
    private final View.OnClickListener onClickListener;
    private long old;

    public DoubleClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        long now = System.currentTimeMillis();
        if (now - old > 1000) {
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
        }
        old = now;
    }
}

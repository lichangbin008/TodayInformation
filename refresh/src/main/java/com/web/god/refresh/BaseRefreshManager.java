package com.web.god.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ${lichangbin} on 2019/11/25.
 */

public abstract class BaseRefreshManager {

    public LayoutInflater layoutInflater;

    public BaseRefreshManager(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public abstract View getHeaderView();

    public abstract void downRefresh();

    public abstract void releaseRefresh();

    public abstract void iddle();

    public abstract void refreshing();

    public abstract void downRefreshPercent(float percent);
}

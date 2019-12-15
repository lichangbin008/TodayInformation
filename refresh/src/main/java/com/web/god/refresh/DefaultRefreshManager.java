package com.web.god.refresh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ${lichangbin} on 2019/11/25.
 */

public class DefaultRefreshManager extends BaseRefreshManager {


    private TextView tvRefresh;

    public DefaultRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        View view = layoutInflater.inflate(R.layout.ulti_header_layout, null, false);
        tvRefresh = view.findViewById(R.id.header_text);
        return view;
    }

    @Override
    public void downRefresh() {
        tvRefresh.setText("下拉刷新");
    }

    @Override
    public void releaseRefresh() {
        tvRefresh.setText("释放刷新");
    }

    @Override
    public void iddle() {
        tvRefresh.setText("下拉刷新");
    }

    @Override
    public void refreshing() {
        tvRefresh.setText("正在刷新");
    }

    @Override
    public void downRefreshPercent(float percent) {

    }
}

package com.lcb.todayinformation.main.shanghai;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseFragment;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.shanghai.adapter.ShanghaiAdapter;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiBean;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDataManager;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by ${lichangbin} on 2019/10/23.
 */

@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShanghaiFragment extends BaseFragment {

    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_ctl)
    CollapsingToolbarLayout shanghaiCtl;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout shanghaiAppBarlayout;
    @BindView(R.id.rv_shanghai)
    RecyclerView rvShanghai;

    @Override
    public void afterBindView() {
        initListener();
        initRecyclerView();
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        rvShanghai.setLayoutManager(new LinearLayoutManager(context));
        ShanghaiAdapter shanghaiAdapter = new ShanghaiAdapter(
                getActivity(), ShanghaiDataManager.getData(), false);
        rvShanghai.setAdapter(shanghaiAdapter);
    }

    private void initListener() {
        shanghaiAppBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("appBarLayout", "verticalOffset" + verticalOffset + "appBarLayout height:" + appBarLayout.getMeasuredHeight());
                if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                    tvShanghaiWelcome.setVisibility(View.INVISIBLE);
                } else {
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}

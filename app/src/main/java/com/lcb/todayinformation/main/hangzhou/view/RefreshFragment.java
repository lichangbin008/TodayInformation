package com.lcb.todayinformation.main.hangzhou.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseFragment;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.hangzhou.adapter.ZhiHuAdapter;
import com.lcb.todayinformation.main.hangzhou.refresh.MeiTuanRefreshManager;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.lcb.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.lcb.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;
import com.web.god.refresh.GodRefreshLayout;

import butterknife.BindView;

/**
 * Created by ${lichangbin} on 2019/11/16.
 */

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShanghaiDetailContract.IView {

    private IShanghaiDetailContract.IPresenter presenter = new ShanghaiDetailPresenter(this);

    @BindView(R.id.refresh_recyclerview)
    RecyclerView refreshRecyclerview;

    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;

    @Override
    public void afterBindView() {
        initRecyclerView();
        initRefreshLayout();

    }

    private void initRefreshLayout() {
        // 1、采用默认方式
//        godRefresh.setRefreshManager();

        // 要支持用户自定义
        godRefresh.setRefreshManager(new MeiTuanRefreshManager(context));

        godRefresh.setRefreshListener(new GodRefreshLayout.RefreshingListener() {
            @Override
            public void onRefreshing() {
                presenter.getNetData(20);
//                godRefresh.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        godRefresh.refreshOver();
//                    }
//                }, 2000);
            }
        });
    }

    private void initRecyclerView() {
        refreshRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        presenter.getNetData(20);
    }

    @Override
    public void showData(ShanghaiDetailBean data) {
        if (refreshRecyclerview.getAdapter() == null) {
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            refreshRecyclerview.setAdapter(adapter);
        }
        godRefresh.refreshOver();
    }
}

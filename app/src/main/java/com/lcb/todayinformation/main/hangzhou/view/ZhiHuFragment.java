package com.lcb.todayinformation.main.hangzhou.view;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseFragment;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.hangzhou.adapter.ZhiHuAdapter;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.lcb.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.lcb.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

/**
 * Created by ${lichangbin} on 2019/11/16.
 */

@ViewInject(mainlayoutid = R.layout.fragment_zhihu)
public class ZhiHuFragment extends BaseFragment implements IShanghaiDetailContract.IView {

    IShanghaiDetailContract.IPresenter presenter = new ShanghaiDetailPresenter(this);

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.zhifu_app_barlayout)
    AppBarLayout zhifuAppBarlayout;
    @BindView(R.id.rv_zhifu)
    RecyclerView rvZhifu;

    @Override
    public void afterBindView() {
        rvZhifu.setLayoutManager(new LinearLayoutManager(context));
        rvZhifu.setAnimation(AnimationUtils.loadAnimation(context, R.anim.zhihu_recyclerview_show));
        presenter.getNetData(20);
    }

    @Override
    public void showData(ShanghaiDetailBean data) {
        if (rvZhifu.getAdapter() == null) {
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            rvZhifu.setAdapter(adapter);
        }
    }
}

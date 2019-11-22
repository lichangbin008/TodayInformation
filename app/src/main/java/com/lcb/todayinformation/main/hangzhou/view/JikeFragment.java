package com.lcb.todayinformation.main.hangzhou.view;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

@ViewInject(mainlayoutid = R.layout.fragment_jike)
public class JikeFragment extends BaseFragment {

    @Override
    public void afterBindView() {

    }
}

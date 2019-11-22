package com.lcb.todayinformation.main.hangzhou;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseFragment;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.hangzhou.adapter.HangzhouAdapter;
import com.lcb.todayinformation.main.hangzhou.view.ZhiHuFragment;
import com.lcb.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.lcb.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

/**
 * Created by ${lichangbin} on 2019/10/23.
 */

@ViewInject(mainlayoutid = R.layout.fragment_hangzhou)
public class HangzhouFragment extends BaseFragment{

    @BindView(R.id.tl_tablayout)
    TabLayout tlTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @Override
    public void afterBindView() {
        tlTablayout.setupWithViewPager(vpViewpager);
        vpViewpager.setAdapter(new HangzhouAdapter(getChildFragmentManager()));
    }
}

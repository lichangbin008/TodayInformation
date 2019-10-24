package com.lcb.todayinformation.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseActivity;
import com.lcb.todayinformation.base.ViewInject;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainlayout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.IView {


    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.rg_main_top)
    RadioGroup rgMainTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rbMainShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;

    private boolean isChangeTopOrBottom = false;

    IMainActivityContract.IPresenter mainPresenter = new MainActivityPresenter(this);

    @Override
    public void afterBindView() {
        initHomeFragment();
        channgeAnime(rgMainBottom, rgMainTop);
    }

    /**
     * 初始化Fragment
     */
    private void initHomeFragment() {
        mainPresenter.initHomeFragment();
    }


    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    channgeAnime(rgMainTop, rgMainBottom);
                } else {
                    channgeAnime(rgMainBottom, rgMainTop);
                }
                break;
        }
    }

    private void channgeAnime(RadioGroup gone, RadioGroup show) {
        // 消失动画
        gone.clearAnimation(); // 清除动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        // 展示动画
        show.clearAnimation(); // 清除动画
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, fragment).commit();
    }

    @Override
    public void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }
}

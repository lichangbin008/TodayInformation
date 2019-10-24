package com.lcb.todayinformation.main;

import android.support.v4.app.Fragment;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.main.beijing.BeijingFragment;
import com.lcb.todayinformation.main.hangzhou.HangzhouFragment;
import com.lcb.todayinformation.main.shanghai.ShanghaiFragment;
import com.lcb.todayinformation.main.shenzhen.ShenzhenFragment;
import com.lcb.todayinformation.mvp.base.BaseMvpPresenter;

/**
 * Created by ${lichangbin} on 2019/10/23.
 */

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.IView> implements IMainActivityContract.IPresenter {


    // 当前Fragment角标
    private int currentFragmentIndex;

    private Fragment[] fragments = new Fragment[4];

    private int currentCheckId;

    public MainActivityPresenter(IMainActivityContract.IView view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.IView getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        currentFragmentIndex = 0;
        replaceFragment(currentFragmentIndex);
    }

    /**
     * 切换Fragment
     *
     * @param index 索引
     */
    private void replaceFragment(int index) {
        for (int i = 0; i < fragments.length; i++) {
            if (index != i) {
                if (fragments[i] != null) {
                    hideFragment(fragments[i]);
                }
            }
        }

        Fragment fragment = fragments[index];
        if (fragment != null) {
            addAndShowFragment(fragment);
            setCurChecked(index);
        } else {
            newCurrentFragment(index);
            setCurChecked(index);
        }
    }

    /**
     * 记录当前角标
     *
     * @param index 索引
     */
    private void setCurChecked(int index) {
        currentFragmentIndex = index;
        switch (currentFragmentIndex) {
            case 0:
                currentCheckId = R.id.rb_main_shanghai;
                break;
            case 1:
                currentCheckId = R.id.rb_main_hangzhou;
                break;
            case 2:
                currentCheckId = R.id.rb_main_beijing;
                break;
            case 3:
                currentCheckId = R.id.rb_main_shenzhen;
                break;
        }
    }

    /**
     * 创建Fragment
     *
     * @param index 索引
     */
    private void newCurrentFragment(int index) {
        Fragment fragment = null;
        switch (index) {
            case 0:
                fragment = new ShanghaiFragment();
                break;
            case 1:
                fragment = new HangzhouFragment();
                break;
            case 2:
                fragment = new BeijingFragment();
                break;
            case 3:
                fragment = new ShenzhenFragment();
                break;
        }

        fragments[index] = fragment;
        addAndShowFragment(fragment);
    }

    /**
     * 显示添加Fragment
     *
     * @param fragment
     */
    private void addAndShowFragment(Fragment fragment) {
        if (fragment.isAdded()) {
            getView().showFragment(fragment);
        } else {
            getView().addFragment(fragment);
        }
    }

    /**
     * 异常当前fragment
     *
     * @param fragment
     */
    private void hideFragment(Fragment fragment) {
        if (fragment != null && fragment.isVisible()) {
            getView().hideFragment(fragment);
        }
    }
}

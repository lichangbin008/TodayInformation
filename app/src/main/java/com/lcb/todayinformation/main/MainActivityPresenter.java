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

    private int topPosition;

    private int bottomPosition;

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

    @Override
    public int getCurrentCheckIndex() {
        return 0;
    }

    @Override
    public void replaceFragment(int fragmentIndex) {
        for (int i = 0; i < fragments.length; i++) {
            if (fragmentIndex != i) {
                if (fragments[i] != null) {
                    hideFragment(fragments[i]);
                }
            }
        }

        Fragment fragment = fragments[fragmentIndex];
        if (fragment != null) {
            addAndShowFragment(fragment);
            setCurChecked(fragmentIndex);
        } else {
            newCurrentFragment(fragmentIndex);
            setCurChecked(fragmentIndex);
        }
    }

    @Override
    public int getTopPosition() {
        return topPosition;
    }

    @Override
    public int getBottomPosition() {
        return bottomPosition;
    }

    @Override
    public int getCurrentCheckId() {
        return currentCheckId;
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
                topPosition = 0;
                break;
            case 1:
                currentCheckId = R.id.rb_main_hangzhou;
                topPosition = 1;
                break;
            case 2:
                currentCheckId = R.id.rb_main_beijing;
                bottomPosition = 2;
                break;
            case 3:
                currentCheckId = R.id.rb_main_shenzhen;
                bottomPosition = 3;
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

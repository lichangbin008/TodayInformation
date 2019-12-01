package com.lcb.todayinformation.main.hangzhou.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lcb.todayinformation.main.hangzhou.view.JikeFragment;
import com.lcb.todayinformation.main.hangzhou.view.RefreshFragment;
import com.lcb.todayinformation.main.hangzhou.view.ZhiHuFragment;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/11/20.
 */

public class HangzhouAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> list = new ArrayList<>();

    public HangzhouAdapter(FragmentManager fm) {
        super(fm);
        list.add("知乎");
        list.add("即刻");
        list.add("下拉刷新");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ZhiHuFragment();
            case 1:
                return new JikeFragment();
            case 2:
                return new RefreshFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}

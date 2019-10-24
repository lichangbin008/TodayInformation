package com.lcb.todayinformation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcb.todayinformation.mvp.view.LifeCircleMvpActivity;
import com.lcb.todayinformation.mvp.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

/**
 * Created by ${lichangbin} on 2019/10/20.
 */

public abstract class BaseFragment extends LifeCircleMvpFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainlayoutid = annotation.mainlayout();
            if (mainlayoutid > 0) {
                setContentView(mainlayoutid);
                initFragmentView(mainlayoutid);
                bindView();
                afterBindView();
            } else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
        return view;
    }

    /**
     * 创建Fragment视图
     *
     * @param mainlayoutid 布局
     * @return 视图
     */
    private View initFragmentView(int mainlayoutid) {
        return LayoutInflater.from();
    }

    // 模板方法 设计模式
    public abstract void afterBindView();

    /**
     * View 的依赖注入绑定
     */
    private void bindView() {
        ButterKnife.bind(this);
    }
}

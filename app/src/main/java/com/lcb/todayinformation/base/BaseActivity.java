package com.lcb.todayinformation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcb.todayinformation.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

/**
 * Created by ${lichangbin} on 2019/10/20.
 */

public abstract class BaseActivity extends LifeCircleMvpActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainlayoutid = annotation.mainlayout();
            if (mainlayoutid > 0) {
                setContentView(mainlayoutid);
                bindView();
                afterBindView();
            } else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
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

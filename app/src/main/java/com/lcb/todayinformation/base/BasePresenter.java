package com.lcb.todayinformation.base;

import com.mvp.mvp.IMvpView;
import com.mvp.mvp.base.BaseMvpPresenter;
import com.task.LfTask;
import com.task.TaskHelper;

/**
 * Created by ${lichangbin} on 2019/11/3.
 * 集成MVP及网络请求快捷方式
 */

public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    public BasePresenter(T view) {
        super(view);
    }

    public void submitTask(LfTask task) {
        TaskHelper.submitTask(task, task);
    }
}

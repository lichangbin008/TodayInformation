package com.lcb.todayinformation.mvp;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ${lichangbin} on 2019/10/22.
 */

public class MvpControler implements ILifeCircle {

    // 存放的是P层的实例
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public void savePresenter(ILifeCircle iLifeCircle){
        lifeCircles.add(iLifeCircle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            presenter.onCreate(savedInstanceState, intent, getArguments);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            presenter.onActivityCreate(savedInstanceState, intent, getArguments);
        }
    }

    @Override
    public void onStart() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onDestroy();
        }
    }

    @Override
    public void destroyView() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.destroyView();
        }
    }

    @Override
    public void onDestroyView() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onDestroyView();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onNewIntent(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle presenter = iterator.next();
            presenter.attachView(iMvpView);
        }
    }
}

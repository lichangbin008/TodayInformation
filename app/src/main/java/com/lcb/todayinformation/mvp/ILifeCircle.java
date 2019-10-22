package com.lcb.todayinformation.mvp;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ${lichangbin} on 2019/10/21.
 */

public interface ILifeCircle {

    void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    void onActivityCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void destroyView();

    void onDestroyView();

    void onNewIntent(Intent intent);

    void onSaveInstanceState(Bundle bundle);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void attachView(IMvpView iMvpView);
}

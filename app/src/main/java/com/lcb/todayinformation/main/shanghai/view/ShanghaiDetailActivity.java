package com.lcb.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseActivity;
import com.lcb.todayinformation.base.ViewInject;

import butterknife.BindView;

/**
 * Created by ${lichangbin} on 2019/10/28.
 */

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity {

    private static String activityOptionsCompat = "ShanghaiDetailActivity";

    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    @Override
    public void afterBindView() {
        initAnima();
    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ViewCompat.setTransitionName(ivShanghaiDetail, activityOptionsCompat);
            // 启动转场动画
            startPostponedEnterTransition();
        }
    }

    /**
     * 用于Android系统5.0界面转场动画，共享元素动画
     */
    public static void start_5_0(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair(view, activityOptionsCompat);
            Intent intent = new Intent(activity, ShanghaiDetailActivity.class);
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
        }

    }
}

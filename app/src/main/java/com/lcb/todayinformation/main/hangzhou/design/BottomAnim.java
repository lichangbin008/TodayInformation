package com.lcb.todayinformation.main.hangzhou.design;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.lcb.todayinformation.R;

/**
 * Created by ${lichangbin} on 2019/11/19.
 */

public class BottomAnim {

    public static void show(View show) {
        // 展示动画
        show.clearAnimation(); // 清除动画
        Animation animationShow = AnimationUtils.loadAnimation(show.getContext(), R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    public static void hide(View gone) {
        // 消失动画
        gone.clearAnimation(); // 清除动画
        Animation animationGone = AnimationUtils.loadAnimation(gone.getContext(), R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.INVISIBLE);
    }
}

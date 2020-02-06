package com.lcb.todayinformation.base.tools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by ${lichangbin} on 2020/2/6.
 * 动画工具类
 */
public class AnimationUtil {

    /**
     * x轴方向的属性动画
     *
     * @param target
     * @param positionStart
     * @param positionEnd
     * @param listener
     */
    public static void startTranslationXAnim(View target, float positionStart, float positionEnd,
                                             Animator.AnimatorListener listener) {
        ObjectAnimator titleAnim = ObjectAnimator.ofFloat(
                target, "translationX", positionStart, positionEnd);
        titleAnim.setDuration(1000);
        titleAnim.start();
        if (listener != null) {
            titleAnim.addListener(listener);
        }
    }
}
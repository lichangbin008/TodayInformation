package com.lcb.todayinformation.main.hangzhou.design;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ${lichangbin} on 2019/11/18.
 */

public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView> {

    public BottomShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 这个方法的回调时机：即将发生嵌套滚动 nestedScrollAxes 用于判断滑动的方向
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    // 发生嵌套滚的时候回调
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        // 向下滑动
        if (dyConsumed + dyUnconsumed > 0) {
            // 隐藏child
            if (child.getVisibility()==View.VISIBLE){
                BottomAnim.hide(child);
            }
        } else { // 向上滑动
            // 展示child
            if (child.getVisibility() != View.VISIBLE){
                BottomAnim.show(child);
            }
        }
    }
}

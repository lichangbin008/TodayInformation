package com.web.god.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ${lichangbin} on 2019/11/25.
 */

public class GodRefreshLayout extends LinearLayout {

    private BaseRefreshManager refreshManager;
    private Context context;
    private View headView;
    private int headViewHeight;
    private int minHeadViewHeight; // 头部布局最小高度
    private int maxHeadViewHeight; // 头部布局最大高度
    private RefreshingListener refreshingListener; // 正在刷新回调接口

    public GodRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
    }

    /**
     * 刷新完成后的操作
     */
    public void refreshOver(){
        hideHeadView(getHeadViewLayoutParams());
    }

    private void initHeaderView() {
        setOrientation(VERTICAL);
        headView = refreshManager.getHeaderView();
        headView.measure(0, 0);
        headViewHeight = headView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, headViewHeight);
        // 通过params.topMargin调节下拉刷新的高度
        minHeadViewHeight = -headViewHeight;
        maxHeadViewHeight = (int) (headViewHeight * 0.3f);
        params.topMargin = minHeadViewHeight;
        addView(headView, 0, params);
    }

    /**
     * 开启下拉刷新，下拉刷新效果是默认的
     */
    public void setRefreshManager() {
        refreshManager = new DefaultRefreshManager(context);
        initHeaderView();
    }

    /**
     * 开启下拉刷新，使用用户自定义的下拉刷新效果
     *
     * @param manager
     */
    public void setRefreshManager(BaseRefreshManager manager) {
        refreshManager = manager;
    }

    private int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getY();
                int dy = moveY - downY;
                if (dy > 0) {
                    LayoutParams params = getHeadViewLayoutParams();
                    // 阻尼效果
                    int topMargin = (int) Math.min(dy / 1.8f + minHeadViewHeight, maxHeadViewHeight);
                    if (topMargin < 0 && currentRefreshState != RefreshState.DOWNREFRESH) {
                        // 提示下拉刷新的状态
                        currentRefreshState = RefreshState.DOWNREFRESH;
                        handleRefreshState(currentRefreshState);
                    } else if (topMargin >= 0 && currentRefreshState != RefreshState.RELEASEREFRESH) {
                        currentRefreshState = RefreshState.RELEASEREFRESH;
                        // 提示释放刷新的状态
                        handleRefreshState(currentRefreshState);
                    }
                    params.topMargin = topMargin;
                    headView.setLayoutParams(params);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (handleEventUp(event)){
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean handleEventUp(MotionEvent event) {
        final LayoutParams params = getHeadViewLayoutParams();
        if (currentRefreshState == RefreshState.DOWNREFRESH){
            hideHeadView(params);
        }else if (currentRefreshState == RefreshState.RELEASEREFRESH){
            // 设置正在刷新的状态
            params.topMargin = 0;
            headView.setLayoutParams(params);
            currentRefreshState = RefreshState.REFRESHING;
            handleRefreshState(currentRefreshState);
            if (refreshingListener != null){
                refreshingListener.onRefreshing();
            }
        }
        return params.topMargin >minHeadViewHeight;

    }

    private void hideHeadView(final LayoutParams layoutParams){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.topMargin,minHeadViewHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                layoutParams.topMargin = animatedValue;
                headView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentRefreshState = RefreshState.IDDLE;
                handleRefreshState(RefreshState.IDDLE);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }

    private void handleRefreshState(RefreshState currentRefreshState) {
        switch (currentRefreshState){
            case IDDLE:
                refreshManager.iddle();
                break;
            case REFRESHING:
                refreshManager.refreshing();
                break;
            case DOWNREFRESH:
                refreshManager.downRefresh();
                break;
            case RELEASEREFRESH:
                refreshManager.releaseRefresh();
                break;
            default:
                break;
        }
    }

    private RefreshState currentRefreshState = RefreshState.IDDLE;

    // 定义下拉刷新状态，依次是静止，下拉刷新，释放刷新，正在刷新，刷新结束
    private enum RefreshState{
        IDDLE,DOWNREFRESH,RELEASEREFRESH, REFRESHING,REFRESHOVER
    }

    private LayoutParams getHeadViewLayoutParams(){
        return (LayoutParams) headView.getLayoutParams();
    }

    public interface RefreshingListener{
        void onRefreshing();
    }
    // 设置刷新回调
    public void setRefreshListener(RefreshingListener refreshingListener){
        this.refreshingListener = refreshingListener;

    }
}

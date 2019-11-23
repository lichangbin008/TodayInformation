package com.lcb.todayinformation.main.hangzhou.jike;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.main.tools.SystemUtil;

/**
 * Created by ${lichangbin} on 2019/11/21.
 */

public class LikeClickView extends View {

    private boolean isLike;
    private Bitmap likeBitmap;
    private Bitmap unlikeBitmap;
    private Bitmap shiningBitmap;
    private Paint bitmapPaint;
    private int left;
    private int top;
    private float handScale = 1.0f;
    private int centerX;
    private int centerY;

    public LikeClickView(Context context) {
        this(context, null, 0);
        init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LikeClickView);
//        isLike = typedArray.getBoolean(R.styleable.LikeClickView_is_like, false);
//        typedArray.recycle();
//        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JiKeLikeView);
        isLike = typedArray.getBoolean(R.styleable.JiKeLikeView_is_like, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        Resources resource = getResources();
        likeBitmap = BitmapFactory.decodeResource(resource, R.mipmap.ic_message_like);
        unlikeBitmap = BitmapFactory.decodeResource(resource, R.mipmap.ic_message_unlike);
        shiningBitmap = BitmapFactory.decodeResource(resource, R.mipmap.ic_message_like_shining);
        bitmapPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = 0;
        int measureHeight = 0;
        // 最大宽度，高度
        int maxHeight = unlikeBitmap.getHeight() + SystemUtil.dp2px(getContext(), 20);
        int maxWidth = unlikeBitmap.getWidth() + SystemUtil.dp2px(getContext(), 30);

        // 还可以定义最小宽度，高度

        // 获取当前控件的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) { // 不是精确模式，没有具体数值
            // 测量模式未指定，如果有背景，这个控件就有多大
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight();
            if (suggestedMinimumWidth == 0) { // 没有背景
                measureWidth = maxWidth;
            } else {
                measureWidth = Math.min(suggestedMinimumWidth, maxWidth);
            }

            if (suggestedMinimumHeight == 0) { // 没有背景
                measureHeight = maxHeight;
            } else {
                measureHeight = Math.min(suggestedMinimumHeight, maxHeight);
            }
        } else {
            // 测量模式指定，根据用户定义大小判断
            measureWidth = Math.min(maxWidth, widthSize);
            measureHeight = Math.min(maxHeight, heightSize);
        }
        setMeasuredDimension(measureWidth, measureHeight);
        getPading(measureWidth, measureHeight);
    }

    private void getPading(int measureWidth, int measureHeight) {
        // 居中显示
        int bitmapWidth = likeBitmap.getWidth();
        int bitmapHeight = likeBitmap.getHeight();
        left = (measureWidth - bitmapWidth) / 2;
        top = (measureHeight - bitmapHeight) / 2;

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 避免在onDraw中创建大量对象，防止内存抖动
        super.onDraw(canvas);
        Bitmap handBitmap = isLike ? likeBitmap : unlikeBitmap;

        // 使用canvas的scale以及其他方法，先调用save，然后再调用restore，（这两个方法成对出现）
        canvas.save();
        canvas.scale(handScale, handScale, centerX, centerY);
        canvas.drawBitmap(handBitmap, left, top, bitmapPaint);
        canvas.restore();
        if (isLike) {
            canvas.drawBitmap(shiningBitmap, left + 10, 0, bitmapPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onClick();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void onClick() {
        isLike = !isLike;
//        final ObjectAnimator handScale = ObjectAnimator.ofFloat(this, "handScale", 1.0f, 0.8f, 1.0f);
//        handScale.setDuration(250);
//        handScale.start();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        valueAnimator.setDuration(250);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                handScale = animatedValue;
                invalidate();
            }
        });
    }

    /**
     * 使用ObjectAnimator，系统会自动调用该属性的set方法
     *
     * @param value
     */
    public void setHandScale(float value) {
        this.handScale = value;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        // 当自定义View从界面消失的时候
        super.onDetachedFromWindow();
        likeBitmap.recycle();
        unlikeBitmap.recycle();
        shiningBitmap.recycle();
    }
}

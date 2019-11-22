package com.lcb.todayinformation.main.hangzhou.jike;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.main.tools.SystemUtil;

/**
 * Created by ${lichangbin} on 2019/11/21.
 */

public class JikeClickView extends View {

    private final boolean isLike;
    private Bitmap likeBitmap;
    private Bitmap unlikeBitmap;
    private Bitmap shiningBitmap;
    private Paint bitmapPaint;

    public JikeClickView(Context context) {
        this(context, null, 0);
        init();
    }

    public JikeClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
        init();
    }

    public JikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JikeClickView);
        isLike = typedArray.getBoolean(R.styleable.JikeClickView_is_like, false);
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 避免在onDraw中创建大量对象，防止内存抖动
        super.onDraw(canvas);

        // 居中显示
        int width = getWidth();
        int height = getHeight();
        int bitmapWidth = likeBitmap.getWidth();
        int bitmapHeight = likeBitmap.getHeight();
        int left = (width - bitmapWidth) / 2;
        int top = (height - bitmapHeight) / 2;

        canvas.drawBitmap(likeBitmap, left, top, bitmapPaint);
    }
}

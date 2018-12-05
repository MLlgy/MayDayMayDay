package com.lgy.customclock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomClock extends View {
    private int mWidth;
    private int mHeight;
    private Paint mCirclePaint;
    private Paint mArchPaint;
    private int mRadiu;
    private RectF mRectF;

    public CustomClock(Context context) {
        this(context, null);
    }

    public CustomClock(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.post(new Runnable() {
            @Override
            public void run() {
                mWidth = getMeasuredWidth();
                mHeight = getMeasuredHeight();
                mRadiu = mWidth * 3 / 7;
                mRectF = new RectF(-mRadiu, -mRadiu, mRadiu, mRadiu);
            }
        });
        mArchPaint = new Paint();
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStrokeWidth(2.0f);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * 3 / 7, mCirclePaint);
//        canvas.drawRect(mRectF, mCirclePaint);
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.drawArc(mRectF, 5, 85, false, mCirclePaint);

        for (int i = 0; i < 4; i++) {
            canvas.drawArc(mRectF, 5, 80, false, mCirclePaint);
            canvas.rotate(90f);
            String mNum = String.valueOf(3 * (i + 1));
        }

    }
}

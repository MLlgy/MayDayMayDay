package com.lgy.customclock.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lgy.customclock.R;

public class CustomClock extends View {
    private int mWidth;
    private int mHeight;
    private Paint mCirclePaint;
    private Paint mArchPaint;
    private int mRadiu;
    private RectF mRectF;
    private Paint mTextPaint;
    private Paint mLinePaint;

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

    @TargetApi(Build.VERSION_CODES.M)
    private void init() {
        this.post(new Runnable() {
            @Override
            public void run() {
                mWidth = getMeasuredWidth();
                mHeight = getMeasuredHeight();
                mRadiu = mWidth * 3 / 7;
//                mRectF = new RectF(-mRadiu, -mRadiu, mRadiu, mRadiu);
                mRectF = new RectF(mWidth / 14, mHeight / 2 - mRadiu,
                        mRadiu * 2 + mWidth / 14, mHeight / 2 + mRadiu);
            }
        });
        mCirclePaint = new Paint();
        mArchPaint = new Paint();
        mArchPaint.setColor(Color.WHITE);
        mArchPaint.setStrokeWidth(2.0f);
        mArchPaint.setAntiAlias(true);
        mArchPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(50f);
        mTextPaint.setColor(getResources().getColor(R.color.colorWhite, null));

        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.colorGray, null));
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(6f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mArchPaint);
//        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mArchPaint);
//        canvas.translate(mWidth / 2, mHeight / 2);

//        for (int i = 0; i < 4; i++) {
//            canvas.drawArc(mRectF, 5, 80, false, mArchPaint);
//            canvas.rotate(90f, mWidth / 2, mHeight / 2);
//            String mNum = String.valueOf(3 * (i + 1));
//            canvas.drawText(mNum, mWidth / 2 - mTextPaint.measureText(mNum) / 2, mHeight / 2 - mRadiu + 20, mTextPaint);
//        }
        canvas.rotate(90, mWidth / 2, mHeight / 2);
        for (int i = 0; i < 60; i++) {
            if (i == 0 || i == 15 || i == 30 || i == 45) {
                canvas.drawArc(mRectF, 5, 80, false, mArchPaint);
                String mNum = String.valueOf((i / 15 + 1) * 3);
                canvas.drawText(mNum, mWidth / 2 - mTextPaint.measureText(mNum) / 2, mHeight / 2 - mRadiu + 20, mTextPaint);

            } else {
                canvas.drawLine(mWidth / 2, mHeight / 2 - mRadiu + 40, mWidth / 2, mHeight / 2 - mRadiu + 90, mLinePaint);
            }
            canvas.rotate(6, mWidth / 2, mHeight / 2);
        }
        canvas.save();
    }
}

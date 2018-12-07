package com.lgy.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.lgy.customview.R;


public class CustomView extends AppCompatTextView {
    private static final String TAG = "CustomView";
    private Paint mPaint;
    private Rect mRect;
    private int mBaseLine;
    private float mTop;
    private float mBottom;
    private Paint mLinePaint;
    private String mShowText;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        mShowText = mTypedArray.getString(R.styleable.CircleView_textShow);
        mTypedArray.recycle();
        mPaint = new Paint();
        mRect = new Rect(100, 100, 500, 100);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(50);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt mFontMetricsInt = mPaint.getFontMetricsInt();
        mTop = mFontMetricsInt.top;
        mBottom = mFontMetricsInt.bottom;
        mLinePaint = new Paint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.d(TAG, "onMeasure: widthSize " + MeasureSpec.getSize(widthMeasureSpec));
        Log.d(TAG, "onMeasure: widthMode " + MeasureSpec.getMode(widthMeasureSpec));
        Log.d(TAG, "onMeasure: heightSize " + MeasureSpec.getSize(heightMeasureSpec));
        Log.d(TAG, "onMeasure: heightMode " + MeasureSpec.getMode(heightMeasureSpec));
        Log.d(TAG, "onMeasure: Mode " + MeasureSpec.AT_MOST);
        Log.d(TAG, "onMeasure: Mode " + MeasureSpec.EXACTLY);
        Log.d(TAG, "onMeasure: Mode " + MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getCustomSize(widthMeasureSpec, "width");
        int height = getCustomSize( heightMeasureSpec, "height");
        int result = width > height ? height : width;
        mBaseLine = (int) (mRect.centerY() - mTop / 2 - mBottom / 2);
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mShowText, mRect.centerX(), mRect.centerY(), mPaint);
        mLinePaint.setColor(Color.BLACK);
        canvas.drawLine(mRect.left,mBaseLine,mRect.right,mBaseLine,mLinePaint);

    }

    private int getCustomSize(int measureSpec, String str) {
        int measureSize = MeasureSpec.getSize(measureSpec);
        int measureMode = MeasureSpec.getMode(measureSpec);
        Log.d(TAG, "getCustomSize: " + str + " " + measureSize);
        int defaultWidth = 100;
        int backSize = 0;
        switch (measureMode) {
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "getCustomSize: 1");
                backSize = measureSize;
                break;
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "getCustomSize: 2");
                backSize = measureSize > defaultWidth ? measureSize : defaultWidth;

                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "getCustomSize: 3");
                backSize = defaultWidth;
                break;
        }
        Log.d(TAG, "getCustomSize: result" + str + " " + backSize);

        return backSize;
    }
}

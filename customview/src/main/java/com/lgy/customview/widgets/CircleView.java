package com.lgy.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lgy.customview.R;


public class CircleView extends View {
    private final int COLOR_RED = Color.BLACK;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mWidth;
    private int mHeigth;
    private int mRadiu;
    private int mColor;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {

        this.post(new Runnable() {
            @Override
            public void run() {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                int paddingRight = getPaddingRight();
                int paddingBottom = getPaddingBottom();
                mWidth = getWidth() - paddingLeft - paddingRight;
                mHeigth = getHeight()- paddingBottom - paddingTop;
                mRadiu = Math.min(mWidth, mHeigth) / 2;
            }
        });

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = mTypedArray.getColor(R.styleable.CircleView_circle_color,Color.BLACK);
        mTypedArray.recycle();
        mPaint.setColor(mColor);
//        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2+ getPaddingLeft(),  mHeigth / 2 + getPaddingTop(), mRadiu, mPaint);
        canvas.drawLine(0,mWidth,mWidth,mWidth,mPaint);

    }
}

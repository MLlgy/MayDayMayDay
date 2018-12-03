package com.lgy.customview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

    private static final String TAG = "MyViewGroup";

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);// 这句话很重要，开启测量子 view

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(heightMeasureSpec);
        int mChildCount = getChildCount();
        if (mChildCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxWidth(mChildCount), getTotalHeight(mChildCount));
            } else if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, getTotalHeight(mChildCount));
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMeasuredWidth(), heightSize);
            } else {
                setMeasuredDimension(widthSize, heightSize);
            }
        }
    }

    private int getTotalHeight(int mChildCount) {
        int mTotalHeight = 0;
        for (int i = 0; i < mChildCount; i++) {
            View mView = getChildAt(i);
            mTotalHeight += mView.getMeasuredHeight();
        }
        return mTotalHeight;
    }

    private int getMaxWidth(int mChildCount) {
        int mMaxWidth = 0;
        for (int i = 0; i < mChildCount; i++) {
            View mView = getChildAt(i);
            if (mView.getMeasuredWidth() > mMaxWidth) {
                mMaxWidth = mView.getMeasuredWidth();
            }
        }
        return mMaxWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mChildCount = getChildCount();
        int mHeight = 0;
        for (int i = 0; i < mChildCount; i++) {
            View mView = getChildAt(i);
            int width = mView.getMeasuredWidth();
            int height = mView.getMeasuredHeight();
            mView.layout(0, mHeight, l + width, mHeight + height);
            mHeight += height;
        }
    }
}

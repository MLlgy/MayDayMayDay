package com.lgy.customclock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CavansTestView extends View {
    private Paint mPaint;
    private Paint mPaint2;

    public CavansTestView(Context context) {
        this(context, null);
    }

    public CavansTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CavansTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint2 = new Paint();
        mPaint.setColor(Color.argb(255, 88, 110, 90));
        mPaint2.setColor(Color.argb(255, 88, 110, 255));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 100, 100, mPaint);
        int mSave1 = canvas.save();//0,0

        canvas.translate(200, 200);
        canvas.drawRect(0, 0, 100, 100, mPaint);
        int mSave2 = canvas.save();//200,200

        canvas.translate(200, 200);
        canvas.drawRect(0, 0, 100, 100, mPaint);
        int mSave3 = canvas.save();//400,400
//        canvas.scale(2,2);
//        canvas.restoreToCount(mSave2);
        canvas.drawRect(200, 0, 300, 100, mPaint);

        canvas.restoreToCount(mSave2);
        mPaint2.setColor(Color.argb(144,90,87,204));
        canvas.drawRect(200, 0, 300, 100, mPaint2);


    }
}

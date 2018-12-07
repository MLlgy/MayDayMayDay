package com.lgy.customclock.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lgy.customclock.R;

import java.util.Calendar;

public class CustomClock extends View {
    private int mWidth;
    private int mHeight;
    // 圆心画笔
    private Paint mCirclePaint;
    // 四弧画笔
    private Paint mArchPaint;
    private int mRadiu;
    private RectF mRectF;
    // 字体画笔
    private Paint mTextPaint;
    //刻度画笔
    private Paint mLinePaint;
    // 测量小时文本宽高的矩形
    private Rect mTextRect = new Rect();

    // 刻度以及相关
    private RectF mShadowRectF;
    private Paint mShadowPaint;
    private Paint mShadowLinePaint;
    private float mShadowWidth;
    private Matrix mShadowMatrix = new Matrix();
    private SweepGradient mShadowGradient;

    // 时针
    private Path mHourPath = new Path();
    // 分针
    private Path mMiuPath = new Path();
    // 秒针
    private Path mSecPath = new Path();
    private float mHourDegree = 0;
    private float mMiuDegree = 0;
    private float mSecDegree = 0;

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
        /**
         * 圆心画笔
         */
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStrokeWidth(5f);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        /**
         * 四弧画笔
         */
        mArchPaint = new Paint();
        mArchPaint.setColor(Color.WHITE);
        mArchPaint.setStrokeWidth(2.0f);
        mArchPaint.setAntiAlias(true);
        mArchPaint.setStyle(Paint.Style.STROKE);

        /**
         * 刻度文字的画笔
         */
        mTextPaint = new Paint();
        mTextPaint.setTextSize(50f);
        mTextPaint.setColor(getResources().getColor(R.color.colorWhite, null));

        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.colorGray, null));
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(6f);

        mShadowPaint = new Paint();
        mShadowPaint.setStyle(Paint.Style.STROKE);
        mShadowPaint.setColor(Color.GRAY);

        mShadowLinePaint = new Paint();
        mShadowLinePaint.setColor(Color.WHITE);
        mShadowLinePaint.setStyle(Paint.Style.STROKE);
        mShadowLinePaint.setStrokeWidth(mShadowWidth * 0.02f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadiu = Math.min(mWidth, mHeight) * 3 / 7;
        mRectF = new RectF(mWidth / 2 - mRadiu, mHeight / 2 - mRadiu,
                mRadiu + mWidth / 2, mHeight / 2 + mRadiu);
        mShadowWidth = 0.12f * mRadiu;
        mShadowPaint.setStrokeWidth(mShadowWidth);

        mShadowRectF = new RectF(mWidth / 2 - mRadiu + mShadowWidth * 1.5f,
                mHeight / 2 - mRadiu + mShadowWidth * 1.5f,
                mRadiu + mWidth / 2 - mShadowWidth * 1.5f,
                mHeight / 2 + mRadiu - mShadowWidth * 1.5f);
        mShadowGradient = new SweepGradient(mWidth / 2, mHeight / 2,
                new int[]{Color.parseColor("#80ffffff"), Color.parseColor("#ffffff")}, new float[]{0.75f, 1});

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRectF, mArchPaint);
        getCurrentTime();
        //辅助线
//        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mArchPaint);
//        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mArchPaint);
        drawArcText(canvas);
        drawShaderScale(canvas);
        drawHourScale(canvas);
        drawMiuScale(canvas);
        drawSecScale(canvas);
        invalidate();
    }

    private void getCurrentTime() {
        Calendar mCalendar = Calendar.getInstance();
        /**
         * 获取精确的时间
         */
        float milliSecond = mCalendar.get(Calendar.MILLISECOND);
        float second = mCalendar.get(Calendar.SECOND) + milliSecond / 1000;
        float minute = mCalendar.get(Calendar.MINUTE) + second / 60;
        float hour = mCalendar.get(Calendar.HOUR) + minute / 60;
        mHourDegree = hour / 12 * 360;
        mMiuDegree = minute / 60 * 360;
        mSecDegree = second / 60 * 360;
    }

    private void drawArcText(Canvas canvas) {
        canvas.save();
        String[] mStrings = new String[]{"12", "3", "6", "9"};
        // drawText 是以文字的做下角为基准，这个很重要
        mTextPaint.getTextBounds(mStrings[1], 0, mStrings[1].length(), mTextRect);
        canvas.drawText(mStrings[0], mWidth / 2 - mTextRect.width() / 2 * 3, mRectF.top + mTextRect.height() / 2, mTextPaint);
        canvas.drawText(mStrings[1], mRectF.right - mTextRect.width() / 2, mHeight / 2 + mTextRect.height() / 2, mTextPaint);
        canvas.drawText(mStrings[2], mWidth / 2 - mTextRect.width() / 2, mRectF.bottom + mTextRect.height() / 2, mTextPaint);
        canvas.drawText(mStrings[3], mRectF.left - mTextRect.width() / 2, mHeight / 2 + mTextRect.height() / 2, mTextPaint);
        for (int i = 0; i < 4; i++) {
            canvas.drawArc(mRectF, 5 + 90 * i, 80, false, mArchPaint);
        }
    }

    private void drawShaderScale(Canvas canvas) {
        canvas.save();
        mShadowMatrix.setRotate(mSecDegree - 90, mWidth / 2, mHeight / 2);
        mShadowGradient.setLocalMatrix(mShadowMatrix);
        mShadowPaint.setShader(mShadowGradient);
        canvas.drawArc(mShadowRectF, 0, 360, false, mShadowPaint);
        /**
         * 此处注意线宽的问题，具体查看有道云笔记
         */
        for (int i = 0; i < 360; i++) {
            canvas.drawLine(mWidth / 2,
                    mHeight / 2 - mRadiu + mShadowWidth * 1.5f - 0.5f * mShadowWidth,
                    mWidth / 2,
                    mHeight / 2 - mRadiu + mShadowWidth * 1.5f + mShadowWidth - 0.5f * mShadowWidth, mShadowLinePaint);
            canvas.rotate(1, mWidth / 2, mHeight / 2);
        }
        canvas.restore();
    }

    private void drawHourScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(mHourDegree, mWidth / 2, mHeight / 2);
        mHourPath.reset();
        mHourPath.moveTo(mWidth / 2 - 0.02f * mRadiu, mHeight / 2 - 0.02f * mRadiu);
        mHourPath.lineTo(mWidth / 2 - 0.008f * mRadiu, mHeight / 2 - mRadiu + mShadowWidth * 5.5f);
        mHourPath.quadTo(mWidth / 2, mHeight / 2 - mRadiu + mShadowWidth * 4.8f,
                mWidth / 2 + 0.008f * mRadiu, mHeight / 2 - mRadiu + mShadowWidth * 5.5f);
        mHourPath.lineTo(mWidth / 2 + 0.02f * mRadiu, mHeight / 2 - 0.02f * mRadiu);
        canvas.drawPath(mHourPath, mLinePaint);
        mHourPath.close();
        canvas.restore();
    }

    private void drawMiuScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(mMiuDegree, mWidth / 2, mHeight / 2);
        mMiuPath.reset();
        mMiuPath.moveTo(mWidth / 2 - 0.012f * mRadiu, mHeight / 2 - 0.02f * mRadiu);
        mMiuPath.lineTo(mWidth / 2 - 0.008f * mRadiu, mHeight / 2 - mRadiu + mShadowWidth * 4.8f);
        mMiuPath.quadTo(mWidth / 2, mHeight / 2 - mRadiu + mShadowWidth * 4.5f,
                mWidth / 2 + 0.008f * mRadiu, mHeight / 2 - mRadiu + mShadowWidth * 4.8f);
        mMiuPath.lineTo(mWidth / 2 + 0.012f * mRadiu, mHeight / 2 - 0.02f * mRadiu);
        canvas.drawPath(mMiuPath, mLinePaint);
        mMiuPath.close();
        canvas.restore();
        canvas.drawCircle(mWidth / 2, mHeight / 2, 12, mCirclePaint);
    }

    private void drawSecScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(mSecDegree, mWidth / 2, mHeight / 2);
        mSecPath.reset();
        mSecPath.moveTo(mWidth / 2, mHeight / 2 - mRadiu + mShadowWidth * 2.1f);
        mSecPath.lineTo(mWidth / 2 + 0.04f * mRadiu, mHeight / 2 - mRadiu + mShadowWidth * 2.1f + mRadiu * 0.068f);
        mSecPath.lineTo(mWidth / 2 - 0.04f * mRadiu, mHeight / 2 - mRadiu + mShadowWidth * 2.1f + mRadiu * 0.068f);
        mSecPath.lineTo(mWidth / 2, mHeight / 2 - mRadiu + mShadowWidth * 2.1f);
        canvas.drawPath(mSecPath, mLinePaint);
        mSecPath.close();
        canvas.restore();
    }


}

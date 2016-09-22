package com.example.bj.superdemo.ui.customview.subject.gesturelock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.bj.superdemo.R;

/**
 * Created by bj on 2016/9/21.
 * description：手势锁屏界面的子视图，就是小圆圈
 */
public class GestureLockView extends View {
    /**
     * 正常状态的颜色
     */
    private int mNormalColor = getResources().getColor(R.color.white);
    /**
     * 按压下去时候的颜色
     */
    private int mTouchColor = getResources().getColor(R.color.palegreen);
    /**
     * 不对的颜色
     */
    private int mWrongColor = getResources().getColor(R.color.red);
    private Paint mPaint;
    private int mRaius;
    /**
     * 圆心坐标x
     */
    private int mRaX;
    /**
     * 圆心坐标y
     */
    private int mRaY;

    enum Mode {
        STATUS_NO_FINGER, STATUS_FINGER_ON, STATUS_FINGER_UP;
    }

    private Mode mCurrentMode = Mode.STATUS_NO_FINGER;

    public GestureLockView(Context context) {
        super(context);
        initData(context);
    }

    public GestureLockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public GestureLockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = mWidth < mHeight ? mWidth : mHeight;
        mRaX = mWidth / 2;
        mRaY = mWidth / 2;
        mRaius = mRaX / 4;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mCurrentMode) {
            case STATUS_NO_FINGER:
                mPaint.setColor(mNormalColor);
                canvas.drawCircle(mRaX, mRaY, mRaius, mPaint);
                break;
            case STATUS_FINGER_ON:
                mPaint.setColor(mTouchColor);
                canvas.drawCircle(mRaX, mRaY, mRaius, mPaint);
                canvas.drawCircle(mRaX, mRaY, mRaX - 4, mPaint);
                break;
            case STATUS_FINGER_UP:
                mPaint.setColor(mWrongColor);
                canvas.drawCircle(mRaX, mRaY, mRaius, mPaint);
                canvas.drawCircle(mRaX, mRaY, mRaX - 4, mPaint);
                break;
        }

    }

    private void initData(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }

    public void setMode(Mode mode) {
        this.mCurrentMode = mode;
        invalidate();
    }

}

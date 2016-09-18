package com.example.bj.superdemo.ui.customview.subject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by bj on 2016/9/13.
 * description：画一个圆形的进度指示器demo,并且在指示器的中央添加一个数字指示器
 */
public class CustomViewDrawCircle extends View {
    private Paint mPaint;
    private RectF mRectF;
    private RectF mRectF2;
    private Paint mPaint2;
    private int mCount = 0;
    /**
     * 当前的进度
     */
    private int mProgress = 0;
    /**
     * 时候下一圈的刷新
     */
    private boolean isNext;
    /**
     * 不然线程跑的标志
     */
    private boolean isRun = true;

    public CustomViewDrawCircle(Context context) {
        super(context);
        initData();
    }

    public CustomViewDrawCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public CustomViewDrawCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    /**
     * 设置线程是否停止的标志
     *
     * @param isRun
     */
    public void setDrop(boolean isRun) {
        this.isRun = isRun;
    }

    private void initData() {
        mPaint2 = new Paint();
        mPaint = new Paint();
//        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        //空心
        mPaint.setStyle(Paint.Style.STROKE);
        //设置圆环的宽度
        mPaint.setStrokeWidth(20);
        mPaint2.setColor(Color.GREEN);
        mPaint2.setTextSize(40);
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (isRun) {
                    mProgress++;
                    mCount = (int) (mProgress / 3.6);
                    if (mProgress == 360) {
                        mProgress = 0;
                        isNext = true;
                    } else {
                        isNext = false;
                    }
                    //不断的刷新界面
                    postInvalidate();
                    SystemClock.sleep(100);
                }
            }
        }).start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
////        //画一个圆
////        //抗锯齿
//        mPaint.setAntiAlias(true);
////        canvas.drawText("画圆：", 100, 200, mPaint);// 画文本
//        canvas.drawCircle(600, 200, 100, mPaint);// 小圆
//        //画一个
//        // mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawOval(600, 600, 900, 800, mPaint);
//        mRectF = new RectF();
//        mRectF.set(80.0f, 80.0f, 500.0f, 500.0f);
//        mPaint.setStrokeWidth(20);
//        canvas.drawArc(mRectF, 150.0f, 140.0f, true, mPaint);////
//
//        mPaint2 = new Paint();
//        mPaint2.setAntiAlias(true);
//        mPaint2.setColor(Color.BLUE);
//        canvas.drawArc(mRectF, 180.0f, 90.0f, true, mPaint2);

//        System.out.println(getMeasuredHeight() + "onDrawmeasureHeight");
//        System.out.println(getHeight() + "onDrawgetHeight");
        int X = getWidth() / 2; // 获取圆心的x坐标
        int Y = getHeight() / 2;
        int radius = 300 / 2;// 半径
        mRectF = new RectF();
        mRectF2 = new RectF();
//        mPaint2.getTextBounds(String.valueOf(mCount), 0, String.valueOf(mCount).length(), mRectF2);
        canvas.drawText(String.valueOf(mCount), 0, String.valueOf(mCount).length(), X, Y, mPaint2);
        mRectF.set(X - radius, Y - radius, X + radius, Y + radius);
        if (isNext) {
            mPaint.setColor(Color.BLUE);
            canvas.drawCircle(X, Y, radius, mPaint);
            mPaint.setColor(Color.RED);
            canvas.drawArc(mRectF, -90, mProgress, false, mPaint);
        } else {
            mPaint.setColor(Color.RED);
            canvas.drawCircle(X, Y, radius, mPaint);
            mPaint.setColor(Color.BLUE);
            canvas.drawArc(mRectF, -90, mProgress, false, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        System.out.println(getMeasuredHeight() + "onMeasuremeasureHeight");
//        System.out.println(getHeight() + "onMeasuregetHeight");
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
//        if (View.INVISIBLE == visibility) {
//            //界面不可见的时候线程停止运行
//            isRun = false;
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isRun) {
                    isRun = false;
                    Toast.makeText(getContext(), "线程关闭", Toast.LENGTH_SHORT).show();
                } else {
                    isRun = true;
//                    Toast.makeText(getContext(), "线程开启", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onTouchEvent(event);

    }
}

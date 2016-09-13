package com.example.bj.superdemo.ui.customview.subject;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bj on 2016/9/13.
 * description：画一个圆形的进度指示器demo
 */
public class CustomViewDrawCircle extends View {
    private Paint mPaint;
    private RectF mRectF;

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

    private void initData() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //画一个圆
//        //抗锯齿
        mPaint.setAntiAlias(true);
//        canvas.drawText("画圆：", 100, 200, mPaint);// 画文本
        canvas.drawCircle(600, 200, 100, mPaint);// 小圆
        //画一个
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(600, 600, 900, 800, mPaint);
        mRectF = new RectF();
        mRectF.set(80.0f, 80.0f, 500.0f, 500.0f);
        canvas.drawArc(mRectF, 150.0f, 140.0f, true, mPaint);//// TODO: 2016/9/13  画一个饼状图 

    }
}

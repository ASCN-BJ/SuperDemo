package com.example.bj.superdemo.ui.customview.subject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bj on 2016/9/20.
 * description：画形状以及对 canvas的save和restore的使用
 */
public class CustomViewDrawShape extends View {
    private Paint mPaint;
    private Path mPath;

    public CustomViewDrawShape(Context context) {
        super(context);
        initData(context);
    }

    public CustomViewDrawShape(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public CustomViewDrawShape(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    /**
     * 初始化数据
     *
     * @param context
     */
    private void initData(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStyle(Paint.Style.STROKE);//采用画笔的样式
        mPaint.setStrokeWidth(40);
        mPath = new Path();
        mPaint.setStrokeCap(Paint.Cap.ROUND);//让画笔画的更圆滑
        mPaint.setStrokeJoin(Paint.Join.ROUND);//让画笔画的更圆滑
//        mPath.setFillType(Path.FillType.);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int xPoint = getMeasuredWidth();
//        int yPoint = getMeasuredHeight();
//        mPaint.setColor(Color.WHITE);
//        canvas.drawRect(0, 0, xPoint, yPoint, mPaint);
//        mPaint.setColor(Color.BLUE);
////        canvas.save();//保存画板之前的状态
//        canvas.rotate(-90, xPoint / 2, yPoint / 2);
////        canvas.rotate(-60, xPoint / 2, yPoint / 2);
//        canvas.drawLine(xPoint / 2, yPoint / 2, xPoint, yPoint / 2, mPaint);
////        canvas.drawLine(xPoint, yPoint / 2, xPoint / 2, yPoint, mPaint);
//        System.out.println(xPoint + "---" + yPoint);
////        canvas.restore();//恢复到画板之前的状态
//        canvas.drawCircle(xPoint / 2-110, yPoint / 2, 100, mPaint);
//        canvas.drawPath(mPath, mPaint);
        //随着手指移动的直线
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("getY()" + event.getY());
        System.out.println("getRawY()" + event.getRawY());
        System.out.println("getLeft" + getLeft());
        System.out.println("getRight" + getRight());
        System.out.println("getBottom" + getBottom());
        System.out.println("getTop" + getTop());
//        int[] ints = new int[2];
//        getLocationOnScreen(ints);
//        System.out.println("getLocationX" + ints[0]);
//        System.out.println("getLocationX" + ints[0]);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
//                mPath.moveTo(x, y);
                mPath.lineTo(x + 10, y + 10);
//                mPath.lineTo();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
//                mPath.reset();
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mPaint != null) {
            canvas.drawPath(mPath, mPaint);
        }

        super.dispatchDraw(canvas);
    }
}

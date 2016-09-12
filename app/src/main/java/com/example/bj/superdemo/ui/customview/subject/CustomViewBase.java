package com.example.bj.superdemo.ui.customview.subject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.bj.superdemo.R;

/**
 * Created by bj on 2016/9/12.
 * description：自定义View初实战
 * 矩形中画一个字符串
 */
public class CustomViewBase extends View {
    private String mTextTitle;
    private int mTextColor;
    private int mTextSize;

    private int defaultColor = Color.BLACK;
    private int defaultSize = 100;

    private Paint mPaint;
    private Rect mRect;

    public CustomViewBase(Context context) {
        super(context);
        initData(context, null);
    }

    public CustomViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public CustomViewBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //一个矩形区域
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        //画文本
        mPaint.setColor(mTextColor);
        canvas.drawText(mTextTitle, getWidth() / 2 - mRect.width() / 2, getHeight() / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        //MeasureSpec MODE = EXACTLY_ATMOST_UNSPECIED
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mTextTitle, 0, mTextTitle.length(), mRect);
            float textWidth = mRect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mTextTitle, 0, mTextTitle.length(), mRect);
            float textWidth = mRect.height();
            int desired = (int) (getPaddingBottom() + textWidth + getPaddingTop());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    //初始化数据
    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewBase);
//        int count = typedArray.getIndexCount();
//        for (int x = 0; x < count; x++) {
//            int attr = typedArray.getIndex(x);
//            switch (attr) {
//                case R.attr.TitleTextColor:
//                    mTextColor = typedArray.getColor(attr, defaultColor);
//                    break;
//                case R.attr.TitleText:
//                    mTextTitle = typedArray.getString(attr);
//                    break;
//                case R.attr.TitleTextSize:
//                    mTextSize = typedArray.getDimensionPixelSize(attr, defaultSize);
//                    break;
//            }
//        }
        mTextColor = typedArray.getColor(R.styleable.CustomViewBase_TitleTextColor, defaultColor);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomViewBase_TitleTextSize, defaultSize);
        mTextTitle = typedArray.getString(R.styleable.CustomViewBase_TitleText);
        //处理attr的中读取的数据
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        //mPaint.setColor(mTextColor);
        mRect = new Rect();
        mPaint.getTextBounds(mTextTitle, 0, mTextTitle.length(), mRect);
    }


}

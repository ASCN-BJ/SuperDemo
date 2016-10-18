package com.example.bj.superdemo.ui.customview.common;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bj on 2016/10/18.
 * description：
 */
public class MyRecycleItemDecoder extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private ViewGroup.LayoutParams mLayoutParams;
    private View mChild;
    private int mStartX;
    private int mEndX;

    private int mStartY;
    private int mEndY;


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        drawHorizontal(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {

        for (int x = 0; x < parent.getChildCount(); x++) {
            mChild = parent.getChildAt(x);
            mLayoutParams = mChild.getLayoutParams();
            mStartX = mChild.getLeft();
            mEndX = mChild.getRight();
            mStartY = mChild.getPaddingTop();
            mEndX = mChild.getPaddingBottom();
            c.drawLine(mStartX, mStartY, mEndX, mEndY, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, 0);
    }
}

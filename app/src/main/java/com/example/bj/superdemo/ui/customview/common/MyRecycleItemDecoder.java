package com.example.bj.superdemo.ui.customview.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.bj.superdemo.R;

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
    private Drawable mDivider;

    public MyRecycleItemDecoder(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.basic_divider_recycle_view);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        mPaint = new Paint();
//        mPaint.setColor(Color.BLUE);
//        mPaint.setStrokeWidth(10);
//        mPaint.setStyle(Paint.Style.STROKE);

        drawVerticalLine(c, parent);
        drawHorLine(c, parent);
    }


    private void drawHorLine(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int x = 0; x < childCount; x++) {
            final View childView = parent.getChildAt(x);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView
                    .getLayoutParams();
            final int top = childView.getBottom() + params.topMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    private void drawVerticalLine(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

//        for (int x = 0; x < parent.getChildCount(); x++) {
//            mChild = parent.getChildAt(x);
//            mLayoutParams = mChild.getLayoutParams();
//            mStartX = mChild.getLeft();
//            mEndX = mChild.getRight();
//            mStartY = mChild.getPaddingTop();
//            mEndX = mChild.getPaddingBottom();
//            c.drawLine(mStartX, mStartY, mEndX, mEndY, mPaint);
//        }
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);

    }
}

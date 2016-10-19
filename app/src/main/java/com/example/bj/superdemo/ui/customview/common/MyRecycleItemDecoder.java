package com.example.bj.superdemo.ui.customview.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

    private int getSpanCount(RecyclerView parent) {
        int spanCount = 0;
        RecyclerView.LayoutManager mAnager = parent.getLayoutManager();
        if (mAnager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) mAnager).getSpanCount();
        } else if (mAnager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) mAnager).getSpanCount();
        }
        return spanCount;
    }

    /**
     * 是否是最后一行
     *
     * @return
     */
    private boolean isLastRaw(RecyclerView parent, int position) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int rawCount = 0;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            //算多少行
            childCount = childCount - childCount % spanCount;
            if ((1 + position) >= childCount) {
                return true;
            }
        } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) parent.getLayoutManager())
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (position >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((position + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

//    private int rawCount(int childCount, int spanCount) {
//        int rawCount = 0;
//        if (childCount % spanCount == 0) {
//            rawCount = childCount / spanCount;
//        } else {
//            rawCount = childCount / spanCount + 1;
//        }
//        return rawCount;
//    }

    /**
     * 是否是最后一列
     *
     * @return
     */
    private boolean isLastColumn(RecyclerView parent, int position) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            if ((position + 1) % spanCount == 0) {
                return true;
            }
        } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) parent.getLayoutManager())
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((position + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (position >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
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

        if (isLastColumn(parent, ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition())) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else if (isLastRaw(parent, ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition())) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
//        System.out.println("outRect-->"+outRect+"itemPosition-->"+itemPosition+"parent-->"+parent);
    }
}

package com.example.bj.superdemo.ui.customview.subject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by bj on 2016/9/19.
 * description：一个竖直滑动的LinearLayout布局
 * ScrollBy或者scrollTo移动的时候是瞬移的所以需要Scroller这个类来做一下缓冲
 */
public class VerticalLinearLayout extends ViewGroup {
    private int mLayoutHeight;
    private int mScreenHeight;
    private Scroller mScroller;
    private boolean mIsScrolling;

    private int mScrollyStart;
    private int mScrollyEnd;
    private int mFirstY;
    //手指滑动的速度
    private int mV;
    private VelocityTracker mVelocityTracker;

    public VerticalLinearLayout(Context context) {
        super(context);
        initData(context);
    }

    public VerticalLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public VerticalLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    //初始化数据
    private void initData(Context context) {
        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
        mScroller = new Scroller(context);
    }

    //根据滑动的距离和速度进行判断
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIsScrolling) {
            return super.onTouchEvent(event);
        }
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mScrollyStart = getScrollY();
                mFirstY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                obtainVelocity(event);
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int scrolly = getScrollY();
                int dy = y - mFirstY;
                //上拉页面的动作
//                if (dy < 0 && scrolly >= 0) {
//                    scrollBy(0, -scrolly);
//                }
//                if (dy > 0 && scrolly >= 0) {
//                    scrollBy(0, getHeight() - scrolly - mScreenHeight);
//                }
// 已经到达顶端，下拉多少，就往上滚动多少
                if (dy < 0 && scrolly + dy <= 0) {
//                    System.out.println("scrolly" + scrolly + "dy" + dy + "dy+scrolly" + (dy + scrolly));
                    dy = -scrolly;
                }
                // 已经到达底部，上拉多少，就往下滚动多少
                if (dy > 0 && scrolly + dy > getHeight() - mScreenHeight) {
                    dy = getHeight() - mScreenHeight - scrolly;
                }
                scrollBy(0, dy);
                mFirstY = y;
                break;
            case MotionEvent.ACTION_UP:

                mScrollyEnd = getScrollY();
                int dScrollY = mScrollyEnd - mScrollyStart;
                if (wantScrollToNext()) {
                    if (shouldScrollToNext()) {
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);

                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }

                }
                if (wantScrollToPre()) {
                    if (shouldScrollToPre()) {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);

                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                }
                mIsScrolling = true;
                postInvalidate();
                recycleVelocity();
                break;
        }
        return true;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        //对子布局进行测量
        for (int x = 0; x < count; x++) {
            measureChild(getChildAt(x), widthMeasureSpec, mScreenHeight);
        }
    }

    private void obtainVelocity(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocity() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //设置布局的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * getChildCount();
        setLayoutParams(mlp);
        //设置子布局的值域
        if (changed) {
            for (int x = 0; x < getChildCount(); x++) {
                getChildAt(x).layout(l, x * mScreenHeight, r, (x + 1) * mScreenHeight);
            }
        }
    }


    /**
     * 根据滚动距离判断是否能够滚动到下一页
     *
     * @return
     */
    private boolean shouldScrollToNext() {
        return mScrollyEnd - mScrollyStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到下一页
     *
     * @return
     */
    private boolean wantScrollToNext() {
        return mScrollyEnd > mScrollyStart;
    }

    /**
     * 根据滚动距离判断是否能够滚动到上一页
     *
     * @return
     */
    private boolean shouldScrollToPre() {
        return -mScrollyEnd + mScrollyStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到上一页
     *
     * @return
     */
    private boolean wantScrollToPre() {
        return mScrollyEnd < mScrollyStart;
    }

    private int getVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);//初始化函数
        return (int) mVelocityTracker.getYVelocity();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {//if it returns true,the animation is not yet finished.
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        } else {
            mIsScrolling = false;
        }
    }
}

package com.example.bj.superdemo.ui.customview.gallery_picture;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;


/**
 * Created by bj on 2016/10/8.
 * description： 一个用于展示图片的画廊效果
 */
public class GalleryRecycleView extends RecyclerView {
    private OnRecycleChangeListner mListner;
    private View mCurrentView;
    private VelocityTracker mTracker;

    public GalleryRecycleView(Context context) {
        super(context);
    }

    public GalleryRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GalleryRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnRecycleChangeListner(OnRecycleChangeListner mListner) {
        this.mListner = mListner;
    }

    public interface OnRecycleChangeListner {
        void change(View view, int i);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(0);
        if (mListner != null) {
            mListner.change(mCurrentView,
                    getChildPosition(mCurrentView));
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                System.out.println("");
////                if (mTracker == null) {
////                    mTracker = VelocityTracker.obtain();
////                } else {
////                    mTracker.clear();//重新初始化一下数据
////                }
////                mTracker.addMovement(e);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mTracker == null) {
//                    mTracker = VelocityTracker.obtain();
//                } else {
//                    mTracker.clear();//重新初始化一下数据
//                }
//                mTracker.addMovement(e);
//                mTracker.computeCurrentVelocity(1000);
//                if (mTracker.getYVelocity() > 300) {
//                    //滚动展示
////                    setView();
//                }
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                mTracker.recycle();
//                break;
//            default:
//                break;
//        }
////        e.recycle();
//        return false;
//    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        setView();
    }

    private void setView() {
        View newView = getChildAt(0);
        if (newView != null && mCurrentView != newView) {
            if (mListner != null) {
                mCurrentView = newView;
                mListner.change(mCurrentView,
                        getChildPosition(mCurrentView));

            }
        }
    }


}

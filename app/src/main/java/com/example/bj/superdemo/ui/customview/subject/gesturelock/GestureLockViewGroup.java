package com.example.bj.superdemo.ui.customview.subject.gesturelock;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by bj on 2016/9/21.
 * description：手势锁屏，默认九个
 */
public class GestureLockViewGroup extends RelativeLayout {
    private int mChildCount = 9;
    private int mHeight;
    private int mWidth;
    private GestureLockView[] gestureLockViews = new GestureLockView[9];
    private int mGestureLockViewWidth;
    private int mMarginBetweenLockView;
    /**
     * 行数、列数的个数
     */
    private int mCount = 3;

    public GestureLockViewGroup(Context context) {
        super(context);
        initData(context);
    }

    public GestureLockViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public GestureLockViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = mHeight = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        //此处可以随便设值
        // 计算每个GestureLockView的宽度
        mGestureLockViewWidth = (int) (4 * mWidth * 1.0f / (5 * mChildCount + 1));
        //计算每个GestureLockView的间距
        mMarginBetweenLockView = (int) ((mWidth - mGestureLockViewWidth * mCount) / (mCount + 1));
//        int topMagrin = mMarginBetweenLockView;
//        int rightMargin = mMarginBetweenLockView;
        //初始化gestureLockView
        for (int x = 0; x < mChildCount; x++) {
            gestureLockViews[x] = new GestureLockView(getContext());
            gestureLockViews[x].setId(x + 1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mGestureLockViewWidth, mGestureLockViewWidth);
            if (x % mCount != 0) {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, gestureLockViews[x - 1].getId());
            }
            if (x > mCount - 1) {
                layoutParams.addRule(RelativeLayout.BELOW, gestureLockViews[x - mCount].getId());
            }
            int rightMargin = mMarginBetweenLockView;
            int bottomMargin = mMarginBetweenLockView;
            int leftMagin = 0;
            int topMargin = 0;
            if (x >= 0 && x < mCount) {
                topMargin = mMarginBetweenLockView;
            }
            if (x % mCount == 0) {
                leftMagin = mMarginBetweenLockView;
            }
            layoutParams.setMargins(leftMagin, topMargin, rightMargin,
                    bottomMargin);
            addView(gestureLockViews[x], layoutParams);
        }

    }


}

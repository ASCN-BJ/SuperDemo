package com.example.bj.superdemo.ui.customview.subject.gesturelock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bj.superdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bj on 2016/9/21.
 * description：手势锁屏，默认九个,这个地方的id值设置的比较简单，如果需要怕重复的话，可以在Value文件中添加自定义的id值
 */
public class GestureLockViewGroup extends RelativeLayout {
    private int mChildCount = 9;
    private int mHeight;
    private int mWidth;
    private GestureLockView[] gestureLockViews;
    private int mGestureLockViewWidth;
    private int mMarginBetweenLockView;
    /**
     * 行数、列数的个数
     */
    private int mCount = 3;

    private Path mPath;
    private Paint mPaint;
    private List<Integer> mChooser = new ArrayList<>();
    private int mLastPathX;
    private int mLastPathY;
    private Point mTmpTarget = new Point();
    private List<Integer> specificGesture;
    private JudgeGestureListner judgeListner;

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
        mPath = new Path();
        mPaint = new Paint();//Paint.ANTI_ALIAS_FLAG
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
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
        setMeasuredDimension(mWidth, mHeight);
        //初始化gestureLockView

        if (gestureLockViews == null) {
            gestureLockViews = new GestureLockView[9];
            for (int x = 0; x < mChildCount; x++) {
                gestureLockViews[x] = new GestureLockView(getContext());
                gestureLockViews[x].setId(x + 1);
                gestureLockViews[x].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

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
//            gestureLockViews[x].setMode(GestureLockView.Mode.STATUS_NO_FINGER);
                this.addView(gestureLockViews[x], layoutParams);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                reset();
                break;
            case MotionEvent.ACTION_MOVE:
                GestureLockView lockView = getChildOnPot(x, y);
                mPaint.setColor(Color.BLUE);
                mPaint.setStrokeWidth(40);
                //set the alpha component [0..255] of the paint's color.
                mPaint.setAlpha(50);
                if (lockView != null) {
                    int id = lockView.getId();
                    if (!mChooser.contains(id)) {
                        mChooser.add(id);
                        mLastPathX = lockView.getLeft() / 2 + lockView.getRight() / 2;
                        mLastPathY = lockView.getTop() / 2 + lockView.getBottom() / 2;
                        lockView.setMode(GestureLockView.Mode.STATUS_FINGER_ON);
                        if (mChooser.size() >= 2) {
                            int endId = mChooser.get(mChooser.size() - 1);
                            int startId = mChooser.get(mChooser.size() - 2);
                            GestureLockView endView = getGestureViewById(endId);
                            GestureLockView startView = getGestureViewById(startId);
                            int middleX = ((endView.getLeft() / 2 + endView.getRight() / 2) + (startView.getLeft() / 2 + startView.getRight() / 2)) / 2;
                            int middleY = ((endView.getTop() / 2 + endView.getBottom() / 2) + (startView.getTop() / 2 + startView.getBottom() / 2)) / 2;
                            GestureLockView glk = getChildOnPot(middleX, middleY);
                            if (glk != null && glk instanceof GestureLockView) {
                                glk.setMode(GestureLockView.Mode.STATUS_FINGER_ON);
                                mChooser.set(mChooser.size() - 1, glk.getId());
                                mChooser.add(endId);
                            }
                        }
                        if (mChooser.size() == 1) {
                            //说明触摸的是第一个
                            mPath.moveTo(mLastPathX, mLastPathY);
                        } else {
                            mPath.lineTo(mLastPathX, mLastPathY);
                        }
                    }
                }
                // 指引线的终点
                mTmpTarget.x = x;
                mTmpTarget.y = y;
                break;
            case MotionEvent.ACTION_UP:
                mTmpTarget.x = mLastPathX;
                mTmpTarget.y = mLastPathY;
                changeItemMode();
                if (specificGesture != null && !specificGesture.isEmpty() && !mChooser.isEmpty()) {
                    if (judgeListner != null) {
                        if (JudgeResult(specificGesture, mChooser)) {
                            judgeListner.getGestureJudgeResult(true);
                        } else {
                            judgeListner.getGestureJudgeResult(false);
                        }
                    }

                }
                break;
        }
        invalidate();
        return true;
    }

    private GestureLockView getGestureViewById(int id) {
        for (GestureLockView gestureLockView : gestureLockViews) {
            if (gestureLockView.getId() == id) {
                return gestureLockView;
            }
        }
        return null;
    }

    /**
     * 设定指定值的手势
     */
    public void setGestureLock(List<Integer> lists) {
        this.specificGesture = lists;
    }

    public interface JudgeGestureListner {
        void getGestureJudgeResult(boolean result);
    }

    /**
     * 设置回调接口
     *
     * @param judgeListner
     */
    public void setJudgeListner(JudgeGestureListner judgeListner) {
        this.judgeListner = judgeListner;
    }


    //校验结果
    private boolean JudgeResult(List<Integer> listsA, List<Integer> listsB) {
        boolean flag = true;
        if (listsA.size() == listsB.size()) {
            for (int x = 0; x < listsA.size(); x++) {
                if (listsA.get(x) != listsB.get(x)) {
                    flag = false;
                }
            }
            return flag;
        }
        return false;
    }


    private void changeItemMode() {
        for (GestureLockView gestureLockView : gestureLockViews) {
            gestureLockView.setMode(GestureLockView.Mode.STATUS_FINGER_UP);
        }
    }

    private void reset() {
        mChooser.clear();
        mPath.reset();
        for (int x = 0; x < gestureLockViews.length; x++) {
            gestureLockViews[x].setMode(GestureLockView.Mode.STATUS_NO_FINGER);
        }
    }

    private boolean checkPostionInChid(GestureLockView child, int x, int y) {
        int padding = (int) (mGestureLockViewWidth * 0);
        if (x >= child.getLeft() + padding && x <= child.getRight() - padding && y <= child.getBottom() + padding && y >= child.getTop() - padding) {
            return true;
        }
        return false;
    }

    @Nullable
    private GestureLockView getChildOnPot(int x, int y) {
        for (GestureLockView gestureLockView : gestureLockViews) {
            if (checkPostionInChid(gestureLockView, x, y)) {
                return gestureLockView;
            }
        }

        return null;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mPath != null) {
            canvas.drawPath(mPath, mPaint);
        }
        if (mChooser.size() > 0) {
            if (mLastPathX != 0 && mLastPathY != 0) {
                canvas.drawLine(mLastPathX, mLastPathY, mTmpTarget.x, mTmpTarget.y, mPaint);
            }
        }
    }
}

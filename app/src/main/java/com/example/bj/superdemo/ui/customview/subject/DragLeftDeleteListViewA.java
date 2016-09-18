package com.example.bj.superdemo.ui.customview.subject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;

/**
 * Created by bj on 2016/9/18.
 * description：一个侧拉删除的ListViewDemo
 */
public class DragLeftDeleteListViewA extends ListView {
    /**
     * 要显示的删除按钮
     */
    private PopupWindow mPop;
    /**
     * 手指滑动的最小距离
     */
    private int mTouchSlop;
    private TextView tv_delete;
    private int xDown, xMove;
    private int yDown, yMove;
    //是否滑动
    private boolean mIsSliding;
    private View mCurrentView;
    private int mCurrentViewPoint;
    private int mPopHeight;
    /**
     * 为删除按钮提供一个回调接口
     */
    private DelButtonClickListener mListener;

    public DragLeftDeleteListViewA(Context context) {
        super(context);
        init(context);
    }

    public DragLeftDeleteListViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragLeftDeleteListViewA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        View popView = LayoutInflater.from(context).inflate(R.layout.itm_drag_left_delete, null);
        mPop = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPop.getContentView().measure(0, 0);
        mPopHeight = mPop.getHeight();
        tv_delete = (TextView) popView.findViewById(R.id.tv_delete);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                if (mPop.isShowing()) {
                    //如果pop显示listView不应该向下传递事件了
                    dismissPop();
                    return false;
                }
                mCurrentViewPoint = pointToPosition(xDown, yDown);
                View view = getChildAt(mCurrentViewPoint - getFirstVisiblePosition());
                mCurrentView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                if (xMove < xDown && Math.abs(dx) > mTouchSlop && Math.abs(dy) < mTouchSlop) {
                    mIsSliding = true;
                } else {
                    mIsSliding = false;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mIsSliding) {
            if (ev.getAction() == MotionEvent.ACTION_MOVE) {

                int[] location = new int[2];
                mCurrentView.getLocationInWindow(location);
                mPop.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
                        location[0] + mCurrentView.getWidth(), location[1] + mCurrentView.getHeight() / 2 - mPop.getContentView().getMeasuredHeight() / 2);
//                System.out.println("mPopHeight" + mPopHeight);
                tv_delete.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getContext(), "这是一个删除", Toast.LENGTH_SHORT).show();
                        if (mListener != null) {
                            mListener.clickHappend(mCurrentViewPoint);
                            mPop.dismiss();
                        }
                    }
                });
            } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                mIsSliding = false;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 掩藏pop
     */
    private void dismissPop() {
        if (mPop != null && mPop.isShowing()) {
            mPop.dismiss();
        }
    }

    public void setDelButtonClickListener(DelButtonClickListener listener) {
        mListener = listener;
    }

    public interface DelButtonClickListener {
        void clickHappend(int position);
    }
}

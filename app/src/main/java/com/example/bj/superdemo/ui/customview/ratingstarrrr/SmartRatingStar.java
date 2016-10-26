package com.example.bj.superdemo.ui.customview.ratingstarrrr;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bj.superdemo.R;

/**
 * Created by bj on 2016/10/26.
 * description：
 */
public class SmartRatingStar extends LinearLayout {

    private TypedArray ta;
    private Bitmap firstDrawable;
    private Bitmap secondDrawable;
    private int maxStarNumber;
    private int minStarNumber;
    private int starMargin;

    private int parentHeight;
    private int parentWidth;
    private int totalStars;
    private Context mContext;

    public SmartRatingStar(Context context) {
        this(context, null);
    }

    public SmartRatingStar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartRatingStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        mContext = context;
        ta = context.obtainStyledAttributes(attrs, R.styleable.SmartRatingStar);
        firstDrawable = BitmapFactory.decodeResource(context.getResources(), R.styleable.SmartRatingStar_FirstDrawable);
        secondDrawable = BitmapFactory.decodeResource(context.getResources(), R.styleable.SmartRatingStar_SecondDrawable);
        maxStarNumber = ta.getInt(R.styleable.SmartRatingStar_MaxStarNumber, 5);
        minStarNumber = ta.getInt(R.styleable.SmartRatingStar_MiniStarNumber, 1);
        starMargin = ta.getInt(R.styleable.SmartRatingStar_StarMargin, 0);
        totalStars = ta.getInt(R.styleable.SmartRatingStar_TotalStars, 5);

    }

    private int moveIndex = 5;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x;
        int y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                x = (int) event.getX();
                y = (int) event.getY();

                setStars(x, y);
                break;
            case MotionEvent.ACTION_MOVE:

                x = (int) event.getX();
                y = (int) event.getY();

                setStars(x, y);
                break;
            default:
                break;
        }

        return true;
    }

    private void setStars(int x, int y) {
        ImageView view = (ImageView) getChildByPos(x, y);
        if (view != null) {
            int w = 0;
            for (int z = 0; z <= getIndexByPos(x, y); z++) {
                getChildAt(z).setBackground(getResources().getDrawable(R.drawable.yellow_star_three));
                w = z + 1;
            }
            if (w <= 4) {
                for (int t = w; t < 5; t++) {
                    getChildAt(t).setBackground(getResources().getDrawable(R.drawable.star_grey_three));
                }
            }
        }
    }

    private int getIndexByPos(int x, int y) {
        for (int z = 0; z < getChildCount(); z++) {
            View view = getChildAt(z);
            int left = view.getLeft();
            int top = view.getTop();
            int right = view.getRight();
            int bottom = view.getBottom();
            if (left < x && x < right & top < y && y < bottom) {
                return z;
            }
        }
        return -1;
    }

    private View getChildByPos(int x, int y) {
        for (int z = 0; z < getChildCount(); z++) {
            View view = getChildAt(z);
            int left = view.getLeft();
            int top = view.getTop();
            int right = view.getRight();
            int bottom = view.getBottom();
            if (left < x && x < right & top < y && y < bottom) {
                return view;
            }
        }
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        switch (MeasureSpec.getMode(widthMeasureSpec)) {
//            case MeasureSpec.EXACTLY:
//                parentWidth = MeasureSpec.getSize(widthMeasureSpec);
//                break;
//            case MeasureSpec.AT_MOST:
//                int childWidth = (firstDrawable.getWidth() >= secondDrawable.getWidth() ? firstDrawable.getWidth() : secondDrawable.getWidth());
//                parentWidth = childWidth * totalStars + starMargin * totalStars;
//                break;
//            default:
//                break;
//        }
//
//        switch (MeasureSpec.getMode(heightMeasureSpec)) {
//            case MeasureSpec.EXACTLY:
//                parentHeight = MeasureSpec.getSize(heightMeasureSpec);
//                break;
//            case MeasureSpec.AT_MOST:
//                int childHeight = (firstDrawable.getHeight() >= secondDrawable.getHeight() ? firstDrawable.getHeight() : secondDrawable.getHeight());
//                parentHeight = childHeight * totalStars + starMargin * totalStars;
//                break;
//            default:
//                break;
//        }
//        setMeasuredDimension(parentWidth, parentHeight);
    }

    @Override
    public void addView(View child) {
//        TextView textView = new TextView(mContext);
//        textView.setText("heihei");
//        textView.setWidth(400);
//        textView.setHeight(400);
//        child = textView;
        super.addView(child);
    }

    //A A A' A A'
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        ViewGroup.LayoutParams params = textView.getLayoutParams();
//        params.width = 400;
//        params.height = 400;
//        textView.setLayoutParams(params);
        System.out.println("getChildCount" + getChildCount());

        super.onLayout(changed, l, t, r, b);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

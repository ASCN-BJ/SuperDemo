package com.example.bj.superdemo.ui.multititle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bj.superdemo.R;

/**
 * Created by bj on 2017-1-4.
 * Description：多个标签栏展示，自动换行
 * 初始计算宽高
 * 计算数组中字符串的长度
 */

public class MultiTitleLayout extends ViewGroup {
    private TypedArray typedArray;
    /*布局宽度*/
    private int ViewWidth;
    /*布局高度*/
    private int ViewHeight;
    /*控件宽度*/
    private int parentWidth;
    /*控件高度*/
    private int parentHeight;
    /*控件中要显示的所有字符串*/
    private String[] datas = {"西游记", "红楼梦", "三国演义", "水浒传", "追风筝的人", "这是一个长的测试哈哈哈哈哈哈哈哈", "真尼玛闲"};
    private Context mContext;
    private int linePaddingWidth;

    public MultiTitleLayout(Context context) {
        this(context, null);
    }

    public MultiTitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiTitleLayout);
        ViewWidth = typedArray.getDimensionPixelSize(R.styleable.MultiTitleLayout_view_width, 100);
        ViewHeight = typedArray.getDimensionPixelSize(R.styleable.MultiTitleLayout_view_heigh, 100);

    }

    /**
     * 设置字符串数据
     *
     * @param datas
     */
    public void setData(String[] datas) {
        this.datas = datas;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        linePaddingWidth = getPaddingLeft() + getPaddingRight();//控件单行的padding宽度
        switch (measureWidthMode) {//宽度
            case MeasureSpec.EXACTLY://精确值
                parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            case MeasureSpec.AT_MOST://取最大值
                break;
            default:
                break;
        }
        switch (measureHeightMode) {//宽度
            case MeasureSpec.EXACTLY://精确值
                parentHeight = MeasureSpec.getSize(heightMeasureSpec);
            case MeasureSpec.AT_MOST://
                break;
            default:
                break;
        }
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        int mWidthMeasureSpec = MeasureSpec.makeMeasureSpec(parentWidth, MeasureSpec.EXACTLY);
        int mHeigthMeasureSpec = MeasureSpec.makeMeasureSpec(parentHeight, MeasureSpec.EXACTLY);
        setMeasuredDimension(mWidthMeasureSpec, mHeigthMeasureSpec);
//
//        for (int i = 0; i < datas.length; i++) {
//            MultiTextView mTextView = new MultiTextView(mContext);
//            mTextView.setText(datas[i]);
//            this.addView(mTextView);
//        }
    }

    int childTotalWidth;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentLine = 0;
        if (!changed) {
            return;
        }
        for (int x = 0; x < this.getChildCount(); x++) {
            View child = getChildAt(x);
            if (child.getVisibility() == View.INVISIBLE || child.getVisibility() == View.GONE) {
                continue;
            }
            int childWidth;
            int childHeigh;
            int marginLeft = 0;
            int marginRight = 0;
            int marginTop = 0;
            int marginBottom = 0;
            LayoutParams lp = child.getLayoutParams();
            childWidth = child.getMeasuredWidth();
            childHeigh = child.getMeasuredHeight();
            if (lp instanceof MarginLayoutParams) {
                MarginLayoutParams mlp = (MarginLayoutParams) lp;
                marginLeft = mlp.leftMargin;
                marginRight = mlp.rightMargin;
                marginTop = mlp.topMargin;
                marginBottom = mlp.bottomMargin;
            } else {

            }
            childTotalWidth += marginLeft + marginRight + childWidth;
            if (childTotalWidth > parentWidth) {
                currentLine += 1;
                childTotalWidth = 0;
                childTotalWidth += marginLeft + marginRight + childWidth;
                child.layout(childTotalWidth - childWidth, (marginBottom + marginTop + childHeigh) * currentLine, childTotalWidth, (marginBottom + marginTop + childHeigh) * currentLine + childHeigh);
            } else {
                child.layout(childTotalWidth - childWidth, (marginBottom + marginTop + childHeigh) * currentLine, childTotalWidth, (marginBottom + marginTop + childHeigh) * currentLine + childHeigh);
            }
        }
    }


}

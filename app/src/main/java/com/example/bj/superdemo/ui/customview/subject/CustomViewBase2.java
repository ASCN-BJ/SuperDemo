package com.example.bj.superdemo.ui.customview.subject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.bj.superdemo.R;

/**
 * Created by bj on 2016/9/13.
 * description：一个用于显示图片以及文字描述的View
 */
public class CustomViewBase2 extends View {
    private String mTextTitle;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Rect mRect;
    private Rect mTextBoundRect;
    private int mTextColor;
    private int mTextSize;
    private int mImgScal;
    private Bitmap mImageView;

    public CustomViewBase2(Context context) {
        super(context);
        initData(context, null);
    }

    public CustomViewBase2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public CustomViewBase2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewBase2);
        mTextColor = typedArray.getColor(R.styleable.CustomViewBase2_TitleTextColors, Color.BLUE);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomViewBase2_TitleTextSizes, 100);
        mTextTitle = typedArray.getString(R.styleable.CustomViewBase2_TitleTexts);
        mImgScal = typedArray.getInt(R.styleable.CustomViewBase2_imageScaleTypes, 0);
        mImageView = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.CustomViewBase2_ImageSrcs, R.drawable.fengjing1));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(mTextColor);

        mRect = new Rect();
        mTextBoundRect = new Rect();
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mTextTitle, 0, mTextTitle.length(), mTextBoundRect);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureModeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureModeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        //图片的宽高
        int imgWidth = getPaddingLeft() + getPaddingRight() + mImageView.getWidth();
        int imgHeigh = mImageView.getHeight();
        //字体的宽高
        int textWidth = getPaddingLeft() + getPaddingRight() + mTextBoundRect.width();
        int textHeight = mTextBoundRect.height();

        //测量宽度
        if (measureModeWidth == MeasureSpec.EXACTLY) {
            mWidth = measureWidth;
        } else {
            if (measureModeWidth == MeasureSpec.AT_MOST) {
                mWidth = Math.max(imgWidth, textWidth);
                mWidth = Math.min(mWidth, measureWidth);
            }
        }
        //测量高度
        if (measureModeHeight == MeasureSpec.EXACTLY) {
            mHeight = measureHeight;
        } else {
            if (measureModeWidth == MeasureSpec.AT_MOST) {
//                mHeight = Math.max(imgHeigh, textHeight);
//                mHeight = Math.min(mHeight, measureHeight);
                mHeight = imgHeigh + textHeight + getPaddingBottom() + getPaddingTop();
                mHeight = Math.min(mHeight, measureHeight);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //添加一个边框
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        //添加文字
        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();
        //
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        //字的长度超过宽度
        if (mTextBoundRect.width() > mWidth) {
            TextPaint textPaint = new TextPaint(mPaint);//Truncate 极端
            String text = TextUtils.ellipsize(mTextTitle, textPaint, (float) mWidth - getPaddingLeft(), TextUtils.TruncateAt.END).toString();
            canvas.drawText(text, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else {//未超过
            canvas.drawText(mTextTitle, mWidth / 2 - mTextBoundRect.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }
        mRect.bottom -= mTextBoundRect.height();
        if (mImgScal == 0) {
            canvas.drawBitmap(mImageView, null, mRect, mPaint);
        } else {
            //计算居中的矩形范围
            mRect.left = mWidth / 2 - mImageView.getWidth() / 2;
            mRect.right = mWidth / 2 + mImageView.getWidth() / 2;
            mRect.top = (mHeight - mTextBoundRect.height()) / 2 - mImageView.getHeight() / 2;
            mRect.bottom = (mHeight - mTextBoundRect.height()) / 2 + mImageView.getHeight() / 2;
            canvas.drawBitmap(mImageView, null, mRect, mPaint);
        }


    }
}

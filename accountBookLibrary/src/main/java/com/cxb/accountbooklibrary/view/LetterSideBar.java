package com.cxb.accountbooklibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 右侧的字母索引View
 * 
 * @author liuc
 * 
 */
public class LetterSideBar extends View {

	public LetterSideBar(Context context) {
		super(context);
	}

	public LetterSideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LetterSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private static final String TAG = LetterSideBar.class.getSimpleName();
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	public static final String[] letters = { "#", "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	private int choose = -1;
	private Paint paint = new Paint();
	private TextView mTextDialog; // 显示字母的TextView

	public void setTextView(TextView view) {
		this.mTextDialog = view;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = getHeight(); // 获取高度
		int width = getWidth(); // 获取宽度
		int singleHeight = height / letters.length;// 获取每一个字母的高度
		for (int i = 0; i < letters.length; i++) {
			// paint.setColor(Color.rgb(33, 65, 98));
			// paint.setColor(Color.WHITE);
			paint.setColor(Color.parseColor("#818181"));
			paint.setTypeface(Typeface.DEFAULT);
			paint.setAntiAlias(true);
			paint.setTextSize(26);
			if (i == choose) { // 选中的状态
				paint.setColor(Color.parseColor("#db444d"));
				paint.setFakeBoldText(true);
			}
			float x = width / 2 - paint.measureText(letters[i]) / 2;
			float y = singleHeight * i + singleHeight;
			canvas.drawText(letters[i], x, y, paint); // 开始绘制
			paint.reset(); // 重置画笔
		}
	}

	@SuppressLint("NewApi")
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / getHeight() * letters.length); // 点击y坐标所占总高度的比例*letters数组的长度得到letters数组中字母的位置
		switch (action) {
		case MotionEvent.ACTION_UP: // 手指抬起时
			setBackground(new ColorDrawable(0x00000000));
			choose = -1;
			invalidate(); // ?
			if (null != mTextDialog) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
			setBackgroundColor(Color.parseColor("#20000000")); // 获取焦点时改变背景颜色
			if (oldChoose != c) {
				if (c >= 0 && c < letters.length) {
					if (null != listener) {
						listener.onTouchingLetterChanged(letters[c]);
					}
					if (mTextDialog != null) {
						mTextDialog.setText(letters[c]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					choose = c;
					invalidate();
				}
			}
			break;
		}
		return true;
	}

	/**
	 * 点击字母监听
	 * 
	 * @author Administrator
	 * 
	 */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	// public LetterSideBar(Context context) {
	// super(context);
	// }
	//
	// public LetterSideBar(Context context, AttributeSet attrs) {
	// super(context, attrs);
	// }
	//
	// public LetterSideBar(Context context, AttributeSet attrs, int
	// defStyleAttr) {
	// super(context, attrs, defStyleAttr);
	// }
	//
	// public LetterSideBar(Context context, AttributeSet attrs, int
	// defStyleAttr,
	// int defStyleRes) {
	// super(context, attrs, defStyleAttr, defStyleRes);
	// }
}
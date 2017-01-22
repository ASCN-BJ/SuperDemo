package com.cxb.accountbooklibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 禁止滚动的GridView
 * 
 * @author liuc
 * 
 */
public class BanRollGridView extends GridView {

	public BanRollGridView(Context context) {
		super(context);
	}

	public BanRollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BanRollGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}

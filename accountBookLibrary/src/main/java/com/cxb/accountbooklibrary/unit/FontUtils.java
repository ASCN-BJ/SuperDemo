package com.cxb.accountbooklibrary.unit;

import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 字体工具类，用于设置字体样式
 * 
 * @author Devin.Ding
 * 
 */
public class FontUtils {
	private static Typeface mTypeface;

	/**
	 * 设置数字字体样式为arial narrow
	 * 
	 * @param context
	 * @return
	 */
	public static Typeface setNumberFont(Context context) {
		if (mTypeface == null) {
			mTypeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/arial_narrow.ttf");
		}
		return mTypeface;

	}

	/**
	 * 把金钱数字转化成三位一分形式，类似于 : 312,125,2.36
	 * 
	 * @param number
	 *            要转化的数字
	 * @return 转换结果
	 */
	public static String setMoneyFormat(String number) {
		String result = number;
		if (number != null && !"".equals(number)) {
			DecimalFormat format = new DecimalFormat("#,###.00");
			if (Double.parseDouble(number) != 0) {
				result = format.format(Double.parseDouble(result));
			}
		}
		return result;
	}
}

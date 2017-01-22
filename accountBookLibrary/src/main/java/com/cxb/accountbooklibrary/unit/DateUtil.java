package com.cxb.accountbooklibrary.unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * 日期处理工具类
 * 
 * @author Devin.Ding
 * 
 */
public class DateUtil {
	/**
	 * 为按钮设置选择的日期
	 * 
	 * @param context
	 * @param button
	 */
	public static void setButtonDate(Context context, final Button button) {
		Calendar calendarStart = Calendar.getInstance();
		DatePickerDialog dialogStart = new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker dp, int year, int month,
							int dayOfMonth) {
						button.setText(year + "-" + (month + 1) + "-"
								+ dayOfMonth);
					}
				}, calendarStart.get(Calendar.YEAR),
				calendarStart.get(Calendar.MONTH),
				calendarStart.get(Calendar.DAY_OF_MONTH));
		dialogStart.show();
	}

	/**
	 * 为文本设置选择的日期
	 * 
	 * @param context
	 * @param button
	 */
	public static void setTextViewDate(Context context, final View textView) {
		Calendar calendarStart = Calendar.getInstance();
		DatePickerDialog dialogStart = new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker dp, int year, int month,
							int dayOfMonth) {
						String mMonth = "";
						String mDay = "";
						if (month + 1 < 10) {
							mMonth = "0" + (month + 1);
						} else {
							mMonth = (month + 1) + "";
						}
						if (dayOfMonth < 10) {
							mDay = "0" + dayOfMonth;
						} else {
							mDay = dayOfMonth + "";
						}
						((TextView) textView).setText(year + "-" + mMonth + "-"
								+ mDay);
					}
				}, calendarStart.get(Calendar.YEAR),
				calendarStart.get(Calendar.MONTH),
				calendarStart.get(Calendar.DAY_OF_MONTH));
		dialogStart.show();
	}
	
	/**
	 * 返回选择时间
	 * 
	 * @param context
	 * @param button
	 */
	static String returnDate;
	public static String setViewDate(Context context) {
		Calendar calendarStart = Calendar.getInstance();
		DatePickerDialog dialogStart = new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker dp, int year, int month,
							int dayOfMonth) {
						String mMonth = "";
						String mDay = "";
						if (month + 1 < 10) {
							mMonth = "0" + (month + 1);
						} else {
							mMonth = (month + 1) + "";
						}
						if (dayOfMonth < 10) {
							mDay = "0" + dayOfMonth;
						} else {
							mDay = dayOfMonth + "";
						}
						returnDate = year + "-" + mMonth + "-" + mDay;
					}
				}, calendarStart.get(Calendar.YEAR),
				calendarStart.get(Calendar.MONTH),
				calendarStart.get(Calendar.DAY_OF_MONTH));
		dialogStart.show();
		return returnDate;
	}

	// /*
	// * 选择时间
	// */
	// public static void setTextViewDate(Context context, final TextView
	// textView) {
	// Calendar calendarStart = Calendar.getInstance();
	// DatePickerDialog dialogStart = new DatePickerDialog(context, new
	// DatePickerDialog.OnDateSetListener() {
	// public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth)
	// {
	// String months;
	// String day;
	// if ((month + 1) < 10) {
	// months = "0" + (month + 1);
	// } else {
	// months = "" + (month + 1);
	// }
	// if (dayOfMonth < 10) {
	// day = "0" + dayOfMonth;
	// }else {
	// day = "" + dayOfMonth;
	// }
	// textView.setText(year + "-" + months + "-" + day);
	// }
	// }, calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
	// calendarStart.get(Calendar.DAY_OF_MONTH));
	// dialogStart.show();
	// }

	/**
	 * 获取当前日期的 年月日(2015-8-26)
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		String date = "2015-8-26";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date(System.currentTimeMillis());// 获取当前时间
		date = formatter.format(currentDate);
		return date;
	}

	/**
	 * 把时间戳转换为日期(年、月)
	 */
	public static String Dates(long time) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为日期(年、月、日)
	 */
	public static String TimeToDate(long time) throws TimeFormatException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为日期(月、日)
	 */
	public static String monthAndDateToDate(long time)
			throws TimeFormatException {
		SimpleDateFormat formatter = new SimpleDateFormat("M-dd");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为日期(带字的年、月、日)
	 */
	public static String TimeToWordDate(long time) throws NumberFormatException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为日期(含时、分、秒)
	 */
	public static String TimeToDate_time(long time)
			throws NumberFormatException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为日期(含时、分 换行)
	 */
	public static String DateAndtimeforTextView(long time)
			throws NumberFormatException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd \n HH:mm");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为日期(含时、分)
	 */
	public static String TimeToDate_point(long time)
			throws NumberFormatException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为日期(含时、分)
	 */
	public static String DateAndtime(long time) throws NumberFormatException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(new Date(time));
	}

	/**
	 * 把时间戳转换为时分秒
	 */
	public static String DateToTime(long time) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(new Date(time));
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		String date = "2015";
		Calendar calendar = Calendar.getInstance();

		date = String.valueOf(calendar.get(calendar.YEAR));
		return date;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		String date = "1";
		Calendar calendar = Calendar.getInstance();

		date = String.valueOf(calendar.get(calendar.MONTH) + 1);
		return date;
	}

	/**
	 * 获取当前时间(日)
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		String date = "1";
		Calendar calendar = Calendar.getInstance();

		date = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
		return date;
	}

	/**
	 * 获取当前年份第一天的日期
	 * 
	 * @return
	 */
	public static String getFirstDayOfYear() {
		String date = "2015-8-26";
		Calendar calendar = Calendar.getInstance();

		date = calendar.get(calendar.YEAR) + "-01-01";
		return date;
	}

	/**
	 * yyyy/MM/dd 类型的日期比较
	 * 
	 * @param context
	 *            Context
	 * @param dateStart
	 *            开始日期
	 * @param dateEnd
	 *            结束日期
	 * @return false 代表dateStart早于dateEnd true代表 dateStart晚于dateEnd
	 */
	public static boolean DateCompare(Context context, String dateStart,
			String dateEnd) {
		boolean result = true;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 比较开始 结束时间
		try {
			if (dateFormat.parse(dateStart).getTime() > dateFormat.parse(
					dateEnd).getTime()) {
				result = false;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	// private static SimpleDateFormat format;
	/**
	 * 将日期转为时间戳
	 * 
	 * @param context
	 * @param dataString
	 * @return
	 */
	public static long DateToTime(Context context, String dataString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long time;
		try {
			time = dateFormat.parse(dataString).getTime();
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 获取当月第一天的日期
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}

	/**
	 * 获取当月最后一天的日期
	 * 
	 * @return
	 */
	public static String getLastDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}

	/**
	 * 根据年月获取当前年月的第一天日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}

	/**
	 * 根据年月获取当月最后一天的日期
	 * 
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		try {
			date = format.parse(year + "-" + month + "-" + "01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}

	/**
	 * 获取当前日期前一月的日期，格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDateOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取和今天有间隔的日期，格式yyyy-MM-dd
	 * 
	 * @param gapDays
	 *            和今天间隔的天数，正数向后推相应的天数，负数向前推相应的天数
	 * @return
	 */
	public static String getSpecifiedGapDate(int gapDays) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, gapDays);
		date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
}
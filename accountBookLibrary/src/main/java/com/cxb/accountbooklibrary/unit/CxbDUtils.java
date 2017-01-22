package com.cxb.accountbooklibrary.unit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 工具类
 * 
 * @author Devin.Ding
 * 
 */
public class CxbDUtils {

	/** 手机号正则 */
//	private static String mobPhoneNumRE = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(18[^4,\\D])|(17[0-9]))\\d{8}$";
	private static String mobPhoneNumRE = "^[1][3,4,5,7,8][0-9]{9}$";

	/** 邮箱正则 */
	private static String emailRE = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";

	/** 座机号正则 */
	private static String phoneNumRE = "^([0-9]{3,4})(-)[0-9]{7,8}$";

	/** 数字正则 */
	public static String numberRE = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";

	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	public static String getImieStatus(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		return deviceId;
	}

	public String getAndroidId(Context context) {
		String androidId = Settings.System.getString(
				context.getContentResolver(), Settings.System.ANDROID_ID);
		return androidId;
	}

	// 获取当前sdk版本号
	public static int getSystemVersion() {
		int sdkVersion = android.os.Build.VERSION.SDK_INT;
		return sdkVersion;
	}

	// 获取当前系统版本号
	public static String getSystemReleas() {
		String releasVersion = android.os.Build.VERSION.RELEASE;
		return releasVersion;
	}

	// // 获取软件版本号
	// public static String getAppVersion(Context context) {
	// String appVersionv = getAppVersion(context);
	// return appVersionv;
	// }

	// 获取手机型号
	public static String getMobileVersion() {
		String mobileVersion = android.os.Build.MODEL;
		return mobileVersion;
	}

	// 获取手机品牌名称
	public static String getMobileBrand() {
		String phoneBrand = android.os.Build.BRAND;
		return phoneBrand;
	}

	// 判断是否为数字
	public static boolean isNumeric(String str) {
		// Pattern pattern = Pattern.compile("[0-9]*");
		Pattern pattern = Pattern.compile(numberRE);
		return pattern.matcher(str).matches();
	}

	public static String StringFilter(String str) throws PatternSyntaxException {
		String regEx = "[/\\:*?<>|\"\n\t]-"; // 要过滤掉的字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * @Title: isEmail
	 * @Description: 验证邮箱
	 * @param @param email
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isEmail(String email) {
		return isModleStr(email, emailRE);
	}

	public static boolean isPhoneOrMob(String phoneNum) {
		return (isModleStr(phoneNum, mobPhoneNumRE) || isModleStr(phoneNum,
				phoneNumRE));
	}

	/**
	 * @Title: isModleStr
	 * @Description: 传入字符串和正则表达式，验证是否符合此正则
	 * @param @param paramString1
	 * @param @param paramString2
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isModleStr(String str, String regex) {
		if (str == null) {
			return false;
		} else {
			return Pattern.compile(regex).matcher(str).matches();
		}
	}

	/**
	 * 改变ListView的高度用于listView嵌套ListView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 读取本地文件中JSON字符串
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getJson(String fileName, Context context) {
		String resultJson = "";
		// StringBuilder stringBuilder = new StringBuilder();
		try {
			// BufferedReader bf = new BufferedReader(new InputStreamReader(
			// context.getAssets().open(fileName)));
			// String line;
			// while ((line = bf.readLine()) != null) {
			// stringBuilder.append(line);
			// }
			InputStream inputStream = context.getResources().getAssets()
					.open(fileName);
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			resultJson = new String(buffer, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultJson;
	}

//	/**
//	 * 图片缓存工具
//	 */
//	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
//			.cacheInMemory(true).considerExifParams(true)
//			.imageScaleType(ImageScaleType.EXACTLY)
//			.showImageForEmptyUri(R.drawable.expense_photo_def)
//			.displayer(new SimpleBitmapDisplayer()).build();

	/**
	 * 判断后台运行
	 * 
	 * @param context
	 * @return
	 */
	// public static boolean isBackground(Context context) {
	// ActivityManager activityManager = (ActivityManager) context
	// .getSystemService(Context.ACTIVITY_SERVICE);
	// List<RunningAppProcessInfo> appProcesses = activityManager
	// .getRunningAppProcesses();
	// for (RunningAppProcessInfo appProcess : appProcesses) {
	// if (appProcess.processName.equals(context.getPackageName())) {
	// /*
	// BACKGROUND=400 EMPTY=500 FOREGROUND=100
	// GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
	// */
	// Log.e(context.getPackageName(), "此appimportace =" + appProcess.importance
	// + ",context.getClass().getName()= " + context.getClass().getName());
	// if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
	// {
	// Log.e(context.getPackageName(), "处于后台" + appProcess.processName);
	// return true;
	// } else {
	// Log.e(context.getPackageName(), "处于前台" + appProcess.processName);
	// return false;
	// }
	// }
	// }
	// return false;
	// }
	public static boolean isRunningForeground(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		if (!TextUtils.isEmpty(currentPackageName)
				&& currentPackageName.equals(context.getPackageName())) {
			return true;
		}

		return false;
	}

	/**
	 * 是否安装微信
	 * 
	 * @param mContext
	 */
	public static boolean isInstallWx(Context mContext, String packageName) {
		try {
			PackageManager manager = mContext.getPackageManager();
			PackageInfo info = manager.getPackageInfo(packageName,
					PackageManager.GET_ACTIVITIES);
			if (info != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String parseChineseNumerals(int arabicNumerals) {
		// String[] nums = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String[] nums = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		// String units[] = new String[] { "元", "拾", "佰", "仟", "万", "拾", "佰",
		// "仟", "亿" };
		String units[] = new String[] { "", "十", "百", "千", "万", "十", "百", "千",
				"亿" };
		String str = String.valueOf(arabicNumerals);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			String c = String.valueOf(str.charAt(i));
			sb = sb.append(nums[Integer.parseInt(c)]);
		}
		str = String.valueOf(sb);
		int i = 0;
		for (int j = str.length(); j > 0; j--) {
			sb = sb.insert(j, units[i++]);
		}
		return sb.toString();
	}

	public static String getRandColor() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;

		return "#" + r + g + b;
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param v
	 */
	public static void dismissSoftInput(View v, Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	/**
	 * dip转像素
	 * 
	 * @param context
	 * @param dip
	 * @return
	 */
	public static int formatDipToPx(Activity context, int dip) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int dip1 = (int) Math.ceil(dip * dm.density);
		return dip1;
	}

	/**
	 * px 转 Dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int formatPxToDip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	// 商品单位列表
	public static ArrayList<String> setProductList() {
		ArrayList<String> taxRateList = new ArrayList<String>();
		taxRateList.add("请选择商品单位");
		taxRateList.add("个");
		taxRateList.add("部");
		taxRateList.add("件");
		taxRateList.add("箱");
		taxRateList.add("瓶");
		taxRateList.add("KG");
		taxRateList.add("盒");
		taxRateList.add("卷");
		taxRateList.add("台");
		taxRateList.add("杯");
		taxRateList.add("条");
		taxRateList.add("桶");
		taxRateList.add("包");
		taxRateList.add("袋");
		taxRateList.add("组");
		taxRateList.add("板");

		return taxRateList;
	}
	
}

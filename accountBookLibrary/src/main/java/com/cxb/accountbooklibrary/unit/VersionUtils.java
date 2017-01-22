package com.cxb.accountbooklibrary.unit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * App版本信息 工具类
 * 
 * @author ZGP
 *
 */
public class VersionUtils {
	/**
	 * 获取App当前 版本名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		return getPackageInfo(context).versionName;
	}

	/**
	 * 获取App当前 版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		return getPackageInfo(context).versionCode;
	}

	/**
	 * 获取App版本信息
	 * 
	 * @param context
	 * @return
	 */
	private static PackageInfo getPackageInfo(Context context) {
		PackageInfo pi = null;
		try {
			PackageManager pm = context.getPackageManager();
			pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);

			return pi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pi;
	}

	/**
	 * 获取当前应用的版本号：
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getVersionNum(Context mContext) {
		String version;
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = mContext.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					mContext.getPackageName(), 0);
			version = "V" + packInfo.versionName;
		} catch (NameNotFoundException e) {
			version = "V1.0.0";
		}
		return version;
	}
}

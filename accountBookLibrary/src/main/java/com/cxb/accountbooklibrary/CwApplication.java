package com.cxb.accountbooklibrary;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.view.WindowManager;

/**
 * 自定义Application
 * @author Devin.Ding
 *
 */
public class CwApplication extends Application {
	// private File PhotoFile;
	public static String KEY_IS_FIRST = "is_first";
	public static String IS_DISMISSDIALOG = "is_DismissDialog";
	// 锁屏广播过滤器
	IntentFilter filter;
    public Vibrator mVibrator;
    private List<Activity> mList = new LinkedList<Activity>();
    private static CwApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		// 有盟统计：包含fragment的程序中我们希望统计更详细的页面，所以需要自己调用方法做更详细的统计,禁止默认的页面统计方式，这样将不会再自动统计Activity。
//		MobclickAgent.openActivityDurationTrack(false);
//		CrashHandler crashHandler = CrashHandler.getInstance();
////		// 注册crashHandler
//		crashHandler.init(getApplicationContext());
//		StringBuffer param = new StringBuffer();
//		param.append("appid="+getString(R.string.app_id));
//		param.append(",");
//		// 设置使用v5+
//		param.append(SpeechConstant.ENGINE_MODE+"="+SpeechConstant.MODE_MSC);
//		SpeechUtility.createUtility(CwApplication.this, param.toString());
	}

	/**
	 * 是否第一次启动应用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isFirstStartAPP(Context context) {
		SharedPreferences pr = context.getSharedPreferences("isFirst",
				Context.MODE_PRIVATE);
		if (pr.getBoolean(KEY_IS_FIRST, true)) {
			pr.edit().putBoolean(KEY_IS_FIRST, false).commit();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否第一次启动应用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isDismissDialog(Context context) {
		SharedPreferences pr = context.getSharedPreferences("isDismissDialog",
				Context.MODE_PRIVATE);
		if (pr.getBoolean(IS_DISMISSDIALOG, true)) {
			pr.edit().putBoolean(IS_DISMISSDIALOG, false).commit();
			return true;
		} else {
			return false;
		}
	}

	private WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();

	public WindowManager.LayoutParams getWindowParams() {
		return windowParams;
	}

	private boolean isCyLogin = false;

	public boolean isCyLogin() {
		return isCyLogin;
	}

	public void setCyLogin(boolean isCyLogin) {
		this.isCyLogin = isCyLogin;
	}

	private boolean isHasFloatView = false;

	public boolean isHasFloatView() {
		return isHasFloatView;
	}

	public void setHasFloatView(boolean isHasFloatView) {
		this.isHasFloatView = isHasFloatView;
	}
	
	public synchronized static CwApplication getInstance() {
		   if (null == instance) {
		         instance = new CwApplication();
		      }
		         return instance;
		      }

		// add Activity
		public void addActivity(Activity activity) {
		      mList.add(activity);
		}

		    public void exit() {
		        try {
		           for (Activity activity : mList) {
		              if (activity != null)
		                 activity.finish();
		                 }
		             } catch (Exception e) {
		                 e.printStackTrace();
		             } finally {
		                System.exit(0);
		             }
		}

		@Override
		public void onLowMemory() {
		    super.onLowMemory();
		      System.gc();
		}

}

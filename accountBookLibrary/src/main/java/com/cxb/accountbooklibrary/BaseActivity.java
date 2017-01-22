/*
 * Copyright 2010-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cxb.accountbooklibrary;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.cxb.accountbooklibrary.constants.Consts;
import com.cxb.accountbooklibrary.view.DialogShow;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * activity基类
 * @author Devin.Ding
 * 
 */
public abstract class BaseActivity extends FragmentActivity {

	protected static final String TAG = BaseActivity.class.getSimpleName();

	private ProgressDialog progressDialog;

	private Timer timer;

	private boolean destroyed = false;
	public static BaseActivity getInstance = null;
	private Context mContext;

	private WindowManager windowManager = null;
	private WindowManager.LayoutParams windowManagerParams = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getInstance = this;
		mContext = BaseActivity.this;
		testConnectivityManager();
		imageLoader();
	}

	/**
	 * 程序是否在前台运行
	 * 
	 * @return
	 */
	public boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		// device
		android.app.ActivityManager activityManager = (android.app.ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = getApplicationContext().getPackageName();

		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}

		return false;
	}

	private void imageLoader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(true)
				.displayer(new SimpleBitmapDisplayer())	
				// .showImageOnFail(R.drawable.ic_launcher)
				// .bitmapConfig(Bitma.Config.RGB_565)// do not cache in memory
				// avoid bitmapOutOfMemory
				.cacheInMemory(true)
				.cacheOnDisc(true).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).defaultDisplayImageOptions(defaultOptions)
				.threadPoolSize(3)
				.taskExecutorForCachedImages(Executors.newCachedThreadPool())
				.discCacheSize(50 * 1024 * 1024).build();
		ImageLoader.getInstance().init(config);
	}

	@Override
	protected void onResume() {

		windowManager = (WindowManager) getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);

//		if (timer != null) {
//			timer.cancel();
//		}
		super.onResume();
	}

	protected void onDestroy() {
		super.onDestroy();
		destroyed = true;
	}

	public void showLoadingProgressDialog() {
		this.showProgressDialog("Loading. Please wait...");
	}

	/**
	 * 显示加载中对话框
	 * 
	 * @param message
	 *            要显示的信息
	 */
	public void showProgressDialog(CharSequence message) {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setIndeterminate(true);
		}
		progressDialog.setMessage(message);
		progressDialog.show();
	}

	/**
	 * 隐藏加载对话框
	 */
	public void dismissProgressDialog() {
		if (progressDialog != null && !destroyed) {
			progressDialog.dismiss();
		}
	}

	/**
	 * 判断网络状态
	 */
	private void testConnectivityManager() {
		ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		// mobile 3G Network
		State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		Log.d("BaseActivity", mobile.toString());
		// wifi Network
		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		Log.d("BaseActivity", wifi.toString());

		// 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
		if (mobile == State.CONNECTED || mobile == State.CONNECTING)
			return;
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			return;

		DialogShow.showDialogView(this, Consts.SET_NETWORK,
				getString(R.string.set_network));
	}

	/**
	 * 注销登录
	 */
	// public void logout() {
	// PersonalRequestHelper helper = PersonalRequestHelper.getInstance();
	// helper.logout("123456789", new BaseHttpResopnse(mContext, this.getClass().getSimpleName()) {
	//
	// @Override
	// public void onSuccess(int statusCode, Header[] header,
	// String response) {
	// if (statusCode == 200) {
	// // 清除用户数据
	// new Sharedpreferences().cleanUserCache(BaseActivity.this);
	// finish();
	// }
	//
	// }
	//
	// @Override
	// public void onFailure(int statusCode, Header[] header,
	// String reponse, Throwable arg3) {
	//
	// }
	// });
	// }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
}

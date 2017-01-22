package com.cxb.accountbooklibrary;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

/**
 * 退出除登录页的所有页面
 * 
 * @author Devin.Ding
 * 
 */
public class ActivityManager {

	private List<Activity> activityList = new LinkedList<Activity>();
	private static ActivityManager instance;

	private ActivityManager() {
	}

	// 单例模式中获取唯一的MyApplication实例
	public static ActivityManager getInstance() {
		if (null == instance) {
			instance = new ActivityManager();
		}
		return instance;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exitAlltoLogin() {
		for (Activity activity : activityList) {
			activity.finish();
		}
//		System.exit(0);
	}
	
	// 遍历所有Activity并finish
	public void exitAll() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	public void exit() {
		try {
			for (Activity activity : activityList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
//		finally {
//			System.exit(0);
//		}
	}
}
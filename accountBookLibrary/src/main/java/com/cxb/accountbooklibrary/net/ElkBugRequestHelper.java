package com.cxb.accountbooklibrary.net;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;

import com.cxb.accountbooklibrary.unit.CxbDUtils;
import com.cxb.accountbooklibrary.unit.NetUtils;
import com.cxb.accountbooklibrary.unit.VersionUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * elk_bug统计
 * 
 * @author Devin.Ding
 * 
 */
public class ElkBugRequestHelper extends BaseRequestHelper {
	private static ElkBugRequestHelper getHelper = null;
//	private Sharedpreferences shared;

	private ElkBugRequestHelper() {
	}

	public static ElkBugRequestHelper getInstance() {
		getClientInstance();
		if (getHelper == null) {
			getHelper = new ElkBugRequestHelper();
		}
		return getHelper;
	}

	@SuppressLint("SimpleDateFormat")
	public void elkBug(Context mContext, String errorClassName, String errorUrl,
			int errorCode, String errorResponse, String errorMsg,
			AsyncHttpResponseHandler handler) {
//		shared = new Sharedpreferences();
		RequestParams params = new RequestParams();
		String appVersion = VersionUtils.getVersionNum(mContext);//软件版本
		String phoneBrand = CxbDUtils.getMobileBrand();//手机品牌
		String phoneType = CxbDUtils.getMobileVersion();//手机型号
		String osVersion = CxbDUtils.getSystemReleas();
		String netWork = NetUtils.GetNetworkType(mContext);
		
		String userId = "";
//		if (shared.getUserId(mContext) != null && !"".equals(shared.getUserId(mContext))) {
//			userId = new Sharedpreferences().getUserId(mContext);
			params.put("userId", userId);
//		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");       
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
		String errorTime = formatter.format(curDate);
		params.put("key", "e93b4fdcafe2dd35ccaeeda166e03d99");
		params.put("productName", "进销存Android");
		params.put("appVersion", appVersion);
		params.put("phoneBrand", phoneBrand);
		params.put("phoneType", phoneType);
		params.put("osVersion", osVersion);
		params.put("osType", "Android");
		params.put("netWork", netWork);
		params.put("errorClassName", errorClassName);
		params.put("errorUrl", errorUrl);
		params.put("errorCode", errorCode);
		params.put("errorResponse", errorResponse);
		params.put("errorMsg", errorMsg);
		params.put("errorTime", errorTime);
		client.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		client.post(ELK_BUG, params, handler);
	}

}
package com.cxb.accountbooklibrary.net;

import com.loopj.android.http.AsyncHttpClient;

/**
 * 网络请求基类
 * 
 * @author Devin.Ding
 * 
 */
public class BaseRequestHelper {

//	 public static final String BASE_URL = "http://192.168.1.11/Jxc";
//	 public static final String BASE_URL = "http://jxc.itest.aicself.com";
	public static final String BASE_URL = "http://api.aicself.com";// 发布

//	 public static final String BASE_WEB_URL = "http://192.168.1.187:8020/AIC_JIEGE";
//	 public static final String BASE_WEB_URL = "http://192.168.1.11/aic_android";
//	 public static final String BASE_WEB_URL = "http://jxcweb.itest.aicself.com/H5";
//	 public static final String BASE_WEB_URL = "file:///android_asset/AIC_JIEGE";//发布本地
	public static final String BASE_WEB_URL = "http://www.aicself.com/H5";// 发布

	protected static AsyncHttpClient client = null;

	protected static AsyncHttpClient getClientInstance() {
		if (client == null) {
			client = new AsyncHttpClient();
		}
		return client;
	}

	public void cancelRequest(boolean isCancel) {
		client.cancelAllRequests(isCancel);
	}

	/*
	 * 修改用户基础信息
	 */
	public final String UPDATE_USERBASICINFO_URL = BASE_URL
			+ "/basicUserInfoRest/updateOrganization.json";
	
	/*
	 * 创建公司
	 */
	public static final String CREATE_ORG = BASE_URL
			+ "/interview/userCenter/add/organization.json";
	
	/*
	 * 切换账套
	 */
	public final String SELECT_ZT = BASE_URL
			+ "/basicUserInfoRest/updateOrgUseStatus.json";
	
	/*
	 * 删除账套
	 */
	public final String DELETE_ZT = BASE_URL
			+ "/basicUserInfoRest/deleteOrganization.json";
	
	/*
	 * 获取账套
	 */
	public final String GET_ZT = BASE_URL
			+ "/basicUserInfoRest/getOrganizationList.json";
	
	/*
	 * 获取公司列表数据
	 */
	public static final String RETRIEVE_ORG_LIST = BASE_URL
			+ "/interview/userCenter/get/organization/list.json";
	
	/*
	 * elk_bug统计
	 */
	public static final String ELK_BUG = "http://bug.ops.runself.com/bug.php";
}

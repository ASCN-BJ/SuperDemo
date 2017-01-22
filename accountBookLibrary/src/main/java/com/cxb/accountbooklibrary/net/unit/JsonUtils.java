package com.cxb.accountbooklibrary.net.unit;

import java.lang.reflect.Type;

import com.cxb.accountbooklibrary.constants.ResultCodeConstants;
import com.cxb.accountbooklibrary.net.response.BaseJsonResponse;
import com.cxb.accountbooklibrary.unit.CxbDUtils;
import com.google.gson.Gson;
/**
 * Json 解析类
 * @author Devin.Ding
 *
 */
public class JsonUtils {
	
	private static String message;

	public static String toJson(Object object, Type type) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(object, type);
		return jsonStr;
	}

	public static <T extends BaseJsonResponse> T fromJson(String jsonStr, Type type) {
		Gson gson = new Gson();
		T response = gson.fromJson(jsonStr, type);
		
		if (response.getResultCode() != null && response.getResultCode() != 0) {
			try {
				if (response.getResultCode() == -1) {
					message = response.getMessage();
				}else {
					//如果resultCode == 1 视为重新登录
					if (response.getResultCode() == 1) {
//						CxbDUtils.exitAndLogin();
						message = "您的账号已在异地登录，如果不是本人操作，请及时修改密码！";
						response.setMessage(message);
					}else {
						message = (String) ResultCodeConstants.class.getField("RESULT_CODE_" + response.getResultCode()).get(ResultCodeConstants.class);
					}
				}
				response.setMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return response;
	}

}

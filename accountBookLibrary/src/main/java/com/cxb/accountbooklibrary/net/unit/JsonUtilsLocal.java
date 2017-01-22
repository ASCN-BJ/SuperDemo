package com.cxb.accountbooklibrary.net.unit;

import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * 本地json数据解析类
 * 
 * @author Devin.Ding
 * 
 */
public class JsonUtilsLocal {

	public static String toJson(Object object, Type type) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(object, type);
		return jsonStr;
	}

	public static <T> T fromJson(String jsonStr, Type type) {
		Gson gson = new Gson();
		T response = gson.fromJson(jsonStr, type);
		return response;
	}

}

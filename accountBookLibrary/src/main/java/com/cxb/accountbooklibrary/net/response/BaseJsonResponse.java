package com.cxb.accountbooklibrary.net.response;

/**
 * JSON解析基类，是数据解析的最外层处理
 * 
 * @author 周国谱
 * 
 */
public class BaseJsonResponse {
	// 接口请求失败消息
	protected String message;
	// 接口请求返回代码
	public int resultCode;
	// 是否请求成功
	protected boolean success = true;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

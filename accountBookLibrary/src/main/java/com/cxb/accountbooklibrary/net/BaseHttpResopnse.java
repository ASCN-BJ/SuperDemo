package com.cxb.accountbooklibrary.net;

import org.apache.http.Header;

import android.content.Context;

import com.cxb.accountbooklibrary.constants.Constant;
import com.cxb.accountbooklibrary.net.response.BaseJsonResponse;
import com.cxb.accountbooklibrary.net.unit.JsonUtils;
import com.cxb.accountbooklibrary.unit.ToastUtil;
import com.loopj.android.http.TextHttpResponseHandler;
/**
 * 网络请求返回参数基类
 * @author Devin.Ding
 *
 */
public abstract class BaseHttpResopnse extends TextHttpResponseHandler{
	BaseHttpResopnse handler;
	ElkBugRequestHelper helper = ElkBugRequestHelper.getInstance();
	public String errorClassName;
	public Context mContext;
	
	public BaseHttpResopnse(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	/**
     * Called when request fails
     *
     * @param statusCode     http response status line
     * @param headers        response headers if any
     * @param responseString string response of given charset
     * @param throwable      throwable returned when processing request
     */
    public abstract void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable);

    /**
     * Called when request succeeds
     *
     * @param statusCode     http response status line
     * @param headers        response headers if any
     * @param responseString string response of given charset
     */
    public abstract void onSuccess(int statusCode, Header[] headers, String responseString);

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBytes) {
    	String response = getResponseString(responseBytes, getCharset());
    	BaseJsonResponse jsonResponse = JsonUtils.fromJson(response, BaseJsonResponse.class);
    	if (jsonResponse.getResultCode() == 1) {
    		if (Constant.OUTPUT_ERROR) {
    			errorRequest(statusCode, responseBytes, jsonResponse.getMessage());
    		}
		}
        onSuccess(statusCode, headers, getResponseString(responseBytes, getCharset()));
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBytes, Throwable throwable) {
		if (Constant.OUTPUT_ERROR) {
			errorRequest(statusCode, responseBytes, throwable.getMessage());
		}
		if (statusCode == 0) {
			ToastUtil.showToastShort(mContext, "网络请求异常，请检查网络是否连接正常！");
		}else if (statusCode == 500) {
			ToastUtil.showToastShort(mContext, "服务器返回异常");
		}else if (statusCode == 404 || statusCode == 400) {
			ToastUtil.showToastShort(mContext, "参数错误");
		}else {
			ToastUtil.showToastShort(mContext, "未知错误");
		}
        onFailure(statusCode, headers, getResponseString(responseBytes, getCharset()), throwable);
    }

	public void errorRequest(int statusCode, byte[] responseBytes, String msg){
		errorClassName = mContext.getClass().getSimpleName();
		helper.elkBug(mContext, errorClassName, getRequestURI().getHost() + getRequestURI().getPath(), statusCode, getResponseString(responseBytes, getCharset()), msg, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				System.out.println(arg2);
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				System.out.println(arg2);
			}
		});
	}
	

}

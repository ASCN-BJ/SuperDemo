package com.cxb.accountbooklibrary.net.response;

import java.io.Serializable;

/**
 * 获取上传头像返回值
 * 
 * @author Devin.Ding
 * 
 */
public class BaseStringResponse extends BaseJsonResponse implements
		Serializable {

	private String datas;

	/**
	 * @return the data
	 */
	public String getData() {
		return datas;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String datas) {
		this.datas = datas;
	}

}

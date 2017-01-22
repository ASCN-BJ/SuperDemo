package com.cxb.accountbooklibrary.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.cxb.accountbooklibrary.net.response.BaseJsonResponse;

/**
 * 行业实体类
 * 
 * @author liuc
 * 
 */
public class TradeBean extends BaseJsonResponse implements Serializable {

	private ArrayList<TradeDatas> datas;

	/**
	 * @return the datas
	 */
	public ArrayList<TradeDatas> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(ArrayList<TradeDatas> datas) {
		this.datas = datas;
	}

	public class TradeDatas implements Serializable {
		private String name;
		private String id;
		private String initial; // 显示数据拼音的首字母

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInitial() {
			return initial;
		}

		public void setInitial(String initial) {
			this.initial = initial;
		}
	}

}

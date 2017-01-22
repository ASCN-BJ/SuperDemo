package com.cxb.accountbooklibrary.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.cxb.accountbooklibrary.net.response.BaseJsonResponse;

public class CityBean extends BaseJsonResponse implements Serializable {

	private ArrayList<Datas> datas;


	/**
	 * @return the datas
	 */
	public ArrayList<Datas> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(ArrayList<Datas> datas) {
		this.datas = datas;
	}


	public class Datas implements Serializable {
		private String id;
		private String name;
		private String provinceName;
		private String provinceId;
		private String initial = "北京";

		/**
		 * @return the initial
		 */
		public String getInitial() {
			return initial;
		}

		/**
		 * @param initial
		 *            the initial to set
		 */
		public void setInitial(String initial) {
			this.initial = initial;
		}

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

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the provinceName
		 */
		public String getProvinceName() {
			return provinceName;
		}

		/**
		 * @param provinceName
		 *            the provinceName to set
		 */
		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}

		/**
		 * @return the provinceId
		 */
		public String getProvinceId() {
			return provinceId;
		}

		/**
		 * @param provinceId
		 *            the provinceId to set
		 */
		public void setProvinceId(String provinceId) {
			this.provinceId = provinceId;
		}

	}
}

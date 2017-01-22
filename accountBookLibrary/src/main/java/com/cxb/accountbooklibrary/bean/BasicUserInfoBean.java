package com.cxb.accountbooklibrary.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.cxb.accountbooklibrary.net.response.ContactListResponse.Authorities;
import com.cxb.accountbooklibrary.net.response.ContactListResponse.DepartmentEmployees;
import com.cxb.accountbooklibrary.net.response.ContactListResponse.Role;

/**
 * 用户信息实体类
 * 
 * @author liuc
 * 
 */
public class BasicUserInfoBean implements Serializable {

	private static final long serialVersionUID = 7465630989840746614L;

	private String id;
	private String weixin; // 微信
	private String email; // 邮箱
	private String department; // 部门
	private String name; // 姓名
	private String mobile; // 手机号
	private String roleId;
	private String iconPath;
	private String iconUrl;
	private ArrayList<Authorities> authorities;
	private Role role;
	private ArrayList<DepartmentEmployees> departmentEmployees;
	private ArrayList<Organization> organizations;

	public class Organization implements Serializable {
		private String id;
		private String email; // 邮箱
		private String tradeId; // 行业id
		private String tradeName; // 行业名称
		private String cityId; // 城市id
		private String cityName; // 城市名称
		private String provinceId; // 省份id
		private String tel; // 联系电话
		private String orgName; // 机构名称
		private String legalPerson; // 企业法人
		private String isLastUse;
		private int taxpayerType;
		private String validTime;

		private String isCurrentUse; // 是否是当前账套 1是 0不是

		/**
		 * @return the isCurrentUse
		 */
		public String getIsCurrentUse() {
			return isCurrentUse;
		}

		/**
		 * @param isCurrentUse
		 *            the isCurrentUse to set
		 */
		public void setIsCurrentUse(String isCurrentUse) {
			this.isCurrentUse = isCurrentUse;
		}

		/**
		 * @return the validTime
		 */
		public String getValidTime() {
			return validTime;
		}

		/**
		 * @param validTime
		 *            the validTime to set
		 */
		public void setValidTime(String validTime) {
			this.validTime = validTime;
		}

		/**
		 * @return the taxpayerType
		 */
		public int getTaxpayerType() {
			return taxpayerType;
		}

		/**
		 * @param taxpayerType
		 *            the taxpayerType to set
		 */
		public void setTaxpayerType(int taxpayerType) {
			this.taxpayerType = taxpayerType;
		}

		/**
		 * @return the isLastUse
		 */
		public String getIsLastUse() {
			return isLastUse;
		}

		/**
		 * @param isLastUse
		 *            the isLastUse to set
		 */
		public void setIsLastUse(String isLastUse) {
			this.isLastUse = isLastUse;
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
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email
		 *            the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the tradeId
		 */
		public String getTradeId() {
			return tradeId;
		}

		/**
		 * @param tradeId
		 *            the tradeId to set
		 */
		public void setTradeId(String tradeId) {
			this.tradeId = tradeId;
		}

		/**
		 * @return the tradeName
		 */
		public String getTradeName() {
			return tradeName;
		}

		/**
		 * @param tradeName
		 *            the tradeName to set
		 */
		public void setTradeName(String tradeName) {
			this.tradeName = tradeName;
		}

		/**
		 * @return the cityId
		 */
		public String getCityId() {
			return cityId;
		}

		/**
		 * @param cityId
		 *            the cityId to set
		 */
		public void setCityId(String cityId) {
			this.cityId = cityId;
		}

		/**
		 * @return the cityName
		 */
		public String getCityName() {
			return cityName;
		}

		/**
		 * @param cityName
		 *            the cityName to set
		 */
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		/**
		 * @return the tel
		 */
		public String getTel() {
			return tel;
		}

		/**
		 * @param tel
		 *            the tel to set
		 */
		public void setTel(String tel) {
			this.tel = tel;
		}

		/**
		 * @return the orgName
		 */
		public String getOrgName() {
			return orgName;
		}

		/**
		 * @param orgName
		 *            the orgName to set
		 */
		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		/**
		 * @return the legalPerson
		 */
		public String getLegalPerson() {
			return legalPerson;
		}

		/**
		 * @param legalPerson
		 *            the legalPerson to set
		 */
		public void setLegalPerson(String legalPerson) {
			this.legalPerson = legalPerson;
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

	/**
	 * @return the authorities
	 */
	public ArrayList<Authorities> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(ArrayList<Authorities> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the departmentEmployees
	 */
	public ArrayList<DepartmentEmployees> getDepartmentEmployees() {
		return departmentEmployees;
	}

	/**
	 * @param departmentEmployees
	 *            the departmentEmployees to set
	 */
	public void setDepartmentEmployees(
			ArrayList<DepartmentEmployees> departmentEmployees) {
		this.departmentEmployees = departmentEmployees;
	}

	/**
	 * @return the iconPath
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * @param iconPath
	 *            the iconPath to set
	 */
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	/**
	 * @return the iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl
	 *            the iconUrl to set
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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
	 * @return the weixin
	 */
	public String getWeixin() {
		return weixin;
	}

	/**
	 * @param weixin
	 *            the weixin to set
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the organizations
	 */
	public ArrayList<Organization> getOrganizations() {
		return organizations;
	}

	/**
	 * @param organizations
	 *            the organizations to set
	 */
	public void setOrganizations(ArrayList<Organization> organizations) {
		this.organizations = organizations;
	}

}

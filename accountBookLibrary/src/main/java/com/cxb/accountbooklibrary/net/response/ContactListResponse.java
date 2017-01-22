package com.cxb.accountbooklibrary.net.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 联系人列表
 */
public class ContactListResponse extends BaseJsonResponse implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5998749755622663345L;
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
		/**
		 * 
		 */
		private static final long serialVersionUID = -8936816929374927738L;
		private String id;
		private ArrayList<DepartmentEmployees> departmentEmployees;
		private String name;
		private String mobile;
		private Role role;
		private String iconUrl;
		private String initial;
		private ArrayList<Authorities> authorities;
		private boolean isChecked; // 是否选中，在添加联系人时用到

		public boolean isChecked() {
			return isChecked;
		}

		public void setChecked(boolean isChecked) {
			this.isChecked = isChecked;
		}

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

	}

	/*
	 * 部门列表
	 */
	public class DepartmentEmployees implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -31611225650510350L;

		private String id;
		private Department department;
		private UserBaseInfo userBaseInfo;
		private int leader;
		private int status;

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
		 * @return the department
		 */
		public Department getDepartment() {
			return department;
		}

		/**
		 * @param department
		 *            the department to set
		 */
		public void setDepartment(Department department) {
			this.department = department;
		}

		/**
		 * @return the userBaseInfo
		 */
		public UserBaseInfo getUserBaseInfo() {
			return userBaseInfo;
		}

		/**
		 * @param userBaseInfo
		 *            the userBaseInfo to set
		 */
		public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
			this.userBaseInfo = userBaseInfo;
		}

		/**
		 * @return the leader
		 */
		public int getLeader() {
			return leader;
		}

		/**
		 * @param leader
		 *            the leader to set
		 */
		public void setLeader(int leader) {
			this.leader = leader;
		}

		/**
		 * @return the status
		 */
		public int getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(int status) {
			this.status = status;
		}

	}

	/*
	 * 部门
	 */
	public class Department implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8283460135352651664L;
		private String id;
		private String name;
		private int status;

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
		 * @return the status
		 */
		public int getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(int status) {
			this.status = status;
		}

	}

	/*
	 * 
	 */
	public class Authorities implements Serializable {

		/**
		 * 权限信息
		 */
		private static final long serialVersionUID = -7859694355114433939L;
		private String id;
		private String maxMoney;
		private int type; // 权限类型(1：报销审批 2：借款审批(审批金额),3：项目管理)
		private String approvalType;

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
		 * @return the maxMoney
		 */
		public String getMaxMoney() {
			return maxMoney;
		}

		/**
		 * @param maxMoney
		 *            the maxMoney to set
		 */
		public void setMaxMoney(String maxMoney) {
			this.maxMoney = maxMoney;
		}

		/**
		 * @return the type
		 */
		public int getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(int type) {
			this.type = type;
		}

		/**
		 * @return the approvalType
		 */
		public String getApprovalType() {
			return approvalType;
		}

		/**
		 * @param approvalType
		 *            the approvalType to set
		 */
		public void setApprovalType(String approvalType) {
			this.approvalType = approvalType;
		}

	}

	/*
	 * 角色
	 */
	public class Role implements Serializable {
		private String userId;
		private int admin;
		private int accounting;
		private int employee;
		private int boss;
		//
		private int cashier; // 出纳

		
		
		public int getCashier() {
			return cashier;
		}

		public void setCashier(int cashier) {
			this.cashier = cashier;
		}

		/**
		 * @return the userId
		 */
		public String getUserId() {
			return userId;
		}

		/**
		 * @param userId
		 *            the userId to set
		 */
		public void setUserId(String userId) {
			this.userId = userId;
		}

		/**
		 * @return the admin
		 */
		public int getAdmin() {
			return admin;
		}

		/**
		 * @param admin
		 *            the admin to set
		 */
		public void setAdmin(int admin) {
			this.admin = admin;
		}

		/**
		 * @return the accounting
		 */
		public int getAccounting() {
			return accounting;
		}

		/**
		 * @param accounting
		 *            the accounting to set
		 */
		public void setAccounting(int accounting) {
			this.accounting = accounting;
		}

		/**
		 * @return the employee
		 */
		public int getEmployee() {
			return employee;
		}

		/**
		 * @param employee
		 *            the employee to set
		 */
		public void setEmployee(int employee) {
			this.employee = employee;
		}

		/**
		 * @return the boss
		 */
		public int getBoss() {
			return boss;
		}

		/**
		 * @param boss
		 *            the boss to set
		 */
		public void setBoss(int boss) {
			this.boss = boss;
		}

	}

	/*
	 * 用户基本信息
	 */
	public class UserBaseInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -150020395902022144L;
		private String id;
		private String name;
		private String mobile;
		private String iconUrl;

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

	}
}

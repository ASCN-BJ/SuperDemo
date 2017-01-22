package com.cxb.accountbooklibrary.bean;

import java.io.Serializable;

/**
 * 
 * 用户基本信息实体类
 * 
 * @author 周国谱
 * 
 */
public class UserBean implements Serializable {

	private static final long serialVersionUID = -106626712934643346L;
	// 用户token
	private String userToken;
	// 用户基本信息
	private BasicUserInfoBean userInfo;
	// 帐套信息
	private BasicOrgInfoBean organization;

	/**
	 * @return the organization
	 */
	public BasicOrgInfoBean getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(BasicOrgInfoBean organization) {
		this.organization = organization;
	}

	public BasicUserInfoBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(BasicUserInfoBean userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return the userToken
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * @param userToken
	 *            the userToken to set
	 */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public class Datas implements Serializable {

		private static final long serialVersionUID = 1L;
		private String userToken;
		private BasicUserInfoBean userInfo;
		private BasicOrgInfoBean organization;

		/**
		 * @return the organization
		 */
		public BasicOrgInfoBean getOrganization() {
			return organization;
		}

		/**
		 * @param organization
		 *            the organization to set
		 */
		public void setOrganization(BasicOrgInfoBean organization) {
			this.organization = organization;
		}

		public BasicUserInfoBean getUserInfo() {
			return userInfo;
		}

		public void setUserInfo(BasicUserInfoBean userInfo) {
			this.userInfo = userInfo;
		}

		/**
		 * @return the userToken
		 */
		public String getUserToken() {
			return userToken;
		}

		/**
		 * @param userToken
		 *            the userToken to set
		 */
		public void setUserToken(String userToken) {
			this.userToken = userToken;
		}

	}

	public class BasicOrgInfoBean implements Serializable {

		private OrganizationDetail organizationDetail;
		private String id; // 帐套ID
		private String email; // 邮箱
		// 帐套是否完整 0--完整 1--不完整
		private int isFull;
		private String tradeId; // 行业ID
		private String cityId; // 城市ID
		private String cityName; // 城市名
		private String tel; // 电话
		private String isLastUse; // 是否是上一次使用
		private String tradeName; // 行业名称
		private String orgName; // 公司名称
		private String provinceId; // 省份id
		private String legalPerson; // 企业法人
		private int taxpayerType; // 纳税人类型 1-- 一般纳税人，2---小规模纳税人
		private String validTime; // 创建时间
		private int isBalance; // 帐是否平衡
		private String linkman;
		private int createMode;
		/*
		 * 科目编码类型 2---编码格式 4-2-2 3---编码格式 4-3-3 4---编码格式 4-4-4
		 */
		private int codeFormat;

		public int getCodeFormat() {
			return codeFormat;
		}

		public void setCodeFormat(int codeFormat) {
			this.codeFormat = codeFormat;
		}

		/**
		 * @return the organizationDetail
		 */
		public OrganizationDetail getOrganizationDetail() {
			return organizationDetail;
		}

		/**
		 * @param organizationDetail
		 *            the organizationDetail to set
		 */
		public void setOrganizationDetail(OrganizationDetail organizationDetail) {
			this.organizationDetail = organizationDetail;
		}

		/**
		 * @return the linkman
		 */
		public String getLinkman() {
			return linkman;
		}

		/**
		 * @param linkman
		 *            the linkman to set
		 */
		public void setLinkman(String linkman) {
			this.linkman = linkman;
		}

		public int getIsFull() {
			return isFull;
		}

		public void setIsFull(int isFull) {
			this.isFull = isFull;
		}

		/**
		 * @return the isBalance
		 */
		public int getIsBalance() {
			return isBalance;
		}

		/**
		 * @param isBalance
		 *            the isBalance to set
		 */
		public void setIsBalance(int isBalance) {
			this.isBalance = isBalance;
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
		 * @return the createMode
		 */
		public int getCreateMode() {
			return createMode;
		}

		/**
		 * @param createMode
		 *            the createMode to set
		 */
		public void setCreateMode(int createMode) {
			this.createMode = createMode;
		}

	}

	public class Organization {

	}

	public class OrganizationDetail implements Serializable {
		private String id;
		private String accountBank;
		private String incomeTaxBelonging;
		private String nationalTaxAdminTel;
		private String accountNumber;
		private String businessScope;
		private String establishDate;
		private String localTaxBureauAddress;
		private String remark;
		private String checkRatifyTaxes;
		private String companyType;
		private String creditCode;
		private String registerAddress;
		private String registerCapital;
		private String nationalTaxAddress;
		private String localTaxBureauAdminTel;
		private String unitLeader;// 单位负责人
		private String lister;// 制表人

		/**
		 * @return the unitLeader
		 */
		public String getUnitLeader() {
			return unitLeader;
		}

		/**
		 * @param unitLeader
		 *            the unitLeader to set
		 */
		public void setUnitLeader(String unitLeader) {
			this.unitLeader = unitLeader;
		}

		/**
		 * @return the lister
		 */
		public String getLister() {
			return lister;
		}

		/**
		 * @param lister
		 *            the lister to set
		 */
		public void setLister(String lister) {
			this.lister = lister;
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
		 * @return the accountBank
		 */
		public String getAccountBank() {
			return accountBank;
		}

		/**
		 * @param accountBank
		 *            the accountBank to set
		 */
		public void setAccountBank(String accountBank) {
			this.accountBank = accountBank;
		}

		/**
		 * @return the incomeTaxBelonging
		 */
		public String getIncomeTaxBelonging() {
			return incomeTaxBelonging;
		}

		/**
		 * @param incomeTaxBelonging
		 *            the incomeTaxBelonging to set
		 */
		public void setIncomeTaxBelonging(String incomeTaxBelonging) {
			this.incomeTaxBelonging = incomeTaxBelonging;
		}

		/**
		 * @return the nationalTaxAdminTel
		 */
		public String getNationalTaxAdminTel() {
			return nationalTaxAdminTel;
		}

		/**
		 * @param nationalTaxAdminTel
		 *            the nationalTaxAdminTel to set
		 */
		public void setNationalTaxAdminTel(String nationalTaxAdminTel) {
			this.nationalTaxAdminTel = nationalTaxAdminTel;
		}

		/**
		 * @return the accountNumber
		 */
		public String getAccountNumber() {
			return accountNumber;
		}

		/**
		 * @param accountNumber
		 *            the accountNumber to set
		 */
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		/**
		 * @return the businessScope
		 */
		public String getBusinessScope() {
			return businessScope;
		}

		/**
		 * @param businessScope
		 *            the businessScope to set
		 */
		public void setBusinessScope(String businessScope) {
			this.businessScope = businessScope;
		}

		/**
		 * @return the establishDate
		 */
		public String getEstablishDate() {
			return establishDate;
		}

		/**
		 * @param establishDate
		 *            the establishDate to set
		 */
		public void setEstablishDate(String establishDate) {
			this.establishDate = establishDate;
		}

		/**
		 * @return the localTaxBureauAddress
		 */
		public String getLocalTaxBureauAddress() {
			return localTaxBureauAddress;
		}

		/**
		 * @param localTaxBureauAddress
		 *            the localTaxBureauAddress to set
		 */
		public void setLocalTaxBureauAddress(String localTaxBureauAddress) {
			this.localTaxBureauAddress = localTaxBureauAddress;
		}

		/**
		 * @return the remark
		 */
		public String getRemark() {
			return remark;
		}

		/**
		 * @param remark
		 *            the remark to set
		 */
		public void setRemark(String remark) {
			this.remark = remark;
		}

		/**
		 * @return the checkRatifyTaxes
		 */
		public String getCheckRatifyTaxes() {
			return checkRatifyTaxes;
		}

		/**
		 * @param checkRatifyTaxes
		 *            the checkRatifyTaxes to set
		 */
		public void setCheckRatifyTaxes(String checkRatifyTaxes) {
			this.checkRatifyTaxes = checkRatifyTaxes;
		}

		/**
		 * @return the companyType
		 */
		public String getCompanyType() {
			return companyType;
		}

		/**
		 * @param companyType
		 *            the companyType to set
		 */
		public void setCompanyType(String companyType) {
			this.companyType = companyType;
		}

		/**
		 * @return the creditCode
		 */
		public String getCreditCode() {
			return creditCode;
		}

		/**
		 * @param creditCode
		 *            the creditCode to set
		 */
		public void setCreditCode(String creditCode) {
			this.creditCode = creditCode;
		}

		/**
		 * @return the registerAddress
		 */
		public String getRegisterAddress() {
			return registerAddress;
		}

		/**
		 * @param registerAddress
		 *            the registerAddress to set
		 */
		public void setRegisterAddress(String registerAddress) {
			this.registerAddress = registerAddress;
		}

		/**
		 * @return the registerCapital
		 */
		public String getRegisterCapital() {
			return registerCapital;
		}

		/**
		 * @param registerCapital
		 *            the registerCapital to set
		 */
		public void setRegisterCapital(String registerCapital) {
			this.registerCapital = registerCapital;
		}

		/**
		 * @return the nationalTaxAddress
		 */
		public String getNationalTaxAddress() {
			return nationalTaxAddress;
		}

		/**
		 * @param nationalTaxAddress
		 *            the nationalTaxAddress to set
		 */
		public void setNationalTaxAddress(String nationalTaxAddress) {
			this.nationalTaxAddress = nationalTaxAddress;
		}

		/**
		 * @return the localTaxBureauAdminTel
		 */
		public String getLocalTaxBureauAdminTel() {
			return localTaxBureauAdminTel;
		}

		/**
		 * @param localTaxBureauAdminTel
		 *            the localTaxBureauAdminTel to set
		 */
		public void setLocalTaxBureauAdminTel(String localTaxBureauAdminTel) {
			this.localTaxBureauAdminTel = localTaxBureauAdminTel;
		}

	}
}

package com.cxb.accountbooklibrary.bean;

/**
 * 编辑账套 新建账套
 * 
 * @author Devin.Ding
 * 
 */
public class EditAddBasicInfoBean {
	private String name;
	private String email;
	private String orgName;
	private String legalPerson;
	private String tel;
	private String orgId;
	private String tradeId;
	private String cityId;
	private String provinceId;
	private int taxpayerType;
	private String dates;
	private int codeFormat; // 科目编码格式

	private String userId;

	/** 启用日期 */
	private String validTime;

	/** 信用代码 */
	private String creditCode;

	/** 成立日期 */
	private String establishDate;

	/** 注册资本 */
	private String registerCapital;

	/** 注册地址 */
	private String registerAddress;

	/** 公司类型 */
	private String companyType;

	/** 经营范围 */
	private String businessScope;

	/** 开户行 */
	private String accountBank;

	/** 账号 */
	private String accountNumber;

	/** 联系人 */
	private String linkman;

	/** 国税纳税地点 */
	private String nationalTaxAddress;

	/** 国税纳税管理员电话 */
	private String nationalTaxAdminTel;

	/** 地税纳税地点 */
	private String LocalTaxBureauAddress;

	/** 地税纳税管理员电话 */
	private String LocalTaxBureauAdminTel;

	/** 所得税归属地 */
	private String incomeTaxBelonging;

	/** 核定税种 */
	private String CheckRatifyTaxes;

	/** 备注 */
	private String remark;
	/** 单位负责人 */
	private String unitLeader;
	/** 制表人 */
	private String lister;

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
	 * @return the localTaxBureauAddress
	 */
	public String getLocalTaxBureauAddress() {
		return LocalTaxBureauAddress;
	}

	/**
	 * @param localTaxBureauAddress
	 *            the localTaxBureauAddress to set
	 */
	public void setLocalTaxBureauAddress(String localTaxBureauAddress) {
		LocalTaxBureauAddress = localTaxBureauAddress;
	}

	/**
	 * @return the localTaxBureauAdminTel
	 */
	public String getLocalTaxBureauAdminTel() {
		return LocalTaxBureauAdminTel;
	}

	/**
	 * @param localTaxBureauAdminTel
	 *            the localTaxBureauAdminTel to set
	 */
	public void setLocalTaxBureauAdminTel(String localTaxBureauAdminTel) {
		LocalTaxBureauAdminTel = localTaxBureauAdminTel;
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
	 * @return the checkRatifyTaxes
	 */
	public String getCheckRatifyTaxes() {
		return CheckRatifyTaxes;
	}

	/**
	 * @param checkRatifyTaxes
	 *            the checkRatifyTaxes to set
	 */
	public void setCheckRatifyTaxes(String checkRatifyTaxes) {
		CheckRatifyTaxes = checkRatifyTaxes;
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

	public int getCodeFormat() {
		return codeFormat;
	}

	public void setCodeFormat(int codeFormat) {
		this.codeFormat = codeFormat;
	}

	/**
	 * @return the dates
	 */
	public String getDates() {
		return dates;
	}

	/**
	 * @param dates
	 *            the dates to set
	 */
	public void setDates(String dates) {
		this.dates = dates;
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
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
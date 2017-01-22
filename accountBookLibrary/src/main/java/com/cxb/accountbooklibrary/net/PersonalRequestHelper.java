package com.cxb.accountbooklibrary.net;

import android.text.TextUtils;

import com.cxb.accountbooklibrary.bean.EditAddBasicInfoBean;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * 
 * @author liuc
 * 
 */
public class PersonalRequestHelper extends BaseRequestHelper {
	private static PersonalRequestHelper personalRequestHelper = null;

	private PersonalRequestHelper() {
	}

	public static PersonalRequestHelper getInstance() {
		getClientInstance();
		if (personalRequestHelper == null) {
			personalRequestHelper = new PersonalRequestHelper();
		}
		return personalRequestHelper;
	}

	/**
	 * 创建公司
	 * 
	 * @param token
	 * @param user
	 * @param handler
	 */
	public void createOrg(String token, EditAddBasicInfoBean addBasicInfoBean,
			TextHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		// params.add("token", token);
		// params.add("name", addBasicInfoBean.getName());
		// params.add("email", addBasicInfoBean.getEmail());
		// params.add("orgName", addBasicInfoBean.getOrgName());
		// params.add("legalPerson", addBasicInfoBean.getLegalPerson());
		// params.add("tel", addBasicInfoBean.getTel());
		// params.add("tradeId", addBasicInfoBean.getTradeId());
		// params.add("cityId", addBasicInfoBean.getCityId());
		// params.add("provinceId", addBasicInfoBean.getProvinceId());
		// params.add("taxpayerType", addBasicInfoBean.getTaxpayerType() + "");
		// params.add("validTime", addBasicInfoBean.getDates() + "");

		params.add("token", token);
		// params.add("userId", addBasicInfoBean.getUserId());
		params.add("orgName", addBasicInfoBean.getOrgName());
		params.add("validTime", addBasicInfoBean.getValidTime());
		params.add("cityId", addBasicInfoBean.getCityId());
		params.add("taxpayerType", addBasicInfoBean.getTaxpayerType() + "");
		params.add("codeFormat", addBasicInfoBean.getCodeFormat() + "");
		params.add("creditCode", addBasicInfoBean.getCreditCode());
		params.add("establishDate", addBasicInfoBean.getEstablishDate());
		params.add("legalPerson", addBasicInfoBean.getLegalPerson());
		params.add("registerCapital", addBasicInfoBean.getRegisterCapital());
		params.add("registerAddress", addBasicInfoBean.getRegisterAddress());
		params.add("companyType", addBasicInfoBean.getCompanyType());
		params.add("businessScope", addBasicInfoBean.getBusinessScope());
		params.add("tradeId", addBasicInfoBean.getTradeId());
		params.add("accountBank", addBasicInfoBean.getAccountBank());
		params.add("accountNumber", addBasicInfoBean.getAccountNumber());
		params.add("linkman", addBasicInfoBean.getLinkman());
		params.add("email", addBasicInfoBean.getEmail());
		params.add("tel", addBasicInfoBean.getTel());
		params.add("nationalTaxAddress",
				addBasicInfoBean.getNationalTaxAddress());
		params.add("nationalTaxAdminTel",
				addBasicInfoBean.getNationalTaxAdminTel());
		params.add("LocalTaxBureauAddress",
				addBasicInfoBean.getLocalTaxBureauAddress());
		params.add("LocalTaxBureauAdminTel",
				addBasicInfoBean.getLocalTaxBureauAdminTel());
		params.add("incomeTaxBelonging",
				addBasicInfoBean.getIncomeTaxBelonging());
		params.add("checkRatifyTaxes", addBasicInfoBean.getCheckRatifyTaxes());
		params.add("remark", addBasicInfoBean.getRemark());
		params.add("unitLeader", addBasicInfoBean.getUnitLeader());
		params.add("lister", addBasicInfoBean.getLister());
		if (!"".equals(addBasicInfoBean.getOrgId())
				&& addBasicInfoBean.getOrgId() != null) {
			params.add("orgId", addBasicInfoBean.getOrgId());
		}
		client.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		String url = "";
		if (TextUtils.isEmpty(addBasicInfoBean.getOrgId())) {
			url = CREATE_ORG;
		} else {
			url = UPDATE_USERBASICINFO_URL;
		}
		client.post(url, params, handler);
	}

	/**
	 * 修改用户基本信息
	 * 
	 * @param token
	 * @param user
	 * @param handler
	 */
	public void updateUserInfo(String token,
			EditAddBasicInfoBean addBasicInfoBean,
			TextHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		// params.add("token", token);
		// params.add("name", addBasicInfoBean.getName());
		// params.add("email", addBasicInfoBean.getEmail());
		// params.add("orgName", addBasicInfoBean.getOrgName());
		// params.add("legalPerson", addBasicInfoBean.getLegalPerson());
		// params.add("tel", addBasicInfoBean.getTel());
		// params.add("tradeId", addBasicInfoBean.getTradeId());
		// params.add("cityId", addBasicInfoBean.getCityId());
		// params.add("provinceId", addBasicInfoBean.getProvinceId());
		// params.add("taxpayerType", addBasicInfoBean.getTaxpayerType() + "");
		// params.add("validTime", addBasicInfoBean.getDates() + "");

		params.add("token", token);
		params.add("userId", addBasicInfoBean.getUserId());
		params.add("orgName", addBasicInfoBean.getOrgName());
		params.add("validTime", addBasicInfoBean.getValidTime());
		params.add("cityId", addBasicInfoBean.getCityId());
		params.add("taxpayerType", addBasicInfoBean.getTaxpayerType() + "");
		params.add("codeFormat", addBasicInfoBean.getCodeFormat() + "");
		params.add("creditCode", addBasicInfoBean.getCreditCode());
		params.add("establishDate", addBasicInfoBean.getEstablishDate());
		params.add("legalPerson", addBasicInfoBean.getLegalPerson());
		params.add("registerCapital", addBasicInfoBean.getRegisterCapital());
		params.add("registerAddress", addBasicInfoBean.getRegisterAddress());
		params.add("companyType", addBasicInfoBean.getCompanyType());
		params.add("businessScope", addBasicInfoBean.getBusinessScope());
		params.add("tradeId", addBasicInfoBean.getTradeId());
		params.add("accountBank", addBasicInfoBean.getAccountBank());
		params.add("accountNumber", addBasicInfoBean.getAccountNumber());
		params.add("linkman", addBasicInfoBean.getLinkman());
		params.add("email", addBasicInfoBean.getEmail());
		params.add("tel", addBasicInfoBean.getTel());
		params.add("nationalTaxAddress",
				addBasicInfoBean.getNationalTaxAddress());
		params.add("nationalTaxAdminTel",
				addBasicInfoBean.getNationalTaxAdminTel());
		params.add("LocalTaxBureauAddress",
				addBasicInfoBean.getLocalTaxBureauAddress());
		params.add("LocalTaxBureauAdminTel",
				addBasicInfoBean.getLocalTaxBureauAdminTel());
		params.add("incomeTaxBelonging",
				addBasicInfoBean.getIncomeTaxBelonging());
		params.add("CheckRatifyTaxes", addBasicInfoBean.getCheckRatifyTaxes());
		params.add("remark", addBasicInfoBean.getRemark());
		if (!"".equals(addBasicInfoBean.getOrgId())
				&& addBasicInfoBean.getOrgId() != null) {
			params.add("orgId", addBasicInfoBean.getOrgId());
		}
		params.add("unitLeader", addBasicInfoBean.getUnitLeader());
		params.add("lister", addBasicInfoBean.getLister());
		client.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		client.post(UPDATE_USERBASICINFO_URL, params, handler);

	}

	/**
	 * 切换账套
	 * 
	 * @param token
	 * @param orgId
	 * @param handler
	 */
	public void selectZT(String token, String orgId,
			TextHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("token", token);
		params.put("orgId", orgId);
		client.post(SELECT_ZT, params, handler);
	}

	/**
	 * 删除账套
	 * 
	 * @param token
	 *            用户token
	 * @param orgId
	 *            账套ID
	 * @param handler
	 *            数据处理
	 */
	public void deleteZT(String token, String orgId,
			TextHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("token", token);
		params.put("orgId", orgId);
		client.post(DELETE_ZT, params, handler);
	}

	/**
	 * 获取账套(一期中未使用)
	 * 
	 * @param token
	 *            用户token
	 * @param handler
	 *            数据处理
	 */
	public void getZT(String token, TextHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("token", token);
		client.post(GET_ZT, params, handler);
	}

	/**
	 * 获取公司列表数据
	 * 
	 * @param token
	 * @param handler
	 */
	public void retrieveOrgList(String token, TextHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("token", token);
		client.post(RETRIEVE_ORG_LIST, params, handler);
	}

}
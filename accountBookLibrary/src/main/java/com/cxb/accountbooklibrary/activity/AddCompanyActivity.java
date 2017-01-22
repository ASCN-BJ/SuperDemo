package com.cxb.accountbooklibrary.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cxb.accountbooklibrary.BaseActivity;
import com.cxb.accountbooklibrary.R;
import com.cxb.accountbooklibrary.bean.CityBean.Datas;
import com.cxb.accountbooklibrary.bean.EditAddBasicInfoBean;
import com.cxb.accountbooklibrary.bean.TradeBean.TradeDatas;
import com.cxb.accountbooklibrary.bean.UserBean;
import com.cxb.accountbooklibrary.net.BaseHttpResopnse;
import com.cxb.accountbooklibrary.net.PersonalRequestHelper;
import com.cxb.accountbooklibrary.net.response.BaseStringResponse;
import com.cxb.accountbooklibrary.net.unit.JsonUtils;
import com.cxb.accountbooklibrary.unit.DateUtil;
import com.cxb.accountbooklibrary.view.PopupwindowListView;

@SuppressLint("NewApi")
public class AddCompanyActivity extends BaseActivity implements OnClickListener {
	private Context mContext;
	private String mUserName; // 使用人
	private String mEmail; // 邮箱
	private String mOrgName; // 公司名称
	private String mLegelPerson; // 企业法人
	private String mTelephone; // 电话
	private String mTradeName; // 所属行业
	private String mCityName; // 所在城市
	private String mTradeId; // 行业ID
	private String mCityID; // 城市ID
	private String mOrgID; // 帐套ID  传入数据
	private String mUserID; // 用户ID  传入数据
	private String token; // 传入数据

	private int mTaxPayer = 0; // 纳税人类型 1一般纳税人，2小规模纳税人
	private String mCreateDate; // 创建时间
	private int mSubjectCode; // 科目编码类型
	private EditText mEdtUser, mEdtEmail, mEdtOrgName, mEdtTel,
			mEdtLegelPerson;
	private RelativeLayout mRLParent; // 父布局
	private View mLayer; // 弹出PopWindow时显示半透明效果时用
	private TextView mTvTrade, mTvCity, mTvTaxPayer, mTvCreateDate,
			mTvSubjectCode;
	private Button mBtnSave;
	private TextView basicinfo_code_type, baseinfo_date_of_establishment;// 编码格式,成立时间
	private EditText basicinfo_unitLeader, basicinfo_lister,
			basicinfo_credit_code, basicinfo_registered_capital,
			basicinfo_registered_address, basicinfo_company_type,
			basicinfo_operating_range, basicinfo_bank_account,
			basicinfo_account, basicinfo_company_contacts,
			basicinfo_tax_payment_place, basicinfo_admin_phone,
			basicinfo_local_tax_place, basicinfo_income_tax_place,
			basicinfo_approved_taxes, basicinfo_remarks,
			basicinfo_local_admin_phone;// 单位负责人，制表人，信用代码，注册资金,注册地址,公司类型，经营范围，开户银行，账号,企业联系人,国税纳税地址,
										// 管理员及电话,地税纳税地点,所得税归属地,核定税种，备注
	// 基础信息引导页面 PoputWindow
	// private PopupWindow mBasicGuidePopWindow;
	private LinearLayout go_back;
	private TextView mTvTitle;
	private EditAddBasicInfoBean addBasicInfoBean;
	private PersonalRequestHelper mPersonalRequestHelper;
	private String mUserToken;

	// private boolean mIsShowGuide = true;// Activity启动后引导页只显示一次

	private LinearLayout mLlContainer1; // 上半部分容器
	private LinearLayout mLlContainer2; // 下半部分容器
	private TextView mTvPerfectInfo; // 完善信息按钮
	private boolean mIsOpen = false; // 是否已经打开

	private boolean mIsModified = false; // 是否已经修改过数据

	private UserBean userBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library_add_company);
		initView();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		mContext = AddCompanyActivity.this;
		mPersonalRequestHelper = PersonalRequestHelper.getInstance();
//		mUserID = mSharedpreferences.getDatas(mContext).getUserInfo().getId();
		mOrgID = getIntent().getStringExtra("orgId");
		mUserID = getIntent().getStringExtra("USER_ID");
		token = getIntent().getStringExtra("TOKEN");
		if (TextUtils.isEmpty(mOrgID)) {
			setDefaultData();
		} else {
//			retrieveCompany();
		}
	}

	/**
	 * 设置默认数据
	 */
	private void setDefaultData() {
		// 启用日期
		mTvCreateDate.setText(DateUtil.getCurrentDate());
		// 所在城市
		mCityID = "1";
		mTvCity.setText("北京市");
		// 纳税规模
		mTaxPayer = 1;
		mTvTaxPayer.setText("一般纳税人");
		// 编码格式
		mSubjectCode = 2;
		basicinfo_code_type.setText("4-2-2");
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			// if (mIsShowGuide)
			// 显示引导页
			// showUserInfoGuide();
			// mIsShowGuide = false;
		}
	}

	private void initTitle() {
		go_back = (LinearLayout) findViewById(R.id.go_back_library);
		mTvTitle = (TextView) findViewById(R.id.title_library);
		go_back.setVisibility(View.VISIBLE);
		go_back.setOnClickListener(this);
		mTvTitle.setText(getResources().getString(R.string.add_company));
	}

	/**
	 * 初始化页面
	 */
	private void initView() {
		initTitle();

		mLlContainer1 = (LinearLayout) findViewById(R.id.basicinfo_container1);
		mLlContainer2 = (LinearLayout) findViewById(R.id.basicinfo_container2);
		mTvPerfectInfo = (TextView) findViewById(R.id.basicinfo_perfect_info);

		mRLParent = (RelativeLayout) findViewById(R.id.rl_parent);
		mLayer = findViewById(R.id.basicinfo_layer);
		mEdtUser = (EditText) findViewById(R.id.basicinfo_name_editor);
		mEdtEmail = (EditText) findViewById(R.id.basicinfo_email_editor);
		mEdtOrgName = (EditText) findViewById(R.id.basicinfo_orgname_editor);
		mEdtLegelPerson = (EditText) findViewById(R.id.basicinfo_legalperson_editor);
		mEdtTel = (EditText) findViewById(R.id.basicinfo_tel_editor);
		mTvTrade = (TextView) findViewById(R.id.basicinfo_trade_editor);
		mTvCity = (TextView) findViewById(R.id.basicinfo_city_editor);
		mBtnSave = (Button) findViewById(R.id.basicinfo_save_btn);
		mTvTaxPayer = (TextView) findViewById(R.id.basicinfo_taxpayer_type);
		mTvCreateDate = (TextView) findViewById(R.id.tv_select_time);
		mTvSubjectCode = (TextView) findViewById(R.id.basicinfo_code_type);
		mBtnSave = (Button) findViewById(R.id.basicinfo_save_btn);

		mTvSubjectCode.setOnClickListener(this);
		mTvCreateDate.setOnClickListener(this);
		mTvTaxPayer.setOnClickListener(this);
		mTvTrade.setOnClickListener(this);
		mTvCity.setOnClickListener(this);
		mBtnSave.setOnClickListener(this);
		mTvPerfectInfo.setOnClickListener(this);

		basicinfo_code_type = (TextView) findViewById(R.id.basicinfo_code_type);// 编码格式,成立时间
		basicinfo_code_type.setOnClickListener(this);
		baseinfo_date_of_establishment = (TextView) findViewById(R.id.baseinfo_date_of_establishment);
		baseinfo_date_of_establishment.setOnClickListener(this);
		basicinfo_unitLeader = (EditText) findViewById(R.id.basicinfo_unitLeader);// 单位负责人
		basicinfo_lister = (EditText) findViewById(R.id.basicinfo_lister);// 制表人
		basicinfo_credit_code = (EditText) findViewById(R.id.basicinfo_credit_code);// 信用代码
		basicinfo_registered_capital = (EditText) findViewById(R.id.basicinfo_registered_capital);// 注册资金
		basicinfo_registered_address = (EditText) findViewById(R.id.basicinfo_registered_address);// 注册地址
		basicinfo_company_type = (EditText) findViewById(R.id.basicinfo_company_type);// 公司类型
		basicinfo_operating_range = (EditText) findViewById(R.id.basicinfo_operating_range);// 经营范围
		basicinfo_bank_account = (EditText) findViewById(R.id.basicinfo_bank_account);// 开户银行
		basicinfo_account = (EditText) findViewById(R.id.basicinfo_account);// 账号
		basicinfo_company_contacts = (EditText) findViewById(R.id.basicinfo_company_contacts);// 企业联系人
		basicinfo_tax_payment_place = (EditText) findViewById(R.id.basicinfo_tax_payment_place);// 国税纳税地址
		basicinfo_admin_phone = (EditText) findViewById(R.id.basicinfo_admin_phone);// 管理员及电话
		basicinfo_local_tax_place = (EditText) findViewById(R.id.basicinfo_local_tax_place);// 地税纳税地点
		basicinfo_income_tax_place = (EditText) findViewById(R.id.basicinfo_income_tax_place);// 所得税归属地
		basicinfo_approved_taxes = (EditText) findViewById(R.id.basicinfo_approved_taxes);// 核定税种
		basicinfo_remarks = (EditText) findViewById(R.id.basicinfo_remarks);// 备注
		basicinfo_local_admin_phone = (EditText) findViewById(R.id.basicinfo_local_admin_phone);// 地税管理员及电话

	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		if (view.getId() == R.id.basicinfo_city_editor
				|| view.getId() == R.id.basicinfo_trade_editor) {
			mUserName = mEdtUser.getText().toString().trim();
			mEmail = mEdtEmail.getText().toString().trim();
			mOrgName = mEdtOrgName.getText().toString().trim();
			mLegelPerson = mEdtLegelPerson.getText().toString().trim();
			mTelephone = mEdtTel.getText().toString().trim();
		}
		int i = view.getId();
		if (i == R.id.go_back_library) {
			intent = new Intent();
			intent.putExtra("isModified", mIsModified);
			setResult(1001, intent);
			finish();

		} else if (i == R.id.basicinfo_perfect_info) {
			mIsOpen = !mIsOpen;
			Drawable right = mIsOpen ? getResources().getDrawable(
					R.drawable.arrow_up_black_library) : getResources().getDrawable(
					R.drawable.arrow_down_black_library);
			right.setBounds(0, 0, right.getMinimumWidth(),
					right.getMinimumHeight());
			mTvPerfectInfo.setCompoundDrawables(null, null, right, null);
			mLlContainer2.setVisibility(mIsOpen ? View.VISIBLE : View.GONE);

		} else if (i == R.id.basicinfo_trade_editor) {
			intent = new Intent(mContext, ChooseTradeActivity.class);
			startActivityForResult(intent, 20);

		} else if (i == R.id.basicinfo_city_editor) {
			intent = new Intent(mContext, ChooseCityActivity.class);
			startActivityForResult(intent, 10);

		} else if (i == R.id.basicinfo_save_btn) {
			if ("一般纳税人".equals(mTvTaxPayer.getText().toString())) {
				mTaxPayer = 1;
			} else if ("小规模纳税人".equals(mTvTaxPayer.getText().toString())) {
				mTaxPayer = 2;
			}
			if (verify()) {
				showProgressDialog("正在保存...");
				mPersonalRequestHelper.createOrg(mUserToken, addBasicInfoBean,
						new BaseHttpResopnse(mContext) {

							@Override
							public void onFailure(int statusCode,
												  Header[] headers, String responseString,
												  Throwable throwable) {
								dismissProgressDialog();
							}

							@Override
							public void onSuccess(int statusCode,
												  Header[] headers, String responseString) {
								BaseStringResponse response = JsonUtils.fromJson(responseString, BaseStringResponse.class);
								if (response.isSuccess()) {
									mIsModified = true;
									Intent intent = new Intent();
									intent.putExtra("isModified", mIsModified);
									setResult(1001, intent);
									finish();
								} else {
									Toast.makeText(mContext,
											response.getMessage() + "",
											Toast.LENGTH_SHORT).show();
								}
								dismissProgressDialog();
							}
						});
			}


		} else if (i == R.id.basicinfo_taxpayer_type) {
			mLayer.setVisibility(View.VISIBLE);
			ArrayList<String> listTaxpayer = new ArrayList<String>();
			listTaxpayer.add("一般纳税人");
			listTaxpayer.add("小规模纳税人");
			PopupwindowListView viewPopWindow = new PopupwindowListView(
					AddCompanyActivity.this, listTaxpayer, mTvTaxPayer);
			// view.setWidth(taxpayerType.getMeasuredWidth());
			viewPopWindow.showAtLocation(mTvTaxPayer, Gravity.BOTTOM, 0, 0);
			viewPopWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					mLayer.setVisibility(View.GONE);
				}
			});

		} else if (i == R.id.basicinfo_code_type) {
			mLayer.setVisibility(View.VISIBLE);
			ArrayList<String> listCodeType = new ArrayList<String>();
			listCodeType.add("4-2-2");
			listCodeType.add("4-3-3");
			listCodeType.add("4-4-4");
			PopupwindowListView codeTypeView = new PopupwindowListView(
					AddCompanyActivity.this, listCodeType, mTvSubjectCode);
			// view.setWidth(taxpayerType.getMeasuredWidth());
			codeTypeView.showAtLocation(mTvSubjectCode, Gravity.BOTTOM, 0, 0);
			codeTypeView.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					mLayer.setVisibility(View.GONE);
				}
			});

		} else if (i == R.id.tv_select_time) {
			setTextViewDate(mContext, mTvCreateDate);

		} else if (i == R.id.baseinfo_date_of_establishment) {
			setEstablishment(mContext, baseinfo_date_of_establishment);

		}
	}

	/*
	 * 选择时间
	 */

	public static void setTextViewDate(Context context, final TextView textView) {
		Calendar calendarStart = Calendar.getInstance();
		DatePickerDialog dialogStart = new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker dp, int year, int month,
							int dayOfMonth) {
						String months;
						if ((month + 1) < 10) {
							months = "0" + (month + 1);
						} else {
							months = "" + (month + 1);
						}
						textView.setText(year + "-" + months);
					}
				}, calendarStart.get(Calendar.YEAR),
				calendarStart.get(Calendar.MONTH),
				calendarStart.get(Calendar.DAY_OF_MONTH));
		dialogStart.show();
		DatePicker datePicker = dialogStart.getDatePicker();
		datePicker = findDatePicker((ViewGroup) dialogStart.getWindow()
				.getDecorView());
		if (datePicker != null) {
			((ViewGroup) ((ViewGroup) datePicker.getChildAt(0)).getChildAt(0))
					.getChildAt(2).setVisibility(View.GONE);
		}
	}

	/*
	 * 选择时间
	 */
	public static void setEstablishment(Context context, final TextView textView) {
		Calendar calendarStart = Calendar.getInstance();
		DatePickerDialog dialogStart = new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker dp, int year, int month,
							int dayOfMonth) {
						String months;
						String day;
						if ((month + 1) < 10) {
							months = "0" + (month + 1);
						} else {
							months = "" + (month + 1);
						}
						if (dayOfMonth < 10) {
							day = "0" + dayOfMonth;
						} else {
							day = dayOfMonth + "";
						}
						textView.setText(year + "-" + months + "-" + day);
					}
				}, calendarStart.get(Calendar.YEAR),
				calendarStart.get(Calendar.MONTH),
				calendarStart.get(Calendar.DAY_OF_MONTH));
		dialogStart.show();
		DatePicker datePicker = dialogStart.getDatePicker();
		datePicker = findDatePicker((ViewGroup) dialogStart.getWindow()
				.getDecorView());
		// if (datePicker != null) {
		// ((ViewGroup) ((ViewGroup)
		// datePicker.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
		// }
	}

	private final static DatePicker findDatePicker(ViewGroup group) {
		if (group != null) {
			for (int i = 0, j = group.getChildCount(); i < j; i++) {
				View child = group.getChildAt(i);
				if (child instanceof DatePicker) {
					return (DatePicker) child;
				} else if (child instanceof ViewGroup) {
					DatePicker result = findDatePicker((ViewGroup) child);
					if (result != null)
						return result;
				}
			}
		}
		return null;
	}

	/**
	 * 数据校验
	 * 
	 * @return
	 */
	private boolean verify() {
		String name = mEdtUser.getText().toString().trim(); // 姓名,2-6位
		String email = mEdtEmail.getText().toString().trim(); // 邮箱,0-50位
		String orgName = mEdtOrgName.getText().toString().trim(); // 公司名称,4-20位
		String legalPerson = mEdtLegelPerson.getText().toString().trim(); // 企业法人姓名,2-6位
		String tel = mEdtTel.getText().toString().trim(); // 16位
		String taxpayer = mTvTaxPayer.getText().toString().trim();
		String selectDate = mTvCreateDate.getText().toString().trim();

		String codeType = basicinfo_code_type.getText().toString().trim();// 编码格式
		String dateOfEstablishment = baseinfo_date_of_establishment.getText()
				.toString().trim();// 成立时间
		String unitLeader = basicinfo_unitLeader.getText().toString().trim();// 单位负责人
		String lister = basicinfo_lister.getText().toString().trim();// 制表人
		String creditCode = basicinfo_credit_code.getText().toString().trim();// 信用代码
		String registeredCapital = basicinfo_registered_capital.getText()
				.toString().trim();// 注册资金
		String registeredAddress = basicinfo_registered_address.getText()
				.toString().trim();// 注册地址
		String companyType = basicinfo_company_type.getText().toString().trim();// 公司类型
		String operatingRange = basicinfo_operating_range.getText().toString()
				.trim();// 经营范围
		String bankAccount = basicinfo_bank_account.getText().toString().trim();// 开户银行
		String account = basicinfo_account.getText().toString().trim();// 账号
		String companyContacts = basicinfo_company_contacts.getText()
				.toString().trim();// 企业联系人
		String taxPaymentPlace = basicinfo_tax_payment_place.getText()
				.toString().trim();// 国税纳税地址
		String adminPhone = basicinfo_admin_phone.getText().toString().trim();// 管理员及电话
		String localTaxPlace = basicinfo_local_tax_place.getText().toString()
				.trim();// 地税纳税地点
		String incomeTaxPlace = basicinfo_income_tax_place.getText().toString()
				.trim();// 所得税归属地
		String approvedTaxes = basicinfo_approved_taxes.getText().toString()
				.trim();// 核定税种
		String remarks = basicinfo_remarks.getText().toString().trim();// 备注
		String localAdminPhone = basicinfo_local_admin_phone.getText()
				.toString().trim();// 地税管理员及电话
		if (codeType.equals("4-2-2")) {
			mSubjectCode = 2;
		} else if (codeType.equals("4-3-3")) {
			mSubjectCode = 3;
		} else if (codeType.equals("4-4-4")) {
			mSubjectCode = 4;
		}
		// String tradeName = tradeEditor.getText().toString().trim();
		// String cityName = cityEditor.getText().toString().trim();

		addBasicInfoBean = new EditAddBasicInfoBean();
		addBasicInfoBean.setOrgId(mOrgID);
		addBasicInfoBean.setOrgName(orgName);
		addBasicInfoBean.setUserId(mUserID);
		addBasicInfoBean.setName(name);
		addBasicInfoBean.setValidTime(selectDate);
		addBasicInfoBean.setCityId(mCityID);
		addBasicInfoBean.setTaxpayerType(mTaxPayer);
		addBasicInfoBean.setCodeFormat(mSubjectCode);
		addBasicInfoBean.setCreditCode(creditCode);
		addBasicInfoBean.setEstablishDate(dateOfEstablishment);
		addBasicInfoBean.setLegalPerson(legalPerson);
		addBasicInfoBean.setRegisterCapital(registeredCapital);
		addBasicInfoBean.setRegisterAddress(registeredAddress);
		addBasicInfoBean.setCompanyType(companyType);
		addBasicInfoBean.setBusinessScope(operatingRange);
		addBasicInfoBean.setTradeId(mTradeId);
		addBasicInfoBean.setAccountBank(bankAccount);
		addBasicInfoBean.setAccountNumber(account);
		addBasicInfoBean.setLinkman(companyContacts);
		addBasicInfoBean.setEmail(email);
		addBasicInfoBean.setTel(tel);
		addBasicInfoBean.setNationalTaxAddress(taxPaymentPlace);
		addBasicInfoBean.setNationalTaxAdminTel(adminPhone);
		addBasicInfoBean.setLocalTaxBureauAddress(localTaxPlace);
		addBasicInfoBean.setLocalTaxBureauAdminTel(localAdminPhone);
		addBasicInfoBean.setIncomeTaxBelonging(incomeTaxPlace);
		addBasicInfoBean.setCheckRatifyTaxes(approvedTaxes);
		addBasicInfoBean.setRemark(remarks);
		addBasicInfoBean.setUnitLeader(unitLeader);
		addBasicInfoBean.setLister(lister);

		if (TextUtils.isEmpty(selectDate)) {
			Toast.makeText(mContext,
					getResources().getString(R.string.basicinfo_please_select_dates),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (TextUtils.isEmpty(mTvCity.getText().toString().trim())) {
			Toast.makeText(mContext,
					getResources().getString(R.string.basicinfo_please_select_city),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (TextUtils.isEmpty(taxpayer)) {
			Toast.makeText(mContext,
					getResources().getString(R.string.basicinfo_please_select_tax_scale),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (TextUtils.isEmpty(codeType)) {
			Toast.makeText(mContext,
					getResources().getString(R.string.basicinfo_please_select_code),
					Toast.LENGTH_SHORT).show();
			return false;
		}

		// int length = 0;
		// if (!TextUtils.isEmpty(name)) {
		// length = name.length();
		// if (length < 2 || length > 6) {
		// Toast.makeText(mContext, R.string.basicinfo_name_err_msg,
		// Toast.LENGTH_SHORT).show();
		// return false;
		// }
		// }
		if (!TextUtils.isEmpty(email)) {
			String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
			Pattern pattern = Pattern.compile(str);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				Toast.makeText(mContext, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		// if (!TextUtils.isEmpty(orgName)) {
		// length = orgName.length();
		// if (length > 20) {
		// Toast.makeText(mContext, R.string.basicinfo_orgname_err_msg,
		// Toast.LENGTH_SHORT).show();
		// return false;
		// }
		// }
		// if (!TextUtils.isEmpty(legalPerson)) {
		// length = legalPerson.length();
		// if (length < 2 || length > 6) {
		// Toast.makeText(mContext,
		// R.string.basicinfo_legalperson_err_msg,
		// Toast.LENGTH_SHORT).show();
		// return false;
		// }
		// }
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 10: // 选择城市
			if (data != null) {
				Datas cityDatas = (Datas) data.getSerializableExtra("datas");
				mCityID = cityDatas.getId();
				mTvCity.setText(cityDatas.getName());
			}
			break;
		case 20: // 选择行业
			if (data != null) {
				TradeDatas tradeDatas = (TradeDatas) data
						.getSerializableExtra("datas");
				mTradeId = tradeDatas.getId();
				mTvTrade.setText(tradeDatas.getName());
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}

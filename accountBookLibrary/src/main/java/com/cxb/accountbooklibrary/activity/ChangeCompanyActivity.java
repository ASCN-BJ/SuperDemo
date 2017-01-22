package com.cxb.accountbooklibrary.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cxb.accountbooklibrary.BaseActivity;
import com.cxb.accountbooklibrary.R;
import com.cxb.accountbooklibrary.adapter.CompanyAdapter;
import com.cxb.accountbooklibrary.adapter.CompanyAdapter.OnEnterClickCallBack;
import com.cxb.accountbooklibrary.bean.BasicUserInfoBean.Organization;
import com.cxb.accountbooklibrary.net.BaseHttpResopnse;
import com.cxb.accountbooklibrary.net.PersonalRequestHelper;
import com.cxb.accountbooklibrary.net.response.BaseStringResponse;
import com.cxb.accountbooklibrary.net.unit.JsonUtils;
import com.cxb.accountbooklibrary.net.unit.JsonUtilsLocal;
import com.cxb.accountbooklibrary.unit.ScreenUtils;
import com.cxb.accountbooklibrary.unit.ToastUtil;
import com.google.gson.reflect.TypeToken;

/**
 * 账套页面
 * 
 * * 使用时需要在 AndroidManifest.xml 中注入以下Activity
 * （AddCompanyActivity， ChangeCompanyActivity,
 * ChooseCityActivity, ChooseTradeActivity）
 * 
 * @author Devin.Ding
 * 
 */
public class ChangeCompanyActivity extends BaseActivity implements
		OnClickListener {

	private Context mContext;
	private PersonalRequestHelper mHelper;
	private LinearLayout mLlRightMenu;
	private TextView mTvRightText;
	// 公司列表
	private ListView mLvCompanyLists;
	// 列表数据
	private ArrayList<Organization> mCompanies;
	// 数据适配器
	private CompanyAdapter mCompanyAdapter;
	private String token = "USER_TOKEN_18310695171_UG_2b5e5adb-21d8-4286-9850-9f13c030d829";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library_change_company);
		mContext = this;
		mHelper = PersonalRequestHelper.getInstance();
//		token = getIntent().getStringExtra("TOKEN");
		initViews();
		initData();
		initEvents();
	}
	
	/**
	 * 初始化组件
	 * 
	 * @param view
	 */
	private void initViews() {
		initTitle();
		mLvCompanyLists = (ListView) findViewById(R.id.change_company_list1);
	}

	/**
	 * 初始化标题
	 */
	private void initTitle() {
//		mLlRightMenu = (LinearLayout) mActivity.findViewById(R.id.right_menu);
//		mLlRightMenu.setVisibility(View.VISIBLE);
//		mLlRightMenu.setOnClickListener(this);
//		mTvRightText = (TextView) mActivity.findViewById(R.id.right_text);
//		mTvRightText.setVisibility(View.VISIBLE);
//		mTvRightText.setText(mActivity.getResources().getString(
//				R.string.add_company));
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		mCompanies = new ArrayList();
		mCompanyAdapter = new CompanyAdapter(mContext, mCompanies, new OnEnterClickCallBack() {

					@Override
					public void onEnter(int pos) {
						// Toast.makeText(mContext, "pos -> " + pos,
						// Toast.LENGTH_SHORT).show();
						changeCompany(pos);
					}
				});
		mLvCompanyLists.setAdapter(mCompanyAdapter);
		retrieveOrgList();
	}

	/**
	 * 初始化事件
	 */
	private void initEvents() {
		mLvCompanyLists.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				// Intent it = new Intent(mContext, AddCompanyActivity.class);
				// it.putExtra("orgId", mCompanies.get(position).getId());
				// startActivityForResult(it, 1001);
			}
		});
		mLvCompanyLists
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0, View v,
							int position, long arg3) {
						// 如果不是当前的公司则显示删除菜单
						if (!"1".equals(mCompanies.get(position).getIsCurrentUse())) {
							showDelMenu(v, mCompanies.get(position).getId());
						}
						return true;
					}
				});
	}

	/**
	 * 获取公司列表数据
	 */
	private void retrieveOrgList() {
		showProgressDialog(mContext.getResources().getString(R.string.loading));
//		String token = mSharedpreferences.getUserToken(mContext);
		mHelper.retrieveOrgList(token, new BaseHttpResopnse(mContext) {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				BaseStringResponse response = JsonUtils.fromJson(arg2,
						BaseStringResponse.class);
				dismissProgressDialog();
				if (response.isSuccess()) {
					Type dataType = new TypeToken<ArrayList<Organization>>() {
					}.getType();
					mCompanies = JsonUtilsLocal.fromJson(response.getData(),
							dataType);
					mCompanyAdapter.setmCompanies(mCompanies);
				} else {
					Toast.makeText(mContext, response.getMessage() + "",
							Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				dismissProgressDialog();
			}
		});
	}

	/**
	 * 切换公司
	 */
	private void changeCompany(int position) {
		showProgressDialog("正在切换...");
		mHelper.selectZT(token, mCompanies.get(position).getId(), new BaseHttpResopnse(mContext) {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				BaseStringResponse response = new BaseStringResponse();
				response = JsonUtils.fromJson(arg2, BaseStringResponse.class);
				if (response.isSuccess()) {
//					UserBean bean = JsonUtilsLocal.fromJson(response.getData(), UserBean.class);
//					retrieveOrgList();
//					String id = bean.getUserInfo().getId();
//					String mobile = bean.getUserInfo().getMobile();
//					String token = bean.getUserToken();
//					mSharedpreferences.WriteSharedPreferences(getActivity(),
//							mobile, id, token, bean);
//					mSharedpreferences.writeIsBalance(mContext, bean
//							.getOrganization().getIsBalance());
//					// LeftFragment.getInstance.getSubjectStatus();
//					// LeftFragment.getInstance.onResume();
//					Intent intent = new Intent();
//					intent.setClass(mContext, HomePageActivity.class);
//					startActivity(intent);
				} else {
					Toast.makeText(mContext, response.getMessage() + "",
							Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				dismissProgressDialog();
			}
		});
	}

	protected void showDelMenu(View view, final String orgId) {
		final View v = View.inflate(mContext, R.layout.popup_library_delete, null);
		final PopupWindow popWindow = new PopupWindow(v,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		Button btnDel = (Button) v.findViewById(R.id.btn_delete);
		btnDel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showProgressDialog("正在删除...");
				mHelper.deleteZT(token, orgId, new BaseHttpResopnse(mContext) {

							@Override
							public void onSuccess(int arg0, Header[] arg1,
									String arg2) {
								dismissProgressDialog();
								BaseStringResponse response = new BaseStringResponse();
								response = JsonUtils.fromJson(arg2,
										BaseStringResponse.class);
								if (response.isSuccess()) {
									ToastUtil.showToastShort(mContext, "删除成功");
									retrieveOrgList();
								} else {
									ToastUtil.showToastShort(mContext, response.getMessage());
								}
							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
									String arg2, Throwable arg3) {
								dismissProgressDialog();
							}
						});
				popWindow.dismiss();
			}
		});

		popWindow.setBackgroundDrawable(new BitmapDrawable());
		popWindow.setTouchable(true);
		popWindow.setFocusable(true);
		popWindow.setOutsideTouchable(true);
		// 获取被点击项所在位置
		int[] arr = new int[2];
		view.getLocationOnScreen(arr);
		int mScreenHeight = ScreenUtils.getScreenHeight(mContext);
		// 在指定位置显示弹窗, 以底部中间为基准点
		popWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER, 0,
				mScreenHeight - arr[1]);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
//		case R.id.right_menu:
//			Intent intent = new Intent(mContext, AddCompanyActivity.class);
//			startActivityForResult(intent, 1001);
//			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null) {
			return;
		}
		if (requestCode == 1001) {
			if (data.getBooleanExtra("isModified", false)) {
				retrieveOrgList();
			}
		}
	}

	@Override
	public void onDestroy() {
		mLlRightMenu.setVisibility(View.GONE);
		mTvRightText.setVisibility(View.GONE);
		super.onDestroy();
	}
}

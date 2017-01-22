package com.cxb.accountbooklibrary.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxb.accountbooklibrary.R;
import com.cxb.accountbooklibrary.bean.BasicUserInfoBean.Organization;
import com.cxb.accountbooklibrary.unit.DateUtil;

/**
 * 切换公司数据适配器
 * @author Devin.Ding
 *
 */
public class CompanyAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Organization> mCompanies;

	private OnEnterClickCallBack mOnEnterClickCallBack;

	public CompanyAdapter(Context context, ArrayList<Organization> companies,
			OnEnterClickCallBack onEnterClickCallBack) {
		this.mContext = context;
		this.mCompanies = companies;
		this.mOnEnterClickCallBack = onEnterClickCallBack;
	}

	/**
	 * @param mCompanies
	 *            the mCompanies to set
	 */
	public void setmCompanies(ArrayList<Organization> mCompanies) {
		this.mCompanies = mCompanies;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mCompanies.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mCompanies.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Organization org = (Organization) mCompanies.get(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_library_change_company, null);
			// 获取组件
			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.tv_item_change_company_name);
			viewHolder.tvTime = (TextView) convertView
					.findViewById(R.id.tv_item_change_company_time);
			viewHolder.tvEnter = (TextView) convertView
					.findViewById(R.id.tv_item_change_company_enter);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// 设置资源
		viewHolder.tvName.setText(org.getOrgName());
		viewHolder.tvTime.setText("加入时间："
				+ (!TextUtils.isEmpty(org.getValidTime()) ? DateUtil
						.TimeToDate(Long.valueOf(org.getValidTime())) : ""));
		if ("0".equals(org.getIsCurrentUse())) {
			viewHolder.tvEnter.setText("进入");
			viewHolder.tvEnter.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.org_set_hierarchy_btn_selector_library));
			viewHolder.tvEnter.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mOnEnterClickCallBack.onEnter(position);
				}
			});
		} else {
			viewHolder.tvEnter.setText("当前所在");
			viewHolder.tvEnter.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.org_set_hierarchy_btn_tran_library));
			viewHolder.tvEnter.setOnClickListener(null);
		}

		return convertView;
	}

	class ViewHolder {
		TextView tvName;
		TextView tvTime;
		TextView tvEnter;
	}

	public interface OnEnterClickCallBack {
		void onEnter(int pos);
	}
}

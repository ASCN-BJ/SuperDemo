package com.cxb.accountbooklibrary.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.cxb.accountbooklibrary.R;
import com.cxb.accountbooklibrary.bean.TradeBean.TradeDatas;

/**
 * 行业列表适配器
 * @author Devin.Ding
 *
 */
public class TradeAdapter extends BaseAdapter implements SectionIndexer {
	private static final String TAG = TradeAdapter.class.getSimpleName();
	private Context context;
	private List<TradeDatas> data;
	private LayoutInflater inflater;

	public TradeAdapter(Context context, ArrayList<TradeDatas> allListItems) {
		this.context = context;
		this.data = allListItems;
		this.inflater = LayoutInflater.from(context);
	}

	/**
	 * 更新数据
	 * 
	 * @param data
	 */
	public void setData(List<TradeDatas> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder viewHolder = null;
		final TradeDatas bean = data.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = inflater.inflate(R.layout.letter_library_list_item, null);
			viewHolder.tvName = (TextView) view
					.findViewById(R.id.letter_list_item_name);
			viewHolder.tvInitial = (TextView) view
					.findViewById(R.id.letter_list_item_initial);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		int section = getSectionForPosition(position);
		if (position == getPositionForSection(section)) {
			viewHolder.tvInitial.setVisibility(View.VISIBLE);
			viewHolder.tvInitial.setText(bean.getInitial());
		} else {
			viewHolder.tvInitial.setVisibility(View.GONE);
		}
		viewHolder.tvName.setText(this.data.get(position).getName());
		viewHolder.tvName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.putExtra("datas", bean);
				((Activity) context).setResult(20, intent);
				((Activity) context).finish();
			}
		});
		return view;
	}

	private class ViewHolder {
		private TextView tvName;
		private TextView tvInitial;
	}

	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String str = data.get(i).getInitial();
			char firstChar = str.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		return data.get(position).getInitial().charAt(0);
	}

	@Override
	public Object[] getSections() {
		return null;
	}

}

package com.cxb.accountbooklibrary.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cxb.accountbooklibrary.R;


/**
 * 下拉菜单（String）
 * @author Devin.Ding
 *
 */
public class PopupwindowListView extends PopupWindow implements OnClickListener{

	private View mMenuView;
	private ListView mListView;
	private TextView mTvCancel;
	public PopupwindowListView(final Activity context, final ArrayList<String> arrayList, final TextView view) {
		super(context);
		mMenuView = View.inflate(context, R.layout.popup_library_listview, null);
		mListView = (ListView) mMenuView.findViewById(R.id.lv_pop);
		mTvCancel = (TextView) mMenuView.findViewById(R.id.tv_bottom_menu_cancel);
		PopupwindowAdapter adapter = new PopupwindowAdapter(context, arrayList);
		mListView.setAdapter(adapter);
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
//		if (arrayList.size() > 10) {
//			mListView..setHeight(1000);
//		}else {
//		}
		this.setFocusable(true);
		this.setAnimationStyle(R.style.popWindow_animation);
		ColorDrawable dw = new ColorDrawable(0xffffffff);
		this.setBackgroundDrawable(dw);
		mTvCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dismiss();
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (arg2 > 0) {
					view.setText(arrayList.get(arg2));
					dismiss();
				}
			}
		});
	}

	class PopupwindowAdapter extends BaseAdapter{
		private Context mContext;
		private ArrayList<String> arrayList;
		public PopupwindowAdapter(Context mContext,
				ArrayList<String> arrayList) {
			this.arrayList = arrayList;
			this.mContext = mContext;
		}

		@Override
		public int getCount() {
			return arrayList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return arrayList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			}else {
				view = View.inflate(mContext, R.layout.item_library_popup_listview, null);
				holder = new ViewHolder();
				holder.tv_popup = (TextView) view.findViewById(R.id.tv_popup);
				view.setTag(holder);
			}
			if (position == 0) {
				holder.tv_popup.setTextColor(mContext.getResources().getColor(R.color.text_b1b1b1));
			}else {
				holder.tv_popup.setTextColor(mContext.getResources().getColor(R.color.black));
			}
			holder.tv_popup.setText(arrayList.get(position));
			return view;
		}
		public final class ViewHolder{ 
			private TextView tv_popup;
		}
	}

	@Override
	public void onClick(View arg0) {
		
	}
}

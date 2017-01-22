package com.cxb.accountbooklibrary.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cxb.accountbooklibrary.R;
import com.cxb.accountbooklibrary.adapter.CityAdapter;
import com.cxb.accountbooklibrary.bean.CityBean;
import com.cxb.accountbooklibrary.bean.CityBean.Datas;
import com.cxb.accountbooklibrary.net.unit.JsonUtils;
import com.cxb.accountbooklibrary.unit.CharacterParser;
import com.cxb.accountbooklibrary.unit.CxbDUtils;
import com.cxb.accountbooklibrary.view.LetterSideBar;
import com.cxb.accountbooklibrary.view.LetterSideBar.OnTouchingLetterChangedListener;

/**
 * 选择城市
 * 
 * @author
 * 
 */
public class ChooseCityActivity extends Activity implements OnClickListener {
	private LinearLayout go_back;
	private TextView title;

	private EditText choosecity_searcheditor; // 搜索框
	private ListView mCityListView;
	private CityBean bean; // 摘要列表
	private LetterSideBar mLetterSideBar; // 显示字母的侧边栏
	private TextView mDialog; // 显示字母的提示框
	private CharacterParser mParser; // 汉字转换拼音的解析对象

	private CityAdapter adapter;
	private ArrayList<Datas> allListItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library_choosecity);
		initTitle();
		initViews();
		LoadData();
		initEvents();
	}

	private void initTitle() {
		go_back = (LinearLayout) findViewById(R.id.go_back_library);
		title = (TextView) findViewById(R.id.title_library);
		go_back.setOnClickListener(this);
		title.setText(getResources().getString(R.string.basicinfo_choose_city));
	}

	/**
	 * 初始化视图
	 */
	private void initViews() {
		choosecity_searcheditor = (EditText) findViewById(R.id.choosecity_searcheditor);
		mCityListView = (ListView) findViewById(R.id.choosecity_citylist);
		mLetterSideBar = (LetterSideBar) findViewById(R.id.choosecity_lettersidrbar);
		mDialog = (TextView) findViewById(R.id.choosecity_dialog);
//		mCityListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				Intent intent = new Intent();
//				intent.putExtra("cityId", allListItems.get(arg2).getId());
//				intent.putExtra("provinceId", allListItems.get(arg2).getProvinceId());
//				setResult(10, intent);
//			}
//		});
	}

	/**
	 * 获取数据
	 */
	private void LoadData() {
		String resultJson = CxbDUtils.getJson("address.txt",
				ChooseCityActivity.this);
		bean = new CityBean();
		allListItems = new ArrayList<Datas>();
		bean = JsonUtils.fromJson(resultJson, CityBean.class);
		if (bean.isSuccess()) {
			mParser = CharacterParser.getInstance();
			mLetterSideBar.setTextView(mDialog);
			if (bean.getDatas() != null) {
				allListItems = bean.getDatas();
			}
			convertData(allListItems);
			if (allListItems.size() > 0) {
				Collections.sort(allListItems, new PinyinComparator());
			}
			adapter = new CityAdapter(ChooseCityActivity.this, allListItems);
			mCityListView.setAdapter(adapter);
		} else {
			Toast.makeText(ChooseCityActivity.this,
					"加载失败", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * 初始化事件
	 */
	private void initEvents() {
		// 设置侧边栏触摸监听
		mLetterSideBar
				.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

					@Override
					public void onTouchingLetterChanged(String s) {
						// 该字母首次出现的位置
						int position = -10;
						if (adapter != null) {
							position = adapter.getPositionForSection(s
									.charAt(0));
						}

						if (position != -1) {
							mCityListView.setSelection(position);
						}

					}
				});

		// 根据输入框输入值的改变来过滤搜索
		choosecity_searcheditor.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				filterData(arg0.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

			}
		});
	}

	private void convertData(ArrayList<Datas> arrayList) {
		String pinyin;
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getName().equals("重庆市")) {
				pinyin = "chongqingshi";
			}else {
				pinyin = mParser.parse(arrayList.get(i).getName(), true);
			}
			String str = pinyin.substring(0, 1).toUpperCase();
			if (str.matches("[A-Z]")) {
				allListItems.get(i).setInitial(str);
			} else {
				allListItems.get(i).setInitial("常用");
			}
		}
	}

	private void filterData(String filterStr) {
		ArrayList<Datas> filterDateList = new ArrayList<Datas>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = allListItems;
		} else {
			filterDateList.clear();
			for (Datas beans : allListItems) {
				String name = beans.getName();
				if (name.toUpperCase().indexOf(
						filterStr.toString().toUpperCase()) != -1
						|| mParser.parse(name, true).toUpperCase()
								.startsWith(filterStr.toString().toUpperCase())) {
					filterDateList.add(beans);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, new PinyinComparator());
		adapter.setData(filterDateList);
		adapter.notifyDataSetChanged();
	}

	class PinyinComparator implements Comparator<Datas> {

		public int compare(Datas o1, Datas o2) {
			if (o1.getInitial().equals("@") || o2.getInitial().equals("#")) {
				return -1;
			} else if (o1.getInitial().equals("#")
					|| o2.getInitial().equals("@")) {
				return 1;
			} else {
				return o1.getInitial().compareTo(o2.getInitial());
			}
		}

	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.go_back_library) {
			finish();

		} else {
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(10, null);
		}
		return super.onKeyDown(keyCode, event);
	}

}

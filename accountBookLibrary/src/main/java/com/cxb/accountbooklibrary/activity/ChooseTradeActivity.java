package com.cxb.accountbooklibrary.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

import com.cxb.accountbooklibrary.BaseActivity;
import com.cxb.accountbooklibrary.R;
import com.cxb.accountbooklibrary.adapter.TradeAdapter;
import com.cxb.accountbooklibrary.bean.AllListItem;
import com.cxb.accountbooklibrary.bean.TradeBean;
import com.cxb.accountbooklibrary.bean.TradeBean.TradeDatas;
import com.cxb.accountbooklibrary.net.unit.JsonUtils;
import com.cxb.accountbooklibrary.unit.CharacterParser;
import com.cxb.accountbooklibrary.unit.CxbDUtils;
import com.cxb.accountbooklibrary.view.LetterSideBar;
import com.cxb.accountbooklibrary.view.LetterSideBar.OnTouchingLetterChangedListener;

/**
 * 选择行业
 * 
 * @author
 * 
 */
public class ChooseTradeActivity extends BaseActivity implements OnClickListener {
	private LinearLayout go_back;
	private TextView title;

	private EditText choosetrade_searcheditor; // 搜索框
	private ListView mTradeListView;
	private TradeBean bean; // 摘要列表
	private LetterSideBar mLetterSideBar; // 显示字母的侧边栏
	private TextView mDialog; // 显示字母的提示框
	private CharacterParser mParser; // 汉字转换拼音的解析对象

	private TradeAdapter adapter;
	private ArrayList<TradeDatas> allListItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library_choosetrade);
		initTitle();
		initViews();
		LoadData();
		initEvents();
	}

	private void initTitle() {
		go_back = (LinearLayout) findViewById(R.id.go_back_library);
		title = (TextView) findViewById(R.id.title_library);
		go_back.setOnClickListener(this);
		title.setText(getResources().getString(R.string.basicinfo_choose_trade));
	}

	/**
	 * 初始化视图
	 */
	private void initViews() {
		choosetrade_searcheditor = (EditText) findViewById(R.id.choosetrade_searcheditor);
		mTradeListView = (ListView) findViewById(R.id.choosetrade_tradelist);
		mLetterSideBar = (LetterSideBar) findViewById(R.id.choosetrade_lettersidrbar);
		mDialog = (TextView) findViewById(R.id.choosetrade_dialog);
	}

	/**
	 * 获取数据
	 */
	private void LoadData() {
		String resultJson = CxbDUtils.getJson("trade.txt",
				ChooseTradeActivity.this);
		bean = new TradeBean();
		allListItems = new ArrayList<TradeDatas>();
		bean = JsonUtils.fromJson(resultJson, TradeBean.class);
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
			adapter = new TradeAdapter(ChooseTradeActivity.this, allListItems);
			mTradeListView.setAdapter(adapter);
		} else {
			Toast.makeText(ChooseTradeActivity.this, "加载失败", Toast.LENGTH_SHORT)
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
							mTradeListView.setSelection(position);
						}

					}
				});

		// 根据输入框输入值的改变来过滤搜索
		choosetrade_searcheditor.addTextChangedListener(new TextWatcher() {

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

	private void convertData(ArrayList<TradeDatas> arrayList) {
		AllListItem beans = null;
		for (int i = 0; i < arrayList.size(); i++) {
			beans = new AllListItem();
			beans.setName(arrayList.get(i).getName());
			String pinyin = mParser.parse(arrayList.get(i).getName(), true);
			String str = pinyin.substring(0, 1).toUpperCase();
			if (str.matches("[A-Z]")) {
				allListItems.get(i).setInitial(str.toUpperCase());
			} else {
				allListItems.get(i).setInitial("常用");
			}
		}
	}

	private void filterData(String filterStr) {
		ArrayList<TradeDatas> filterDateList = new ArrayList<TradeDatas>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = allListItems;
		} else {
			filterDateList.clear();
			for (TradeDatas beans : allListItems) {
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
	}

	class PinyinComparator implements Comparator<TradeDatas> {

		public int compare(TradeDatas o1, TradeDatas o2) {
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
			setResult(20, null);
		}
		return super.onKeyDown(keyCode, event);
	}

}

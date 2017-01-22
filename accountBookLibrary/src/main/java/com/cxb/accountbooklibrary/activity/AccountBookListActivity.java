package com.cxb.accountbooklibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cxb.accountbooklibrary.R;

/**
 * 账套列表
 * 
 * 使用时需要在 AndroidManifest.xml 中注入以下Activity
 * （AccountBookListActivity，AddCompanyActivity， ChangeCompanyActivity,
 * ChooseCityActivity, ChooseTradeActivity）
 * 
 * @author Devin.Ding
 * 
 */
public class AccountBookListActivity extends Activity {

	private String token;
	private TextView names;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library_account_book);
		names = (TextView) findViewById(R.id.accountName);
		token = getIntent().getStringExtra("TOKEN");
		Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
		if (token != null) {
			names.setText(token);
		}
		names.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

}

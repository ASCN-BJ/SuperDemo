package com.cxb.accountbooklibrary.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cxb.accountbooklibrary.ActivityManager;
import com.cxb.accountbooklibrary.R;

/**
 * 网络访问框
 * 
 * @author Devin.Ding
 *
 */
public class DialogShow {
	/**
	 * dialog展示
	 * @param baseActivity 
	 * @param num
	 * @param text 
	 */
	public static void showDialogView(final Context context, final int num, String text) {
		final Dialog dialog = new Dialog(context, R.style.add_node_dialog);
		Window window = dialog.getWindow();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_library_exit_system);
		dialog.setCanceledOnTouchOutside(false);
		Button yes = (Button) window.findViewById(R.id.yes);
		Button no = (Button) window.findViewById(R.id.no);
		TextView set_text = (TextView) window.findViewById(R.id.set_text);
		set_text.setText(text);
		yes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.cancel();
				switch (num) {
				case 0:
					context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));// 进入无线网络配置界面
					break;
				case 1:
//					((Activity) context).finish();
					ActivityManager.getInstance().exitAll();
					break;
				default:
					break;
				}
			}
		});
		no.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.cancel();
			}
		});
		dialog.show();
	}
}

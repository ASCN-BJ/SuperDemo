package com.example.bj.superdemo.ui.ui;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.utils.viewutil.TextUtil;

import java.math.BigDecimal;

/**
 * 两个值计算数值
 *
 * @author bj
 */
public class CaclActivity extends BaseActivity {
    private EditText et_num1, et_num2, et_show_content;
    private final String TAG = "CaclActivity";
    private Handler mHandler;
    private final int what = 10;

    public void initData() {
        setContentView(R.layout.content_cacl);
        onChange();
        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mHandler.sendEmptyMessage(what);
            }
        });
        et_num2 = (EditText) findViewById(R.id.et_num2);
        et_num2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG, "afterTextChanged: " + s);
                mHandler.sendEmptyMessage(what);
            }
        });
        et_show_content = (EditText) findViewById(R.id.et_show_content);
    }

    private void onChange() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what > 0) {//说明文本改变了
                    double num1;
                    double num2;
                    if (!TextUtils.isEmpty(et_num1.getText())) {
                        num1 = Double.valueOf(et_num1.getText().toString().replaceAll(" ", "").trim());
                    } else {
                        num1 = 0.0d;
                    }
                    if (!TextUtils.isEmpty(et_num2.getText())) {
                        num2 = Double.valueOf(et_num2.getText().toString().replaceAll(" ", "").trim());
                    } else {
                        num2 = 0.0d;
                    }
                    et_show_content.setText(String.valueOf(new BigDecimal(num1).multiply(new BigDecimal(num2)).setScale(8, BigDecimal.ROUND_CEILING).doubleValue()));
                }
            }
        };
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 文本变化
     */
    public interface OnEditTextChangeListner {
        void onChange(String s);
    }
}

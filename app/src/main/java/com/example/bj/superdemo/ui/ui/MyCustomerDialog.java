package com.example.bj.superdemo.ui.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.bj.superdemo.R;

import kotlin.jvm.Throws;

/**
 * Created by bj on 2017-1-3.
 * Description：
 */

public class MyCustomerDialog {

    private AlertDialog mDialog;
    private AlertDialog.Builder mBuilder;
    private View mDialogView;
    private TextView tv_dialog_cancel;//取消
    private TextView tv_dialog_ok;//确认
    private TextView textView;//内容
    private View.OnClickListener mOkListner;
    private View.OnClickListener mCancelListner;

    public MyCustomerDialog(Context mContext) {
        if (mContext != null) {
            if (mBuilder == null) {
                mBuilder = new AlertDialog.Builder(mContext);
                mDialogView = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_common, null);
                tv_dialog_cancel = (TextView) mDialogView.findViewById(R.id.tv_dialog_cancel);
                tv_dialog_ok = (TextView) mDialogView.findViewById(R.id.tv_dialog_ok);
                textView = (TextView) mDialogView.findViewById(R.id.textView);
                mBuilder.setView(mDialogView);
            }
        } else {
            throw new NullPointerException("can't find the context");
        }
    }

    /**
     * 创建对话框
     */
    public MyCustomerDialog createDialog() {
        if (mDialog == null) {
            mDialog = mBuilder.create();
        }
        return this;
    }

    /**
     * 显示对话框
     */
    public void showDialog() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    /**
     * 设置文本内容
     *
     * @param mString
     * @return
     */
    public MyCustomerDialog setContent(String mString) {
        textView.setText(mString);
        return this;
    }

    /**
     * 确定按钮
     *
     * @return
     */
    public MyCustomerDialog setPostiveButton(final View.OnClickListener mListner) {
        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.onClick(v);
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });
        return this;

    }

    /**
     * 取消按钮
     *
     * @return
     */
    public MyCustomerDialog setNegativeButton(final View.OnClickListener mListner) {
        tv_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.onClick(v);
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param flag
     * @return
     */
    public MyCustomerDialog setCancelable(boolean flag) {
        if (mBuilder != null) {
            mBuilder.setCancelable(flag);
        }
        return this;
    }

}

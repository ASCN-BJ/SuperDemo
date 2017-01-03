package com.example.bj.superdemo.ui.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

public class MyDialogActivity extends BaseActivity {
    private AlertDialog.Builder mDialogBuilder;
    private Button btn_show_common_dialog;
    private Button btn_customer_dialog;
    private Button btn_customer_dialog2;
    private MyCustomerDialog myCustomerDialog;

    @Override
    public void initData() {
        setContentView(R.layout.activity_my_dialog);
        btn_show_common_dialog = (Button) findViewById(R.id.btn_show_common_dialog);
        btn_show_common_dialog.setOnClickListener(this);
        btn_customer_dialog = (Button) findViewById(R.id.btn_customer_dialog);
        btn_customer_dialog.setOnClickListener(this);
        btn_customer_dialog2 = (Button) findViewById(R.id.btn_customer_dialog2);
        btn_customer_dialog2.setOnClickListener(this);
        myCustomerDialog = new MyCustomerDialog(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_common_dialog:
                mDialogBuilder = new AlertDialog.Builder(this).setTitle("这是一个title")
                        .setIcon(R.drawable.raw_left)
                        .setMessage("这是一个message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "CANCEL", Toast.LENGTH_SHORT).show();
                            }
                        });
                mDialogBuilder.create().show();
                break;
            case R.id.btn_customer_dialog:
                if (mDialogBuilder != null) {
                    mDialogBuilder = null;
                }
                View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_common, null);
                TextView tv_dialog_cancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
                tv_dialog_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                });
                TextView tv_dialog_ok = (TextView) view.findViewById(R.id.tv_dialog_ok);
                tv_dialog_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                    }
                });
                mDialogBuilder = new AlertDialog.Builder(this)
//                        .setTitle("这是一个自定义title")
//                        .setIcon(R.drawable.raw_left)
//                        .setMessage("这是一个自定义message")
                        .setView(view);
                mDialogBuilder.create().show();
                break;
            case R.id.btn_customer_dialog2:
                myCustomerDialog.setContent("自定义弹框哈哈哈").setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                }).setPostiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                    }
                }).createDialog().showDialog();

                break;
            default:
                break;
        }
    }
}

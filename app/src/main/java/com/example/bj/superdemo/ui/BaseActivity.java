package com.example.bj.superdemo.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 内容描述：
 * 一个activity的基类别
 * -----------------------------------------------------------------------------------------
 * 作者：BJ
 * 日期：2016/7/25
 */
public abstract class BaseActivity extends AutoLayoutActivity implements View.OnClickListener {
    public Context context = BaseActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View view =getWindow().getDecorView();
//        int option=view.SYSTEM_UI_FLAG_FULLSCREEN;
//        view.setSystemUiVisibility(option);
//        android.support.v7.app.ActionBar actionBar=this.getSupportActionBar();
//        actionBar.hide();

        initData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 初始化数据
     */
    public abstract void initData();
}

package com.example.bj.superdemo.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
        initData();
    }

    /**
     * 初始化数据
     */
    public abstract void initData();
}

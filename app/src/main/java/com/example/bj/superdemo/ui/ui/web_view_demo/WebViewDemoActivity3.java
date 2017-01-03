package com.example.bj.superdemo.ui.ui.web_view_demo;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

public class WebViewDemoActivity3 extends BaseActivity {
    private LinearLayout ll_container;

    @Override
    public void initData() {
        setContentView(R.layout.activity_web_view_demo5);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ff = fm.beginTransaction();
        BppFragment fragment = new BppFragment();
//        ff.add(R.id.ll_container, fragment);
    }

    @Override
    public void onClick(View v) {

    }
}

package com.example.bj.superdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.customview.ViewPagerActivity;

public class Main3Activity extends BaseActivity {
    private TextView tv_my_view_pager;
    private TextView iv_viewp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    @Override
    public void initData() {
        tv_my_view_pager= (TextView)this.findViewById(R.id.tv_my_view_pager);
       // tv_my_view_pager.setOnClickListener(this);
        iv_viewp= (TextView) findViewById(R.id.iv_viewp);

       // tv_view_pager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_my_view_pager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            default:
                break;
        }
    }
}

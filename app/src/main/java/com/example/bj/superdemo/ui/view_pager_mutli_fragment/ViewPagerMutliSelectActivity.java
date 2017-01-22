package com.example.bj.superdemo.ui.view_pager_mutli_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

public class ViewPagerMutliSelectActivity extends BaseActivity {
    private ViewPager vp_multi_select;
    private TabLayout tl_table;

    @Override
    public void initData() {
        setContentView(R.layout.activity_view_pager_mutli_select);
        vp_multi_select = (ViewPager) findViewById(R.id.vp_multi_select);
        tl_table = (TabLayout) findViewById(R.id.tl_table);
        setDatas();

    }

    private void setDatas() {
        MutliTabAdapter adapter = new MutliTabAdapter(getSupportFragmentManager(), this);
        vp_multi_select.setAdapter(adapter);
        tl_table.setupWithViewPager(vp_multi_select);
    }

    @Override
    public void onClick(View v) {

    }
}

package com.example.bj.superdemo.ui.view_pager_mutli_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.customview.gallery_picture.WaterFallFragment2;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerMutliSelectActivity extends BaseActivity {
    private ViewPager vp_multi_select;
    private TabLayout tl_table;
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8", "Tab9"};
//    private String[] titles = new String[]{"Tab1", "Tab2"};
    private List<Fragment> mListFragments;
    @Override
    public void initData() {
        setContentView(R.layout.activity_view_pager_mutli_select);
        vp_multi_select = (ViewPager) findViewById(R.id.vp_multi_select);
        tl_table = (TabLayout) findViewById(R.id.tl_table);
        if (titles.length > 5) {
            tl_table.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            tl_table.setTabMode(TabLayout.MODE_FIXED);
        }
        initFragments();
        setDatas();
    }

    private void initFragments() {
        mListFragments=new ArrayList();
//        for (int x=0;x<9;x++){
//            mListFragments.add(WaterFallFragment2.newInstance(x+1));
//        }
    }

    private void setDatas() {
        MutliTabAdapter adapter = new MutliTabAdapter(getSupportFragmentManager(), this, titles,mListFragments);
        vp_multi_select.setAdapter(adapter);
        tl_table.setupWithViewPager(vp_multi_select);
    }

    @Override
    public void onClick(View v) {

    }
}

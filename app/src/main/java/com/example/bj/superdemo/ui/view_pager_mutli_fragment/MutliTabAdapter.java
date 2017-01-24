package com.example.bj.superdemo.ui.view_pager_mutli_fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.bj.superdemo.ui.customview.gallery_picture.WaterFallFragment;
import com.example.bj.superdemo.ui.customview.gallery_picture.WaterFallFragment2;
import com.example.bj.superdemo.ui.ui.RecyclerFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22.
 */

public class MutliTabAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{};
    //    private String[] titles = new String[]{"Tab1", "Tab2","Tab3"};
    private Context context;
    private List<Fragment> mLists;

    public MutliTabAdapter(FragmentManager fm, Context context, String[] titles, List<Fragment> mLists) {
        super(fm);
        this.titles = titles;
        this.context = context;
        this.mLists = mLists;
    }

    @Override
    public Fragment getItem(int position) {
//        return WaterFallFragment2.newInstance(position + 1);
        return RecyclerFragment.getInstance();
//        return mLists.get(position);
    }

    //
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//    }
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

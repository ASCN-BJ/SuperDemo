package com.example.bj.superdemo.ui.ui;

import android.view.View;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseFragment;

/**
 * Created by Administrator on 2017/1/24.
 */

public class RecyclerFragment extends BaseFragment {

    public static RecyclerFragment getInstance() {
        return new RecyclerFragment();
    }

    @Override
    public int getContentID() {
        return R.layout.fragment_water_fall2;
    }

    @Override
    public void initViews(View contentView) {

    }

    @Override
    public void loadDatas() {

    }
}

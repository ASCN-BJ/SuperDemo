package com.example.bj.superdemo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bj.superdemo.R;

/**
 * Created by bj on 2017/1/24.
 */

public abstract class BaseFragment extends Fragment {
    public static int DelayTime = 500;
    private View rootView, contentView;
    //    private LayoutInflater mInflater = LayoutInflater.from(this.getContext());
    private LayoutInflater mInflater = LayoutInflater.from(App.getContext());
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.layout_base_fragment,
                    container, false);
            if (null != contentView) {
                ((ViewGroup) rootView).addView(contentView);
            }
        }
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (null == contentView) {
                dialog = ProgressDialog.show(getActivity(), null, null);
                contentView = mInflater.inflate(getContentID(), null);
                initViews(contentView);
                if (null != rootView) {
                    ((ViewGroup) rootView).addView(contentView);
                }
            }
            loadDatas();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ViewGroup) rootView).removeView(rootView);
    }

    /**
     * 获取要加载资源的布局
     *
     * @return
     */
    public abstract int getContentID();

    /**
     * 初始化布局通过布局进行注入findById的操作等
     *
     * @param contentView
     */
    public abstract void initViews(View contentView);

    /**
     * 加载数据,例如布局操作等
     */
    public abstract void loadDatas();
}

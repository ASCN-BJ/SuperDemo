package com.example.bj.superdemo.ui.mvpdemo_a.views.views;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.base.animator.CommonAlphaAnimator;
import com.example.bj.superdemo.ui.mvpdemo_a.views.iview.IUserInfo;
import com.example.bj.superdemo.ui.mvpdemo_a.views.modle.UserInfo;
import com.example.bj.superdemo.ui.mvpdemo_a.views.presenter.UserPresenter;

public class ShowUserData extends BaseActivity implements IUserInfo {
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_age;
    private LinearLayout show_progress;
    private UserPresenter mPresenter;

    @Override
    public void initData() {
        setContentView(R.layout.activity_show_data);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);
        show_progress = (LinearLayout) findViewById(R.id.show_progress);
        mPresenter = new UserPresenter(this);
        mPresenter.setUserData();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showDialog() {
        show_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(UserInfo userInfo) {
        tv_name.setText(userInfo.getUserName());
        tv_age.setText(String.valueOf(userInfo.getUserAge()));
        tv_sex.setText(userInfo.getUserSex());
    }

    @Override
    public void closeDialog() {
        show_progress.setAnimation(CommonAlphaAnimator.getInstance().showInvisible());
        show_progress.setVisibility(View.GONE);
    }
}

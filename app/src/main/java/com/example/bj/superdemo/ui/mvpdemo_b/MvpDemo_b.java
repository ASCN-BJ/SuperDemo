package com.example.bj.superdemo.ui.mvpdemo_b;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.base.animator.CommonAlphaAnimator;
import com.example.bj.superdemo.ui.mvpdemo_a.views.modle.UserInfo;
import com.example.bj.superdemo.ui.mvpdemo_b.contract.UserInfoConstract;
import com.example.bj.superdemo.ui.mvpdemo_b.presenter.IUserPresenter;
import com.example.bj.superdemo.ui.mvpdemo_b.presenter.ImplPresenter;

public class MvpDemo_b extends BaseActivity implements UserInfoConstract.View {
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_age;
    private LinearLayout show_progress;
    private IUserPresenter mPresenter;

    @Override
    public void initData() {
        setContentView(R.layout.activity_show_data);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);
        show_progress = (LinearLayout) findViewById(R.id.show_progress);
        mPresenter = new ImplPresenter(this);
        mPresenter.start();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showDialog() {
        show_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(UserInfo mUserInfo) {
        tv_name.setText(mUserInfo.getUserName());
        tv_age.setText(String.valueOf(mUserInfo.getUserAge()));
        tv_sex.setText(mUserInfo.getUserSex());
    }

    @Override
    public void dissMissDialog() {
        show_progress.setAnimation(CommonAlphaAnimator.getInstance().showInvisible());
        show_progress.setVisibility(View.GONE);
    }

    @Override
    public String getUserId() {
        return "1234567890";
    }

    @Override
    public void setPresenter(IUserPresenter presenter) {
        this.mPresenter = presenter;
    }
}

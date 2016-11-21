package com.example.bj.superdemo.ui.mvpdemo_b.presenter;

import android.os.Handler;

import com.example.bj.superdemo.ui.mvpdemo_a.views.modle.UserInfo;
import com.example.bj.superdemo.ui.mvpdemo_b.contract.UserInfoConstract;

/**
 * Created by BJ on 2016/11/21.
 */

public class ImplPresenter implements UserInfoConstract.IPresenter {
    private UserInfoConstract.View mView;

    public ImplPresenter(UserInfoConstract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);//一种规范
    }

    @Override
    public void start() {
        loadUserInfo();
    }

    @Override
    public void loadUserInfo() {
        mView.showDialog();
        mView.getUserId();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserInfo mInfo = new UserInfo(13, "test", "man");
                mView.setData(mInfo);
                mView.dissMissDialog();
            }
        }, 3000);
    }
}

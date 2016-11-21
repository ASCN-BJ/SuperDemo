package com.example.bj.superdemo.ui.mvpdemo_a.views.presenter;

import android.os.Handler;

import com.example.bj.superdemo.ui.mvpdemo_a.views.iview.IUserInfo;
import com.example.bj.superdemo.ui.mvpdemo_a.views.modle.UserInfo;

/**
 * Created by BJ on 2016/11/21.
 * mvp的presenter
 */

public class UserPresenter {
    private IUserInfo mUser;

    public UserPresenter(IUserInfo mUser) {
        this.mUser = mUser;
    }

    public void setUserData() {
        mUser.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserInfo mInfo = new UserInfo(24, "bj", "man");
                mUser.showData(mInfo);
                mUser.closeDialog();
            }
        }, 2000);
    }
}

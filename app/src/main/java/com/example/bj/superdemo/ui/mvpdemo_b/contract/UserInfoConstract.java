package com.example.bj.superdemo.ui.mvpdemo_b.contract;

import com.example.bj.superdemo.ui.mvpdemo_a.views.modle.UserInfo;
import com.example.bj.superdemo.ui.mvpdemo_b.presenter.IUserPresenter;

/**
 * Created by BJ on 2016/11/21.
 */

public interface UserInfoConstract {
    interface View extends BaseView<IUserPresenter> {
        void showDialog();

        void setData(UserInfo mUserInfo);

        void dissMissDialog();

        String getUserId();
    }

    interface IPresenter extends IUserPresenter {
        void loadUserInfo();
    }
}

package com.example.bj.superdemo.ui.mvpdemo_a.views.iview;

import com.example.bj.superdemo.ui.mvpdemo_a.views.modle.UserInfo;

/**
 * Created by BJ on 2016/11/21.
 */

public interface IUserInfo {
    /**
     * 显示对话框
     */
    void showDialog();

    /**
     * 显示数据
     */
    void showData(UserInfo userInfo);

    /**
     * 关闭对话框
     */
    void closeDialog();
}

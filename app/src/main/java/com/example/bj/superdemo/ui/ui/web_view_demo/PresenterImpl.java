package com.example.bj.superdemo.ui.ui.web_view_demo;

/**
 * Created by bj on 2016-12-5.
 * Description：
 */

public class PresenterImpl {
    private Ipresenter mPresenter;

    public PresenterImpl(Ipresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void showInformation() {
        mPresenter.showDialog();
        WebInformation mInfromation = new WebInformation();
        mPresenter.setData(mInfromation);
        mPresenter.closeDialog();
    }


}

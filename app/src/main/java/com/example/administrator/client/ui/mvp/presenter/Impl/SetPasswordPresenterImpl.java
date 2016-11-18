package com.example.administrator.client.ui.mvp.presenter.Impl;

import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.model.Impl.SetPasswordModelImpl;
import com.example.administrator.client.ui.mvp.model.SetPasswordModel;
import com.example.administrator.client.ui.mvp.presenter.SetPasswordPresenter;
import com.example.administrator.client.ui.mvp.view.SetPasswordView;

/**
 * Created by Administrator on 2016/11/18.
 */

public class SetPasswordPresenterImpl implements SetPasswordPresenter {
    private SetPasswordModel setPasswordModel;
    private SetPasswordView setPasswordView;

    public SetPasswordPresenterImpl(SetPasswordView setPasswordView) {
        this.setPasswordModel = new SetPasswordModelImpl();
        this.setPasswordView = setPasswordView;
    }

    @Override
    public void loadDatas(String password, String phone, String verification) {
        setPasswordModel.loadDatas(password, phone, verification, new SetPasswordModel.Callback() {
            @Override
            public void loadSuccess(ResponseClient client) {
                setPasswordView.setSuccess(client);
            }

            @Override
            public void loadFailed() {
                setPasswordView.setFailed();
            }

        });
    }
}

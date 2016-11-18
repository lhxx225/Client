package com.example.administrator.client.ui.mvp.presenter.Impl;

import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.model.Impl.ReceiveVerificationModelImpl;
import com.example.administrator.client.ui.mvp.model.ReceiveVerificationModel;
import com.example.administrator.client.ui.mvp.presenter.ReceiveVerificationPresenter;
import com.example.administrator.client.ui.mvp.view.ReceiveVerificationView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ReceiveVerificationPresenterImpl implements ReceiveVerificationPresenter {
    private ReceiveVerificationModel receiveVerificationModel;
    private ReceiveVerificationView receiveVerificationView;

    public ReceiveVerificationPresenterImpl(ReceiveVerificationView receiveVerificationView) {
        this.receiveVerificationModel = new ReceiveVerificationModelImpl();
        this.receiveVerificationView = receiveVerificationView;
    }

    @Override
    public void loadDatas(String phone) {
        receiveVerificationModel.loadDatas(phone, new ReceiveVerificationModel.Callback() {
            @Override
            public void loadSuccess(ResponseClient responseClient) {
                receiveVerificationView.ReceiveVerificationSuccess(responseClient);
            }

            @Override
            public void loadFailed() {
                receiveVerificationView.ReceiveVerificationFailed();
            }
        });
    }
}

package com.example.administrator.client.ui.mvp.presenter.Impl;

import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.model.Impl.TestVerificationModelImpl;
import com.example.administrator.client.ui.mvp.model.TestVerificationModel;
import com.example.administrator.client.ui.mvp.presenter.TestVerificationPresenter;
import com.example.administrator.client.ui.mvp.view.TestVerificationView;
import com.squareup.okhttp.Response;

/**
 * Created by Administrator on 2016/11/18.
 */

public class TestVerificationPresenterImpl implements TestVerificationPresenter {
    private TestVerificationModel testVerificationModel;
    private TestVerificationView testVerificationView;

    public TestVerificationPresenterImpl(TestVerificationView testVerificationView) {
        this.testVerificationModel = new TestVerificationModelImpl();
        this.testVerificationView = testVerificationView;
    }

    @Override
    public void loadDatas(String phone, String verification) {
        testVerificationModel.loadDatas(phone, verification, new TestVerificationModel.Callback() {
            @Override
            public void loadSuccess(ResponseClient client) {
                testVerificationView.testSuccess(client);
            }

            @Override
            public void loadFailed() {
                testVerificationView.testFailed();
            }
        });
    }
}

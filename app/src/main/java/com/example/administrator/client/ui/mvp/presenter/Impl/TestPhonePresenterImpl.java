package com.example.administrator.client.ui.mvp.presenter.Impl;

import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.model.Impl.TestPhoneModelImpl;
import com.example.administrator.client.ui.mvp.model.TestPhoneModel;
import com.example.administrator.client.ui.mvp.presenter.TestPhonePresenter;
import com.example.administrator.client.ui.mvp.view.TestPhoneView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class TestPhonePresenterImpl implements TestPhonePresenter {
    private TestPhoneModel testPhoneModel;
    private TestPhoneView testPhoneView;

    public TestPhonePresenterImpl(TestPhoneView testPhoneView) {
        this.testPhoneModel = new TestPhoneModelImpl();
        this.testPhoneView = testPhoneView;
    }

    @Override
    public void loadDatas(String phone) {
        testPhoneModel.loadDatas(phone, new TestPhoneModel.Callback() {
            @Override
            public void loadSuccess(ResponseClient responseClient) {
                testPhoneView.Success(responseClient);
            }

            @Override
            public void loadFailed() {

            }
        });
    }
}

package com.example.administrator.client.ui.mvp.presenter.Impl;

import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.model.ClientModel;
import com.example.administrator.client.ui.mvp.model.Impl.ClientModelImpl;
import com.example.administrator.client.ui.mvp.presenter.ClientPresenter;
import com.example.administrator.client.ui.mvp.view.ClientView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ClientPresenterImpl implements ClientPresenter {
    private ClientModel clientModel;
    private ClientView clientView;

    public ClientPresenterImpl(ClientView clientView) {
        this.clientModel = new ClientModelImpl();
        this.clientView = clientView;
    }

    @Override
    public void loadDatas(String userName, String password) {
        clientModel.loadDatas(userName, password, new ClientModel.Callback() {
            @Override
            public void loadSuccess(ResponseClient responseClient) {
                clientView.showSuccess(responseClient);
            }

            @Override
            public void loadFailed() {

            }
        });
    }
}

package com.example.administrator.client.ui.mvp.model;

import com.example.administrator.client.entity.ResponseClient;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface ClientModel {
    void loadDatas(String User,String password,Callback callback);
    interface Callback{
        void loadSuccess(ResponseClient responseClient);
        void loadFailed();
    }
}

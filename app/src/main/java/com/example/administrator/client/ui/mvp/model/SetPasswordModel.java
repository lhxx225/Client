package com.example.administrator.client.ui.mvp.model;

import com.example.administrator.client.entity.ResponseClient;

/**
 * Created by Administrator on 2016/11/18.
 */

public interface SetPasswordModel {
    void loadDatas(String password, String phone,String verification, Callback callback);
    interface Callback{
        void loadSuccess(ResponseClient client);
        void loadFailed();
    }
}

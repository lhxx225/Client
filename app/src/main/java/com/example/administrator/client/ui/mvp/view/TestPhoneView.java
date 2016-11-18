package com.example.administrator.client.ui.mvp.view;

import com.example.administrator.client.entity.ResponseClient;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface TestPhoneView {
    void Success(ResponseClient responseClient);
    void Failed();
}

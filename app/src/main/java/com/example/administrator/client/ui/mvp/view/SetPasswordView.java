package com.example.administrator.client.ui.mvp.view;

import com.example.administrator.client.entity.ResponseClient;

/**
 * Created by Administrator on 2016/11/18.
 */

public interface SetPasswordView {
    void setSuccess(ResponseClient client);
    void setFailed();
}

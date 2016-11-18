package com.example.administrator.client.ui.mvp.view;

import com.example.administrator.client.entity.ResponseClient;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface ReceiveVerificationView {
    void ReceiveVerificationSuccess(ResponseClient responseClient);
    void ReceiveVerificationFailed();
}

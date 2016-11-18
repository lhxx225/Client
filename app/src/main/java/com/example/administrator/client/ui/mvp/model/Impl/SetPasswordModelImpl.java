package com.example.administrator.client.ui.mvp.model.Impl;

import com.alibaba.fastjson.JSON;
import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.model.SetPasswordModel;
import com.example.administrator.client.utils.APIUtils;
import com.example.administrator.client.utils.Apis;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/18.
 */

public class SetPasswordModelImpl implements SetPasswordModel {
    @Override
    public void loadDatas(String password, String phone, String verification, final Callback callback) {
        HashMap<String,String> map = new HashMap<>();
        map.put("password",password);
        map.put("mobile",phone);
        map.put("verify_code",verification);
        RequestBody body = new FormEncodingBuilder()
                .add("password",password)
                .add("mobile",phone)
                .add("verify_code",verification)
                .build();
        Request.Builder builder = new Request.Builder()
                .url(Apis.URL_SET_PASSWORD)
                .post(body)
                .header("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        OkHttpClient client = new OkHttpClient();
        client.newCall(builder.build()).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.loadFailed();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                ResponseClient responseClient = JSON.parseObject(response.body().string(), ResponseClient.class);
                callback.loadSuccess(responseClient);
            }
        });
    }
}

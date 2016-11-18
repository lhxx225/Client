package com.example.administrator.client.ui.mvp.model.Impl;

import com.alibaba.fastjson.JSON;
import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.model.TestPhoneModel;
import com.example.administrator.client.utils.APIUtils;
import com.example.administrator.client.utils.Apis;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class TestPhoneModelImpl implements TestPhoneModel {
    @Override
    public void loadDatas(String phone, final Callback callback) {
        HashMap<String,String> map = new HashMap<>();
        map.put("field","mobile");
        map.put("value",phone);
        RequestBody body = new FormEncodingBuilder()
                .add("field","mobile")
                .add("value",phone)
                .build();
        Request.Builder builder = new Request.Builder()
                .url(Apis.URL_TEXT_PHONE)
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        OkHttpClient okHttpClient = new OkHttpClient();
        final Call call = okHttpClient.newCall(builder.build());
        call.enqueue(new com.squareup.okhttp.Callback() {
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

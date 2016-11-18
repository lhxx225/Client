package com.example.administrator.client.ui.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/11/17.
 */

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        initViews();
        initDatas();
    }

    protected abstract int getContentViewResId();

    protected abstract void initDatas();

    protected abstract void initViews();
}

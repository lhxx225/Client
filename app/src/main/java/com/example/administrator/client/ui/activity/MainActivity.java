package com.example.administrator.client.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.client.R;
import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.presenter.ClientPresenter;
import com.example.administrator.client.ui.mvp.presenter.Impl.ClientPresenterImpl;
import com.example.administrator.client.ui.mvp.view.ClientView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ClientView {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText_client)
    EditText editTextClient;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText_password)
    EditText editTextPassword;
    @BindView(R.id.textView_forget_password)
    TextView textViewForgetPassword;
    @BindView(R.id.textView_client)
    TextView textViewClient;
    @BindView(R.id.textView_register)
    TextView textViewRegister;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private ClientPresenter clientPresenter;
    private boolean isClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        clientPresenter = new ClientPresenterImpl(this);
        textViewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick)
                    return;
                clientPresenter.loadDatas(editTextClient.getText().toString(), editTextPassword.getText().toString());
                isClick = true;
            }
        });
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReceiveVerificationActivity.class);
                startActivity(intent);
            }
        });
        editTextClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editTextClient.getText().toString()) && !TextUtils.isEmpty(editTextPassword.getText().toString())) {
                    textViewClient.setEnabled(true);
                    textViewClient.setTextColor(Color.argb(0xff, 0xff, 0xff, 0xff));
                }else {
                    textViewClient.setEnabled(false);
                    textViewClient.setTextColor(Color.argb(88, 0xff, 0xff, 0xff));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editTextClient.getText().toString()) && !TextUtils.isEmpty(editTextPassword.getText().toString())) {
                    textViewClient.setEnabled(true);
                    textViewClient.setTextColor(Color.argb(0xff, 0xff, 0xff, 0xff));
                }else {
                    textViewClient.setEnabled(false);
                    textViewClient.setTextColor(Color.argb(88, 0xff, 0xff, 0xff));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    public void showSuccess(ResponseClient responseClient) {
        handler.sendMessage(handler.obtainMessage(1, responseClient));
        isClick = false;
    }

    @Override
    public void showFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG);
            }
        });

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseClient responseClient = (ResponseClient) msg.obj;
            Toast.makeText(MainActivity.this, responseClient.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}

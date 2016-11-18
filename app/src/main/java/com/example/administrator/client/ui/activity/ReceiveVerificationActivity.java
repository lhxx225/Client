package com.example.administrator.client.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.client.R;
import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.presenter.Impl.ReceiveVerificationPresenterImpl;
import com.example.administrator.client.ui.mvp.presenter.Impl.TestPhonePresenterImpl;
import com.example.administrator.client.ui.mvp.presenter.Impl.TestVerificationPresenterImpl;
import com.example.administrator.client.ui.mvp.presenter.ReceiveVerificationPresenter;
import com.example.administrator.client.ui.mvp.presenter.TestPhonePresenter;
import com.example.administrator.client.ui.mvp.presenter.TestVerificationPresenter;
import com.example.administrator.client.ui.mvp.view.ReceiveVerificationView;
import com.example.administrator.client.ui.mvp.view.TestPhoneView;
import com.example.administrator.client.ui.mvp.view.TestVerificationView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ReceiveVerificationActivity extends BaseActivity implements ReceiveVerificationView, TestPhoneView ,TestVerificationView{
    @BindView(R.id.editText_user)
    EditText editTextUser;
    @BindView(R.id.editText_verification)
    EditText editTextVerification;
    @BindView(R.id.textView_receive_verification)
    TextView textViewReceiveVerification;
    @BindView(R.id.textView_next_stpe)
    TextView textViewNextStpe;
    private TestPhonePresenter testPhonePresenter;
    private ReceiveVerificationPresenter receiveVerificationPresenter;
    private TestVerificationPresenter testVerificationPresenter;
    private String verificationCode;
    private int i = 60;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        testPhonePresenter = new TestPhonePresenterImpl(this);
        receiveVerificationPresenter = new ReceiveVerificationPresenterImpl(this);
        testVerificationPresenter = new TestVerificationPresenterImpl(this);
        setListener();
    }

    private void setListener() {
        editTextUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editTextUser.getText().toString())) {
                    textViewReceiveVerification.setEnabled(false);
                } else {
                    textViewReceiveVerification.setEnabled(true);
                }
                if (start == 10)
                    testPhonePresenter.loadDatas(editTextUser.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textViewReceiveVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextUser.getText().toString())) {
                    Toast.makeText(ReceiveVerificationActivity.this, "请输入手机号", Toast.LENGTH_LONG);
                } else if (editTextUser.getText().toString().length() < 11) {
                    Toast.makeText(ReceiveVerificationActivity.this, "手机号码不正确", Toast.LENGTH_LONG);
                } else {
//                    receiveVerificationPresenter.loadDatas(editTextUser.getText().toString());
                    Toast.makeText(ReceiveVerificationActivity.this, "验证码发送成功", Toast.LENGTH_LONG).show();
                    textViewReceiveVerification.setEnabled(false);
                    handler.sendEmptyMessage(2);
                }
            }
        });
        editTextVerification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start == 5){
                    testVerificationPresenter.loadDatas(editTextUser.getText().toString(),editTextVerification.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textViewNextStpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceiveVerificationActivity.this,RegisterActivity.class);
                intent.putExtra("phone",editTextUser.getText().toString());
                intent.putExtra("verification",editTextVerification.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.register;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    public void showSuccess(ResponseClient responseClient) {
        if("验证码不正确".equals(responseClient.getMessage())){
            Toast.makeText(this, "验证码不正确,请重新输入", Toast.LENGTH_SHORT).show();
            editTextVerification.getText().clear();
        }else {
            textViewNextStpe.setEnabled(true);
            textViewNextStpe.setTextColor(Color.argb(0xff,0xff,0xff,0xff));
        }
    }

    @Override
    public void showFailed() {

    }

    @Override
    public void Success(ResponseClient responseClient) {
        if("手机号未填写或格式不正确".equals(responseClient.getMessage())){
            handler.sendMessage(handler.obtainMessage(3, "手机号码不正确"));
        }else if ("手机号不存在".equals(responseClient.getMessage())) {
            handler.sendMessage(handler.obtainMessage(1, responseClient));
        }

    }

    @Override
    public void Failed() {

    }
    @Override
    public void testSuccess(ResponseClient client) {
        Log.d("aa", client.getMessage());
    }

    @Override
    public void testFailed() {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ResponseClient responseClient = (ResponseClient) msg.obj;
                    Toast.makeText(ReceiveVerificationActivity.this, "可以注册", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    i--;
                    if(i == 0){
                        i = 60;
                        textViewReceiveVerification.setText("获取验证码");
                        textViewReceiveVerification.setEnabled(true);
                        return;
                    }
                    textViewReceiveVerification.setText(i+"s后重新获取");
                    sendEmptyMessageDelayed(2,1000);
                    break;
                case 3:
                    String str = (String) msg.obj;
                    Toast.makeText(ReceiveVerificationActivity.this,str, Toast.LENGTH_LONG).show();
            }
        }
    };


}

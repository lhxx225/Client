package com.example.administrator.client.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.client.R;
import com.example.administrator.client.entity.ResponseClient;
import com.example.administrator.client.ui.mvp.presenter.Impl.SetPasswordPresenterImpl;
import com.example.administrator.client.ui.mvp.presenter.SetPasswordPresenter;
import com.example.administrator.client.ui.mvp.view.SetPasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/18.
 */

public class RegisterActivity extends BaseActivity implements SetPasswordView {
    @BindView(R.id.edit_input_password)
    EditText editInputPassword;
    @BindView(R.id.editText_confirm_password)
    EditText editTextConfirmPassword;
    @BindView(R.id.textView_confirm)
    TextView textViewConfirm;
    private SetPasswordPresenter setPasswordPresenter;
    private String password;
    private String phone;
    private String verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setPasswordPresenter = new SetPasswordPresenterImpl(this);
        phone = getIntent().getStringExtra("phone");
        verification = getIntent().getStringExtra("verification");
        setListener();
    }

    private void setListener() {
        editInputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editInputPassword.getText().toString()) && !TextUtils.isEmpty(editTextConfirmPassword.getText().toString()))
                    textViewConfirm.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editInputPassword.getText().toString()) && !TextUtils.isEmpty(editTextConfirmPassword.getText().toString()))
                    textViewConfirm.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textViewConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editInputPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "密码输入不相同", Toast.LENGTH_SHORT).show();
                } else {
                    password = editInputPassword.getText().toString();
                    setPasswordPresenter.loadDatas(password, phone, verification);
                }
            }
        });
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.reset_password;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }


    @Override
    public void setSuccess(ResponseClient client) {
        handler.sendMessage(handler.obtainMessage(1, client));
    }

    @Override
    public void setFailed() {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseClient client = (ResponseClient) msg.obj;
            Toast.makeText(RegisterActivity.this, client.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}

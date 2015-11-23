package com.android.lovehome.activity;

import android.widget.Button;
import android.widget.EditText;

import com.android.lovehome.R;
import com.android.lovehome.base.BaseActivity;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity {
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtEnsurePassword;
    private EditText edtPhone;
    private Button benSendCode;
    private EditText edtPhoneCode;
    private Button btnRegister ;

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        edtUserName = (EditText) findViewById(R.id.edt_register_username);
        edtPassword = (EditText) findViewById(R.id.edt_register_password);
        edtEnsurePassword = (EditText) findViewById(R.id.edt_register_ensure_password);
        edtPhone = (EditText) findViewById(R.id.edt_register_phone);
        benSendCode = (Button) findViewById(R.id.btn_register_send_code);
        edtPhoneCode = (EditText) findViewById(R.id.edt_register_phone_code);
        btnRegister = (Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void init() {
        head_title.setText("用户注册");
    }

    @Override
    protected void initEvent() {

    }
}

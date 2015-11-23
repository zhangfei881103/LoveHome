package com.android.lovehome.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.lovehome.R;
import com.android.lovehome.base.BaseActivity;

/**
 * 注册
 */
public class LoginActivity extends BaseActivity {
    private EditText edtPhone;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnForgetPassword;

    @Override
    public int getLayoutId() {
        return R.layout.layout_login;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        edtPhone = (EditText) findViewById(R.id.edt_login_phone);
        edtPassword = (EditText) findViewById(R.id.edt_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnForgetPassword = (Button) findViewById(R.id.btn_forget_password);
    }

    @Override
    protected void init() {
        head_title.setText("登录");
    }

    @Override
    protected void initEvent() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnForgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_login:

                break ;
            case R.id.btn_register:
                Intent intent = new Intent();
                intent.setClass(this , RegisterActivity.class);
                startIntent(intent);
                break ;
            case R.id.btn_forget_password:
                Intent intent1 = new Intent();
                intent1.setClass(this , ForgetPasswordActivity.class);
                startIntent(intent1);
                break ;

        }
    }
}

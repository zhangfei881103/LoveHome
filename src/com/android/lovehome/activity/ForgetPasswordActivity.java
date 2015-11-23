package com.android.lovehome.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.lovehome.R;
import com.android.lovehome.base.BaseActivity;

/**
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {
    private EditText edtUserName;
    private EditText edtPhone;
    private Button btnGetPassword;

    @Override
    public int getLayoutId() {
        return R.layout.layout_forget_password;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        edtUserName = (EditText) findViewById(R.id.edt_forget_password_username);
        edtPhone = (EditText) findViewById(R.id.edt_forget_password_phone);
        btnGetPassword = (Button) findViewById(R.id.btn_forget_password_get_password);
    }

    @Override
    protected void init() {
        head_title.setText("忘记密码");
    }

    @Override
    protected void initEvent() {
        btnGetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_forget_password_get_password:

                break;
        }
    }
}

package com.android.lovehome.activity;


import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.base.BaseActivity;


/**
 * 平台申明
 */
public class MyStamentActivity extends BaseActivity {
    private TextView txtContent;

    private String tag ;
    @Override
    public int getLayoutId() {
        return R.layout.layout_mystament;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        txtContent = (TextView) findViewById(R.id.txt_my_stament_content);
    }

    @Override
    protected void init() {
        tag = getIntent().getStringExtra("from");
        if("statement".equals(tag)){
            head_title.setText("平台声明");
        }else if("about".equals(tag)){
            head_title.setText("关于爱家乡");
        }
    }

    @Override
    protected void initEvent() {

    }
}

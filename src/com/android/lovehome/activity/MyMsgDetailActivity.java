package com.android.lovehome.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.adapter.MyMsgAdapter;
import com.android.lovehome.base.BaseActivity;
import com.android.lovehome.entity.MyMsgEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的消息详情
 */
public class MyMsgDetailActivity extends BaseActivity {
    private TextView txtTitle;
    private TextView txtTime;
    private TextView txtContent;

    @Override
    public int getLayoutId() {
        return R.layout.layout_mymsg_detail;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        txtTitle = (TextView) findViewById(R.id.txt_mymsg_detail_title);
        txtTime = (TextView) findViewById(R.id.txt_mymsg_detail_time);
        txtContent = (TextView) findViewById(R.id.txt_mymsg_detail_content);
    }

    @Override
    protected void init() {
        head_title.setText("系统消息");
    }

    @Override
    protected void initEvent() {

    }
}

package com.android.lovehome.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.lovehome.R;
import com.android.lovehome.adapter.MyMsgAdapter;
import com.android.lovehome.base.BaseActivity;
import com.android.lovehome.entity.MyMsgEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的消息
 */
public class MyMsgActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ListView listview;
    private MyMsgAdapter msgAdapter;
    private List<MyMsgEntity> lists;

    @Override
    public int getLayoutId() {
        return R.layout.layout_mymsg;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        listview = (ListView) findViewById(R.id.listview_mymsg);
    }

    @Override
    protected void init() {
        head_title.setText("我的消息");
        lists = new ArrayList<MyMsgEntity>();
        for (int i = 0; i < 5; i++) {
            MyMsgEntity entity = new MyMsgEntity(i + "", i + "", i + "", i + "", i + "");
            lists.add(entity);
        }
        msgAdapter = new MyMsgAdapter(this, lists);
        listview.setAdapter(msgAdapter);
    }

    @Override
    protected void initEvent() {
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this , MyMsgDetailActivity.class);
        startIntent(intent);
    }
}

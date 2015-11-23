package com.android.lovehome.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.lovehome.R;
import com.android.lovehome.adapter.MyReleaseAdapter;
import com.android.lovehome.base.BaseActivity;
import com.android.lovehome.entity.MyReleaseEntity;
import com.android.lovehome.utils.uiutils.DialogUtil;
import com.android.lovehome.utils.uiutils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的发布  我的草稿  我的收藏
 */
public class MyReleaseActivity extends BaseActivity{
    private ListView pullToRefreshListView;

    private MyReleaseAdapter myReleaseAdapter;
    private List<MyReleaseEntity> lists;
    private String tag ;
    @Override
    public int getLayoutId() {
        return R.layout.layout_release;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        pullToRefreshListView = (ListView) findViewById(R.id.listview_release);

    }

    @Override
    protected void init() {

        tag = getIntent().getStringExtra("from");
        if("release".equals(tag)){
            head_title.setText("我的发布");
        }else if("draft".equals(tag)){
            head_title.setText("我的草稿");
        }else if("collection".equals(tag)){
            head_title.setText("我的收藏");
        }
        lists = new ArrayList<MyReleaseEntity>();
        for (int i = 0; i < 5; i++) {
            MyReleaseEntity entity = new MyReleaseEntity("url" + i, "name" + i, "price" + i);
            lists.add(entity);
        }

        myReleaseAdapter = new MyReleaseAdapter(MyReleaseActivity.this, lists , tag);
        pullToRefreshListView.setAdapter(myReleaseAdapter);
    }

    @Override
    protected void initEvent() {

    }
}

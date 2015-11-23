package com.android.lovehome.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.lovehome.R;
import com.android.lovehome.adapter.GoodAdapter;
import com.android.lovehome.base.BaseActivity;
import com.android.lovehome.entity.GoodEntity;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by yongbing.chen on 2015/11/4.
 */
public class GoodActivity extends BaseActivity implements View.OnClickListener{

    private PullToRefreshListView good_listview;

    private GoodAdapter goodAdapter;
    private List<GoodEntity> goodEntityList;

    @Override
    public int getLayoutId() {
        return R.layout.good;
    }

    @Override
    protected void initTitleBar() {
        head_title.setText("查找商户");
        rigth_image_button.setImageResource(R.drawable.search);
    }

    @Override
    protected void initView() {
        initList();
        good_listview=(PullToRefreshListView)findViewById(R.id.good_listview);
        goodAdapter=new GoodAdapter(GoodActivity.this,goodEntityList);
        good_listview.setAdapter(goodAdapter);
        good_listview.setOnItemClickListener(new MyOnItemClicklistener());
        good_listview.setMode(Mode.BOTH);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {

    }

    public void initList(){
        goodEntityList=new ArrayList<GoodEntity>();

        for (int i=0; i<10; i++){
            GoodEntity goodEntity=new GoodEntity();
            goodEntity.setShopName("龙湾大酒店");
            goodEntityList.add(goodEntity);
        }
    }

    public class MyOnItemClicklistener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent=new Intent(GoodActivity.this,GoodDetailActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.rigth_image_button:
                onBackPressed();
                break;
        }

    }
}

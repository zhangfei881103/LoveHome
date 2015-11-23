package com.android.lovehome.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.lovehome.R;
import com.android.lovehome.base.BaseActivity;
import com.android.lovehome.utils.uiutils.DialogUtil;
import com.android.lovehome.utils.uiutils.DialogUtil.DialogButtomCallBack;
import com.android.lovehome.utils.uiutils.DialogUtil.DialogButtonCallBack;

/**
 * Created by yongbing.chen on 2015/11/4.
 */
public class GoodDetailActivity extends BaseActivity{

    private LinearLayout phone_layout,jubao_layout;

    @Override
    public int getLayoutId() {
        return R.layout.good_detail;
    }

    @Override
    protected void initTitleBar() {
        head_title.setText("详情");
        rigth_image_button.setImageResource(R.drawable.love);
        rigth_image_button2.setImageResource(R.drawable.fenxiang);
    }

    @Override
    protected void initView() {
        phone_layout=(LinearLayout)findViewById(R.id.phone_layout);
        jubao_layout=(LinearLayout)findViewById(R.id.jubao_layout);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        phone_layout.setOnClickListener(this);
        jubao_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rigth_image_button:
                DialogUtil.getInstance(GoodDetailActivity.this).showDialog("确定要拨打：0577-6443152？", new DialogButtonCallBack() {
					
					@Override
					public void onRigthButtonClick() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onLeftButtonClick() {
						// TODO Auto-generated method stub
						
					}
				});
                break;
            case R.id.rigth_image_button2:
                DialogUtil.getInstance(GoodDetailActivity.this).showSharedDialog(new MyShareDialogCallBack());
                break;
            case R.id.jubao_layout:
                DialogUtil.getInstance(GoodDetailActivity.this).showSharedDialog(new MyShareDialogCallBack());
                break;
            case R.id.phone_layout:
                DialogUtil.getInstance(GoodDetailActivity.this).showSharedDialog(new MyShareDialogCallBack());
                break;
        }
    }

    class MyShareDialogCallBack implements DialogUtil.DialogSharedCallBack{
        @Override
        public void onWeixinClick() {

        }

        @Override
        public void onWeixinCicleClick() {

        }

        @Override
        public void onQQClick() {

        }

        @Override
        public void onWeiboClick() {

        }
    }
}

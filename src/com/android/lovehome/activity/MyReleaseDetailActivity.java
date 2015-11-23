package com.android.lovehome.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.adapter.MyReleaseAdapter;
import com.android.lovehome.base.BaseActivity;
import com.android.lovehome.entity.MyReleaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布详情
 */
public class MyReleaseDetailActivity extends BaseActivity {
    private TextView txtDetailName;
    private TextView txtDetailBusinessTime;
    private TextView txtPerConsumption;
    private TextView txtDetailAddress;
    private TextView txtContactPersion;
    private TextView txtDetailPhone;

    private TextView txtTakeawayTime;
    private TextView txtTakeawayRange;
    private TextView txtTakeawayCharge;

    private EditText edtDetail;
    private Button btnSave, btnRelease;

    @Override
    public int getLayoutId() {
        return R.layout.layout_release_detail;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        txtDetailName = (TextView) findViewById(R.id.txt_release_detail_name);
        txtDetailBusinessTime = (TextView) findViewById(R.id.txt_release_detail_business_time);
        txtPerConsumption = (TextView) findViewById(R.id.txt_release_detail_per_consumption);
        txtDetailAddress = (TextView) findViewById(R.id.txt_release_detail_address);
        txtContactPersion = (TextView) findViewById(R.id.txt_release_detail_contact_persion);
        txtDetailPhone = (TextView) findViewById(R.id.txt_release_detail_phone);

        txtTakeawayTime = (TextView) findViewById(R.id.txt_release_detail_takeaway_time);
        txtTakeawayRange = (TextView) findViewById(R.id.txt_release_detail_takeaway_range);
        txtTakeawayCharge = (TextView) findViewById(R.id.txt_release_detail_takeaway_charge);

        edtDetail = (EditText) findViewById(R.id.edt_release_detail);
        btnSave = (Button) findViewById(R.id.btn_release_detail_save);
        btnRelease = (Button) findViewById(R.id.btn_release_detail_release);

    }

    @Override
    protected void init() {
        head_title.setText("美食-酒店");
    }

    @Override
    protected void initEvent() {

    }
}

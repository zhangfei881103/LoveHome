package com.android.lovehome.fragment;

import org.json.JSONObject;
import org.w3c.dom.Text;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lovehome.R;
import com.android.lovehome.activity.LoginActivity;
import com.android.lovehome.activity.MyInfoActivity;
import com.android.lovehome.activity.MyMsgActivity;
import com.android.lovehome.activity.MyReleaseActivity;
import com.android.lovehome.activity.MyStamentActivity;
import com.android.lovehome.base.BaseFragment;
import com.android.lovehome.utils.uiutils.DialogUtil;


public class MyFragment extends BaseFragment {
    private MainActivity mAct;

    private RelativeLayout layoutInfo ;
    private ImageView imgPic;
    private TextView txtName, txtAddress;
    private TextView txtLogin;
    private RelativeLayout layoutRelease, layoutDraft, layoutCollection, layoutMsg,
            layoutStatement, layoutAbout, layoutShare, layoutPassword, layoutBuffer;
    private Button btnExit;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initTitleBar() {
        LinearLayout head_layout=(LinearLayout)view.findViewById(R.id.head_layout);
        head_layout.setVisibility(View.GONE);
    }


    @Override
    protected void initView() {
        layoutInfo = (RelativeLayout)view.findViewById(R.id.my_info_layout);
        imgPic = (ImageView)view.findViewById(R.id.my_msg_photo_img);
        txtName = (TextView)view.findViewById(R.id.my_msg_name);
        txtAddress = (TextView)view.findViewById(R.id.my_msg_address);
        txtLogin = (TextView)view.findViewById(R.id.my_msg_login);

        layoutRelease = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_release);
        layoutDraft = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_draft);
        layoutCollection = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_collection);
        layoutMsg = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_msg);
        layoutStatement = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_statement);
        layoutAbout = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_about);
        layoutShare = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_share);
        layoutPassword = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_password);
        layoutBuffer = (RelativeLayout)view.findViewById(R.id.layout_fragment_my_buffer);
        btnExit = (Button)view.findViewById(R.id.btn_fragment_exit);
    }

    private void setIndexTxt(View view , int id){
        ImageView img = (ImageView)view.findViewById(R.id.fragment_my_item_left_img);
        TextView txt = (TextView)view.findViewById(R.id.fragment_my_item_name);
        switch (id){
            case R.id.layout_fragment_my_release:
                img.setBackgroundResource(R.drawable.img_my_fragment_release_left);
                txt.setText("我的发布");
                break ;
            case R.id.layout_fragment_my_draft:
                img.setBackgroundResource(R.drawable.img_my_fragment_draft_left);
                txt.setText("我的草稿");
                break ;
            case R.id.layout_fragment_my_collection:
                img.setBackgroundResource(R.drawable.img_my_fragment_collection_left);
                txt.setText("我的收藏");
                break ;
            case R.id.layout_fragment_my_msg:
                img.setBackgroundResource(R.drawable.img_my_fragment_msg_left);
                txt.setText("我的消息");
                break ;
            case R.id.layout_fragment_my_statement:
                img.setBackgroundResource(R.drawable.img_my_fragment_stament_left);
                txt.setText("平台声明");
                break ;
            case R.id.layout_fragment_my_about:
                img.setBackgroundResource(R.drawable.img_my_fragment_about_left);
                txt.setText("关于“爱家乡”");
                break ;
            case R.id.layout_fragment_my_share:
                img.setBackgroundResource(R.drawable.img_my_fragment_share_left);
                txt.setText("分享软件");
                break ;
            case R.id.layout_fragment_my_password:
                img.setBackgroundResource(R.drawable.img_my_fragment_password_left);
                txt.setText("修改密码");
                break ;
            case R.id.layout_fragment_my_buffer:
                img.setBackgroundResource(R.drawable.img_my_fragment_buffer_left);
                txt.setText("清除缓存");
                break ;
        }
    }

    @Override
    protected void initEvent() {
        layoutInfo.setOnClickListener(this);
        layoutRelease.setOnClickListener(this);
        layoutDraft.setOnClickListener(this);
        layoutCollection.setOnClickListener(this);
        layoutMsg.setOnClickListener(this);
        layoutStatement.setOnClickListener(this);
        layoutAbout.setOnClickListener(this);
        layoutShare.setOnClickListener(this);
        layoutPassword.setOnClickListener(this);
        layoutBuffer.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info_layout:
                Intent intent = new Intent();
                intent.setClass(getActivity() , MyInfoActivity.class);
                startIntent(intent);
                break ;
            case R.id.layout_fragment_my_release:
                Intent intent1 = new Intent();
                intent1.setClass(getActivity() , MyReleaseActivity.class);
                intent1.putExtra("from" , "release");
                startIntent(intent1);
                break ;
            case R.id.layout_fragment_my_draft:
                Intent intent2 = new Intent();
                intent2.putExtra("from" , "draft");
                intent2.setClass(getActivity() , MyReleaseActivity.class);
                startIntent(intent2);
                break ;
            case R.id.layout_fragment_my_collection:
                Intent intent3 = new Intent();
                intent3.putExtra("from" , "collection");
                intent3.setClass(getActivity() , MyReleaseActivity.class);
                startIntent(intent3);
                break ;
            case R.id.layout_fragment_my_msg:
                Intent intent4 = new Intent();
                intent4.setClass(getActivity() , MyMsgActivity.class);
                startIntent(intent4);
                break ;
            case R.id.layout_fragment_my_statement:
                Intent intent5 = new Intent();
                intent5.putExtra("from" , "statement");
                intent5.setClass(getActivity() , MyStamentActivity.class);
                startIntent(intent5);
                break ;
            case R.id.layout_fragment_my_about:
                Intent intent6 = new Intent();
                intent6.putExtra("from" , "about");
                intent6.setClass(getActivity() , MyStamentActivity.class);
                startIntent(intent6);
                break ;
            case R.id.layout_fragment_my_share:
                Intent intent7 = new Intent();
                intent7.setClass(getActivity() , LoginActivity.class);
                startIntent(intent7);
                break ;
            case R.id.layout_fragment_my_password:

                break ;
            case R.id.layout_fragment_my_buffer:
                DialogUtil.getInstance(getActivity()).IgnoreDialog(new DialogUtil.DialogButtonCallBack() {

                    @Override
                    public void onRigthButtonClick() {

                    }

                    @Override
                    public void onLeftButtonClick() {

                    }
                });
                break ;
            case R.id.btn_fragment_exit:

                break ;
        }
    }


    @Override
    protected void init() {
        mAct = (MainActivity) mFragmentActivity;
        setIndexTxt(layoutRelease , R.id.layout_fragment_my_release);
        setIndexTxt(layoutDraft , R.id.layout_fragment_my_draft);
        setIndexTxt(layoutCollection , R.id.layout_fragment_my_collection);
        setIndexTxt(layoutMsg , R.id.layout_fragment_my_msg);
        setIndexTxt(layoutStatement , R.id.layout_fragment_my_statement);
        setIndexTxt(layoutAbout , R.id.layout_fragment_my_about);
        setIndexTxt(layoutShare , R.id.layout_fragment_my_share);
        setIndexTxt(layoutPassword , R.id.layout_fragment_my_password);
        setIndexTxt(layoutBuffer , R.id.layout_fragment_my_buffer);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void startRequest(int taskId) {
        showDialog("加载中...");
    }

    @Override
    public void succeed(int taskId, JSONObject jObject) {

    }

    @Override
    public void failed(int taskId, JSONObject jObject) {
    }

    @Override
    public void exceptioned(int taskId, String msg) {
        dimissDialog();
    }

}

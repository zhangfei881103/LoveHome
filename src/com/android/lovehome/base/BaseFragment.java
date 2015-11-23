package com.android.lovehome.base;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lovehome.R;
import com.android.lovehome.constant.Constant;
import com.android.lovehome.interferce.HttpRequestCallBack;

/**
 * BaseFragment
 *
 */
public abstract class BaseFragment extends Fragment implements OnClickListener,
        HttpRequestCallBack {

    protected View view;

    protected FragmentActivity mFragmentActivity;

    /**
     *
     *
     */
   // private PushState pushstate;

    protected ImageView rigth_image_button,left_image_button,rigth_image_button2;
    protected TextView head_title;


    /**
     * 初始化
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        IntentFilter filter = new IntentFilter();
      //  pushstate = new PushState();
        // 这里添加action!!!!!!!!!
        //mFragmentActivity.registerReceiver(pushstate, filter);

        view = inflater.inflate(getLayoutId(), null);
        initHeadView();
        initTitleBar();
        initView();
        init();
        initEvent();


        return view;
    }

    /**
     * 获取layoutid
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mFragmentActivity = (FragmentActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * findHeadViews 初始化头部视图 void
     *
     * @throws
     * @since 1.0.0
     */
    protected abstract void initTitleBar();

    /**
     * findViews 初始化视图 void
     *
     * @throws
     * @since 1.0.0
     */
    protected abstract void initView();

    /**
     * init 初始化数据 void
     *
     * @throws
     * @since 1.0.0
     */
    protected abstract void init();

    /**
     * setListeners 初始化监听事件 void
     *
     * @throws
     * @since 1.0.0
     */
    protected abstract void initEvent();

    /**
     * 启动页面
     *
     * @param intent
     */
    public void startIntent(Intent intent) {
        // StackUtil.setFlagReorderToFront(intent);
        startActivity(intent);
        mFragmentActivity.overridePendingTransition(R.anim.slide_in_from_right,
                R.anim.slide_out_to_left);
    }

    /**
     * 关闭页面
     */
    public void finish() {
        mFragmentActivity.finish();
        mFragmentActivity.overridePendingTransition(R.anim.slide_in_from_left,
                R.anim.slide_out_to_right);
    }


    public void  initHeadView(){
        left_image_button =(ImageView)view.findViewById(R.id.left_image_button);
        rigth_image_button=(ImageView)view.findViewById(R.id.left_image_button);
        rigth_image_button2=(ImageView)view.findViewById(R.id.rigth_image_button2);
        head_title=(TextView)view.findViewById(R.id.head_title);
        rigth_image_button.setOnClickListener(this);
        left_image_button.setOnClickListener(this);
        rigth_image_button2.setOnClickListener(this);

    }

    protected Dialog dialog = null;

    /**
     * 加载框
     *
     * @param title
     */
    protected void showDialog(String title) {
        /*if (dialog == null) {
            dialog = new Dialog(mFragmentActivity);
            View v = LayoutInflater.from(mFragmentActivity).inflate(
                    R.layout.loading_dialog, null);
            ImageView v_image = (ImageView) v.findViewById(R.id.iv_anim);
            v_image.setImageResource(R.anim.loader_animation);
            AnimationDrawable animationDrawable = (AnimationDrawable) v_image
                    .getDrawable();
            animationDrawable.start();
            dialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(v);
            TextView loadingtitle = (TextView) v
                    .findViewById(R.id.tv_loadingtitle);
            loadingtitle.setText(title);
            dialog.show();
        }*/
    }

    /**
     * 关闭dialog
     */
    protected void dimissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onClick(View arg0) {

    }

    @Override
    public void onResume() {
        super.onResume();
        //getVersionCodeInfo();
    }

    /**
     * 获取版本信息
     *//*
    private void getVersionCodeInfo() {
        new VersionController(mFragmentActivity, new HttpRequestCallBack() {

            @Override
            public void succeed(int taskId, JSONObject jObject) {
                try {
                    //isupdate 0不更新 1更新
                    //isForcedUpdate 0不强制，1强制
                    String version = jObject.getString("updateVersion");
                    String versionmsg = jObject.getString("updateVersionMsg");
                    String isupdate = jObject.getString("isAppUpdate");
                    String isForcedUpdate = jObject.getString("isForcedAppUpdate");
                    //是否需要更新(1:更新，0：不更新)
                    if (!version.equals(MyApplication.getIntence().getVersionName()) && isupdate.equals("1")) {
                        updataApp(versionmsg, isForcedUpdate);
                    } else {
                        return;
                    }
                    //是否需要重新登录 1重新登录，0不需要
                    String isReLogin=jObject.getString("isReLogin");
                    if(isReLogin.equals("1")){
                        MyApplication.getIntence().quiteAndGoToRegist=true;
                        JPushInterface.stopPush(MyApplication.getIntence().getApplicationContext());
                        UserInfoUtil.quiteAccount(mFragmentActivity);
                        Intent it = new Intent(mFragmentActivity, LoginActivity.class);
                        mFragmentActivity.startActivity(it);
                    }
                    // readProtocolStatus是否读取协议1 已读；0 未读
                    String readProtocolStatus = jObject
                            .getString("isReadProtocol");
                    if (readProtocolStatus.equals("0")) {
                        Intent it = new Intent(mFragmentActivity,
                                UpdateAgreementActivity.class);
                        it.putExtra("remark",
                                jObject.getString("protocolRemark"));
                        mFragmentActivity.startActivity(it);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void startRequest(int taskId) {

            }

            @Override
            public void failed(int taskId, JSONObject jObject) {
            }

            @Override
            public void exceptioned(int taskId, String msg) {

            }
        }, Constant.POST, TaskConstant.UPDATE_VERSION, null);
    }
*/
    /**
     * 更新新版本
     *
     * @param versionmsg
     * @param isupdate
     */
    private void updataApp( String versionmsg, final String isupdate) {
        /** 开始调用自动更新函数 **/
//        versionmsg="1.本次版本修复了XXXbr2.啦啦啦啦";
      /*  if(!StringUtils.isEmpty(versionmsg)){
            if(versionmsg.contains("br")){
                versionmsg= versionmsg.replace("br","\n");
            }
        }
        final String versionmsgs=versionmsg;
        UpdateConfig.setDebug(true);
        UmengUpdateAgent.setUpdateOnlyWifi(false); // 是否在只在wifi下提示更新，默认为 true
        UmengUpdateAgent.setUpdateAutoPopup(false); // 是否自动弹出更新对话框
        //UmengUpdateAgent.setDownloadListener(null); // 下载新版本过程事件监听，可设为 null
        //UmengUpdateAgent.setDialogListener(null); // 用户点击更新对话框按钮的回调事件，直接 null
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {

                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        //UmengUpdateAgent.showUpdateDialog(Main.this, updateInfo);
                        UpdateApplication.getInstance().showUpdateDialog(mFragmentActivity, updateInfo, versionmsgs, isupdate);
                        break;
                    case UpdateStatus.No: // has no update
                        Log.i("MainFragment", "没有更新");
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        Log.i("MainFragment", "没有wifi连接， 只在wifi下更新");
                        Toast.makeText(mFragmentActivity, "没有wifi连接， 只在wifi下更新", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.Timeout: // time out
                        Log.i("MainFragment", "超时");
                        Toast.makeText(mFragmentActivity, "超时", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });
        UmengUpdateAgent.update(mFragmentActivity);*/
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//		 getActivity().unregisterReceiver(pushstate);
    }

    @Override
    public void startRequest(int taskId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void succeed(int taskId, JSONObject jObject) {
        // TODO Auto-generated method stub
        dimissDialog();
    }

    @Override
    public void failed(int taskId, JSONObject jObject) {
        // TODO Auto-generated method stub
        dimissDialog();
    }

    @Override
    public void exceptioned(int taskId, String msg) {
        // TODO Auto-generated method stub
        dimissDialog();
    }
/*
    *//**
     * 类名称:PushState 类描述:广播接收器 创建人:sht 修改人:sht
     * <p/>
     * 修改备注:
     *
     * @version 1.0.0
     *//*
    public class PushState extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            // int type = intent.getExtras().getInt(Constant.ISKEEPLOGIN);
            System.out.println("接收到信息了=======act===============");
            String extra = intent.getExtras().getString(Constant.PUSHTYPE);
            System.out.println("接收到信息了=======act===============" + extra);
            try {
                JSONObject extraJson = new JSONObject(extra);
//                JSONObject android = extraJson.getJSONObject("android");
                int msgtype = Integer.parseInt(extraJson.getString("msgtype"));
                switch (msgtype) {
                    case 0: // 来新抢单了
                        if (BaseFragment.this instanceof GrabOrderFragment) {
                            ((GrabOrderFragment) BaseFragment.this)
                                    .notifyOrderPush();
                        }
                        break;

                    case 31: // 来新消息了
                        Constant.PUSH_MSG_NUM += 1;
                        if (BaseFragment.this instanceof GrabOrderFragment) {
                            ((GrabOrderFragment) BaseFragment.this).notifyMsgPush();
                        }
                        break;
                    default:
                        break;
                }
                // {"android":{"msgtype":"0","sound":"happy","badge":1}}

                // if (msgtype.equals(Constant.ENTRUSTDISABLED)) {
                // ToastUtils.getInstance(MyApplication.getIntence().getApplicationContext()).show("您的账号已经被禁用，请联系客服!");
                // UserInfoUtil.quiteAccount(MyApplication.getIntence().getApplicationContext());
                // UserInfoUtil.setExitAccount(MyApplication.getIntence().getApplicationContext(),
                // true);
                // SharePreferenceUtil.getInstance(MyApplication.getIntence().getApplicationContext())
                // .setString(SharePreferenceConstant.ENTRUST_MOBLIEPHONE, "");
                // MyApplication.getIntence().isBackToMain = true;
                // Intent intentLogin = new Intent(getActivity(),
                // LoginActivity.class);
                // startActivity(intentLogin);
                // new ActivityAnimation(getActivity(), Constant.RIGHT_ENTER);
                // JPushInterface.stopPush(MyApplication.getIntence().getApplicationContext());
                // }
                
				 * msgtype 类型 0 您有一条新的抢单 1 抢单成功 2 抢单失败 3 结算成功 4 承运商禁用 14 委托方禁用
				 * 11 您有一条新的委托已成立 12 您有一条订单已送达 31 新的公告
				 
                // if(msgtype.equals("0")){
                //
                // }else if(msgtype.equals("31")){
                //
                // }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }*/

}

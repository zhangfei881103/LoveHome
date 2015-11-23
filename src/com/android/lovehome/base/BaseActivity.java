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
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.app.MyApplication;
import com.android.lovehome.constant.Constant;
import com.android.lovehome.interferce.HttpRequestCallBack;

/**
 * 类名称:BaseActivity 类描述: 
 *  修改时间:2015-11-5
 * 上午10:11:44 修改备注:
 * 
 * @version 1.0.0
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener,
		OnFocusChangeListener, OnKeyListener, HttpRequestCallBack {
	// 方便调用
	public Activity mActivity;
	protected Dialog dialog = null;
	
    protected ImageView rigth_image_button,left_image_button,rigth_image_button2;
    protected TextView head_title;
    
	/**
	 * 初始化
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		mActivity = this;
		MyApplication.getIntence().mainActivities.add(mActivity);
		IntentFilter filter = new IntentFilter();
		//这里添加action!!!!!!!!!
		filter.addAction(Constant.PUSHACTION);
        initHeadView();
		initTitleBar();
		initView();
		init();
		initEvent();

	}

	/**
	 * 从右边进入动画
	 * 
	 * @param intent
	 */
	public void startIntent(Intent intent) {
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_from_right,
				R.anim.slide_out_to_left);
	}

	/**
	 * 从右边出去动画
	 */
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_from_left,
				R.anim.slide_out_to_right);
	}

	/**
	 * getLayoutId 这里是活动当前activity的layout_id void
	 * 
	 * @throws
	 * @since 1.0.0
	 */
	public abstract int getLayoutId();

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

	
	 public void  initHeadView(){
         left_image_button=(ImageView)findViewById(R.id.left_image_button);
         rigth_image_button=(ImageView)findViewById(R.id.rigth_image_button);
         rigth_image_button2=(ImageView)findViewById(R.id.rigth_image_button2);
		 head_title=(TextView)findViewById(R.id.head_title);
		 rigth_image_button.setOnClickListener(this);
		 left_image_button.setOnClickListener(this);
         rigth_image_button2.setOnClickListener(this);
		 
	 }

	private AnimationDrawable animationDrawable;
	private ImageView imageView;

	/**
	 * 加载框
	 * 
	 * @param title
	 */
	protected void showDialog(String title) {
		/*if (dialog == null) {
			dialog = new Dialog(this);
			View v = LayoutInflater.from(this).inflate(R.layout.loading_dialog,
					null);
			dialog.getWindow().setBackgroundDrawable(
					new ColorDrawable(Color.TRANSPARENT));
			dialog.setContentView(v);
			TextView loadingtitle = (TextView) v
					.findViewById(R.id.tv_loadingtitle);
			ImageView iv_anim = (ImageView) v.findViewById(R.id.iv_anim);
			animationDrawable = (AnimationDrawable) iv_anim.getBackground();
			loadingtitle.setText(title);
			animationDrawable.start();
			dialog.show();
		}*/
	}

	/**
	 * 关闭dialog
	 */
	protected void dimissDialog() {
		if (dialog != null) {
			dialog.dismiss();
			animationDrawable.stop();
			dialog = null;
		}
	}

	/**
	 * 回退事件
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void onClick(View v) {
        if(v.getId()==R.id.left_image_button){
            finish();
        }
	}


	@Override
	public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
		return false;
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 软键盘是否隐藏
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			// 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {
				((InputMethodManager) this
						.getSystemService(INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(this.getCurrentFocus()
								.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);

				// return false;
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * isShouldHideInput
	 * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
	 * 
	 * @param v
	 * @param event
	 * @return boolean
	 * @throws
	 * @since 1.0.0
	 */
	public boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = { 0, 0 };
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 点击EditText的事件，忽略它。
				return false;
			} else {
				return true;
			}
		}
		// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		mActivity.unregisterReceiver(pushstate);
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
	/**
	 * 类名称:PushState
	 * 类描述:广播接收器
	 * 创建人:sht
	 * 修改人:sht
	 * <p/>
	 * 修改备注:
	 *
	 * @version 1.0.0
	 */
	public class PushState extends BroadcastReceiver {

		public void onReceive(Context context, Intent intent) {
			// int type = intent.getExtras().getInt(Constant.ISKEEPLOGIN);
			System.out.println("接收到信息了=======act===============");
			String extra = intent.getExtras().getString(Constant.PUSHTYPE);
			System.out.println("接收到信息了=======act===============" + extra);
			try {
				JSONObject extraJson = new JSONObject(extra);
//				JSONObject android = extraJson.getJSONObject("android");
				int msgtype = Integer.parseInt(extraJson.getString("msgtype"));
				switch (msgtype) {
					case 0: //来新抢单了
						break;

					case 31: //来新消息了
						Constant.PUSH_MSG_NUM += 1;
						break;
					default:
						break;
				}
				//{"android":{"msgtype":"0","sound":"happy","badge":1}}

//                if (msgtype.equals(Constant.ENTRUSTDISABLED)) {
//                    ToastUtils.getInstance(MyApplication.getIntence().getApplicationContext()).show("您的账号已经被禁用，请联系客服!");
//                    UserInfoUtil.quiteAccount(MyApplication.getIntence().getApplicationContext());
//                    UserInfoUtil.setExitAccount(MyApplication.getIntence().getApplicationContext(), true);
//                    SharePreferenceUtil.getInstance(MyApplication.getIntence().getApplicationContext())
//                            .setString(SharePreferenceConstant.ENTRUST_MOBLIEPHONE, "");
//                    MyApplication.getIntence().isBackToMain = true;
//                    Intent intentLogin = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intentLogin);
//                    new ActivityAnimation(getActivity(), Constant.RIGHT_ENTER);
//                    JPushInterface.stopPush(MyApplication.getIntence().getApplicationContext());
//                }
				 /*
     *  msgtype 类型
     *  0   您有一条新的抢单
     *  1   抢单成功
     *  2   抢单失败
     *  3   结算成功
     *  4   承运商禁用
     *  14  委托方禁用
     *  11  您有一条新的委托已成立
     *  12  您有一条订单已送达
     *  31  新的公告
     */
//				if(msgtype.equals("0")){
//
//				}else if(msgtype.equals("31")){
//
//				}
			} catch (JSONException e) {
				e.printStackTrace();
			}


		}
	}

}

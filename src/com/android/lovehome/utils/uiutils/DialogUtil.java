package com.android.lovehome.utils.uiutils;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.lovehome.R;
import com.android.lovehome.adapter.PublishDialogAdapter;
import com.android.lovehome.app.MyApplication;

/**
 * DialogUtil
 * @author yongbing.chen
 * @updateauthor yongbing.chen
 * @description 弹出框工具类
 * @createtime  2015-1-5
 * @updatetime  2015-1-5
 *
 */
public class DialogUtil {

	private static DialogUtil dialogUtil=null;
	private static Context context;
	private Dialog dialog;
	private int drawbleId= R.drawable.ic_launcher;
	private String telnumber;//联系号码

	DialogButtonCallBack dialogButtonCallBack;
	DialogCallBack dialogCallBack;
	DialogButtomCallBack buttomCallBack;
	DialogOkCallBack  butOkCallBack ;
	DialogSharedCallBack sharedCallBack;
    DialogListClickCallBack dialogListClickCallBack;

    public interface DialogListClickCallBack{
        void onItemClicklistener();
    }
	//一个回调的接口
	public interface DialogCallBack{
		void onButtomClick();  // 点击弹框底部按钮回调
	}
	//两个回调的接口
	public interface DialogButtonCallBack{
		void onLeftButtonClick();  // 点击弹框左边按钮回调
		void onRigthButtonClick();    // 点击弹框右边按钮回调
	}
	//四个回调的接口
	public interface DialogButtomCallBack{
		void onTopButtonClick();  // 点击弹框顶部按钮回调
		void onCenterButtonClick(String textString);    // 点击弹框中间按钮回调
		void onButtomClick();  //点击弹框底部按钮回调
	}
	
	//分享回调接口
	public interface DialogSharedCallBack{
		void onWeixinClick();
		void onWeixinCicleClick();
		void onQQClick();
		void onWeiboClick();
	}
	
	public interface DialogOkCallBack{
		void onOkClick(String task_id, String entrust_name, String entrust_id) ;
		void onPhone() ;
	}
	/**
	 * @param con
	 * @return
	 */
	public static DialogUtil getInstance(Context con) {
		if(dialogUtil==null){
			dialogUtil=new DialogUtil();
		}
		context=con;
		return dialogUtil;
	}

	/**
	 * 显示标题  内容     一个按钮
	 * @param content
	 */
	public void showDialog(String content,DialogButtonCallBack dialogCallBack) {
		this.dialogButtonCallBack=dialogCallBack;
		showDialog(content);
	}

	/**
	 * 设置背景图片
	 */
	public void setBackgroundResource(int drawbleId) {
		this.drawbleId=drawbleId;
	}

	private void showDialog(String content) {

		dialog=new Dialog(context,R.style.GrouppublishDialog);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
		//LinearLayout layout_bg=(LinearLayout) view.findViewById(R.id.bg);
		//layout_bg.setBackgroundResource(drawbleId);
		TextView text_title=(TextView) view.findViewById(R.id.text_content);
        text_title.setText(content);

		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystylefrom_top); // 添加动画
		dialog.show();
	}
	
	/**
	 * 弹出退出登录框
	 * @param title
	 * @param content
	 * @param leftButtonText
	 * @param rigthButtonText
	 * @param buttomText
	 */
	private void showExitDialog(String title,String content,String leftButtonText,String rigthButtonText,String buttomText) {

		/*dialog=new Dialog(context,R.style.GrouppublishDialog);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_version, null);
		TextView text_title=(TextView) view.findViewById(R.id.dialog_version_title);
		text_title.setTextColor(context.getResources().getColor(R.color.text_whitle));
		if(title!=null){
			text_title.setText(title);
			text_title.setVisibility(View.VISIBLE);
		}
		if(content!=null){
			TextView text_content=(TextView)view.findViewById(R.id.dialog_version_content);
			text_content.setText(content);
			text_content.setVisibility(View.VISIBLE);
		}
		TextView text_igoner=(TextView)view.findViewById(R.id.text_igoner);
		text_igoner.setVisibility(View.VISIBLE);
		text_igoner.setText(leftButtonText);
		TextView text_update=(TextView)view.findViewById(R.id.text_update);
		text_update.setText(rigthButtonText);
		text_igoner.setOnClickListener(new MyOnClickListener());
		text_update.setOnClickListener(new MyOnClickListener());

		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystylefrom_top); // 添加动画
		dialog.show();*/
	}


	/***
	 * 物流专员弹出框
	 * @param customer
	 * @param phone
	 * @param buttomCallBack
	 */
	public void showDialogCustomer(String customer, String phone ,DialogButtomCallBack  buttomCallBack) {
		this.buttomCallBack=buttomCallBack;
		showButtomDialog("您的物流专员是 "+customer , phone , true);
	}
	/**
	 * 客服热线弹出框
	 */
	public void showDialogCustomer(String phone ,DialogButtomCallBack  buttomCallBack) {
		this.buttomCallBack=buttomCallBack;
		if(phone!=null&&!phone.equals("")){
			showButtomDialog("物流客户端服务热线\n(工作日服务时间: 8:30~17:30)" , phone , true);
		}
		else{
			showButtomDialog("物流客户端服务热线\n(工作日服务时间: 8:30~17:30)" , "021-60100381" , true);
		}
	}


	/**
	 * 
	 * @param topTitle 标题
	 * @param content  内容
	 * @param bool  true 表示客服     false表示拍照
	 */
	private void showButtomDialog(String topTitle,String content, boolean bool) {

		/*dialog=new Dialog(context,R.style.GrouppublishDialog);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_button, null);
		Button text_title=(Button) view.findViewById(R.id.dialog_top);
		TextView text_dialog=(TextView)view.findViewById(R.id.text_dialog);
		TextView dialog_line=(TextView)view.findViewById(R.id.dialog_line);
		//客服
		if(bool){
			text_title.setVisibility(View.GONE);
			text_dialog.setVisibility(View.VISIBLE);
			dialog_line.setVisibility(View.GONE);
			text_dialog.setText(topTitle);
		}
		//拍照
		else{
			text_title.setVisibility(View.VISIBLE);
			text_dialog.setVisibility(View.GONE);
			dialog_line.setVisibility(View.VISIBLE);
			text_title.setText(topTitle);
			text_title.setOnClickListener(new MyOnClickListener());
		}
		if(content!=null){
			Button text_content=(Button)view.findViewById(R.id.dialog_center);
			text_content.setText(content);
			text_content.setVisibility(View.VISIBLE);
			text_content.setOnClickListener(new MyOnClickListener());
			telnumber=content;
		}
		Button text_buttom_sure=(Button) view.findViewById(R.id.dialog_buttom);
		text_buttom_sure.setOnClickListener(new MyOnClickListener());

		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画
		dialog.show();*/
	}


    /**
     * 弹出打电话框
     * @param name
     * @param mobilephone
     * @param telephone
     */
    private void showExitDialog(String name, String mobilephone, String telephone) {

        dialog=new Dialog(context, R.style.GrouppublishDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        if(name!=null){
            TextView text_content=(TextView)view.findViewById(R.id.dialog_title);
            text_content.setText(name);
            text_content.setVisibility(View.VISIBLE);
        }
        TextView text_igoner=(TextView)view.findViewById(R.id.dialog_mobile);
        text_igoner.setVisibility(View.VISIBLE);
        text_igoner.setText(mobilephone);
        TextView text_update=(TextView)view.findViewById(R.id.dialog_telphone);
        text_update.setText(telephone);

        Button button=(Button)view.findViewById(R.id.button_mobile);
        Button button_tel=(Button)view.findViewById(R.id.button_telphone);
        button.setOnClickListener(new MyOnClickListener());
        button_tel.setOnClickListener(new MyOnClickListener());

        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystylefrom_top); // 添加动画
        dialog.show();
    }

    //忽略抢单
    public void  IgnoreDialog(DialogButtonCallBack dialogButtonCallBack){
        this.dialogButtonCallBack=dialogButtonCallBack;
        dialog=new Dialog(context,R.style.GrouppublishDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_exit, null);
        TextView txtCenter = (TextView) view.findViewById(R.id.tv_ignore_content);
        txtCenter.setText("确定要忽略该条求购吗？");

        TextView tv_cancel=(TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_sure=(TextView) view.findViewById(R.id.tv_sure);
        tv_cancel.setText("确定");
        tv_sure.setText("取消");
        tv_cancel.setOnClickListener(new MyOnClickListener());
        tv_sure.setOnClickListener(new MyOnClickListener());

        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystylefrom_top); // 添加动画
        dialog.show();
    }

	/**
	 * 微信弹出框
	 * @param sharedCallBack
	 */
	public void showSharedDialog(DialogSharedCallBack sharedCallBack){
		this.sharedCallBack=sharedCallBack;
		showSharedDialog();
	}
	
	/**
	 * 分享dialog
	 */
	private void showSharedDialog() {
		
		dialog=new Dialog(context,R.style.GrouppublishDialog);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_shared, null);
		Button button_weixin=(Button) view.findViewById(R.id.button_weixin);
		Button button_weixin_cicle=(Button) view.findViewById(R.id.button_weixin_cicle);
		Button button_qq=(Button) view.findViewById(R.id.button_qq);
		Button btn_cancel=(Button) view.findViewById(R.id.btn_cancel);
		button_weixin.setOnClickListener(new MyOnClickListener());
		button_weixin_cicle.setOnClickListener(new MyOnClickListener());
		button_qq.setOnClickListener(new MyOnClickListener());
		btn_cancel.setOnClickListener(new MyOnClickListener());
		
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画
		dialog.show();
	}
	
	
	/**
	 * 弹出list列表框
	 * @param list
	 */
	public void showDialogList(List<String> list,DialogListClickCallBack dialogListClickCallBack)
    {
        this.dialogListClickCallBack=dialogListClickCallBack;
		showDialog(list);
	}

	/**
	 * 带list的弹出框
	 * @param list
	 */
	private void showDialog(List<String> list){
		dialog=new Dialog(context,R.style.GrouppublishDialog);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
		ListView dialog_listview=(ListView) view.findViewById(R.id.dialog_list);
        PublishDialogAdapter dialogAdapter=new PublishDialogAdapter(context,list);
		dialog_listview.setAdapter(dialogAdapter);
        dialog_listview.setOnItemClickListener(new MyListItemOnClicklistener());

		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画
		dialog.show();
	}


    class MyListItemOnClicklistener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            dialog.dismiss();
            dialogListClickCallBack.onItemClicklistener();
        }
    }


	public class MyOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			
			}
		}
	}
}

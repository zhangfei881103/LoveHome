package com.android.lovehome.utils.uiutils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lovehome.R;
import com.android.lovehome.app.MyApplication;

/**
 * 
 * 
 * 类名称:ToastUtils
 * 类描述:重写Toast，控制宽度且显示在正中间
 * 修改时间:2015-11-6 上午9:39:42
 * 修改备注:
 * @version 1.0.0
 *
 */
public class ToastUtils {
	private Toast toast ;
	private static Context mContext ;
	private static ToastUtils instance = null; 
	private static int screenWidth ;
	private static int screenHeight ;
	private static String showContent;//上次显示的文字

	public static ToastUtils getInstance(Context context) {    
		if (instance == null) {
			mContext = context ;
			getScreenSize();
			instance = new ToastUtils();   
		}    
		return instance;    
	} 

	/**
	 * 
	 * show toast显示
	 * @param text 需要显示的文字
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	long exitTime = 0;
	public void show(String text) {
		//如果两次显示内容一样       那就判断时间大于3秒在显示
		if(text.equals(showContent)){
			if ((System.currentTimeMillis() - exitTime) > 3000) {
				exitTime = System.currentTimeMillis();
				showContent(text);
			} 
		}
		else{
			showContent(text);
		}
	}
	/**
	 * 显示内容
	 * @param text
	 */
	private void showContent(String text) {
		View layout = LayoutInflater.from(mContext).inflate(R.layout.layout_toast , null);
		TextView btn = (TextView) layout.findViewById(R.id.txt_toast);
		btn.setText(text);
		toast = new Toast(mContext);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
		showContent=text;
	}
	/**
	 * show toast显示在底部
	 * @param text 需要显示的文字
	 * void
	 */
	public void showforButtom(String text) {
		View layout = LayoutInflater.from(mContext).inflate(R.layout.layout_toast , null);
		TextView btn = (TextView) layout.findViewById(R.id.txt_toast);
		btn.setText(text);
		toast = new Toast(mContext);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}

	/**
	 * 
	 * show toast显示
	 * @param id 需要显示的资源文件id
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void show(int id){
		View layout = LayoutInflater.from(mContext).inflate(R.layout.layout_toast , null);
		layout.getBackground().setAlpha(100);
		TextView btn = (TextView) layout.findViewById(R.id.txt_toast);
		btn.setWidth(2*screenWidth/3);
		btn.setText(mContext.getResources().getString(id));
		toast = new Toast(mContext);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}
    /**
     *
     * show(这里用一句话描述这个方法的作用) 显示自定义提示
     *
     * @param custom
     *            void
     * @exception
     * @since 1.0.0
     */
    public static void shows(String custom) {
        show(MyApplication.getIntence().getApplicationContext(), custom, Toast.LENGTH_SHORT);
    }
    public static void show(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }
	/**
	 * 
	 * getScreenSize 获取屏幕大小
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	private static void getScreenSize(){
		WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		screenHeight = wm.getDefaultDisplay().getHeight();
	}

}

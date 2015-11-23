
package com.android.lovehome.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.widget.Toast;

import com.android.lovehome.app.MyApplication;

/**
 * 
 * 
 * 类名称:Http
 * 类描述:网络功能处理类
 * 修改时间:2014-11-5 上午11:19:43
 * 修改备注:
 * @version 1.0.0
 *
 */
public class Http {
	private static ConnectivityManager cm = (ConnectivityManager) MyApplication.getApp().getApplicationContext()
			.getSystemService(Context.CONNECTIVITY_SERVICE);
	private static final String APK = ".apk";
	private static final String TEMP = ".temp";
	public static int MSG_ERROR = 0x00ae;
	public static int MSG_SUCCESS = MSG_ERROR + 1;
	public static int MSG_USERCANCEL = MSG_SUCCESS + 1;
	
	/**
	 * 
	 * getNetStatic
	 * 获取是否可用网络状态  可以吐司
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean getNetStatic(Context context){
		if(get3GEnvironment()||getWIFIEnvironment()){
			return true;
		}else{
			Toast.makeText(context, "当前暂无网络，请检测你的网络！", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	/**
	 * 
	 * getNetStatic
	 * 获取是否可用网络状态   不可以吐司
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean getNetStatic(){
		if(get3GEnvironment()||getWIFIEnvironment()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 获取3G网络状态
	 * @return
	 */
	public static boolean get3GEnvironment() {
		State result = null;
		result = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		if (result == State.CONNECTED) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取WIFI网络状态
	 * 
	 * @return
	 */
	public static boolean getWIFIEnvironment() {
		State result = null;
		result = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		if (result == State.CONNECTED) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取当前正在使用的网络类型
	 * @return
	 */
	public static String getInternetConnectionType() {
		String type = "";
		NetworkInfo[] result = cm.getAllNetworkInfo();
		for (int i = 0; i < result.length; i++) {
			boolean state = result[i].isConnectedOrConnecting();
			if (state) {
				type = result[i].getTypeName();
			}
		}
		return type;
	}

	public static int getConnectionType(Context context) {
		int flag = -1;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net3g = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifi.isConnectedOrConnecting()) {
			flag = 0;
		} else if (net3g.isConnectedOrConnecting()) {
			flag = 1;
		}
		// 其他网络
		else {
			// return false;
		}
		return flag;
	}
}

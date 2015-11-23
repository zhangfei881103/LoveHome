package com.android.lovehome.app;

import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class MyApplication extends Application {
	private static MyApplication mAppApplication;
	public static Context context;
	private int screenHeight;
	private int screenWidth;
	private int versionCode;
	private String versionName;
	public boolean isSSLERROR=false;
	/**
	 * 评价所使用的栈
	 */
	public Stack<Activity> evaluateActivities;
	// 退出应用后去注册页面的状态
	public static boolean quiteAndGoToRegist = false;
	/**
	 * 登录使用的栈
	 */
	public Stack<Activity> loginActivities;
	/**
	 * 所有页面的栈，退出时候用
	 */
	public Stack<Activity> mainActivities;

	@Override
	public void onCreate() {
		super.onCreate();
		mAppApplication = this;
		context = getApplicationContext();
		evaluateActivities = new Stack<Activity>();
		loginActivities = new Stack<Activity>();
		mainActivities = new Stack<Activity>();

	}

	// 单例防止重复new
	public static MyApplication getIntence() {
		if (mAppApplication == null) {
			mAppApplication = new MyApplication();
		}
		return mAppApplication;
	}

	/** 获取Application */
	public static MyApplication getApp() {
		return mAppApplication;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	/**
	 * 获取版本名称
	 * 
	 * @return
	 */
	public String getVersionName() {
		try {
			PackageManager packageManager = getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			versionName = packInfo.versionName;
			return versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取版本号
	 * 
	 * @return
	 */
	public int getVersionCode() {
		try {
			PackageManager packageManager = getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			versionCode = packInfo.versionCode;
			return versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

package com.android.lovehome.controller;

import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.android.lovehome.http.HttpRequestAsyncController;
import com.android.lovehome.interferce.HttpRequestCallBack;
/**
 * MainControllerAsync
 * @description 一次可以同时执行多次的异步控制类
 * @createtime  2015-11-6
 * @updatetime  2015-11-6
 *
 */
@SuppressLint("NewApi")
public abstract class MainControllerAsync extends Controller{

	/**
	 * @param mContext  上下文
	 * @param mCallback  回调接口
	 * @param method   网络请求方式
	 * @param taskId   请求任务id
	 * @param param    动态传参
	 */
	public MainControllerAsync(Context mContext, HttpRequestCallBack mCallback,
			int method,int taskId,String... param) {
		super(mContext, mCallback, method,taskId);
	}

	/**
	 * 异步执行方法
	 */
	@Override
	public void doAsyncController(Context mContext,HttpRequestCallBack mCallback,List<NameValuePair> valuePairs,
			String json, String url, int method,int taskId,String requestMethod) {
		/**
		 * android 3.0以后    新增了接口#executeOnExecutor()  
		 * 3.0之前的手机都不能运行
		 */
		String version=android.os.Build.VERSION.RELEASE;
		int version2=Integer.parseInt(version.substring(0, 1));
		if(version2<3){
			new HttpRequestAsyncController(mContext,mCallback, valuePairs, json, url, method,taskId,requestMethod)
			.execute();
		}
		else{
			new HttpRequestAsyncController(mContext,mCallback, valuePairs,json, url, method,taskId,requestMethod)
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
		}
	}

	/**设置参数 
	 * 
	 * @param param
	 */
	public abstract void setParams(String... param);
}

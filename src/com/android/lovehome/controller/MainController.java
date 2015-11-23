package com.android.lovehome.controller;

import java.util.List;

import org.apache.http.NameValuePair;

import android.content.Context;

import com.android.lovehome.http.HttpRequestAsyncController;
import com.android.lovehome.interferce.HttpRequestCallBack;

/**
 * MainController
 * @description 一次只能执行一次的异步控制类
 * @createtime  2015-11-6
 * @updatetime  2015-11-6
 *
 */
public abstract class MainController extends Controller{
	
	private HttpRequestAsyncController httpRequestAsyncController;

	/**
	 * @param mContext  上下文
	 * @param mCallback  回调接口
	 * @param method   网络请求方式
	 * @param taskId   请求任务id
	 * @param param    动态传参
	 */
	public MainController(Context mContext, HttpRequestCallBack mCallback,
			int method,int taskId,String... param) {
		super(mContext, mCallback, method,taskId);
	}

	/**
	 * 异步执行方法
	 */
	@Override
	public void doAsyncController(Context mContext,HttpRequestCallBack mCallback,List<NameValuePair> valuePairs,
			String json, String url, int method, int taskId,String requestMethod) {
		onCancle();
		httpRequestAsyncController = new HttpRequestAsyncController(mContext,mCallback, valuePairs, json, url, method,taskId,requestMethod);
		httpRequestAsyncController.execute();
	}
	
	/**设置参数 
	 * 
	 * @param param
	 */
	public abstract void setParams(String... param);
	
	/**
	 * 取消网络执行
	 */
	public void onCancle() {
		if(null != httpRequestAsyncController){
			httpRequestAsyncController.cancel(true); //如果还没执行完    就取消掉
		}
	}
}

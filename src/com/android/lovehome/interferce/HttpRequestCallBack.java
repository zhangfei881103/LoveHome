package com.android.lovehome.interferce;

import org.json.JSONObject;

/**
 * HttpRequestCallBack
 * HttpRequestCallBack 接口回调方法
 */
public interface HttpRequestCallBack {
	
	/**
	 * 开始请求
	 */
	public void startRequest(int taskId);
	
	/**
	 * 回调成功
	 * @param taskId
	 * @param jObject
	 */
	public void succeed(int taskId, JSONObject jObject);
	
	/**
	 * 回调失败
	 * @param taskId
	 * @param jObject
	 */
	public void failed(int taskId, JSONObject jObject);
	
	/**
	 * 回调异常
	 * @param msg
	 */
	public void exceptioned(int taskId, String msg);
}

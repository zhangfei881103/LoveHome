package com.android.lovehome.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;

import com.android.lovehome.constant.Constant;
import com.android.lovehome.interferce.HttpRequestCallBack;

/**
 * Controller
 * 
 * @description 底层控制层
 * @createtime 2015-11-5
 * @updatetime 2015-11-5
 * 
 */
public abstract class Controller {

	private Context mContext;
	private HttpRequestCallBack mCallback;
	private int requestType;
	private int taskId;

	public Controller(Context mContext, HttpRequestCallBack mCallback,
			int requestType, int taskId) {
		this.mContext = mContext;
		this.mCallback = mCallback;
		this.requestType = requestType;
		this.taskId = taskId;
	}

	/**
	 * 只传方法 不传参数
	 * 
	 * @param method
	 */
	public void setKeyValues(String method) {
		this.setKeyValuesL2(method, new String[] {}, new String[] {});
	}

	/**
	 * 设置参数
	 * 
	 * @param method
	 * @param keys
	 * @param values
	 */
	public void setKeyValuesL1(String[] keys, String[] values) {
		//onRequestL1(keys, values);
	}

	/**
	 * 设置参数
	 * 
	 * @param method
	 * @param keys
	 * @param values
	 */
	public void setKeyValuesL2(String method, String[] keys, String[] values) {
		onRequestL2(method, keys, values);
	}

	/**
	 * 设置参数
	 * 
	 * @param method
	 * @param keys
	 * @param values
	 */
	public void setKeyValuesL3(String method, String[] keys, String[] values) {
		onRequestL3(method, keys, values);
	}

	/**
	 * 添加参数 级别L2加密请求
	 * 
	 * @param method
	 * @param keys
	 * @param values
	 */
	private synchronized void onRequestL2(String method, String keys[],
			String values[]) {

		// /////////////////////
		long time = new Date().getTime();
		String eventTime = String.valueOf(time);
/*
		// 加密的参数
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appId", Constant.APPID));
		params.add(new BasicNameValuePair("method", method));
		// params.add(new BasicNameValuePair("eventTime", "1436855977"));
		params.add(new BasicNameValuePair("eventTime", eventTime));
		for (int i = 0; i < keys.length; i++) {
			params.add(new BasicNameValuePair(keys[i], values[i]));
		}

		// ////////////////
		String url = Constant.URL;
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("method", method);
		jsonObject.addProperty("appId", Constant.APPID);
		// jsonObject.addProperty("eventTime", "1436855977");
		jsonObject.addProperty("eventTime", eventTime);
		for (int i = 0; i < keys.length; i++) {
			jsonObject.addProperty(keys[i], values[i]);
		}

		// jsonObject.addProperty("sign", "F793FBFBDE0D6B9CC61A4644E8CD6F60");
		jsonObject.addProperty("sign", (MD5.getSign(params)).toUpperCase());
	    System.out.println(" L2 参数 "+jsonObject.toString());
		doAsyncController(mContext, mCallback, null, jsonObject.toString(),
				url, requestType, taskId, "json");*/
	}

	/**
	 * 级别L1请求 登陆调用
	 * 
	 * @param method
	 * @param keys
	 * @param values
	 *//*
	private synchronized void onRequestL1(String keys[], String values[]) {

		String url = Constant.LOGIN_URL;
		// 加密的参数
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		for (int i = 0; i < keys.length; i++) {
			params.add(new BasicNameValuePair(keys[i], values[i]));
			Log.e("方法名：" + " Controller 参数：", keys[i] + "  ------ " + values[i]);
		}
		
		JsonObject jsonObject = new JsonObject();
		for (int i = 0; i < keys.length; i++) {
			jsonObject.addProperty(keys[i], values[i]);
		}
		
		doAsyncController(mContext, mCallback, params, jsonObject.toString(), url, 5, taskId,
				"https");
	}*/

	/**
	 * 级别L3级别加密请求
	 * 
	 * @param method
	 * @param keys
	 * @param values
	 */
	private synchronized void onRequestL3(String method, String keys[],
			String values[]) {
		/*try {
			long time = new Date().getTime();
			String eventTime = String.valueOf(time);

			String url = Constant.URL;
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("method", method);
			jsonObject.addProperty("appId", Constant.APPID);
			jsonObject.addProperty("eventTime", eventTime);
			for (int i = 0; i < keys.length; i++) {
				jsonObject.addProperty(keys[i], values[i]);
			}
			String SecretKey = jsonObject.toString();
			System.out.println(SecretKey);
			
			JsonObject json = new JsonObject();
			json.addProperty(
					"D",
					Constant.TOKEN
							+ AES256Cipher.AES_Encode(SecretKey, Constant.SK));
			// System.out.println("chen L3 AES256加密 : "+AES256Cipher.AES_Encode(SecretKey,Constant.SK));
			System.out.println(" L3 参数 "+json.toString());
			doAsyncController(mContext, mCallback, null, json.toString(), url,
					requestType, taskId, "json");

		} catch (Exception e) {
			e.printStackTrace();
		}*/

	}

	/**
	 * 执行请求方法
	 * 
	 * @param mContext
	 * @param mCallback
	 * @param params
	 * @param url
	 * @param method
	 * @param taskId
	 */
	public abstract void doAsyncController(Context mContext,
			HttpRequestCallBack mCallback, List<NameValuePair> valuePairs,
			String json, String url, int method, int taskId,
			String requestMethod);

}

package com.android.lovehome.http;

import org.json.JSONObject;

import android.os.AsyncTask;

/**
 * HttpAsyncController
 * 类名称:HttpAsyncController
 * 类描述:异步请求抽象类    拓展性比较强
 * 修改时间:2015-11-6
 * 修改备注:
 * @version 1.0.0
 */
public abstract class HttpAsyncController extends AsyncTask<Object, Integer, JSONObject>{
	
	@Override
	protected JSONObject doInBackground(Object... arg0) {
		return doInBack(arg0);
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		onPre();
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		onPost(result);
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}
	
	protected abstract JSONObject doInBack(Object... arg0);
	
	protected abstract void onPre();
	
	protected abstract void onPost(JSONObject result);
	
}

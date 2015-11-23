package com.android.lovehome.http;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.lovehome.R;
import com.android.lovehome.app.MyApplication;
import com.android.lovehome.interferce.HttpRequestCallBack;
import com.android.lovehome.utils.uiutils.ToastUtils;


/**
 * HttpRequestAsyncController
 * 类名称:HttpRequestAsyncController
 * 类描述:异步请求类
 * 修改时间:2015-11-8
 * 修改备注:
 *
 * @version 1.0.0
 */
public class HttpRequestAsyncController extends HttpAsyncController {
    private Context mContext;
    //接口回调
    private HttpRequestCallBack callBack;
    //参数
    private List<NameValuePair> valuePairs;
    private String json;
    //服务器地址
    private String requestUrl;
    //请求方式  post  get
    private int method;
    //请求方法
    private String requestMethod;  //json  参数请求  还是 list集合参数
    //任务id    避免数据混淆
    private int taskId = 0;
    private static final int HTTP_REQUEST_SUCCES = 1;   //请求网咯成功
    private static final int HTTP_REQUEST_NO_MESSAGE = 0;//请求无聊成功  但无数据
    private static final int HTTP_REQUEST_FAILED = 2;  //token失效
    private static int HTTP_STATE;                         //网络请求状态
    private static final int HTTP_STATE_CONNECTTIMEOUT = 3;  //连接超时
    private static final int HTTP_STATE_CONNECT = 4;         //网络异常
    private static final int HTTP_STATE_SOCKETTIMEOUT = 5;   //socket连接异常
    private static final int HTTP_STATE_ILLEGALSTATE = 6;    //非法异常
    private static final int HTTP_STATE_JSON = 7;            //json解析异常
    private static final int HTTP_STATE_QUITE = -2;          //账号失效

    public HttpRequestAsyncController(Context mContext, HttpRequestCallBack callBack, List<NameValuePair> valuePairs,
                                      String json, String url, int method, int taskId, String requestMethod) {
        this.mContext = mContext;
        this.callBack = callBack;
        this.valuePairs = valuePairs;
        this.json = json;
        this.requestUrl = url;
        this.method = method;
        this.taskId = taskId;
        this.requestMethod = requestMethod;
    }

    @Override
    protected JSONObject doInBack(Object... arg0) {
        if (Http.getNetStatic()) {
            try {
                if (requestMethod.equals("json")) {
                    return HttpHelper.getInstance().execute(method, json, requestUrl);
                } else if(requestMethod.equals("https")){
                	return new JSONObject(HttpHelper.getInstance().doPostHttps(requestUrl, json));
                }else {
                    Map<String, String> params = new HashMap<String, String>();
                    for (int i = 0; i < valuePairs.size(); i++) {
                        params.put(valuePairs.get(i).getName(), valuePairs.get(i).getValue());
                    }
                    String json = HttpHelper.getInstance().sendHttpsRequestByPost(requestUrl, params);
                    return new JSONObject(json);
                }
            } catch (IllegalStateException e) {
                HTTP_STATE = HTTP_STATE_ILLEGALSTATE;
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                if((e instanceof SSLHandshakeException)){
                }
                if ((e instanceof ConnectTimeoutException)) {
                    //请求超时
                    HTTP_STATE = HTTP_STATE_CONNECTTIMEOUT;
                } else if (((e instanceof ConnectException)) || ((e.getCause() instanceof ConnectException))) {
                    // 网络异常
                    HTTP_STATE = HTTP_STATE_CONNECT;
                } else if (e instanceof SocketTimeoutException) {
                    //请求超时
                    HTTP_STATE = HTTP_STATE_SOCKETTIMEOUT;
                } else if (e instanceof JSONException) {
                    //数据解析异常
                    HTTP_STATE = HTTP_STATE_JSON;
                } else {
                    //未知异常 全部处理为网络异常
                    HTTP_STATE = HTTP_STATE_CONNECT;
                }
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    protected void onPre() {
        callBack.startRequest(taskId);
    }

    @Override
    protected void onPost(JSONObject result) {
        if (result != null) {
            Log.i("AsyncController 返回结果： ", result.toString() + " taskid: " + taskId);
            try {

                if (requestMethod.equals("json")) {
                    int success = Integer.parseInt(result.getString("success"));
                    switch (success) {
                        case HTTP_REQUEST_SUCCES:
//                            showQuite();
                            callBack.succeed(taskId, result);
                            break;
                        case HTTP_STATE_QUITE:

                        	break;
                        case HTTP_REQUEST_NO_MESSAGE:
                            callBack.failed(taskId, result);
                            break;
                        case HTTP_REQUEST_FAILED:
                            callBack.failed(taskId, result);
                            break;
                        default:
                            callBack.failed(taskId, result);
                            break;
                    }
                } else {
                    String RSCode = result.getString("RSCode");
                    if (RSCode.equals("0")) {
                        callBack.succeed(taskId, result);
                    } else {
                        callBack.failed(taskId, result);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                showErrorMessage();
            }
        } else {
            switch (HTTP_STATE) {
                case HTTP_STATE_CONNECTTIMEOUT:
                    showErrorOutTime();
                    break;
                case HTTP_STATE_CONNECT:
                    showErrorInfo();
                    break;
                case HTTP_STATE_SOCKETTIMEOUT:
                    showErrorInfo();
                    break;
                case HTTP_STATE_ILLEGALSTATE:
                    showErrorMessage();
                    break;
                case HTTP_STATE_JSON:
                    showErrorMessage();
                    break;
                default:
                    showErrorInfo();
                    break;
            }
        }
    }

    /**
     * 提示连接超时
     */
    private void showErrorOutTime() {
        ToastUtils.getInstance(mContext).show(MyApplication.getIntence().getResources().
                getString(R.string.defultshownonew));
        callBack.exceptioned(taskId, MyApplication.getIntence().getResources()
                .getString(R.string.connect_outtime));
    }

    /**
     * 显示弹出错误提示网络信息
     */
    private void showErrorInfo() {
        if(MyApplication.getIntence().isSSLERROR){
            ToastUtils.getInstance(mContext).show(MyApplication.getIntence().getResources().
                    getString(R.string.defultsslerror));

        }else{
            ToastUtils.getInstance(mContext).show(MyApplication.getIntence().getResources().
                    getString(R.string.defultshownonew));
        }
        MyApplication.getIntence().isSSLERROR=false;
        callBack.exceptioned(taskId, MyApplication.getIntence().getResources()
                .getString(R.string.defultshownonew));
    }

    /**
     * 显示弹出错误信息
     */
    private void showErrorMessage() {
        ToastUtils.getInstance(mContext).show(MyApplication.getIntence().getResources().
                getString(R.string.defultshownonew));
        callBack.exceptioned(taskId, MyApplication.getIntence().getResources()
                .getString(R.string.defultshowerror));
    }

}

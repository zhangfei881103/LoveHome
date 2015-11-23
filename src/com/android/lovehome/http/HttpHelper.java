package com.android.lovehome.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.lovehome.app.MyApplication;


/**
 * 类名称:HttpHelper
 * 类描述:网络请求
 * 修改时间:2014-11-5
 * 修改备注:
 * @version 1.0.0
 * @author yongbing.chen
 *
 */

@SuppressLint("NewApi")
public class HttpHelper {
	        
	private static HttpHelper httpHelper=null;
	private static final int SO_TIMEOUT =8000;
	private static final int CONNECTION_TIMEOUT = 8000;
	
	private static final int GET = 0;
	private static final int POST = 1;
	private static final int PUT = 2;
	private static final int DELETE = 3;
	private static final int BITMAP = 4;
	
	public static HttpHelper getInstance(){
		if(httpHelper==null){
			httpHelper=new HttpHelper();
		}
		return httpHelper;
	}
	
	/**
	 * 执行网络请求
	 * @param method
	 * @param paramer
	 * @param url
	 */
	public JSONObject execute(int method,String json,String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpClient.getParams(), SO_TIMEOUT);
		try {
			HttpResponse response = null;
			switch (method) {
			case GET:
				response = httpClient.execute(new HttpGet(url));
				break;
			case POST:
				/*HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(paramer,HTTP.UTF_8));
				response = httpClient.execute(httpPost);*/
			    HttpPost post = new HttpPost(url);
			    
			    //json="{\"method\":\"logistics.carrier.verification.get\",\"carrierCode\":\"18516211375\",\"type\":\"1\",\"appId\":\"pmwlid\",\"eventTime\":\"1436754977262\",\"sign\":\"F793FBFBDE0D6B9CC61A4644E8CD6F60\"}";
			     
			    post.setEntity(new StringEntity(json));
			    StringEntity se = new StringEntity(json,"UTF-8");
			    se.setContentEncoding("UTF-8");
			    se.setContentType("application/json");
			    post.setEntity(se);
			    response= httpClient.execute(post);
				break;
			case PUT:
				/*HttpPut httpPut = new HttpPut(url);
				httpPut.setEntity(new UrlEncodedFormEntity(paramer,HTTP.UTF_8));
				response = httpClient.execute(httpPut);*/
				break;
			case DELETE:
				response = httpClient.execute(new HttpDelete(url));
				break;
			case BITMAP:
				response = httpClient.execute(new HttpGet(url));
				processBitmapEntity(response.getEntity());
				break;
			}
			if (method < BITMAP)
				return processEntity(response.getEntity());
		} catch (Exception e) {
			if(e!=null){
				e.printStackTrace();
			}
			return null;
		} finally{
			
		}
		
		return null;
	}
	
	/**
     * 发送HTTPS  POST请求   登陆接口调用
     * 
     * @param 要访问的HTTPS地址,POST访问的参数Map对象
     * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">@return</a>  返回响应值
     * */
    public static final String sendHttpsRequestByPost(String url, Map<String, String> params) {
       
    	
    	String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}
		}; 
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);
            //创建SSLSocketFactory
            //KeyStore keyStore=new 
            //SSLSocketFactory.getSocketFactory().setHostnameVerifier(hostnameVerifier);
            SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity != null) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        }
		catch (SSLHandshakeException e){
			MyApplication.getIntence().isSSLERROR=true;
			e.printStackTrace();
		}
		catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
    
    ////////////////////////////////
    /**
     * https 免证书访问请求
     * @param url
     * @param json
     * @return
     */
    public static String doPostHttps(String url, String json) {
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        try {
            if (url.startsWith("https:")) {
                //免证书
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                       /* logger.info("-----doPostHttps : HostnameVerifier" + urlHostName + " vs. "
                                + session.getPeerHost());*/
                        return true;
                    }
                };
                try {
                    trustAllHttpsCertificates();
                } catch (Exception e) {

                }
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
            }
            URL validationUrl = new URL(url);
            connection = (HttpURLConnection) validationUrl.openConnection();
            connection.setRequestMethod("POST");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Length", String.valueOf(json.length()));
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            // 发送请求参数
            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(json);
            out.flush();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer stringBuffer = new StringBuffer(255);

            synchronized (stringBuffer) {
                while ((line = reader.readLine()) != null) {
                    stringBuffer.append(new String(line.getBytes(), "utf-8"));
                    stringBuffer.append("\n");
                }
                return stringBuffer.toString();
            }
        } catch (final IOException e) {
            return "";
        } catch (final Exception e1) {
            return "";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }

        }
    }

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }
    
    static class miTM implements TrustManager,
	    X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
		    return null;
		}
		
		public boolean isServerTrusted(
		        X509Certificate[] certs) {
		    return true;
		}
		
		public boolean isClientTrusted(
		        X509Certificate[] certs) {
		    return true;
		}
		
		public void checkServerTrusted(
		        X509Certificate[] certs, String authType)
		        throws CertificateException {
		    return;
		}
		
		public void checkClientTrusted(
		        X509Certificate[] certs, String authType)
		        throws CertificateException {
		    return;
		}
	}

    
    ////////////////////////

	
	private JSONObject processEntity(HttpEntity entity) throws IllegalStateException,
		IOException,Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(entity
				.getContent()));
		String line, result = "";
		while ((line = br.readLine()) != null)
			result += line;
		return new JSONObject(result);
	}
	
	private void processBitmapEntity(HttpEntity entity) throws IOException {
		BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
		Bitmap bm = BitmapFactory.decodeStream(bufHttpEntity.getContent());
		//httpCallBack.succse();
	}
}

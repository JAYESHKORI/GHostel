package com.example.jayesh.ghostel.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.example.jayesh.ghostel.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.security.KeyStore;


public class WebInterface {




	public static boolean isOnline(Context context) {
		try {
			if (context == null)
				return false;

			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (cm != null) {
				return cm.getActiveNetworkInfo().isConnected();
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}



	public static ApiResponse doPost(String url, String jsonString)
			throws Exception {


		ApiResponse apiResponse = null;
		HttpEntity httpentity = null;
		HttpClient httpClient = null;
		HttpParams httpParameters = null;
		HttpPost httppost = null;
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(null, null);

		MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		registry.register(new Scheme("https", sf, 443));

		ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,Const.TIMEOUT_CONNECTION);
		HttpConnectionParams.setSoTimeout(httpParameters, Const.TIMEOUT_SOCKET);

		// set timeout
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,Const.TIMEOUT_CONNECTION);
		HttpConnectionParams.setSoTimeout(httpParameters, Const.TIMEOUT_SOCKET);


		httpClient = new DefaultHttpClient(ccm, params);
		httppost = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonString,"UTF-8");
		httppost.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(httppost);

		apiResponse = new ApiResponse();
		apiResponse.code = httpResponse.getStatusLine().getStatusCode();


		if (apiResponse.code == HttpStatus.SC_OK)
		{
			httpentity = httpResponse.getEntity();
			apiResponse.response = new String(EntityUtils.toString(httpentity));
		}

		// release
		httpentity = null;
		httpResponse = null;
		httppost = null;
		httpClient = null;
		System.out.println("********apiResponse************"+apiResponse);
		return apiResponse;
	}



	public static ApiResponse doGet(String url) throws Exception
	{

		ApiResponse apiResponse = null;
		HttpEntity httpentity = null;
		HttpGet httpGet = null;

		HttpClient httpclient = new DefaultHttpClient();

		url = url.replace(" ", "%20");

		httpGet = new HttpGet(url);

		HttpResponse httpResponse = httpclient.execute(httpGet);

		apiResponse = new ApiResponse();
		apiResponse.code = httpResponse.getStatusLine().getStatusCode();

		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			httpentity = httpResponse.getEntity();
			apiResponse.response = new String(EntityUtils.toString(httpentity));
		}

		// Release
		httpResponse = null;
		httpclient = null;
		httpGet = null;
		httpentity = null;

		return apiResponse;
	}

	public static void showAPIErrorAlert(Context context, String errorType,String errorMessage)
	{
		System.out.println("errortype:"+errorType);
		System.out.println("errormessage:"+errorMessage);

		if (Const.NETWORK_ERROR.equals(errorType))
			Toast.makeText(context,context.getString(R.string.api_no_internet_msg),Toast.LENGTH_LONG).show();
		else if (Const.API_ERROR.equals(errorType))
			Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show();
		else if (Const.API_ALERT.equals(errorType))
			Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show();
		else
		{}
	}
}
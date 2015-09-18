package com.missingdrop.utils.common;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.ConnectivityManager;

public class CheckConnectivity {

	public static boolean isOnline(Context context, boolean checkRouter,
			String Link) {
		boolean result = true;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// CachedLogger.Log(context, TAG, "starting Connection Checker");
		result = cm != null;
		if (result)
			result = cm.getActiveNetworkInfo() != null;
		if (result)
			result = cm.getActiveNetworkInfo().isConnectedOrConnecting();
		if (result && checkRouter)
			result = RouterIsConnected(context, Link);

		return result;
	}

	public static boolean RouterIsConnected(Context context, String Link) {
		// String checkUrl = "http://www.google.com";
		String checkUrl = Link;
		// CachedLogger.Log(context, TAG, "Checking Router");
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
		HttpConnectionParams.setSoTimeout(httpParameters, 10000);
		HttpGet httpget = new HttpGet(checkUrl);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setParams(httpParameters);

		try {
			HttpResponse response = httpClient.execute(httpget);
			int code = response.getStatusLine().getStatusCode();
			return code == HttpStatus.SC_OK;
		} catch (Exception e) {
		}
		return false;
	}
}

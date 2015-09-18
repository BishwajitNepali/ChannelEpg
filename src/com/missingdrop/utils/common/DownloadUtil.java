package com.missingdrop.utils.common;

import java.net.InetAddress;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DownloadUtil {

	private static final String TAG = "com.newitventure.smartvision.movies.util.DownloadUtil";

	private String link;
	private Context context;
	private String encoding = "utf-8";

	public DownloadUtil(String link, Context context) {
		this.link = link;
		this.context = context;
	}

	public String downloadStringContent() {

		String responseString = "";

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(link);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			responseString = EntityUtils.toString(httpEntity);
			return responseString;
		} catch (Exception e) {
			CustomDialogManager.ReUsedCustomDialogs
					.showDataNotFetchedAlert(context);
			AppConfig.MyLog.MyLogRed(TAG, "Exception: " + e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public boolean isServerReachable(String value)
	// To check if server is reachable
	{
		try {
			InetAddress.getByName(value).isReachable(3000);
			// //Replace with your name
			AppConfig.MyLog.MyLogGreen(TAG, "connection established");
			return true;
		} catch (Exception e) {
			AppConfig.MyLog.MyLogGreen(TAG, "connection lost");
			return false;
		}
	}

	private boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

}

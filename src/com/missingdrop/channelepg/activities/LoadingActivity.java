package com.missingdrop.channelepg.activities;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.missingdrop.channelepg.R;
import com.missingdrop.channelepg.utils.ChannelParser;
import com.missingdrop.channelepg.utils.LinkConfig;
import com.missingdrop.utils.common.AppConfig.MyLog;
import com.missingdrop.utils.common.CustomDialogManager;
import com.missingdrop.utils.common.DownloadUtil;

public class LoadingActivity extends Activity {
	private static final int TIME = 4 * 1000;// 4 seconds

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		new LoadChannels().execute(LinkConfig.ChannelsUrl);

	}

	private class LoadChannels extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			MyLog.MyLogRed("link", params[0]);
			// if (CheckConnectivity.RouterIsConnected(LoadingActivity.this,
			// params[0])) {
			DownloadUtil dUtil = new DownloadUtil(params[0],
					LoadingActivity.this);
			return dUtil.downloadStringContent();

			// } else
			// return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			MyLog.MyLogBlue("json string", result + "");
			try {
				new ChannelParser(result).parse();
				openNewActivity();

			} catch (JSONException e) {
				CustomDialogManager.ReUsedCustomDialogs
						.showDataNotFetchedAlert(LoadingActivity.this);
				e.printStackTrace();
			}
		}

	}

	public void openNewActivity() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(LoadingActivity.this,
						MainActivity.class);
				startActivity(intent);
				LoadingActivity.this.finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		}, TIME);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

			}
		}, TIME);

	}

}
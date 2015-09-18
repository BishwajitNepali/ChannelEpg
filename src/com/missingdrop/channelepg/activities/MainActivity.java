package com.missingdrop.channelepg.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.missingdrop.channelepg.R;
import com.missingdrop.channelepg.adapter.CustomListAdapter;
import com.missingdrop.channelepg.utils.ChannelParser;
import com.missingdrop.channelepg.utils.EPGParser;
import com.missingdrop.channelepg.utils.LinkConfig;
import com.missingdrop.utils.common.AppConfig.MyLog;
import com.missingdrop.utils.common.CustomDialogManager;
import com.missingdrop.utils.common.DownloadUtil;

public class MainActivity extends Activity {
	GridView showGrid, channelList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		final String formattedDate = df.format(c.getTime()).replace("-", "");
		MyLog.MyLogRed("date", formattedDate);
		showGrid = (GridView) findViewById(R.id.grid_epg);
		channelList = (GridView) findViewById(R.id.grid_channels);
		channelList.setAdapter(new CustomListAdapter(MainActivity.this,
				ChannelParser.Channels));
		// new
		// EpgLoader().execute("http://indian-television-guide.appspot.com/indian_television_guide?channel=6tv&date=29062015");
		channelList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> adapter, View view,
							int position, long arg3) {
						new EpgLoader().execute(LinkConfig.ChannelEPG(
								ChannelParser.Channels.get(position),
								formattedDate));

					}
				});

	}

	private class EpgLoader extends AsyncTask<String, Void, String> {
		CustomDialogManager loading;

		@Override
		protected void onPreExecute() {
			loading = new CustomDialogManager(MainActivity.this,
					CustomDialogManager.LOADING);
			loading.build();
			loading.show();
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			DownloadUtil dUtil = new DownloadUtil(params[0], MainActivity.this);
			return dUtil.downloadStringContent();
		}

		@Override
		protected void onPostExecute(String result) {
			if (loading.isShowing())
				loading.dismiss();
			try {
				EPGParser parser = new EPGParser(MainActivity.this, result);
				parser.parse();
				if (EPGParser.shows.size() == 0) {
					CustomDialogManager noDatas = new CustomDialogManager(
							MainActivity.this, CustomDialogManager.ALERT);
					noDatas.build();
					noDatas.setMessage("No Datas found for this Channel");
					CustomDialogManager.ReUsedCustomDialogs
							.addDissmissButtonToDialog(MainActivity.this,
									noDatas);
					noDatas.show();
				} else {
					Intent i = new Intent(MainActivity.this, EPGActivity.class);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				CustomDialogManager noDatas = new CustomDialogManager(
						MainActivity.this, CustomDialogManager.ALERT);
				noDatas.build();
				noDatas.setMessage("Sorry! either you have your device date wrong or the channel you selected doesnot provide an EPG");
				noDatas.show();
				CustomDialogManager.ReUsedCustomDialogs
						.addDissmissButtonToDialog(MainActivity.this, noDatas);
			}
			super.onPostExecute(result);
		}

	}

}

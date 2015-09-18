package com.missingdrop.channelepg.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.missingdrop.utils.common.CustomDialogManager;

public class EPGParser {

	String jsonString;
	Context context;
	public static ArrayList<ShowsInChannel> shows;

	public EPGParser(Context context, String jsonString) {
		this.context = context;
		this.jsonString = jsonString;
		shows = new ArrayList<ShowsInChannel>();

	}

	public void parse() throws JSONException {
		JSONObject jObject = new JSONObject(jsonString);
		Log.d("epg json", jsonString);
		JSONArray listOfShows = jObject.getJSONArray("listOfShows");

		Log.d("total shows", listOfShows.length() + "");

		for (int i = 0; i < listOfShows.length(); i++) {
			JSONObject jobj2 = listOfShows.getJSONObject(i);
			Log.d("name", jobj2.getString("showTitle"));

			ShowsInChannel show = new ShowsInChannel();
			show.setShowName(jobj2.getString("showTitle"));

			show.setShowTime(jobj2.getString("showTime"));
			show.setShowLogoLink(jobj2.getString("showThumb"));
			try {
				show.setShowLanguage(jobj2.getJSONObject("showDetails")
						.getString("Language"));

			} catch (JSONException je) {
				show.setShowLanguage("N/A");
				shows.add(show);
			}

		}
	}

}

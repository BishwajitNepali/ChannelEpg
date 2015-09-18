package com.missingdrop.channelepg.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ChannelParser {
	private String jsonString;
	public static ArrayList<String> Channels;

	public ChannelParser(String jsonString) {
		this.jsonString = jsonString;
		Channels = new ArrayList<String>();
	}

	public void parse() throws JSONException {
		try {
			JSONObject jObject = new JSONObject(jsonString);
			JSONArray result = jObject.getJSONArray("results");
			for (int i = 0; i < result.length(); i++) {
				String channelName = result.getJSONObject(i).getString("value");

				Channels.add(channelName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

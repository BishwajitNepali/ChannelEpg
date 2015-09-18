package com.missingdrop.channelepg.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.missingdrop.channelepg.R;

public class CustomListAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final ArrayList<String> channelList;

	public CustomListAdapter(Activity context, ArrayList<String> channelList) {
		super(context, R.layout.list_item_channel, channelList);
		this.context = context;
		this.channelList = channelList;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.list_item_channel, null, true);
		try {
			TextView Title = (TextView) rowView.findViewById(R.id.channel_name);
			Title.setText(channelList.get(position));
			// Date.setText(listOfVideos.get(position).get("date"));

			return rowView;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}
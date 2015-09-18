package com.missingdrop.channelepg.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.missingdrop.channelepg.R;
import com.missingdrop.channelepg.utils.ShowsInChannel;

public class ShowGridAdapter extends ArrayAdapter<ShowsInChannel> {
	private final Activity context;
	private final ArrayList<ShowsInChannel> showsInChannel;

	public ShowGridAdapter(Activity context,
			ArrayList<ShowsInChannel> showsInChannel) {
		super(context, R.layout.grid_item_epg, showsInChannel);
		this.context = context;
		this.showsInChannel = showsInChannel;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		view = inflater.inflate(R.layout.grid_item_epg, null, true);
		try {
			TextView name = (TextView) view.findViewById(R.id.show_name);
			TextView language = (TextView) view
					.findViewById(R.id.show_language);
			TextView time = (TextView) view.findViewById(R.id.show_time);
			ImageView image = (ImageView) view.findViewById(R.id.show_logo);

			name.setText(showsInChannel.get(position).getShowName());
			language.setText(showsInChannel.get(position).getShowLanguage());
			time.setText(showsInChannel.get(position).getShowTime());
			UrlImageViewHelper.setUrlDrawable(image,
					showsInChannel.get(position).getShowLogoLink());
			Log.e("grid image", showsInChannel.get(position).getShowLogoLink());

			// Date.setText(listOfVideos.get(position).get("date"));

			return view;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
}
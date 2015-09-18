package com.missingdrop.channelepg.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.missingdrop.channelepg.R;
import com.missingdrop.channelepg.adapter.ShowGridAdapter;
import com.missingdrop.channelepg.adapter.ShowGridAdapter2;
import com.missingdrop.channelepg.utils.EPGParser;

public class EPGActivity extends Activity {
	GridView gridEPG;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_epg);
		gridEPG = (GridView)findViewById(R.id.grid_epg);
		Log.d("EPGACTIVITY", EPGParser.shows.size()+"");
		
		ShowGridAdapter adapter = new ShowGridAdapter(EPGActivity.this, EPGParser.shows);
		gridEPG.setAdapter(adapter);
	}

}

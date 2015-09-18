package com.missingdrop.channelepg.utils;

public class LinkConfig {
	public static String ChannelsUrl = "https://api.import.io/store/data/1bd24781-d63e-4c6f-9850-da4999c38211/_query?input%2Fwebpage%2Furl=http%3A%2F%2Findian-television-guide.appspot.com%2F&_user=1f737b29-ee16-4559-9519-770235ddd211&_apikey=1f737b29-ee16-4559-9519-770235ddd211%3Ai92xWkMre0wNgDAR%2BpV%2B2OMRaSLQABsqDutktsJoTjzRq5IWABC3rXYS5gPF6f29Dw2B2DZx%2BN%2FZ2Y7Ej5s9mA%3D%3D";
	public static String ChannelEPG(String ChannelName,String date){
/**
 * Date format : ddmmyyyy
 * with none signs
 */
		//		27062015
		String url = "http://indian-television-guide.appspot.com/indian_television_guide?channel="+ChannelName+"&date="+date;

		return url;
		
	}
}

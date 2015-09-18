package com.missingdrop.utils.common;

import android.util.Log;

public class AppConfig {
	private static final boolean isInDevelopment = true;

	public static boolean isDevelopment() {
		return isInDevelopment;
	}

	public static String getMacAddress() {
		return "acdbda259dcf"; // sadip@nitv.com
		// return "00219c0225ce"; // nettv black box
		// return "00219c0225cb";
		// return "00219c138853";

		// return "acdbdaff02f5";
		// return "acdbda2111";

	}

	public static String getPrefsName() {
		return "NETTV_PREFS";
	}

	public static class MyLog {

		public static void MyLogBlue(String TAG, String value) {
			if (isInDevelopment)
				Log.d(TAG, value);
		}

		public static void MyLogRed(String TAG, String value) {
			if (isInDevelopment)
				Log.e(TAG, value);
		}

		public static void MyLogGreen(String TAG, String value) {
			if (isInDevelopment)
				Log.i(TAG, value);
		}
	}

}

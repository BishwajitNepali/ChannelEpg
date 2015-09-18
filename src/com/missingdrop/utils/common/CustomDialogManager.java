package com.missingdrop.utils.common;

/**
 *Dismiss Button --> extra 
 */

import java.util.ArrayList;

import com.missingdrop.channelepg.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomDialogManager {

	public static final int DEFAULT = 0;
	public static final int LOADING = 1;
	public static final int PROGRESS = 2;
	public static final int ALERT = 3;
	public static final int MESSAGE = 4;
	public static final int WARNING = 5;

	private Context context = null;
	private String version = "", macAddress = "", title = "", message = "";

	private Dialog d;
	private TextView alertTitle, /*MacTextView, versionTextView,*/ messageTextView;
//	private RelativeLayout macAndVersion;
	private LinearLayout buttonLayout;
	private Button positive, neutral, negative, extra;
	private ImageView error_image;
	private ProgressBar progressBar;
	private View viewBelowMac, viewAboveButtons;

	private int type = DEFAULT;

	public CustomDialogManager(Context context, int type) {
		this.context = context;
		this.type = type;
		this.title = context.getString(R.string.app_name);
		this.message = context.getString(R.string.err_unexpected);
	}

	public CustomDialogManager(Context context, String message, int type) {
		this.context = context;
		this.type = type;
		this.message = message;
		this.title = context.getString(R.string.app_name);
	}

	public CustomDialogManager(Context context, String title, String message,
			int type) {
		this.context = context;
		this.type = type;
		this.title = title;
		this.message = message;
	}

	public void build() {
		d = new Dialog(context);
		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
		d.setContentView(R.layout.custom_dialog);
		d.setCancelable(false);

		alertTitle = (TextView) d.findViewById(R.id.dialog_heading);
		alertTitle.setText(title);
//		macAndVersion = (RelativeLayout) d.findViewById(R.id.mac_version);
//		MacTextView = (TextView) d.findViewById(R.id.macaddress_variable);
//		versionTextView = (TextView) d.findViewById(R.id.app_version_variable);

		viewBelowMac = (View) d.findViewById(R.id.view_below_mac);
		viewAboveButtons = (View) d.findViewById(R.id.view_above_button);

		progressBar = (ProgressBar) d.findViewById(R.id.progressBar);
		error_image = (ImageView) d.findViewById(R.id.error_image);
		messageTextView = (TextView) d.findViewById(R.id.message);

		buttonLayout = (LinearLayout) d.findViewById(R.id.button_layout);
		positive = (Button) d.findViewById(R.id.positive);
		neutral = (Button) d.findViewById(R.id.neutral);
		extra = (Button) d.findViewById(R.id.extra);
		negative = (Button) d.findViewById(R.id.negative);

//		macAndVersion.setVisibility(View.GONE);
		progressBar.setVisibility(View.GONE);
		error_image.setVisibility(View.GONE);
		viewAboveButtons.setVisibility(View.GONE);
		viewBelowMac.setVisibility(View.GONE);
		buttonLayout.setVisibility(View.GONE);
		positive.setVisibility(View.GONE);
		neutral.setVisibility(View.GONE);
		extra.setVisibility(View.GONE);
		negative.setVisibility(View.GONE);

		if (type == LOADING) {
			alertTitle.setVisibility(View.GONE);
			error_image.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			viewBelowMac.setVisibility(View.GONE);
			this.message = context.getString(R.string.txt_loading);
			messageTextView.setText(this.message);
		} else if (type == PROGRESS) {
			alertTitle.setVisibility(View.VISIBLE);
//			macAndVersion.setVisibility(View.GONE);
			viewAboveButtons.setVisibility(View.GONE);
			error_image.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			messageTextView.setText(this.message);
		} else if (type == ALERT) {
			alertTitle.setVisibility(View.VISIBLE);
			error_image.setImageResource(R.drawable.error);
			error_image.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			messageTextView.setText(this.message);
		} else if (type == WARNING) {
			alertTitle.setVisibility(View.VISIBLE);
			error_image.setImageResource(R.drawable.warning);
			error_image.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			messageTextView.setText(this.message);
		} else if (type == MESSAGE) {
			alertTitle.setVisibility(View.VISIBLE);
//			macAndVersion.setVisibility(View.GONE);
			error_image.setVisibility(View.GONE);
			progressBar.setVisibility(View.GONE);
			messageTextView.setText(this.message);
		}

	}

	public void dismissDialogOnBackPressed() {
		d.setOnKeyListener(new DialogInterface.OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:
					d.dismiss();
					return true;

				default:
					return false;
				}
			}
		});
	}

	public Button setPositiveButton(String btn_text,
			View.OnClickListener onClickListener) {
		viewAboveButtons.setVisibility(View.VISIBLE);
		buttonLayout.setVisibility(View.VISIBLE);
		positive.setText(btn_text);
		positive.setVisibility(View.VISIBLE);
		positive.setOnClickListener(onClickListener);
		return positive;

	}

	public Button setExtraButton(String btn_text,
			View.OnClickListener onClickListener) {
		viewAboveButtons.setVisibility(View.VISIBLE);
		buttonLayout.setVisibility(View.VISIBLE);
		extra.setText(btn_text);
		extra.setVisibility(View.VISIBLE);
		extra.setOnClickListener(onClickListener);
		return extra;

	}

	public Button setNeutralButton(String btn_text,
			View.OnClickListener onClickListener) {
		viewAboveButtons.setVisibility(View.VISIBLE);
		buttonLayout.setVisibility(View.VISIBLE);
		neutral.setText(btn_text);
		neutral.setVisibility(View.VISIBLE);
		neutral.setOnClickListener(onClickListener);
		return neutral;

	}

	public Button setNegativeButton(String btn_text,
			View.OnClickListener onClickListener) {
		viewAboveButtons.setVisibility(View.VISIBLE);
		buttonLayout.setVisibility(View.VISIBLE);
		negative.setText(btn_text);
		negative.setVisibility(View.VISIBLE);
		negative.setOnClickListener(onClickListener);
		return negative;

	}

	public void setTitle(String title) {
		this.title = title;
		alertTitle.setText(title);
		alertTitle.setVisibility(View.VISIBLE);
	}

	public String getTitle() {
		return alertTitle.getText() + "";
	}

	public void setMessage(String message) {
		this.message = message;
		messageTextView.setText(message);
	}

	/*
	 * public void showMacAndVersion() { if (AppConfig.isDevelopment()) {
	 * macAddress = AppConfig.getMacAddress(); } else { macAddress =
	 * GetMac.getMac(context); } try { PackageInfo pInfo =
	 * context.getPackageManager().getPackageInfo( context.getPackageName(), 0);
	 * version = pInfo.versionName; } catch (NameNotFoundException e) { version
	 * = "N/A"; e.printStackTrace(); } MacTextView.setText(macAddress);
	 * versionTextView.setText(version);
	 * 
	 * macAndVersion.setVisibility(View.VISIBLE);
	 * viewBelowMac.setVisibility(View.VISIBLE); }
	 */

	public void show() {
		d.show();
	}

	public void hide() {
		d.hide();
	}

	public void dismiss() {
		d.dismiss();
	}

	public boolean isShowing() {
		return d.isShowing();
	}

	public Button getNeutralButton() {
		return neutral;
	}

	// end of re used custom Dialog

	public static class ReUsedCustomDialogs {
		// Dialog to show when required data not available
		public static void showDataNotFetchedAlert(final Context context) {
			final CustomDialogManager error = new CustomDialogManager(context,
					CustomDialogManager.ALERT);
			error.build();
			// error.showMacAndVersion();
			error.setMessage(context.getString(R.string.err_json_exception));
			addDissmissButtonToDialog(context, error);
			error.setNegativeButton("Restart", new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					error.dismiss();
					Intent i = ((ContextWrapper) context)
							.getBaseContext()
							.getPackageManager()
							.getLaunchIntentForPackage(
									((ContextWrapper) context).getBaseContext()
											.getPackageName());
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(i);

					((Activity) context).finish();

				}
			});
			error.show();
		}

		public static CustomDialogManager addDissmissButtonToDialog(
				final Context context, final CustomDialogManager customDialog) {
			customDialog.setExtraButton(
					context.getString(R.string.btn_dismiss),
					new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							customDialog.dismiss();
							if (context
									.getClass()
									.getName()
									.equals("com.newitventure.nettv.livetv.EntryPoint"))
								((Activity) context).finish();

						}
					});
			return customDialog;

		}
	}
}

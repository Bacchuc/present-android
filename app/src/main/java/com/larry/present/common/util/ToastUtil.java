package com.larry.present.common.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Context mContext = null;
	private static Toast toast = null;

	public static Toast makeText(Context context, String tip, int duration) {
		if (mContext == context.getApplicationContext()) {
			toast.setText(tip);
		} else {
			mContext = context.getApplicationContext();
			toast = Toast.makeText(mContext, tip, duration);
		}
		return toast;
	}

	public static Toast makeText(Context context, int resId, int duration) {
		String tip = context.getString(resId);
		return makeText(context, tip, duration);
	}

	public static void showShort(Context context, String tip) {
		makeText(context, tip, Toast.LENGTH_SHORT).show();
	}

	public static void showShort(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_SHORT).show();
	}

	public static void showLong(Context context, String tip) {
		makeText(context, tip, Toast.LENGTH_LONG).show();
	}

	public static void showLong(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_LONG).show();
	}

}

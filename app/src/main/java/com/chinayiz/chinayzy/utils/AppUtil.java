package com.chinayiz.chinayzy.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AppUtil {
	private static TelephonyManager tm;

	/**
	 * 获取当前运行的进程名
	 *
	 * @param context
	 * @return
	 */
	public static String getProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}

		return null;
	}

	/**
	 * 获取当前运行的所有进程名
	 *
	 * @param context
	 * @return
	 */
	public static List<String> getProcessName(Context context, String packageName) {
		List<String> list = new ArrayList<String>();
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
			if (appProcess.processName.startsWith(packageName)) {
				// System.out.println("p:"+appProcess.processName);
				list.add(appProcess.processName);
			}
		}
		return list;
	}

	/**
	 * 获取当前运行界面的包名
	 *
	 * @param context
	 * @return
	 */
	public static String getTopPackageName(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = activityManager.getRunningTasks(1).get(0).topActivity;
		return cn.getPackageName();
	}

//	/**
//	 * 生成二维码图片
//	 *
//	 * @param str
//	 * @param widthAndHeight
//	 * @return
//	 * @throws WriterException
//	 */
//
//	public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException {
//		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//		BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
//		int width = matrix.getWidth();
//		int height = matrix.getHeight();
//		int[] pixels = new int[width * height];
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				if (matrix.get(x, y)) {
//					pixels[y * width + x] = BLACK;
//				} else {
//					pixels[y * width + x] = WHITE;
//				}
//			}
//		}
//		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//		return bitmap;
//	}

//	/**
//	 * 实现文本复制功能
//	 * 
//	 * @param content
//	 */
//	public static void copy(Context context, String content) {
//		// 得到剪贴板管理器
//		ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//		cmb.setText(content.trim());
//	}
//
//	/**
//	 * 实现粘贴功能
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static String paste(Context context) {
//		// 得到剪贴板管理器
//		ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//		return cmb.getText().toString().trim();
//	}

	/**
	 * 获取当前应用的versionCode
	 *
	 * @param context
	 * @return
	 */
	public static final int getVersionCode(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 获取当前应用的versionName
	 *
	 * @param context
	 * @return
	 */

	public static final String getVersionName(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取Manifest里面配置的渠道版本
	 * <p>
	 * 2014-11-14
	 * </p>
	 *
	 * @return
	 * @author RANDY.ZHANG
	 */

	public static final String getMetaData(Context context, String name) {
		String metaData = "";
		try {
			metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData
					.getString(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metaData;
	}

	// 判断当前设备是否是模拟器。如果返回TRUE，则当前是模拟器，不是返回FALSE
	public static boolean isEmulator(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			if (imei != null && imei.equals("000000000000000")) {
				return true;
			}
			return (Build.MODEL.equals("sdk")) || (Build.MODEL.equals("google_sdk"));
		} catch (Exception ioe) {

		}
		return false;
	}



	public static String getDeviceId(Context context) {
		String  Imei = null;
		if (tm == null) {
			tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		}
		if (context.getPackageManager().checkPermission(Manifest.permission.READ_PHONE_STATE,
				context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {

			 Imei = tm.getDeviceId();

			Log.e("test", Imei);
		}
		return Imei;
	}



	public static String SimSerialNumber(Context context) {
		if (tm == null) {
			tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		}
		return tm.getSimSerialNumber();
	}

	public static String getAndroidId(Context context) {
		return android.provider.Settings.Secure.getString(context.getApplicationContext().getContentResolver(),
				android.provider.Settings.Secure.ANDROID_ID);
	}

	public static String getUUID(Context context) {
		if (tm == null) {
			tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		}

		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(context.getApplicationContext().getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String deviceId = deviceUuid.toString();
		return deviceId;
	}

	/**
	 * 跳转到应用市场
	 * @param paramContext
	 * @return
	 */

	public static Intent getIntent(Context paramContext) {
		StringBuilder localStringBuilder = new StringBuilder().append("market://details?id=");
		String str = paramContext.getPackageName();
		localStringBuilder.append(str);
		Uri localUri = Uri.parse(localStringBuilder.toString());
		return new Intent("android.intent.action.VIEW", localUri);
	}


	/**
	 * 判断是否有应用市场
	 * @param paramContext
	 * @param paramIntent
	 * @return
	 */
	public static boolean judge(Context paramContext, Intent paramIntent) {
		List localList = paramContext.getPackageManager().queryIntentActivities(paramIntent, PackageManager.GET_INTENT_FILTERS);
		if ((localList != null) && (localList.size() > 0)) {
			return false;
		} else {
			return true;
		}
	}



}

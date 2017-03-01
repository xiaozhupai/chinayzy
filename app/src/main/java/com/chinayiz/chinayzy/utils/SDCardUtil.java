package com.chinayiz.chinayzy.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;



public class SDCardUtil {
	private static SDCardUtil instance;

	private static Context context;

	private static final String[] checkPath = new String[]{"/mnt/sdcard1", "/mnt/sdcard2", "/mnt/sdcard-ext", "/mnt/extSdCard"};

	private static final long K = 1024;
	private static final long M = K * K;

	/**
	 * 手机默认预警临界值
	 */
	private static final long DEF_PHONE_WARNING_LIMIT_SPACE_SIZE = 50 * M;

	/**
	 * 存储卡默认预警临界值
	 */
	private static final long DEF_SDCARD_WARNING_LIMIT_SPACE_SIZE = 2 * DEF_PHONE_WARNING_LIMIT_SPACE_SIZE;

	/**
	 * 本地主目录
	 */
	public static final String HOME_dir ="chinayzy";

	/**
	 * 下载目录
	 */
	public static final String download_dir = "download/";

	/**
	 * 日志目录
	 */
	public static final String log_dir = "log/";

	/**
	 * 临时目录
	 */
	public static final String temp_dir = "temp/";

	/**
	 * 图片存储目录
	 */
	public static final String image_dir = "image/";

	/**
	 * 内置存储大小
	 */
	private static long InternalSDCardSpace = 0;

	/**
	 * 外置存储大小
	 */
	private static long ExternalSDCardSpace = 0;

	/**
	 * 内置SD卡路径
	 */
	private static String InternalSDCardPath = "";

	/**
	 * 外置SD卡路径
	 */
	private static String ExternalSDCardPath = "";

	/**
	 * 主存储SD卡路径
	 */
	private static String SDCardPath = "";

	private static ArrayList<String> ExternalSDCardPath_check = new ArrayList<>();

	private static final int WAIT_TIME = 3000;
	private static long lastTextChangedTime = 0;
	private static Thread thread;

	private SDCardUtil() {
		initSDCard();
	}

	public static SDCardUtil getInstance(Context context) {
		if (instance == null) {
			instance = new SDCardUtil();
		}
		if (SDCardUtil.context == null && context != null) {
			SDCardUtil.context = context;
			// 在IntentFilter中选择你要监听的行为
			IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);// sd卡被插入，且已经挂载
			intentFilter.setPriority(1000);// 设置最高优先级
			intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);// sd卡存在，但还没有挂载
			intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);// sd卡被移除
			intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);// sd卡作为 USB大容量存储被共享，挂载被解除
			intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);// sd卡已经从sd卡插槽拔出，但是挂载点还没解除
			intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);// 开始扫描
			intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);// 扫描完成
			intentFilter.addDataScheme("file");
			SDCardUtil.context.registerReceiver(new SDBroadcastReceiver(SDCardUtil.context), intentFilter);// 注册监听函数
		}
		return instance;
	}

	private static class SDBroadcastReceiver extends BroadcastReceiver {
		@SuppressWarnings("unused")
		private static Context context;

		public SDBroadcastReceiver(Context mContext) {
			context = mContext;
		}

		@Override
		public void onReceive(Context context, Intent intent) {
//			String action = intent.getAction();
//			if (action.equals("android.intent.action.MEDIA_MOUNTED")) {// SD 卡已经成功挂载
////				Toast.makeText(context, "我的卡已经成功挂载", 0).show();
//			} else if (action.equals("android.intent.action.MEDIA_REMOVED")
//					|| action.equals("android.intent.action.ACTION_MEDIA_UNMOUNTED")
//					|| action.equals("android.intent.action.ACTION_MEDIA_BAD_REMOVAL")) {// 各种未挂载状态
////				Toast.makeText(context, "我的各种未挂载状态", 0).show();
//			} else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) {// 开始扫描
////				Toast.makeText(context, "开始扫描...", 0).show();
//			} else if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) {// 扫描完成
////				Toast.makeText(context, "扫描完成...", 0).show();
//			} else if (action.equals(Intent.ACTION_MEDIA_SHARED)) {// 扩展介质的挂载被解除 (unmount)。因为它已经作为 USB 大容量存储被共享
////				Toast.makeText(context, " USB 大容量存储被共享...", 0).show();
//			} else {
//				// Toast.makeText(context, "其他状态...", 0).show();
//			}
//			XImageView.initImageLoader(context);
			// 重新扫描
			toRefresh();
		}

	}

	private static void initSDCard() {
		long start = System.currentTimeMillis();
		// 内置sd卡
		InternalSDCardPath = Environment.getExternalStorageDirectory().getPath();
		// #debug
		System.out.println("InternalSDCardPath=" + InternalSDCardPath);
		// 外置sd卡
		for (String path : checkPath) {
			try {
				StatFs fs = getStatFs(path);
				if (fs != null) {
					ExternalSDCardPath_check.add(path);
					// #debug
					System.out.println("ExternalSDCardPath=" + path);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		InternalSDCardSpace = getInternalSDCardSpace();
		ExternalSDCardSpace = getExternalSDCardSpace();
		SDCardPath = (InternalSDCardSpace > 0 ? InternalSDCardPath : (ExternalSDCardSpace > 0 ? ExternalSDCardPath : null));
		warningLimitSpace();
		// #debug
		System.out.println("InternalSDCardSpace:" + InternalSDCardPath + "(" + InternalSDCardSpace + ") " + " ExternalSDCardSpace=" + ExternalSDCardPath + "(" + ExternalSDCardSpace + ") ");
		// #debug
		System.out.println("用时：" + ((System.currentTimeMillis() - start) / 1000) + "秒  SDCardPath=" + SDCardPath);
	}

	private static void warningLimitSpace() {
		if (SDCardPath != null) {
			if (context != null && (SDCardPath.equals(InternalSDCardPath) ? InternalSDCardSpace : ExternalSDCardSpace) < DEF_SDCARD_WARNING_LIMIT_SPACE_SIZE) {
				Toast.makeText(context, SDCardPath + "存储空间不足，请及时清理！", Toast.LENGTH_LONG).show();
			}
		}
	}

	private static void toRefresh() {
		lastTextChangedTime = System.currentTimeMillis();
		if (thread != null) {
			if (!thread.isAlive() && thread.getState() == Thread.State.TERMINATED) {
				thread = null;
			}
		}
		if (thread == null) {
			thread = new Thread(waitRunnable);
			thread.start();
		}
	}

	private static Runnable waitRunnable = new Runnable() {
		public void run() {
			while ((System.currentTimeMillis() - lastTextChangedTime) < WAIT_TIME) {
				try {
					Thread.sleep(WAIT_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			initSDCard();
		}
	};

	/**
	 * 判断SDCARD是否有效
	 *
	 * @return isSDCARDMounted
	 */
	@SuppressWarnings("unused")
	public static boolean isSDCARDMounted(Context context) {
		if (instance == null) {
			if (context == null) {
				// #debug
				System.out.println("-------SDCardUtil未初始化-------");
			}
			SDCardUtil.getInstance(context);
		}
		return isInternalSDCardExist() || isExternalSDCardExist();
	}

	public static String getMainSDCARD(Context context) {
		if (instance == null) {
			if (context == null) {
				// #debug
				System.out.println("-------SDCardUtil未初始化-------");
			}
//			SDCardUtil.getInstance(context);
		}
		if (TextUtils.isEmpty(SDCardPath)) {
			return  (InternalSDCardSpace > 0 ? InternalSDCardPath : (ExternalSDCardSpace > 0 ? ExternalSDCardPath : null));
		}
		return SDCardPath;
	}

	/**
	 * @param context context
	 * @return File 返回类型
	 */
	public static File getHomePath(Context context) {
		File file = new File(getMainSDCARD(context), HOME_dir);
		if (!file.exists()) {
			return file.mkdirs() ? file : null;
		}
		return file;
	}

	public static File getTempPath(Context context) {
		File file = new File(getHomePath(context), temp_dir);
		if (!file.exists()) {
			return file.mkdirs() ? file : null;
		}
		return file;
	}

	public static File getDownloadPath(Context context) {
		File file = new File(getHomePath(context), download_dir);
		if (!file.exists()) {
			return file.mkdirs() ? file : null;
		}
		return file;
	}

	public static File getLogPath(Context context) {
		File file = new File(getHomePath(context), log_dir);
		if (!file.exists()) {
			return file.mkdirs() ? file : null;
		}
		return file;
	}

	@SuppressWarnings("unused")
	public static File getImagePath(Context context) {
		File file = new File(getTempPath(context), image_dir);
		if (!file.exists()) {
			return file.mkdirs() ? file : null;
		}
		return file;
	}

	/**
	 * 内置存储卡是否存在
	 */
	private static boolean isInternalSDCardExist() {
		boolean bExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		return bExist && (InternalSDCardSpace > 0 ? InternalSDCardSpace : getInternalSDCardSpace()) > 0;
	}

	/**
	 * @return long 返回类型
	 */
	private static long getInternalSDCardSpace() {
		StatFs fs = getStatFs(InternalSDCardPath);
		return getSDCardSpace(fs);
	}

	/**
	 * 外置存储卡是否存在
	 */
	private static boolean isExternalSDCardExist() {
		boolean bExist = ExternalSDCardPath_check != null && ExternalSDCardPath_check.size() > 0;
		if (!bExist)
			return false;
		// 存在,并且空间大于0
		return (ExternalSDCardSpace > 0 ? ExternalSDCardSpace : getExternalSDCardSpace()) > 0;
	}

	/**
	 */
	private static long getExternalSDCardSpace() {
		long size = 0;
		for (String path : ExternalSDCardPath_check) {

			long space = getSDCardSpace(getStatFs(path));
			if (space > size) {
				size = space;
				ExternalSDCardPath = path;
			}
		}
		return size;
	}

	/**
	 * @param path 文件路径
	 * @return 文件路径的StatFs对象
	 */
	private static StatFs getStatFs(String path) {
		try {
			return new StatFs(path);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取存储卡剩余空间
	 */
	private static long getSDCardSpace(StatFs fs) {
		return getResidualSpace(fs);
	}

	/**
	 * 获取目录剩余空间
	 *
	 * @param sf sf
	 */
	@SuppressWarnings("deprecation")
	private static long getResidualSpace(StatFs sf) {
		try {
			long blockSize = sf.getBlockSize();
			long availCount = sf.getAvailableBlocks();
			return availCount * blockSize;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unused")
	public static File mkDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return file.mkdirs() ? file : null;
		}
		return file;
	}
}

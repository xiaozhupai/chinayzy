/**
 * Copyright (C) 2014 Luki(liulongke@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chinayiz.chinayzy.entity;

import android.content.Context;

import com.chinayiz.chinayzy.utils.AppUtil;


public class AppInfo {
	public static String VERSION_NAME;
	public static int VERSION_CODE;
//	public static String CHANNEL;
//	public static String JCHANNEL;
	public static String IMEI;
	public static String UUID;
	public static String PORT;
	public static String ANDROID_ID;


	public static void init(Context context) {
		VERSION_CODE = AppUtil.getVersionCode(context);
		VERSION_NAME = AppUtil.getVersionName(context);
//		CHANNEL = AppUtil.getMetaData(context, "UMENG_CHANNEL");
//		JCHANNEL = AppUtil.getMetaData(context, "JPUSH_CHANNEL");
		PORT = AppUtil.getMetaData(context, "PORT");
		IMEI = AppUtil.getDeviceId(context);
		UUID = AppUtil.getUUID(context);
		ANDROID_ID = AppUtil.getAndroidId(context);

	}
}
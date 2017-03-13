package com.chinayiz.chinayzy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.chinayiz.chinayzy.database.SearchDao;
import com.chinayiz.chinayzy.entity.AppInfo;
import com.chinayiz.chinayzy.utils.GlideCacheUtil;
import com.chinayiz.chinayzy.utils.SDCardUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 11:46
 * Class APP
 */
public class APP extends Application {
    /**
     * 全局用户ID
     */
	public static String sUserid="0";
    public static  APP instance;
    public static GlideCacheUtil cacheUtil;
    private static final ArrayList<Activity> activityLists = new ArrayList<>();

    // 运行sample前需要配置以下字段为有效的值
    public static OSS oss;
    private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static final String accessKeyId = "LTAIUtA7pQz24S4r";
    private static final String accessKeySecret = "uAD9Mkes66zETtRKXy7fSmZLyIJ2s7";
    public static final String testBucket = "yzy-app-img";

    public static APP getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        cacheUtil=GlideCacheUtil.getInstance();
        SDCardUtil.getInstance(this);
        ShareSDK.initSDK(this);
        SearchDao.getInstance(this);
        AppInfo.init(this);
        initData();
        initoss();
        sUserid=getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0)+"";
        Logger.i(sUserid);
    }

    /**
     * 应用启动时初始化加载用户设置偏好
     */
    private void initData() {

    }

    /**
     * OSS配置
     */
    private void initoss(){
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(this, endpoint, credentialProvider, conf);
    }

    /**
     * 添加存储Activity
     *
     * @param activity activity
     */
    public synchronized static void register(Activity activity) {
        for (int i = activityLists.size() - 1; i >= 0; i--) {
            Activity ac = activityLists.get(i);
            if (activity.getClass().getName().equals(ac.getClass().getName())) {
                activityLists.remove(ac);
                if (!ac.isFinishing()) {
                    ac.finish();
                }
                break;
            }
        }
        activityLists.add(activity);
    }

    /**
     * 杀死Activity
     *
     * @param activity activity
     */
    public synchronized static void unRegister(Activity activity) {
        if (activity != null && activityLists != null && activityLists.size() != 0) {
            activityLists.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 根据类名获取所对应Activity
     *
     * @param name name
     * @return activity
     */
    public static Activity getActivityByName(String name) {
        for (int i = activityLists.size() - 1; i >= 0; i--) {
            Activity ac = activityLists.get(i);
            if (ac.isFinishing()) {
                continue;
            }
            if (ac.getClass().getName().contains(name)) {
                return ac;
            }
        }
        return null;
    }

    /**
     * 根据类名获取所对应Activity
     *
     * @return activity
     */
    public static Activity getActivityTop() {
        if (activityLists.isEmpty()) {
            return null;
        }
        return activityLists.get(activityLists.size() - 1);
    }

    /**
     * 杀死所有Activity
     */
    public static void exit() {
        if (activityLists != null && activityLists.size() != 0) {
            for (Activity ac : activityLists) {
                if (!ac.isFinishing()) {
                    ac.finish();
                }
            }
        }
    }


}

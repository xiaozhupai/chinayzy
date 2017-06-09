package com.chinayiz.chinayzy;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.chinayiz.chinayzy.database.SearchDao;
import com.chinayiz.chinayzy.entity.AppInfo;
import com.chinayiz.chinayzy.utils.DES3;
import com.chinayiz.chinayzy.utils.GlideCacheUtil;
import com.chinayiz.chinayzy.utils.SDCardUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.mob.MobSDK;
import com.orhanobut.logger.Logger;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.util.ArrayList;
import java.util.logging.Level;

import cn.sharesdk.framework.ShareSDK;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 11:46
 * Class APP
 */
public class APP extends TinkerApplication {
    /**
     * 全局用户ID
     */
    public static String sUserid = "0";
    public static APP instance;
    public static String phone;
    public static String Version ="";
    public static GlideCacheUtil cacheUtil;
    private static final ArrayList<Activity> activityLists = new ArrayList<>();

    // 运行sample前需要配置以下字段为有效的值
    public static OSS oss;
    private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static final String accessKeyId = "LTAIUtA7pQz24S4r";
    private static final String accessKeySecret = "uAD9Mkes66zETtRKXy7fSmZLyIJ2s7";
    public static final String testBucket = "yzy-app-img";
    public static boolean APP_DBG = false; // 是否是debug模式

    /**
     * 参数1：tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
     参数2：delegateClassName Application代理类 这里填写你自定义的ApplicationLike
     参数3：loaderClassName Tinker的加载器，使用默认即可
     参数4：tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false
     */
    public  APP(){
        super(ShareConstants.TINKER_ENABLE_ALL, "com.chinayiz.chinayzy.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }


    public static void initdebug(Context context) {
        APP_DBG = isApkDebugable(context);
    }

    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @return
     * @author SHANHY
     * @date 2015-8-7
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }

    public static APP getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initdebug(this);
//        CrashReport.initCrashReport(getApplicationContext(), "a3d9e23cd5", false);  //bugly初始化
        MobSDK.init(this);   //Share初始化
        cacheUtil = GlideCacheUtil.getInstance();
        SDCardUtil.getInstance(this);
        ShareSDK.initSDK(this);
        SearchDao.getInstance(this);
        AppInfo.init(this);
        initData();
        initoss();
        sUserid = getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid", 0) + "";
        phone = getSharedPreferences("login", Context.MODE_PRIVATE).getString("phone", "-1");
    }

    /**
     * 应用启动时初始化加载用户设置偏好
     */
    private void initData() {

        HttpParams params = new HttpParams();
        params.put("imei",AppInfo.IMEI);
        Logger.i("设备唯一标识="+AppInfo.IMEI);
        OkGo.init(this);
        try{
            OkGo.getInstance()
                    .debug("OkGo", Level.INFO, true)
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    .setCacheMode(CacheMode.NO_CACHE)
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(2)
                    //可以设置https的证书,以下几种方案根据需要自己设置
                    //方法一：信任所有证书,不安全有风险
                    .setCertificates()
                    .addCommonParams(params);   //设置全局公共参数

        }catch (RuntimeException e){
            Logger.e("Okhttp初始化失败");
            e.printStackTrace();
        }

    }

    /**
     * OSS配置
     */
    private void initoss() {
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

    public static String DES3code(String str) {
        String sign;
        sign = DES3.encode(str);
        sign = ((char) (sign.charAt(sign.length() - 1) - 1)) + sign.substring(1, sign.length() - 1) + ((char) (sign.charAt(0) + 1));
        System.out.println(sign);
        sign = sign.replace("+", "%2B");
        return sign;
    }

}

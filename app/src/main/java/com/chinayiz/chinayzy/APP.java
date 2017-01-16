package com.chinayiz.chinayzy;

import android.app.Application;

import com.chinayiz.chinayzy.entity.AppInfo;

import cn.sharesdk.framework.ShareSDK;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 11:46
 * Class APP
 */
public class APP extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initData();
        ShareSDK.initSDK(this);
        AppInfo.init(this);
    }

    /**
     * 应用启动时初始化加载用户设置偏好
     */
    private void initData() {

    }
}

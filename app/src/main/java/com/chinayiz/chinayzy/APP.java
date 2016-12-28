package com.chinayiz.chinayzy;

import android.app.Application;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 11:46
 * Class APP
 */
public class APP extends Application {
    public static final String VERSION="1.0";
    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    /**
     * 应用启动时初始化加载用户设置偏好
     */
    private void initData() {

    }
}

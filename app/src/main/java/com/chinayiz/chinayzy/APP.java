package com.chinayiz.chinayzy;

import android.app.Application;
import android.content.Context;

import com.chinayiz.chinayzy.database.SearchDao;
import com.chinayiz.chinayzy.entity.AppInfo;

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

    public static APP getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
        SearchDao.getInstance(this);
        AppInfo.init(this);
        initData();
        sUserid=getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0)+"";
    }

    /**
     * 应用启动时初始化加载用户设置偏好
     */
    private void initData() {

    }
}

package com.chinayiz.chinayzy.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/2/16.
 */

public class WindowMagerUntil {
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void backgroundAlpha(float bgAlpha, Context context)
    {
     Activity activity=(Activity)context;

        WindowManager.LayoutParams lp =activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
       activity.getWindow().setAttributes(lp);
    }
}

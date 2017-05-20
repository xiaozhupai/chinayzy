package com.chinayiz.chinayzy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/20.
 */

public class JavaUntil {
    /**
     * 年月日工具
     * @param date 日期
     * @return
     */
    public static String getTime(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}

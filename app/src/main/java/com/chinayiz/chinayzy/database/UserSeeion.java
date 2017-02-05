package com.chinayiz.chinayzy.database;

import android.content.Context;

/**
 * Created by Administrator on 2017/2/5.
 */

public class UserSeeion {
    public static boolean isLogin(Context context){
            return context.getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0)!=0;
    }
}

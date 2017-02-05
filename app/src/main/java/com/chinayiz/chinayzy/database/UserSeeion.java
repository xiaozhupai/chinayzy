package com.chinayiz.chinayzy.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/2/5.
 */

public class UserSeeion {
    public static boolean isLogin(Context context){
            return context.getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0)!=0;
    }

    public static void logout(Context context){
     SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
      SharedPreferences.Editor editor=sp.edit();
         editor.putInt("userid",0);
         editor.commit();
    }
}

package com.chinayiz.chinayzy.database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.ui.activity.LoginActivity;


/**
 * Created by Administrator on 2017/2/5.
 */

public class UserSeeion {
    public static boolean isLogin(Context context){
        int isLogin=context.getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0);
        if (isLogin==0){  //没有登录
            Intent intent=new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return false;
        }
        return true;
    }

    public static void setMember(Context context){
        SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("ismember","1");
        editor.commit();
    }

    public static boolean isMember(Context context){
        String isMember=context.getSharedPreferences("login",Context.MODE_PRIVATE).getString("ismember","");
        if (isMember.equals("0") || TextUtils.isEmpty(isMember)){
            Skip.toDeposit(context);
            return false;
        }else {
            return true;
        }
    }

    public static void logout(Context context){
        SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("userid",0);
        editor.commit();
    }

    public static int getUserid(Context context){
        return context.getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0);
    }

    public static void setPhone(Context context,String phone){
        SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("phone",phone);
        editor.commit();
    }

    public static String getPhone(Context context){
       return context.getSharedPreferences("login", Context.MODE_PRIVATE).getString("phone","");
    }
}

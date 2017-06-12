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
    /**
     * 是否登录
     * @param context
     * @return
     */
    public static boolean isLogin(Context context){
        int isLogin=context.getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0);
        if (isLogin==0){  //没有登录
            Intent intent=new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return false;
        }
        return true;
    }

    /**
     * 设置会员
     * @param context
     */
    public static void setMember(Context context){
        SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("ismember","1");
        editor.commit();
    }

    /**
     * 是否是会员
     * @param context
     * @return
     */
    public static boolean isMember(Context context){
        String isMember=context.getSharedPreferences("login",Context.MODE_PRIVATE).getString("ismember","");
        if (isMember.equals("0") || TextUtils.isEmpty(isMember)){
            Skip.toDeposit(context);
            return false;
        }else {
            return true;
        }
    }

    public static boolean isMember(Context context,String a){
        String isMember=context.getSharedPreferences("login",Context.MODE_PRIVATE).getString("ismember","");
        if (isMember.equals("0") || TextUtils.isEmpty(isMember)){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 登出
     * @param context
     */
    public static void logout(Context context){
        SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("userid",0);
        editor.commit();
    }

    /**
     * 获得userid
     * @param context
     * @return
     */
    public static int getUserid(Context context){
        return context.getSharedPreferences("login", Context.MODE_PRIVATE).getInt("userid",0);
    }

    /**
     * 设置手机号码
     * @param context
     * @param phone
     */
    public static void setPhone(Context context,String phone){
        SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("phone",phone);
        editor.commit();
    }

    /**
     * 获得手机号码
     * @param context
     * @return
     */
    public static String getPhone(Context context){
       return context.getSharedPreferences("login", Context.MODE_PRIVATE).getString("phone","");
    }

    /**
     * 获得认证状态
     * @param context
     * @return
     */
    public static String getSys_auth(Context context){
        return context.getSharedPreferences("login", Context.MODE_PRIVATE).getString("sys_auth","");
    }

    /**
     * 设置认证状态
     * @param context
     * @param sys_auth   认证状态
     */
    public static void setSys_auth(Context context,String sys_auth){
        SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("sys_auth",sys_auth);
        editor.commit();
    }
}

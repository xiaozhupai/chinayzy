package com.chinayiz.chinayzy.net.Login;


import com.chinayiz.chinayzy.entity.AppInfo;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.LoginModel;
import com.chinayiz.chinayzy.entity.response.RegisterModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.entity.response.ThirdModel;
import com.chinayiz.chinayzy.entity.response.WechatAccessModel;
import com.chinayiz.chinayzy.entity.response.WechatInfoModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.StrCallback;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/4.
 */

public class LoginNet {
    private Gson mGson = new Gson();
    private static LoginNet loginNet;

    public static LoginNet getLoginNet(){
        if (loginNet==null){
            loginNet=new LoginNet();
        }
        return loginNet;
    }

    /**
     * 注册
     * @param username 手机号	String	是	11位
     * @param password 密码	String	是
     * @param sendMessage 验证码	String	是	5位
     * @param nickname  昵称	String	是
     * @param idcard   身份证号	String	是
     * @param realname  真实姓名	String	是
     * @param pic   身份证照片	String	是
     * @param sex   性别	String	是	0.男 1.女
     * @param birthday   出生年月	String	是
     * @param usualplace  常驻地	String	是
     * @param ismarriage  婚姻状况	String	是	0未婚1已婚2离异
     * @param height     身高	String	是
     * @param weight     体重	String	是
     * @param educational   学历	String	是	1高中2大专3本科4研究生
     * @param politics    政治面貌	String	是	1团员2党员3群众
     */
    public  void toRegister(String username,String password,String sendMessage,String nickname,String idcard,String realname,String pic,String sex,String birthday,String usualplace,String ismarriage,String height,String weight,String educational,String politics){
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.REGISTER)
                .addParams("imei", AppInfo.IMEI)
                .addParams("userid","0")
                .addParams("phone", username)
                .addParams("yzm",sendMessage)
                .addParams("password",password)
                .addParams("nickname",nickname)
                .addParams("idcard",idcard)
                .addParams("realname",realname)
                .addParams("pic",pic)
                .addParams("sex",sex)
                .addParams("birthday",birthday)
                .addParams("usualplace",usualplace)
                .addParams("ismarriage",ismarriage)
                .addParams("height",height)
                .addParams("weight",weight)
                .addParams("educational",educational)
                .addParams("politics",politics)
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.REGISTER
                                    ,mGson.fromJson(s,RegisterModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 登录
     */

    public void toLogin(String username,String password) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.LOGIN)
                .addParams("phone", username)
                .addParams("password",password)
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.LOGIN
                                    ,mGson.fromJson(s,LoginModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 找回密码
     */

    public void toBackpwd(String username,String newpassword,String sendMessage) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.BACKPWD)
                .addParams("phone", username)
                .addParams("newpwd",newpassword)
                .addParams("yzm",sendMessage)
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.BACKPWD
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 发送验证码
     */
    public void toSendMessage(String  phone) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.SRYCODE)
                .addParams("phone", phone)
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SRYCODE
                                    ,mGson.fromJson(s,StringModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }



    /**第三方登录
     *
     * @param thirdid 第三方登录唯一ID
     * @param logintype 登陆方式
     * @param pic  第三方头像
     * @param nickname 昵称
     * @param sex  性别 0.男 1.女
     */
    public void toThird(String thirdid,String logintype,String pic,String nickname,String sex) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.THIRD)
                .addParams("imei", AppInfo.IMEI)
                .addParams("thirdid",thirdid)
                .addParams("logintype",logintype)
                .addParams("pic",pic)
                .addParams("nickname",nickname)
                .addParams("sex",sex)
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.THIRD
                                    ,mGson.fromJson(s,ThirdModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 微信获得 access_token
     * @param code
     */
    public void togetAccessToken(String code){
        OkHttpUtils
                .get()
                .url(Commons.ACCESS_TOKEN)
                .addParams("appid", "")
                .addParams("secret","")
                .addParams("code",code)
                .addParams("grant_type","grant_type")
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ACCESS_TOKEN
                                    ,mGson.fromJson(s,WechatAccessModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     *   刷新access_token
     * @param refresh_token  刷新token的唯一标识符
     */
    public void toRefreshToken(String refresh_token){
        OkHttpUtils
                .get()
                .url(Commons.REFRESH_TOKEN)
                .addParams("appid", "")
                .addParams("refresh_token",refresh_token)
                .addParams("grant_type","refresh_token")
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.REFRESH_TOKEN
                                    ,mGson.fromJson(s,WechatAccessModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 获得微信用户信息
     * @param access_token
     * @param openid
     */
    public void togetWechatUserInfo(String access_token,String openid){
        OkHttpUtils
                .get()
                .url(Commons.REFRESH_TOKEN)
                .addParams("access_token",access_token)
                .addParams("openid",openid)
                .tag("login")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.REFRESH_TOKEN
                                    ,mGson.fromJson(s,WechatInfoModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }




}

package com.chinayiz.chinayzy.net.Login;


import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.LoginModel;
import com.chinayiz.chinayzy.entity.response.RegisterModel;
import com.chinayiz.chinayzy.entity.response.RongModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.entity.response.ThirdModel;
import com.chinayiz.chinayzy.entity.response.WechatAccessModel;
import com.chinayiz.chinayzy.entity.response.WechatInfoModel;
import com.chinayiz.chinayzy.net.Commons;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

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

//    /**
//     * 注册
//     * @param username 手机号	String	是	11位
//     * @param password 密码	String	是
//     * @param sendMessage 验证码	String	是	5位
//     * @param nickname  昵称	String	是
//     * @param idcard   身份证号	String	是
//     * @param realname  真实姓名	String	是
//     * @param pic   身份证照片	String	是
//     * @param sex   性别	String	是	0.男 1.女
//     * @param birthday   出生年月	String	是
//     * @param usualplace  常驻地	String	是
//     * @param ismarriage  婚姻状况	String	是	0未婚1已婚2离异
//     * @param height     身高	String	是
//     * @param weight     体重	String	是
//     * @param educational   学历	String	是	1高中2大专3本科4研究生
//     * @param politics    政治面貌	String	是	1团员2党员3群众
//     * @param recommendcard    推荐码
//     */
//    public  void toRegister(String username,String password,String sendMessage,String nickname,String idcard,String realname,String pic,String sex,String birthday,String usualplace,String ismarriage,String height,String weight,String educational,String politics,String recommendcard){
//        String time=System.currentTimeMillis()+"";
//        String sing= Md5Untils.getSign(time);
//        post()
//                .url(Commons.API + Commons.REGISTER)
//                .addParams("imei", AppInfo.IMEI)
//                .addParams("userid","0")
//                .addParams("phone", username)
//                .addParams("yzm",sendMessage)
//                .addParams("password",password)
//                .addParams("nickname",nickname)
//                .addParams("idcard",idcard)
//                .addParams("realname",realname)
//                .addParams("pic",pic)
//                .addParams("sex",sex)
//                .addParams("birthday",birthday)
//                .addParams("usualplace",usualplace)
//                .addParams("ismarriage",ismarriage)
//                .addParams("height",height)
//                .addParams("weight",weight)
//                .addParams("educational",educational)
//                .addParams("politics",politics)
//                .addParams("recommendcard",recommendcard)
//                .addParams("sign",sing)
//                .addParams("time",time)
//                .tag("login")
//                .build()
//                .execute(new StrCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int i) {
//                        Logger.e("错误信息："+e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String s, int i) {
//                        try {
//                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
//                                    , Commons.REGISTER
//                                    ,mGson.fromJson(s,RegisterModel.class)));
//                        }catch (Exception e){
//                            onError(null,e,i);
//                        }
//                    }
//                });
//    }



    /**
     *
     * @param phone   phone	手机号	String	是
     * @param yzm    yzm	验证码	String	是
     * @param password  password	密码	String	是
     * @param recommendcard  recommendcard	推荐码	String	否
     */
    public void toRegister(String phone,String yzm,String password,String recommendcard){
        OkGo.post(Commons.API + Commons.REGISTER).
                params("phone",phone)
                .params("yzm",yzm)
                .params("password",password)
                .params("recommendcard",recommendcard)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.REGISTER
                                    ,mGson.fromJson(s,RegisterModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 登录
     */

    public void toLogin(String username,String password) {
        OkGo.post(Commons.API + Commons.LOGIN).
                params("phone",username)
                .params("password",password)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.LOGIN
                                    ,mGson.fromJson(s,LoginModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 获得token
     */

    public void getToken() {
        OkGo.post(Commons.API + Commons.TOKEN)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.TOKEN
                                    ,mGson.fromJson(s,RongModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 找回密码
     */

    public void toBackpwd(String username,String newpassword,String sendMessage) {
        OkGo.post(Commons.API + Commons.BACKPWD)
                .params("phone",username)
                .params("yzm",sendMessage)
                .params("newpwd",newpassword)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.BACKPWD
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 发送验证码
     */
    public void toSendMessage(String  phone) {
        OkGo.post(Commons.API + Commons.SRYCODE)
                .params("phone",phone)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SRYCODE
                                    ,mGson.fromJson(s,StringModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.THIRD)
                .params("thirdid",thirdid)
                .params("logintype",logintype)
                .params("pic",pic)
                .params("nickname",nickname)
                .params("sex",sex)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.THIRD
                                    ,mGson.fromJson(s,ThirdModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 微信获得 access_token
     * @param code
     */
    public void togetAccessToken(String code){
        OkGo.post(Commons.API + Commons.ACCESS_TOKEN)
                .params("code",code)
                .params("appid","")
                .params("secret","")
                .params("grant_type","grant_type")
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ACCESS_TOKEN
                                    ,mGson.fromJson(s,WechatAccessModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     *   刷新access_token
     * @param refresh_token  刷新token的唯一标识符
     */
    public void toRefreshToken(String refresh_token){
        OkGo.post(Commons.API + Commons.REFRESH_TOKEN)
                .params("refresh_token","refresh_token")
                .params("appid","")
                .params("grant_type","refresh_token")
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.REFRESH_TOKEN
                                    ,mGson.fromJson(s,WechatAccessModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.REFRESH_TOKEN)
                .params("access_token",access_token)
                .params("openid",openid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.REFRESH_TOKEN
                                    ,mGson.fromJson(s,WechatInfoModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
}

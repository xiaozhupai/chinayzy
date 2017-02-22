package com.chinayiz.chinayzy.net.Login;


import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.AppInfo;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.LoginModel;
import com.chinayiz.chinayzy.entity.response.NY_RecommentModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.entity.response.ThirdModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.callback.StrCallback;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import org.greenrobot.eventbus.EventBus;
import java.util.Date;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/4.
 */

public class LoginNet {
    private Gson mGson = new Gson();

    /**
     * 注册
     */

    public  void toRegister(String username,String password,String sendMessage) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.REGISTER)
                .addParams("imei", AppInfo.IMEI)
                .addParams("userid", APP.sUserid)
                .addParams("phone", username)
                .addParams("yzm",sendMessage)
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
                                    ,Contants.REGISTER
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
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
                .url(Contants.API + Contants.LOGIN)
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
                                    ,Contants.LOGIN
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
                .url(Contants.API + Contants.BACKPWD)
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
                                    ,Contants.BACKPWD
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
                .url(Contants.API + Contants.SRYCODE)
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
                                    ,Contants.SRYCODE
                                    ,mGson.fromJson(s,StringModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }



    /**第三方登录
     *
     * @param imei 手机唯一标识
     * @param thirdid 第三方登录唯一ID
     * @param logintype 登陆方式
     * @param pic  第三方头像
     * @param nickname 昵称
     * @param sex  性别 0.男 1.女
     */
    public void toThird(String thirdid,String logintype,String pic,String nickname,String sex) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.THIRD)
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
                                    ,Contants.THIRD
                                    ,mGson.fromJson(s,ThirdModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


}

package com.chinayiz.chinayzy.net.User;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.PersonalModel;
import com.chinayiz.chinayzy.entity.response.TagsModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.StrCallback;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/22.
 */

public class UserNet {
    private static UserNet mNet;
    private static Gson mGson = new Gson();


    public static UserNet getNet() {
        if (mNet == null) {
            mNet = new UserNet();
        }
        return mNet;
    }

    /**
     * 个人中心个人信息
     */
    public void getPersonalCenter() {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.GETPERSONALCENTER)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("sign", "")
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GETPERSONALCENTER
                                    ,mGson.fromJson(s,PersonalModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.GETUSERINFO)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("sign", "")
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GETPERSONALCENTER
                                    ,mGson.fromJson(s,PersonalModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 编辑用户信息
     * pic
     nickname
     email
     sex
     height
     weight
     truename
     idcard
     */
    public void getEditerUser(int index,String param) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.EDITUSER)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("sign", "")
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.EDITUSER
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


    /**
     *
     * @param orderid 订单编号
     * @param pic  图片地址
     * @param isanonymity 是否匿名
     * @param descpoInteger  描述分
     * @param commentscontent  评论内容
     */
    public void getCommentOrder(Long orderid,String pic,String isanonymity,double descpoInteger,String commentscontent) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.COMMENTORDER)
                .addParams("orderid", orderid+"")
                .addParams("pic", pic)
                .addParams("isanonymity", isanonymity)
                .addParams("descpoInteger", descpoInteger+"")
                .addParams("commentscontent", commentscontent)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.COMMENTORDER
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


    /**
     *  意见反馈
     * @param theme 反馈主题
     * @param idea  反馈内容
     */
    public void getAddIdea(String theme,String idea) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.ADDIDEA)
                .addParams("theme", theme)
                .addParams("idea", idea)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ADDIDEA
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


    /**
     *  获取个性标签
     */
    public void getTags() {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.GETTAGS)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GETTAGS
                                    ,mGson.fromJson(s,TagsModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     *  添加自定义个性标签
     * @param tag 添加的标签名字
     */
    public void getAddTags(String tag) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.ADDTAGS)
                .tag("ny")
                .addParams("tag",tag)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ADDTAGS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


    /**
     * 修改完成标签
     * @param tag 自定义标签内容，逗号分隔
     * @param tagid  系统标签id集合，逗号分隔
     */
    public void getFinishTags(String tag,String tagid) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.FINISHTAGS)
                .tag("ny")
                .addParams("tag",tag)
                .addParams("tagid",tagid)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.FINISHTAGS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


}

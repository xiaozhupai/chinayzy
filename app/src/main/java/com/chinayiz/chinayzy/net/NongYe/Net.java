package com.chinayiz.chinayzy.net.NongYe;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;
import com.chinayiz.chinayzy.entity.response.NY_FeatureModel;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.chinayiz.chinayzy.entity.response.NY_RecommentModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.callback.StrCallback;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import okhttp3.Call;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 11:59
 * Class Net   生态农业板块请求
 */
public class Net {
    private   Gson mGson = new Gson();

    /**
     * 获取生态农业首页广告轮播data
     */
    public void getBanner() {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.NY_BANNER)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("sign", "")
                .tag("ny")
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
                                    ,Contants.NY_BANNER
                                    ,mGson.fromJson(s,NY_BannerModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 获取生态农业首页推荐版块data
     */
    public void getRecomment() {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.NY_RECOMMENT)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("sign", "")
                .tag("ny")
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
                                    ,Contants.NY_RECOMMENT
                                    ,mGson.fromJson(s,NY_RecommentModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 获取生态农业首页特色购版块data
     */
    public void getFeature() {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.NY_FEATURE)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("sign", "")
                .tag("ny")
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
                                    ,Contants.NY_FEATURE
                                    ,mGson.fromJson(s,NY_FeatureModel.class)));
                        } catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }
    /**
     * 获取生态农业首页爱吃版块data （支持分页）
     */
    public void getEatTheme() {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.NY_EATTHEME)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("sign", "")
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Contants.NY_EATTHEME
                                    ,mGson.fromJson(s,NY_EatThemeModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }
    /**
     * 获取生态农业首页爱吃版块商品data （支持分页）
     *
     * @param page 当前页数
     * @param size 加载页数
     */
    public void getEatItem(String page, String size) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.NY_EATITEM)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", "")
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Contants.NY_EATITEM
                                    ,mGson.fromJson(s,NY_EatItemModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 取消请求
     *
     * @param url 需要取消的URL
     */
    public void cancelGet(String url) {
        RequestCall call = OkHttpUtils.get().url(url).build();
        call.cancel();
    }

}

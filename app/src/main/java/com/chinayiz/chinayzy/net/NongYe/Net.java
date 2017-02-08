package com.chinayiz.chinayzy.net.NongYe;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.AppInfo;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.entity.response.FindTypeModel;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;
import com.chinayiz.chinayzy.entity.response.NY_FeatureModel;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.chinayiz.chinayzy.entity.response.NY_RecommentModel;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.entity.response.SearchLabelModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
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
     * 搜索所有标签
     *
     */
    public void getSearchFarm(String title,String page,String size) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.SEARCHFARM)
                .addParams("userid","5")
                .addParams("searchkey",title)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("type","1")
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
                                    ,Contants.SEARCHFARM
                                    ,mGson.fromJson(s,SearchFarmModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }
    /**
     * 搜索所有标签
     *
     */
    public void getALLTab() {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.GETSEARCHKEY)
                .addParams("userid","5")
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
                                    ,Contants.GETSEARCHKEY
                                    ,mGson.fromJson(s,SearchLabelModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


//
//    /**
//     * 删除历史搜索记录
//     *
//     */
//    public void getRemoveSearch() {
//        OkHttpUtils
//                .post()
//                .url(Contants.API + Contants.DELSEARCHKEY)
//                .addParams("userid","5")
//                .tag("ny")
//                .build()
//                .execute(new StrCallback(){
//                    @Override
//                    public void onError(Call call, Exception e, int i) {
//                        Logger.e("错误信息："+e.toString());
//                    }
//                    @Override
//                    public void onResponse(String s, int i) {
//                        try {
//                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
//                                    ,Contants.DELSEARCHKEY
//                                    ,mGson.fromJson(s,BaseResponseModel.class)));
//                        }catch (Exception e){
//                            onError(null,e,i);
//                        }
//                    }
//                });
//    }


    /**
     * 购物车
     */
    public void getShopCart(){
        OkHttpUtils
                .post()
                .url(Contants.SHOPCART)
                .addParams("userid", APP.sUserid)
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
                                    ,Contants.SHOPCART
                                    ,mGson.fromJson(s,ShopCartModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 发现类型
     *
     */
    public void getFindType() {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.FINDTYPE)
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
                                    ,Contants.FINDTYPE
                                    ,mGson.fromJson(s,FindTypeModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 发现列表
     *
     */
    public void getFindBlogByType(String type) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.FINDBLOGBYTYPE)
                .addParams("type",type)
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
                                    ,Contants.FINDBLOGBYTYPE
                                    ,mGson.fromJson(s,FindListModel.class)));
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

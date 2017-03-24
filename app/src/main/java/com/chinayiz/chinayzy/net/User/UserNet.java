package com.chinayiz.chinayzy.net.User;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AddressListModel;
import com.chinayiz.chinayzy.entity.response.ArticleModel;
import com.chinayiz.chinayzy.entity.response.GoodsCollectModel;
import com.chinayiz.chinayzy.entity.response.MyStepModel;
import com.chinayiz.chinayzy.entity.response.PersonalModel;
import com.chinayiz.chinayzy.entity.response.ShopCollectModel;
import com.chinayiz.chinayzy.entity.response.TagsModel;
import com.chinayiz.chinayzy.entity.response.UserModel;
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
    public static final String  EMAIL="EMAIL";
    public static final String SEX="SEX";
    public static final String HEIGHT="HEIGHT";
    public static final String WEIGHT="WEIGHT";
    public static final String TRUENAME="TRUENAME";
    public static final String IDCARD="IDCARD";
    public static final String TAGS="TAGS";
    public static final String NICKNAME ="NICKNAME";
    public static final String PIC ="PIC";


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
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.GETPERSONALCENTER
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
                                    ,Commons.GETUSERINFO
                                    ,mGson.fromJson(s,UserModel.class)));
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
    public void getEditerUser(final String index, String param) {
        String key = null;

        switch (index){
            case EMAIL:
                key="email";
                break;
            case SEX:
                key="sex";
                break;
            case HEIGHT:
                key="height";
                break;
            case WEIGHT:
                key="weight";
                break;
            case TRUENAME:
                key="truename";
                break;
            case IDCARD:
                key="idcard";
                break;
            case NICKNAME:
                key="nickname";
                break;
            case PIC:
                key="pic";
                break;
        }
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.EDITUSER)
                .addParams("userid", APP.sUserid)
                .addParams(key,param)
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
                                    ,index
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
                .addParams("userid",APP.sUserid)
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
                                    ,Commons.ADDIDEA
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
                .addParams("userid",APP.sUserid)
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
                                    ,Commons.GETTAGS
                                    ,mGson.fromJson(s,TagsModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

//    /**
//     *  添加自定义个性标签
//     * @param tag 添加的标签名字
//     */
//    public void getAddTags(String tag) {
//        OkHttpUtils
//                .post()
//                .url(Commons.API + Commons.ADDTAGS)
//                .tag("ny")
//                .addParams("tag",tag)
//                .build()
//                .execute(new StrCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int i) {
//                        Logger.e("错误信息："+e.toString()+"错误码："+i);
//                    }
//
//                    @Override
//                    public void onResponse(String s, int i) {
//                        try {
//                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
//                                    ,Commons.ADDTAGS
//                                    ,mGson.fromJson(s,BaseResponseModel.class)));
//                        }catch (Exception e){
//                            onError(null,e,i);
//                        }
//                    }
//                });
//    }


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
                .addParams("userid",APP.sUserid)
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
                                    ,Commons.FINISHTAGS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 博文点赞
     * @param bid 博文ID
     * @param isdianzan  是否点赞
     */
    public void getDiZan(String bid,String isdianzan) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.DIZAN)
                .tag("ny")
                .addParams("userid",APP.sUserid)
                .addParams("blogid",bid)
                .addParams("isdianzan",isdianzan)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.DIZAN
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     *  博文收藏
     * @param bid 博文ID
     * @param iscollect  是否收藏
     */
    public void getCollection(String bid,String iscollect) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.CANCELCOLLECT)
                .tag("ny")
                .addParams("userid",APP.sUserid)
                .addParams("blogid",bid)
                .addParams("iscollect",iscollect)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.CANCELCOLLECT
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     *  获得收货地址列表
     */
    public void getshowAddress() {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.SHOWADDRESS)
                .tag("ny")
                .addParams("userid",APP.sUserid)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.SHOWADDRESS
                                    ,mGson.fromJson(s,AddressListModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


    /**
     *编辑收货地址
     * @param addressid 地址id
     * @param consignee  收货人
     * @param phone  手机号  11位
     * @param area  所在地区
     * @param address  地址信息
     * @param lng  经度
     * @param lat  纬度
     */
    public void geteditAddress(String addressid,String consignee,String phone,String area,String address,String lng,String lat) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.EDITADDRESS)
                .tag("ny")
                .addParams("userid",APP.sUserid)
                .addParams("addressid",addressid)
                .addParams("consignee",consignee)
                .addParams("phone",phone)
                .addParams("area",area)
                .addParams("address",address)
                .addParams("lng",lng)
                .addParams("lat",lat)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.EDITADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


    /**
     * 将收货地址设置成默认
     * @param addressid   地址ID
     */
    public void getdefaultAddress(String addressid) {
        OkHttpUtils
                .post()
                .url(Commons.API+Commons.DEFAULTADDRESS)
                .tag("ny")
                .addParams("userid",APP.sUserid)
                .addParams("addressid",addressid)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.DEFAULTADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 删除收货地址
     * @param addressid   地址ID
     */
    public void getdeleteAddress(String addressid) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.DELETEADDRESS)
                .tag("ny")
                .addParams("userid",APP.sUserid)
                .addParams("addressid",addressid)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.DELETEADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 新增收货地址
     * @param consignee  收货人
     * @param phone  手机号  11位
     * @param area  所在地区
     * @param address  地址信息
     * @param lng  经度
     * @param lat  纬度
     */
    public void getaddAddress(String consignee,String phone,String area,String address,String lng,String lat) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.ADDADDRESS)
                .tag("ny")
                .addParams("userid",APP.sUserid)
                .addParams("consignee",consignee)
                .addParams("phone",phone)
                .addParams("area",area)
                .addParams("address",address)
                .addParams("lng",lng)
                .addParams("lat",lat)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.ADDADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 宝贝收藏
     * @param page   当前页
     * @param size    当前页条数
     */
    public void getshowGoodsCollect(String page,String size) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.SHOWGOODSCOLLECT)
                .tag("ny")
                .addParams("userid", APP.sUserid)
                .addParams("page",page)
                .addParams("size",size)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWGOODSCOLLECT
                                    ,mGson.fromJson(s,GoodsCollectModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 店铺收藏
     * @param page   当前页
     * @param size    当前页条数
     */
    public void getshowShopCollect(String page,String size) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.SHOWSHOPCOLLECT)
                .tag("ny")
                .addParams("userid", APP.sUserid)
                .addParams("page",page)
                .addParams("size",size)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWSHOPCOLLECT
                                    ,mGson.fromJson(s,ShopCollectModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 博文收藏
     * @param page   当前页
     * @param size    当前页条数
     */
    public void getshowBlogCollect(String page,String size) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.SHOWBLOGCOLLECT)
                .tag("ny")
                .addParams("userid", APP.sUserid)
                .addParams("page",page)
                .addParams("size",size)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWBLOGCOLLECT
                                    ,mGson.fromJson(s,ArticleModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 足迹列表
     * @param page   当前页
     * @param size    当前页条数
     */
    public void getshowFootMarks(String page,String size) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.SHOWFOOTMARKS)
                .tag("ny")
                .addParams("userid", APP.sUserid)
                .addParams("page",page)
                .addParams("size",size)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWFOOTMARKS
                                    ,mGson.fromJson(s,MyStepModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 删除足迹
     * @param goodsid  商品id
     */

    public void getdeletefootmark(String goodsid) {
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.DELETEFOOTMARK)
                .tag("ny")
                .addParams("userid", APP.sUserid)
                .addParams("goodsid",goodsid)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString()+"错误码："+i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.DELETEFOOTMARK
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }





}

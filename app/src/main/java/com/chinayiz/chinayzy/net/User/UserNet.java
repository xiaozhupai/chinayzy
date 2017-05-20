package com.chinayiz.chinayzy.net.User;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AddressListModel;
import com.chinayiz.chinayzy.entity.response.ArticleModel;
import com.chinayiz.chinayzy.entity.response.GoodsCollectModel;
import com.chinayiz.chinayzy.entity.response.HyqyAndYhxyModel;
import com.chinayiz.chinayzy.entity.response.MyStepModel;
import com.chinayiz.chinayzy.entity.response.PersonalModel;
import com.chinayiz.chinayzy.entity.response.ShopCollectModel;
import com.chinayiz.chinayzy.entity.response.TagsModel;
import com.chinayiz.chinayzy.entity.response.UserModel;
import com.chinayiz.chinayzy.net.Commons;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

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
    public static final String BIRTHDAY="BIRTHDAY";
    public static final String USUALPLACE="USUALPLACE";
    public static final String ISMARRIAGE="ISMARRIAGE";
    public static final String EDUCATIONAL="EDUCATIONAL";
    public static final String POLITICS="POLITICS";



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
        OkGo.post(Commons.API + Commons.GETPERSONALCENTER)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.GETPERSONALCENTER
                                    ,mGson.fromJson(s,PersonalModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 获取用户信息
     */

    public void gethyqyandyhxy() {
        OkGo.post(Commons.API + Commons.GETHYQYANDYHXY)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.GETHYQYANDYHXY
                                    ,mGson.fromJson(s,HyqyAndYhxyModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }


    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        OkGo.post(Commons.API + Commons.GETUSERINFO)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.GETUSERINFO
                                    ,mGson.fromJson(s,UserModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
            case BIRTHDAY:
                key="birthday";
                break;
            case USUALPLACE:
                key="usualplace";
                break;
            case ISMARRIAGE:
                key="ismarriage";
                break;
            case POLITICS:
                key="politics";
                break;
            case EDUCATIONAL:
                key="educational";
                break;
        }
        OkGo.post(Commons.API + Commons.EDITUSER)
                .params(key,param)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,index
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.EDITUSER)
                .params("theme",theme)
                .params("idea",idea)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.ADDIDEA
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }


    /**
     *  获取个性标签
     */
    public void getTags() {
        OkGo.post(Commons.API + Commons.GETTAGS)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.GETTAGS
                                    ,mGson.fromJson(s,TagsModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.FINISHTAGS)
                .params("tag",tag)
                .params("tagid",tagid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.FINISHTAGS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.DIZAN)
                .params("bid",bid)
                .params("isdianzan",isdianzan)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.DIZAN
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.CANCELCOLLECT)
                .params("bid",bid)
                .params("iscollect",iscollect)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.CANCELCOLLECT
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     *  获得收货地址列表
     */
    public void getshowAddress() {
        OkGo.post(Commons.API + Commons.SHOWADDRESS)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.SHOWADDRESS
                                    ,mGson.fromJson(s,AddressListModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
    public void geteditAddress(String addressid,String consignee,String phone,String area,String address,String lng,String lat,String specificaddress) {

        OkGo.post(Commons.API + Commons.EDITADDRESS)
                .params("addressid",addressid)
                .params("consignee",consignee)
                .params("phone",phone)
                .params("area",area)
                .params("address",address)
                .params("lng",lng)
                .params("lat",lat)
                .params("specificaddress",specificaddress)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.EDITADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }


    /**
     * 将收货地址设置成默认
     * @param addressid   地址ID
     */
    public void getdefaultAddress(String addressid) {
        OkGo.post(Commons.API + Commons.DEFAULTADDRESS)
                .params("addressid",addressid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.DEFAULTADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 删除收货地址
     * @param addressid   地址ID
     */
    public void getdeleteAddress(String addressid) {
        OkGo.post(Commons.API + Commons.DELETEADDRESS)
                .params("addressid",addressid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.DELETEADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
    public void getaddAddress(String consignee,String phone,String area,String address,String lng,String lat,String specificaddress) {
        OkGo.post(Commons.API + Commons.ADDADDRESS)
                .params("consignee",consignee)
                .params("phone",phone)
                .params("area",area)
                .params("address",address)
                .params("lng",lng)
                .params("lat",lat)
                .params("specificaddress",specificaddress)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.ADDADDRESS
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.SHOWGOODSCOLLECT)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWGOODSCOLLECT
                                    ,mGson.fromJson(s,GoodsCollectModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.SHOWSHOPCOLLECT)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWSHOPCOLLECT
                                    ,mGson.fromJson(s,ShopCollectModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.SHOWBLOGCOLLECT)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWBLOGCOLLECT
                                    ,mGson.fromJson(s,ArticleModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
        OkGo.post(Commons.API + Commons.SHOWFOOTMARKS)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWFOOTMARKS
                                    ,mGson.fromJson(s,MyStepModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 删除足迹
     * @param goodsid  商品id
     */

    public void getdeletefootmark(String goodsid) {
        OkGo.post(Commons.API + Commons.DELETEFOOTMARK)
                .params("goodsid",goodsid)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.DELETEFOOTMARK
                                    ,mGson.fromJson(s,BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }





}

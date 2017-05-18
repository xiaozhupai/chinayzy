package com.chinayiz.chinayzy.net.NongYe;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.BrandModel;
import com.chinayiz.chinayzy.entity.response.ClassifyCodesModel;
import com.chinayiz.chinayzy.entity.response.ClassifyTypesModel;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.entity.response.FindTypeModel;
import com.chinayiz.chinayzy.entity.response.GoodsSteModel;
import com.chinayiz.chinayzy.entity.response.KeeporZanModel;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;
import com.chinayiz.chinayzy.entity.response.NY_FeatureModel;
import com.chinayiz.chinayzy.entity.response.NY_RecommentModel;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.entity.response.SearchLabelModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.StrCallback;
import com.chinayiz.chinayzy.utils.Md5Untils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 11:59
 * Class Net   生态农业板块请求
 */
public class Net {
    private static Net mNet;
    private static Gson mGson = new Gson();

    private Net() {
    }

    public static Net getNet() {
        if (mNet == null) {
            mNet = new Net();
        }
        return mNet;
    }

    /**
     * 获取广告轮播data
     *
     * @param url 链接
     */
    public void getBanner(final String url) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + url)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , url
                                    , mGson.fromJson(s, NY_BannerModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 获取生态农业首页推荐版块data
     */
    public void getRecomment() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.NY_RECOMMENT)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i("退间数据"+s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.NY_RECOMMENT
                                    , mGson.fromJson(s, NY_RecommentModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 获取生态农业首页特色购版块data
     */
    public void getFeature() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.NY_FEATURE)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.NY_FEATURE
                                    , mGson.fromJson(s, NY_FeatureModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 获取生态农业首页爱吃版块data （支持分页）
     */
    public void getEatTheme() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.NY_EATTHEME)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.NY_EATTHEME
                                    , mGson.fromJson(s, NY_EatThemeModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 获取生态农业首页爱吃版块商品data （支持分页）
     *
     * @param page 当前页数
     * @param size 需要加载的数量
     */
    public void getEatItem(String page, String size) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.NY_EATITEM)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                        EventBus.getDefault().post(new EventMessage(BaseMessage.ERROR_EVENT,"",""));
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.NY_EATITEM
                                    , mGson.fromJson(s, NY_EatItemModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 获取农产品自营茶叶首页的二级分类code
     * @param type  类型代码：  1.有机农业 2.野生农业 3.地区特产 4.食品保健组合 5.期货 6.其他
     */
    public void getTypeCodes(String type) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.TYPE_CODES)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("type",type)
                .addParams("sign", sing)
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
                                    , Commons.TYPE_CODES
                                    ,mGson.fromJson(s,ClassifyTypesModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }
    /**
     * 获取农产品自营茶叶首页的三级级分类code
     */
    public void getClassCodes(String typecode) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.CLASS_CODES)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("typecode",typecode)
                .addParams("sign", sing)
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
                                    , Commons.CLASS_CODES
                                    ,mGson.fromJson(s,ClassifyCodesModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     *  搜索结果
     * @param title 关键字
     * @param page 页标
     * @param size 分页数量
     * @param type 类型 1热卖降序2热卖升序3销量降序4销量升序5价格降序6价格升序
     * @param isself   是否自营 1是 0否
     * @param credit   信用度  1是  0否
     * @param brands   品牌   用逗号隔开
     */
    public void getSearchFarm(String title,String page,String size,String type,String isself,String credit,String brands) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.SEARCHFARM)
                .addParams("userid", APP.sUserid)
                .addParams("searchkey",title)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("type",type)
                .addParams("time",time)
                .addParams("sign",sing)
                .addParams("isself",isself)
                .addParams("credit",credit)
                .addParams("brands",brands)
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SEARCHFARM
                                    ,mGson.fromJson(s,SearchFarmModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }
	 /**
     * 搜索所有标签
     */
    public void getALLTab() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GETSEARCHKEY)
                .addParams("userid", APP.sUserid)
                .addParams("time",time)
                .addParams("sign",sing)
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
                                    , Commons.GETSEARCHKEY
                                    ,mGson.fromJson(s,SearchLabelModel.class)));
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.FINDTYPE)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.FINDTYPE
                                    ,mGson.fromJson(s,FindTypeModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 发现列表
     */
    public void getFindBlogByType(final String type,String page,String size){
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        Logger.i("发现列表  请求地址"+Commons.API + Commons.FINDBLOGBYTYPE);
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.FINDBLOGBYTYPE)
                .addParams("time",time)
                .addParams("type",type)
                .addParams("userid",APP.sUserid)
                .addParams("page",page)
                .addParams("size",size)
                .addParams("sign",sing)
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,type
                                    ,mGson.fromJson(s,FindListModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }


    /**
     * 是否点赞 是否收藏
     * @param bid  博文id
     */
    public void getCollectOrPraise( String bid) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.ISCOLLECTORPRAISE)
                .addParams("bid",bid)
                .addParams("userid",APP.sUserid)
                .addParams("time",time)
                .addParams("sign",sing)
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.ISCOLLECTORPRAISE
                                    ,mGson.fromJson(s,KeeporZanModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 获取品牌
     * @param searchkey  搜索关键字
     */

    public void getbrands( String searchkey,String goodstype) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GETBRANDS)
                .addParams("searchkey",searchkey)
                .addParams("userid",APP.sUserid)
                .addParams("goodstype",goodstype)
                .addParams("time",time)
                .addParams("sign",sing)
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,Commons.GETBRANDS
                                    ,mGson.fromJson(s,BrandModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

    /**
     * 二级分类搜索结果
     * @param page 页标
     * @param size 分页数量
     * @param type 类型 1热卖降序2热卖升序3销量降序4销量升序5价格降序6价格升序
     */
    public void getGoosSet(String page,String size,String type,String itemcode) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GOODS_SET)
                .addParams("userid", APP.sUserid)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("type",type)
                .addParams("itemcode",itemcode)
                .addParams("time",time)
                .addParams("sign",sing)
                .tag("ny")
                .build()
                .execute(new StrCallback(){
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息："+e.toString());
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GOODS_SET
                                    ,mGson.fromJson(s,GoodsSteModel.class)));
                        }catch (Exception e){
                            onError(null,e,i);
                        }
                    }
                });
    }

}

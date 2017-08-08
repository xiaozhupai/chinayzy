package com.chinayiz.chinayzy.net;

import android.text.TextUtils;

import com.chinayiz.chinayzy.adapter.MainHomeRecylAdapter;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ResponseModel;
import com.chinayiz.chinayzy.entity.request.CommentGoodsModel;
import com.chinayiz.chinayzy.entity.response.ActivityDetailModel;
import com.chinayiz.chinayzy.entity.response.ActivityMainModel;
import com.chinayiz.chinayzy.entity.response.ActivityResultModel;
import com.chinayiz.chinayzy.entity.response.AddRedPacketCarModel;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.AppUpdataModel;
import com.chinayiz.chinayzy.entity.response.AwardRecodModel;
import com.chinayiz.chinayzy.entity.response.CommentListModel;
import com.chinayiz.chinayzy.entity.response.CouponModel;
import com.chinayiz.chinayzy.entity.response.DealListModel;
import com.chinayiz.chinayzy.entity.response.DefaultAddressModel;
import com.chinayiz.chinayzy.entity.response.DelRedPacketCarModel;
import com.chinayiz.chinayzy.entity.response.ExpenseCalendarModel;
import com.chinayiz.chinayzy.entity.response.GoodStandardModel;
import com.chinayiz.chinayzy.entity.response.GoodsGroupModel;
import com.chinayiz.chinayzy.entity.response.GoodsShareInfoModel;
import com.chinayiz.chinayzy.entity.response.HomeActivitysModel;
import com.chinayiz.chinayzy.entity.response.HomeGoodsListModel;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.entity.response.HomeMainActivitysModel;
import com.chinayiz.chinayzy.entity.response.HomeMenusModel;
import com.chinayiz.chinayzy.entity.response.HomeNewsModel;
import com.chinayiz.chinayzy.entity.response.HomeThemesModel;
import com.chinayiz.chinayzy.entity.response.ImGoldModel;
import com.chinayiz.chinayzy.entity.response.NewGoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.OrderDetailModel;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.entity.response.PayModel;
import com.chinayiz.chinayzy.entity.response.RecommendCodeModel;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.entity.response.ResultRedModel;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.entity.response.SettleAccountsModel;
import com.chinayiz.chinayzy.entity.response.ShareCrowdModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.entity.response.ShoppingCarCountModel;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeDetailModel;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeModel;
import com.chinayiz.chinayzy.entity.response.ShowRedPacketCarmodel;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.entity.response.UpdateRedPacketCarModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.ui.fragment.mine.ResuestTakeFragment;
import com.chinayiz.chinayzy.utils.StrCallback;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/5 10:33
 * Class ContentRequestUtils 公共请求
 */
public class CommonRequestUtils {
    private static CommonRequestUtils sRequestUtils;
    public static Gson mGson = new Gson();

    private CommonRequestUtils() {
    }

    public static CommonRequestUtils getRequestUtils() {
        if (sRequestUtils == null) {
            sRequestUtils = new CommonRequestUtils();
            return sRequestUtils;
        }
        return sRequestUtils;
    }

    /**
     * 获取商铺首页信息data
     *
     * @param shopID 商品ID
     */
    public void getStoerInfo(String shopID) {
        OkGo.post(Commons.API + Commons.STORE_HOME)
                .params("shopid", shopID)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.STORE_HOME
                                    , mGson.fromJson(s, StoreInfoModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });

    }

    /**
     * 获取APP更新信息
     *
     * @param buildcode 本地版本号
     */
    public void getCanUpdata(String buildcode) {
        OkGo.post(Commons.API + Commons.UPDATA)
                .params("buildcode", buildcode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.UPDATA
                                    , mGson.fromJson(s, AppUpdataModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });


    }

    /**
     * 关注商店
     *
     * @param storeID 店铺ID
     */
    public void doAttentionStore(String storeID) {
        OkGo.post(Commons.API + Commons.ATTENTION_STORE)
                .params("shopid", storeID)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 取消关注商店
     *
     * @param storeID 店铺ID
     */
    public void doUnAttentionStore(String storeID) {
        OkGo.post(Commons.API + Commons.UNATTENTION_STORE)
                .params("shopid", storeID)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.UNATTENTION_STORE
                                    , mGson.fromJson(s, ResponseModel.class)));
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });


    }

    /**
     * 请求店铺首页具体商品类别
     *
     * @param storeID  店铺ID
     * @param typecode 商品类型代码
     */
    public void getGoodsListByPosition(String storeID, String typecode, String page, String size) {
        OkGo.post(Commons.API + Commons.FORTYPEBY_GOODSS)
                .params("shopid",storeID)
                .params("typecode",typecode)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.FORTYPEBY_GOODSS
                                    , mGson.fromJson(s, StoreGoodsListModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 请求商品相关简要信息
     *
     * @param goodsId 商品ID
     */
    public void getGoodsDetail(String goodsId) {
        OkGo.post(Commons.API + Commons.GOODS_DETAIL)
                .params("goodsid",goodsId)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GOODS_DETAIL
                                    , mGson.fromJson(s, NewGoodsDetailModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 请求商品组合（套餐）信息
     *
     * @param goodsId
     */
    public void getGoodsGroup(String goodsId) {
        OkGo.post(Commons.API + Commons.GOODS_GROUP)
                .params("goodsid",goodsId)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GOODS_GROUP
                                    , mGson.fromJson(s, GoodsGroupModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 请求我的积分信息
     */
    public void getImGodl() {
        OkGo.post(Commons.API + Commons.IM_GOLD)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.IM_GOLD
                                    , mGson.fromJson(s, ImGoldModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 请求积分列表
     *
     * @param tradetype 交易类型
     */
    public void getDealList(String tradetype) {
        OkGo.post(Commons.API + Commons.DEAL_LIST)
                .params("tradetype",tradetype)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.DEAL_LIST
                                    , mGson.fromJson(s, DealListModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 获取商品详情相关商品
     *
     * @param itemcode 商品类型标码
     * @param page     分页
     * @param size     数量
     */
    public void getRelatedGoods(String itemcode, String page, String size) {
        OkGo.post(Commons.API + Commons.GOODS_RELATED)
                .params("itemcode",itemcode)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GOODS_RELATED
                                    , mGson.fromJson(s, RelatedGoodsModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 收藏商品
     *
     * @param goodsID 商品ID
     */
    public void doCollectGoods(String goodsID) {
        OkGo.post(Commons.API + Commons.GOODS_COLLECT)
                .params("goodsid",goodsID)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 取消收藏商品
     *
     * @param goodsID 商品ID
     */
    public void doUnCollectGoods(String goodsID) {
        OkGo.post(Commons.API + Commons.GOODS_UNCOLLECT)
                .params("goodsid",goodsID)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GOODS_UNCOLLECT
                                    , mGson.fromJson(s, ResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 获取商品评论列表
     *
     * @param goodsid 商品ID
     * @param page    分页
     * @param size    数量
     */
    public void getCommentList(String goodsid, String page, String size) {
        OkGo.post(Commons.API + Commons.COMMENT_LIST)
                .params("goodsid",goodsid)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.COMMENT_LIST
                                    , mGson.fromJson(s, CommentListModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 购物车
     */
    public void getShopCart() {
        OkGo.post(Commons.API + Commons.SHOPCART)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOPCART
                                    , mGson.fromJson(s, ShopCartModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }
    /**
     * 是否已经完善资料
     */
    public void getUserInfoOk() {
        OkGo.post(Commons.API + Commons.USERINFO_ISOK)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.USERINFO_ISOK
                                    , mGson.fromJson(s, ResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }
    /**
     * 加入购物车
     *
     * @param shopid          店铺id
     * @param goodsstandardid 规格id
     * @param count           数量
     */
    public void getJoinCart(String shopid, String goodsstandardid, String count) {
        OkGo.post(Commons.API + Commons.ADDSHOPPINGCAR)
                .params("shopid",shopid)
                .params("goodsstandardid",goodsstandardid)
                .params("count",count)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ADDSHOPPINGCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 红包专场加入购物车
     *
     * @param userid          用户id
     * @param shopid          店铺id
     * @param goodsstandardid 规格id
     * @param count           数量
     */
    public void addRedPacketCar(String userid, String shopid, String goodsstandardid, String count) {
        OkGo.post(Commons.API + Commons.ADDREDPACKETCAR)
                .params("userid", userid)
                .params("shopid", shopid)
                .params("goodsstandardid", goodsstandardid)
                .params("count", count)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ADDREDPACKETCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 删除购物车商品
     *
     * @param carids 购物车唯一标识符，用逗号隔开
     */
    public void getDelCart(String carids) {
        OkGo.post(Commons.API + Commons.DELSHOPPINGCAR)
                .params("carids",carids)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.DELSHOPPINGCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 编辑购物车完成
     *
     * @param caridandcounts
     */
    public void getUpdateCart(String caridandcounts) {
        OkGo.post(Commons.API + Commons.UPDATESHOPPINGCAR)
                .params("caridandcounts",caridandcounts)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.UPDATESHOPPINGCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }


    /**
     * 获取商品套餐
     *
     * @param goodsid 商品ID
     */
    public void getShopGoodStandard(String goodsid) {
        OkGo.post(Commons.API + Commons.SHOWGOODSSTANDARD)
                .params("goodsid",goodsid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWGOODSSTANDARD
                                    , mGson.fromJson(s, GoodStandardModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 结算购物车选中的订单，预览订单
     *
     * @param carids 购物车id数组
     */
    public void getPreviewOrder(String carids,String couponlogid) {
        OkGo.post(Commons.API + Commons.PREVIEWORDER)
                .params("carids",carids)
                .params("couponlogid",couponlogid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.PREVIEWORDER
                                    , mGson.fromJson(s, ResultModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }


    /**
     *   预览订单
     * @param crowdfid   活动ID
     */

    public void getPreviewCrowdfOrder(String crowdfid) {
        OkGo.post(Commons.API + Commons.PREVIEWCROWDFORDER)
                .params("crowdfid",crowdfid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.PREVIEWCROWDFORDER
                                    , mGson.fromJson(s, ActivityResultModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }


    /**
     *   亿众币支付
     * @param total  总价
     * @param crowdfid   活动ID
     */
    public void getYzbPayOrder(String total,String crowdfid) {
        OkGo.post(Commons.PAY + Commons.YZBPAYORDER)
                .params("total",total)
                .params("crowdfid",crowdfid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.YZBPAYORDER
                                    , mGson.fromJson(s, StringModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }


    /**
     * 支付宝支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getAliPayOrder(String type, String total, String orderbill) {
        OkGo.post(Commons.PAY + Commons.ALIPAYORDER)
                .params("type",type)
                .params("total",total)
                .params("orderbill",orderbill)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ALIPAYORDER
                                    , mGson.fromJson(s, AlipayModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 支付宝支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     */
    public void getAliPay(String type, String total,String crowdfid) {
        OkGo.post(Commons.PAY + Commons.ALIPAYORDER)
                .params("type",type)
                .params("total",total)
                .params("crowdfid",crowdfid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ALIPAYORDER
                                    , mGson.fromJson(s, AlipayModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 支付宝支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getAliPayOrder(String type, String total, String orderbill,String userid) {

        OkGo.post(Commons.PAY + Commons.ALIPAYORDER)
                .params("type",type)
                .params("total",total)
                .params("orderbill",orderbill)
                .params("userid",userid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ALIPAYORDER
                                    , mGson.fromJson(s, AlipayModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 微信支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getWxPayOrder(String type, String total, String orderbill) {

        OkGo.post(Commons.PAY + Commons.WXPAYORDER)
                .params("type",type)
                .params("total",total)
                .params("orderbill",orderbill)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.WXPAYORDER
                                    , mGson.fromJson(s, WxpayModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 微信支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     */
    public void getWxPay(String type, String total, String crowdfid) {

        OkGo.post(Commons.PAY + Commons.WXPAYORDER)
                .params("type",type)
                .params("total",total)
                .params("crowdfid",crowdfid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.WXPAYORDER
                                    , mGson.fromJson(s, WxpayModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 微信支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getWxPayOrder(String type, String total, String orderbill,String userid) {
        OkGo.post(Commons.PAY + Commons.WXPAYORDER)
                .params("type",type)
                .params("total",total)
                .params("orderbill",orderbill)
                .params("userid",userid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.WXPAYORDER
                                    , mGson.fromJson(s, WxpayModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 申请验证码
     *
     * @param phone 电话号码
     */
    public void getVerifyCode(String phone) {
        OkGo.post(Commons.API + Commons.SRYCODE)
                .params("phone",phone)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT
                                    , ResuestTakeFragment.SEND_CODE, mGson.
                                    fromJson(s, ResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 申请积分提现
     *
     * @param phone     手机号码
     * @param sprice    提现金额
     * @param code      验证码
     * @param account   提现账号
     * @param dealthird 提现渠道 ( 1：支付宝 2：微信 3：银联 4：其他 )
     */
    public void requestTake(String phone, String sprice, String code, String account, String dealthird) {
        OkGo.post(Commons.API + Commons.GET_GOLD)
                .params("phone",phone)
                .params("sprice",sprice)
                .params("code",code)
                .params("account",account)
                .params("dealthird",dealthird)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault()
                                    .post(new EventMessage(EventMessage.NET_EVENT
                                            , Commons.GET_GOLD, mGson.fromJson(s, ResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 我的订单
     *
     * @param type 订单类型
     */
    public void getImOrder(String type) {
        OkGo.post(Commons.API + Commons.ORDER_STATE)
                .params("type",type)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ORDER_STATE
                                    , mGson.fromJson(s, OrderListModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 订单详情
     *
     * @param orderId 订单号
     */
    public void getOrderDetail(String orderId) {
        OkGo.post(Commons.API + Commons.ORDER_DETAIL)
                .params("orderid",orderId)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i("订单详情数据="+s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ORDER_DETAIL
                                    , mGson.fromJson(s, OrderDetailModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 删除订单
     *
     * @param orderId 订单号
     */
    public void deleteOrder(String orderId) {
        OkGo.post(Commons.API + Commons.DELETE_ORDER)
                .params("orderid",orderId)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT
                                    , Commons.DELETE_ORDER
                                    , mGson.fromJson(s, ResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 取消订单
     *
     * @param orderId 订单号
     */
    public void cancelOrder(String orderId) {
        OkGo.post(Commons.API + Commons.CANCEL_ORDER)
                .params("orderid",orderId)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT
                                    , Commons.CANCEL_ORDER
                                    , mGson.fromJson(s, ResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     * 确认收货
     *
     * @param orderId 订单号
     */
    public void recognizelOrder(String orderId) {
        OkGo.post(Commons.API + Commons.CONFIRM_ORDER)
                .params("orderid",orderId)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT
                                    , Commons.CONFIRM_ORDER
                                    , mGson.fromJson(s, ResponseModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 快速支付
     * @param orderid 订单号
     * @param total   支付价格
     */
    public void fastPay(String orderid, String total) {
        OkGo.post(Commons.PAY + Commons.FAST_PAY)
                .params("orderid",orderid)
                .params("total",total)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.FAST_PAY
                                    , mGson.fromJson(s, PayModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 评价商品
     *
     * @param model 评论信息
     */
    public void commentGoods(CommentGoodsModel model) {
        if (TextUtils.isEmpty(model.getPic())) {
            OkGo.post(Commons.API + Commons.COMMENT_ORDER)
                    .params("orderid",model.getOrderid())
                    .params("isanonymity", model.getIsanonymity())
                    .params("descpoint",model.getDescpoint())
                    .params("commentscontent",model.getCommentscontent())
                    .params("orderdetailid",model.getOrderdetailid())
                    .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Logger.i(s);
                            try {
                                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                        , Commons.COMMENT_ORDER
                                        , mGson.fromJson(s, ResponseModel.class)));
                            }catch (Exception e){
                                onError(null,response,e);
                            }
                        }
                    });
        }else {
            OkGo.post(Commons.API + Commons.COMMENT_ORDER)
                    .params("orderid",model.getOrderid())
                    .params("isanonymity", model.getIsanonymity())
                    .params("descpoint",model.getDescpoint())
                    .params("commentscontent",model.getCommentscontent())
                    .params("orderdetailid",model.getOrderdetailid())
                    .params("pic",model.getPic())
                    .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Logger.i(s);
                            try {
                                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                        , Commons.COMMENT_ORDER
                                        , mGson.fromJson(s, ResponseModel.class)));
                            }catch (Exception e){
                                onError(null,response,e);
                            }
                        }
                    });
        }
    }
    /**
     * 获取推荐信息
     */
    public void getRecommendInfo() {
        OkGo.post(Commons.API + Commons.RECOMMEND_INFO)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.RECOMMEND_INFO
                                    , mGson.fromJson(s, RecommendCodeModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     *  亿众商城
     * @param page 页标
     * @param size 分页数量
     * @param type 类型 1热卖降序2热卖升序3销量降序4销量升序5价格降序6价格升序
     * @param isself   是否自营 1是 0否
     * @param credit   信用度  1是  0否
     * @param brands   品牌   用逗号隔开
     */
    public void getSearchMallGoods(String page,String size,String type,String isself,String credit,String brands) {
        OkGo.post(Commons.API + Commons.SEARCHMALLGOODS)
                .params("page",page)
                .params("size",size)
                .params("type",type)
                .params("isself",isself)
                .params("credit",credit)
                .params("brands",brands)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SEARCHMALLGOODS
                                    , mGson.fromJson(s, SearchFarmModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }

    /**
     * 请求新首页板块菜单按钮图
     */
    public void getHomeModel() {
        OkGo.post(Commons.API + Commons.HOME_MODEL)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.HOME_MODEL
                                    , mGson.fromJson(s, HomeMenusModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     *  请求新首页广告主题图
     * @param uir  Commons.HOME_THEME1  Commons.HOME_THEME2
     */
    public void getHomeTheme(final String uir) {
        OkGo.post(Commons.API + uir)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , uir
                                    , mGson.fromJson(s, HomeThemesModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }

    /**
     *  请求新首页横向滑动商品集合
     * @param uir  Commons.HOME_LIST1  Commons.HOME_LIST2
     */
    public void getHomeList(final String uir) {
        OkGo.post(Commons.API + uir)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , uir
                                    , mGson.fromJson(s, HomeGoodsListModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });


    }
    /**
     *  请求新首页(为你推荐)
     */
    public void getHomeHotGoods(String page,String size) {
        OkGo.post(Commons.API + Commons.HOME_REXIAO)
                .params("page",page)
                .params("size",size)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.HOME_REXIAO
                                    , mGson.fromJson(s, HomeHotGoodsModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     *  请求商城首页主题商品（1 爱时尚，2 世界硒都，3 奢侈品）
     */
    public void getHomeThemeGoods(final String type) {
        String code="";
        switch (type){
            case MainHomeRecylAdapter.TYPE_LOVEFS:{code="1";break;}
            case MainHomeRecylAdapter.TYPE_XIINFO:{code="2";break;}
            case MainHomeRecylAdapter.TYPE_LUXURY:{code="3";break;}
        }
        OkGo.post(Commons.API + Commons.HOME_THEME_GOODS)
                .params("type",code)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    ,type
                                    , mGson.fromJson(s, HomeHotGoodsModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     *  首页亿众头条
     */
    public void getHomeNews() {
        OkGo.post(Commons.API + Commons.HOME_NEWS)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.HOME_NEWS
                                    , mGson.fromJson(s, HomeNewsModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     *  首页主要活动(新人)
     */
    public void getHomeMainActivitys() {
        OkGo.post(Commons.API + Commons.HONE_ACTIVITYS)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.HONE_ACTIVITYS
                                    , mGson.fromJson(s, HomeMainActivitysModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     *  首页倒计时（众筹活动）商品
     */
    public void getHomeZhongChuo() {
        OkGo.post(Commons.API + Commons.HOME_ZHONGCHOU)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.HOME_ZHONGCHOU
                                    , mGson.fromJson(s, HomeActivitysModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     *  首页中奖商品弹窗
     */
    public void getActivityMain() {
        OkGo.post(Commons.API + Commons.OPENWINNER)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.OPENWINNER
                                    , mGson.fromJson(s, ActivityMainModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     *  获奖详情
     */
    public void getActivityDetail(String crowdfid) {
        OkGo.post(Commons.API + Commons.WINNERDETAIL)
                .params("crowdfid",crowdfid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.WINNERDETAIL
                                    , mGson.fromJson(s, ActivityDetailModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }


    /**
     *   兑换亿众币
     * @param crowdfid   活动ID
     * @param total    兑换金额
     */
    public void getChangeyzb(String crowdfid,String total) {
        OkGo.post(Commons.API + Commons.CHANGEYZB)
                .params("crowdfid",crowdfid)
                .params("total",total)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.CHANGEYZB
                                    , mGson.fromJson(s, StringModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }



    /**
     *   确认收货地址
     * @param crowdfid   活动ID
     * @param addressid    地址ID
     */
    public void getConfirmAddress(String crowdfid,String addressid) {
        OkGo.post(Commons.API + Commons.CONFIRMADDRESS)
                .params("crowdfid",crowdfid)
                .params("addressid",addressid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.CONFIRMADDRESS
                                    , mGson.fromJson(s, StringModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     *   获得默认收货地址
     */
    public void getdefaultaddress() {
        OkGo.post(Commons.API + Commons.GETDEFAULTADDRESS)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GETDEFAULTADDRESS
                                    , mGson.fromJson(s, DefaultAddressModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 获得默认收货地址
     * @param type  1.全部 2.进行中3.已揭晓
     */
    public void getCrowdfrecord(final String type) {
        OkGo.post(Commons.API + Commons.CROWDFRECORD)
                .params("type",type)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , type
                                    , mGson.fromJson(s, AwardRecodModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     *  获取分享众筹活动
     * @param crowdfid  活动ID
     */
    public void getSharecrowdfmessage(String crowdfid, final String id) {
        OkGo.post(Commons.API + Commons.SHARECROWDFMESSAGE)
                .params("crowdfid",crowdfid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , id
                                    , mGson.fromJson(s, ShareCrowdModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     *  确认收货
     * @param crowdfid  活动ID
     */
    public void getConfirmshouhuo(String crowdfid) {
        OkGo.post(Commons.API + Commons.CONFIRMSHOUHUO)
                .params("crowdfid",crowdfid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.CONFIRMSHOUHUO
                                    , mGson.fromJson(s, StringModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }


    /**
     * 优惠券列表
     *
     * @param couponlogids 可用优惠券ids
     */
    public void getCanUseCoupon(String couponlogids) {
        OkGo.post(Commons.API + Commons.CANUSECOUPON)
                .params("couponlogids",couponlogids)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.CANUSECOUPON
                                    , mGson.fromJson(s, CouponModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 获取购物车数量
     */
    public void getShoppingCarCount(){
        OkGo.post(Commons.API+Commons.SHOPPINGCARTCOUNT)
                .execute(new StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOPPINGCARTCOUNT
                                    , mGson.fromJson(s, ShoppingCarCountModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }

    /**
     * 获取分享商品信息
     */
    public void getGoodsShareInfo(String goodsid) {
        OkGo.post(Commons.API+ Commons.SHARE_GOODS_INFO)
                .params("goodsid",goodsid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHARE_GOODS_INFO
                                    , mGson.fromJson(s, GoodsShareInfoModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场自营商品分类
     */
    public void getShowClassifyCode() {
        OkGo.post(Commons.API + Commons.SHOWCLASSIFYCODE)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWCLASSIFYCODE
                                    , mGson.fromJson(s, ShowClassifyCodeModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场分类查询自营产品
     */
    public void showClassifyCodeDetail(int page, int size, String itemcode) {
        OkGo.post(Commons.API + Commons.SHOWCLASSIFYCODEDETAIL)
                .params("page", page)
                .params("size", size)
                .params("itemcode", itemcode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWCLASSIFYCODEDETAIL
                                    , mGson.fromJson(s, ShowClassifyCodeDetailModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场商品加入购物车
     */
    public void addRedPacketCar(Long userid, long shopid, long goodsstandardid, int count) {
        OkGo.post(Commons.API + Commons.ADDREDPACKETCAR)
                .params("userid", userid)
                .params("shopid", shopid)
                .params("goodsstandardid", goodsstandardid)
                .params("count", count)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ADDREDPACKETCAR
                                    , mGson.fromJson(s, AddRedPacketCarModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场购物车列表
     */
    public void showRedPacketCar(String userid) {
        OkGo.post(Commons.API + Commons.REDPACKETCAR)
                .params("userid", userid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.REDPACKETCAR
                                    , mGson.fromJson(s, ShopCartModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 删除红包专场购物车商品
     */
    public void delRedPacketCar(String carids) {
        OkGo.post(Commons.API + Commons.DELREDPACKETCAR)
                .params("carids", carids)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.DELREDPACKETCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 编辑红包专场购物车点击列表完成
     */
    public void updateRedPacketCar(String caridandcounts) {
        OkGo.post(Commons.API + Commons.UPDATEREDPACKETCAR)
                .params("caridandcounts", caridandcounts)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.UPDATEREDPACKETCAR
                                    , mGson.fromJson(s, UpdateRedPacketCarModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场消费记录
     */
    public void expenseCalendar(String userid) {
        OkGo.post(Commons.API + Commons.EXPENSECALENDAR)
                .params("userid", userid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.EXPENSECALENDAR
                                    , mGson.fromJson(s, ExpenseCalendarModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场订单结算
     */
    public void settleAccounts(String carids, String userid) {
        OkGo.post(Commons.API + Commons.SETTLEACCOUNTS)
                .params("carids", carids)
                .params("userid", userid)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SETTLEACCOUNTS
                                    , mGson.fromJson(s, ResultModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场支付宝支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getRedAliPayOrder(String type, String total, String orderbill) {
        OkGo.post(Commons.PAY + Commons.ALIPAYVIPORDER)
                .params("type", type)
                .params("total", total)
                .params("orderbill", orderbill)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ALIPAYVIPORDER
                                    , mGson.fromJson(s, AlipayModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    /**
     * 红包专场微信支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getRedWxPayOrder(String type, String total, String orderbill) {

        OkGo.post(Commons.PAY + Commons.WXPAYVIPORDER)
                .params("type", type)
                .params("total", total)
                .params("orderbill", orderbill)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.WXPAYVIPORDER
                                    , mGson.fromJson(s, WxpayModel.class)));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }
}
























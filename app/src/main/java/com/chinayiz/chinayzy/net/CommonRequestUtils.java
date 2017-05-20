package com.chinayiz.chinayzy.net;

import android.text.TextUtils;

import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ResponseModel;
import com.chinayiz.chinayzy.entity.request.CommentGoodsModel;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.AppUpdataModel;
import com.chinayiz.chinayzy.entity.response.CommentListModel;
import com.chinayiz.chinayzy.entity.response.DealListModel;
import com.chinayiz.chinayzy.entity.response.GoodStandardModel;
import com.chinayiz.chinayzy.entity.response.GoodsGroupModel;
import com.chinayiz.chinayzy.entity.response.HomeGoodsListModel;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.entity.response.HomeMenusModel;
import com.chinayiz.chinayzy.entity.response.HomeThemesModel;
import com.chinayiz.chinayzy.entity.response.ImGoldModel;
import com.chinayiz.chinayzy.entity.response.NewGoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.OrderDetailModel;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.entity.response.PayModel;
import com.chinayiz.chinayzy.entity.response.RecommendCodeModel;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.ui.fragment.mine.ResuestTakeFragment;
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
                .params("shopid",shopID)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.STORE_HOME
                                    , mGson.fromJson(s, StoreInfoModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });

    }
    /**
     * 获取APP更新信息
     * @param buildcode 本地版本号
     */
    public void getCanUpdata(String buildcode) {
        OkGo.post(Commons.API + Commons.UPDATA)
                .params("buildcode",buildcode)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.UPDATA
                                    , mGson.fromJson(s, AppUpdataModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
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
                .params("shopid",storeID)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                        }catch (Exception e){
                            onError(null,response,e);
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
                .params("shopid",storeID)

                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.UNATTENTION_STORE
                                    , mGson.fromJson(s, ResponseModel.class)));
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                        }catch (Exception e){
                            onError(null,response,e);
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
                        Logger.i(s);
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
    public void getPreviewOrder(String carids) {
        OkGo.post(Commons.API + Commons.PREVIEWORDER)
                .params("carids",carids)
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
     * 支付宝支付
     *
     * @param type      类型 1充值2购物
     * @param total     支付总价	如果是购物的话，支付总价=商品总价+运费-抵扣积分
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getAliPayOrder(String type, String total, String orderbill) {
        OkGo.post(Commons.API + Commons.ALIPAYORDER)
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
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getAliPayOrder(String type, String total, String orderbill,String userid) {

        OkGo.post(Commons.API + Commons.ALIPAYORDER)
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

        OkGo.post(Commons.API + Commons.WXPAYORDER)
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
     * @param orderbill 订单内容json		json格式，购物的时候传入
     */
    public void getWxPayOrder(String type, String total, String orderbill,String userid) {
        OkGo.post(Commons.API + Commons.WXPAYORDER)
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
                        Logger.i(s);
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
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
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
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
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
        OkGo.post(Commons.API + Commons.FAST_PAY)
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
        OkGo.post(Commons.API + Commons.RECOMMEND_INFO)
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
                                    , Commons.RECOMMEND_INFO
                                    , mGson.fromJson(s, RecommendCodeModel.class)));
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
                        Logger.i(s);
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
                        Logger.i(s);
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
                        Logger.i(s);
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
     *  请求新首页热销商品
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
}

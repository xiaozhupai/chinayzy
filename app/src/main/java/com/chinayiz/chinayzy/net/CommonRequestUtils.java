package com.chinayiz.chinayzy.net;

import android.text.TextUtils;

import com.chinayiz.chinayzy.APP;
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
import com.chinayiz.chinayzy.net.callback.StrCallback;
import com.chinayiz.chinayzy.ui.fragment.mine.ResuestTakeFragment;
import com.chinayiz.chinayzy.utils.Md5Untils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/5 10:33
 * Class ContentRequestUtils 公共请求
 */
public class CommonRequestUtils {
    private static CommonRequestUtils sRequestUtils;
    private static Gson mGson = new Gson();

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
        String time=System.currentTimeMillis()+"";
        String sing= Md5Untils.getSign(time);
        OkHttpUtils
                .post()
                .url(Commons.API + Commons.STORE_HOME)
                .addParams("time",time )
                .addParams("userid", APP.sUserid)
                .addParams("shopid", shopID)
                .addParams("sign", sing)
                .tag(Commons.STORE_HOME)
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
                                    , Commons.STORE_HOME
                                    , mGson.fromJson(s, StoreInfoModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }
    /**
     * 获取APP更新信息
     * @param buildcode 本地版本号
     */
    public void getCanUpdata(String buildcode) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.UPDATA)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("buildcode", buildcode)
                .addParams("sign", sing)
                .tag(Commons.UPDATA)
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
                                    , Commons.UPDATA
                                    , mGson.fromJson(s, AppUpdataModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.ATTENTION_STORE)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("shopid", storeID)
                .addParams("sign", sing)
                .tag("content")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.UNATTENTION_STORE)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("shopid", storeID)
                .addParams("sign", sing)
                .tag("content")
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
                                    , Commons.UNATTENTION_STORE
                                    , mGson.fromJson(s, ResponseModel.class)));
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.FORTYPEBY_GOODSS)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("shopid", storeID)
                .addParams("typecode", typecode)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", sing)
                .tag("content")
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
                                    , Commons.FORTYPEBY_GOODSS
                                    , mGson.fromJson(s, StoreGoodsListModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GOODS_DETAIL)
                .addParams("time",time)
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsId)
                .addParams("sign",sing)
                .tag("content")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                        EventBus.getDefault().post(new EventMessage(EventMessage.ERROR_EVENT
                                , Commons.GOODS_DETAIL
                                , null));
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i("商品详情="+s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GOODS_DETAIL
                                    , mGson.fromJson(s, NewGoodsDetailModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GOODS_GROUP)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsId)
                .addParams("sign", sing)
                .tag("content")
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
                                    , Commons.GOODS_GROUP
                                    , mGson.fromJson(s, GoodsGroupModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 请求我的积分信息
     */
    public void getImGodl() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.IM_GOLD)
                .addParams("time",time)
                .addParams("userid",APP.sUserid)
                .addParams("sign", sing)
                .tag("content")
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
                                    , Commons.IM_GOLD
                                    , mGson.fromJson(s, ImGoldModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.DEAL_LIST)
                .addParams("time", time)
                .addParams("userid",APP.sUserid)
                .addParams("tradetype", tradetype)
                .addParams("sign", sing)
                .tag("content")
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
                                    , Commons.DEAL_LIST
                                    , mGson.fromJson(s, DealListModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GOODS_RELATED)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("itemcode", itemcode)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", sing)
                .tag(Commons.GOODS_RELATED)
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
                                    , Commons.GOODS_RELATED
                                    , mGson.fromJson(s, RelatedGoodsModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GOODS_COLLECT)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsID)
                .addParams("sign", sing)
                .tag("content")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i("收藏信息="+s);
                        try {
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GOODS_UNCOLLECT)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsID)
                .addParams("sign", sing)
                .tag("content")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i("收藏信息="+s);
                        try {
                            Logger.i(mGson.fromJson(s, ResponseModel.class).toString());
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.GOODS_UNCOLLECT
                                    , mGson.fromJson(s, ResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.COMMENT_LIST)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsid)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", sing)
                .tag(Commons.COMMENT_LIST)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i("评论列表"+s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.COMMENT_LIST
                                    , mGson.fromJson(s, CommentListModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 购物车
     */
    public void getShopCart() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.SHOPCART)
                .addParams("userid", APP.sUserid)
                .addParams("time", time)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOPCART
                                    , mGson.fromJson(s, ShopCartModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }
    /**
     * 是否已经完善资料
     */
    public void getUserInfoOk() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.USERINFO_ISOK)
                .addParams("userid", APP.sUserid)
                .addParams("time", time)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.USERINFO_ISOK
                                    , mGson.fromJson(s, ResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.ADDSHOPPINGCAR)
                .addParams("userid", APP.sUserid)
                .addParams("time", time)
                .addParams("shopid", shopid)
                .addParams("goodsstandardid", goodsstandardid)
                .addParams("count", count)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            Logger.i(s);
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ADDSHOPPINGCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.DELSHOPPINGCAR)
                .addParams("userid", APP.sUserid)
                .addParams("carids", carids)
                .addParams("time", time)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.DELSHOPPINGCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.UPDATESHOPPINGCAR)
                .addParams("userid", APP.sUserid)
                .addParams("time", time)
                .addParams("sign", sing)
                .addParams("caridandcounts", caridandcounts)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.UPDATESHOPPINGCAR
                                    , mGson.fromJson(s, BaseResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.SHOWGOODSSTANDARD)
                .addParams("goodsid", goodsid)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.SHOWGOODSSTANDARD
                                    , mGson.fromJson(s, GoodStandardModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.PREVIEWORDER)
                .addParams("carids", carids)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.PREVIEWORDER
                                    , mGson.fromJson(s, ResultModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.PAY + Commons.ALIPAYORDER)
                .addParams("type", type)
                .addParams("userid", APP.sUserid)
                .addParams("total", total)
                .addParams("time", time)
                .addParams("orderbill", orderbill)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ALIPAYORDER
                                    , mGson.fromJson(s, AlipayModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.PAY + Commons.WXPAYORDER)
                .addParams("type", type)
                .addParams("userid", APP.sUserid)
                .addParams("total", total)
                .addParams("time", time)
                .addParams("orderbill", orderbill)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.WXPAYORDER
                                    , mGson.fromJson(s, WxpayModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.SRYCODE)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("phone", phone)
                .addParams("sign", sing)
                .tag("content")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT
                                    , ResuestTakeFragment.SEND_CODE, mGson.
                                    fromJson(s, ResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.GET_GOLD)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("phone", phone)
                .addParams("sprice", sprice)
                .addParams("code", code)
                .addParams("tx_account", account)
                .addParams("dealthird", dealthird)
                .addParams("sign", sing)
                .tag("content")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault()
                                    .post(new EventMessage(EventMessage.NET_EVENT
                                            , Commons.GET_GOLD, mGson.fromJson(s, ResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.ORDER_STATE)
                .addParams("userid", APP.sUserid)
                .addParams("time", time)
                .addParams("type", type)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ORDER_STATE
                                    , mGson.fromJson(s, OrderListModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.ORDER_DETAIL)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("orderid", orderId)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.ORDER_DETAIL
                                    , mGson.fromJson(s, OrderDetailModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.DELETE_ORDER)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("orderid", orderId)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.DELETE_ORDER
                                    , mGson.fromJson(s, ResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.CANCEL_ORDER)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("orderid", orderId)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.CANCEL_ORDER
                                    , mGson.fromJson(s, ResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.CONFIRM_ORDER)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("orderid", orderId)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT
                                    , Commons.CONFIRM_ORDER
                                    , mGson.fromJson(s, ResponseModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 快速支付
     *
     * @param orderid 订单号
     * @param total   支付价格
     */
    public void fastPay(String orderid, String total) {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.FAST_PAY)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("orderid", orderid)
                .addParams("total", total)
                .addParams("sign", sing)
                .tag("ny")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.FAST_PAY
                                    , mGson.fromJson(s, PayModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
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
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        if (TextUtils.isEmpty(model.getPic())) {
            post()
                    .url(Commons.API + Commons.COMMENT_ORDER)
                    .addParams("time", time)
                    .addParams("userid", APP.sUserid)
                    .addParams("orderid", model.getOrderid())
                    .addParams("isanonymity", model.getIsanonymity())
                    .addParams("descpoint", model.getDescpoint())
                    .addParams("commentscontent", model.getCommentscontent())
                    .addParams("orderdetailid", model.getOrderdetailid())
                    .addParams("sign", sing)
                    .tag("ny")
                    .build()
                    .execute(new StrCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {
                            Logger.i("报错信息Code=" + i);
                            Logger.e("错误信息：" + e.toString());
                        }

                        @Override
                        public void onResponse(String s, int i) {
                            try {
                                Logger.i("评论订单" + s);
                                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                        , Commons.COMMENT_ORDER
                                        , mGson.fromJson(s, ResponseModel.class)));
                            } catch (Exception e) {
                                onError(null, e, i);
                            }
                        }
                    });
        }else {
            post()
                    .url(Commons.API + Commons.COMMENT_ORDER)
                    .addParams("time", time)
                    .addParams("userid", APP.sUserid)
                    .addParams("orderid", model.getOrderid())
                    .addParams("isanonymity", model.getIsanonymity())
                    .addParams("descpoint", model.getDescpoint())
                    .addParams("commentscontent", model.getCommentscontent())
                    .addParams("orderdetailid", model.getOrderdetailid())
                    .addParams("pic", model.getPic())
                    .addParams("sign", sing)
                    .tag("ny")
                    .build()
                    .execute(new StrCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {
                            Logger.i("报错信息Code=" + i);
                            Logger.e("错误信息：" + e.toString());
                        }
                        @Override
                        public void onResponse(String s, int i) {
                            try {
                                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                        , Commons.COMMENT_ORDER
                                        , mGson.fromJson(s, ResponseModel.class)));
                            } catch (Exception e) {
                                onError(null, e, i);
                            }
                        }
                    });
        }

    }
    /**
     * 获取推荐信息
     */
    public void getRecommendInfo() {
        String time=System.currentTimeMillis()+"";
        String sing=Md5Untils.getSign(time);
        post()
                .url(Commons.API + Commons.RECOMMEND_INFO)
                .addParams("time", time)
                .addParams("userid", APP.sUserid)
                .addParams("sign", sing)
                .tag(Commons.RECOMMEND_INFO)
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i("推荐信息="+s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.RECOMMEND_INFO
                                    , mGson.fromJson(s, RecommendCodeModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }
}

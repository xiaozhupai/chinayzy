package com.chinayiz.chinayzy.net;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ResponseModel;
import com.chinayiz.chinayzy.entity.response.CommentListModel;
import com.chinayiz.chinayzy.entity.response.GoodStandardModel;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.GoodsGroupModel;
import com.chinayiz.chinayzy.entity.response.GoodsPicDetailModel;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;
import com.chinayiz.chinayzy.net.callback.StrCallback;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import okhttp3.Call;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/5 10:33
 * Class ContentRequestUtils 公共请求
 */
public class ContentRequestUtils {
    private static ContentRequestUtils sRequestUtils;
    private static Gson mGson = new Gson();

    private ContentRequestUtils() {
    }

    public static ContentRequestUtils getRequestUtils() {
        if (sRequestUtils == null) {
            sRequestUtils = new ContentRequestUtils();
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
        Logger.i("执行请求");
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.STORE_HOME)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("shopid", shopID)
                .addParams("sign", "")
                .tag(Contants.STORE_HOME)
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
                                    , Contants.STORE_HOME
                                    , mGson.fromJson(s, StoreInfoModel.class)));
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.ATTENTION_STORE)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("shopid", storeID)
                .addParams("sign", "")
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.UNATTENTION_STORE)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("shopid", storeID)
                .addParams("sign", "")
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
     * 请求店铺首页具体商品类别
     *
     * @param storeID  店铺ID
     * @param typecode 商品类型代码
     */
    public void getGoodsListByPosition(String storeID, String typecode,String page,String size) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.FORTYPEBY_GOODSS)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("shopid", storeID)
                .addParams("typecode", typecode)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", "")
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
                                    , Contants.FORTYPEBY_GOODSS
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.GOODS_DETAIL)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsId)
                .addParams("sign", "")
                .tag("content")
                .build()
                .execute(new StrCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("错误信息：" + e.toString() + "错误码：" + i);
                        EventBus.getDefault().post(new EventMessage(EventMessage.REQUEST_ERROR
                                , Contants.GOODS_DETAIL
                                , null));
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Contants.GOODS_DETAIL
                                    , mGson.fromJson(s, GoodsDetailModel.class)));
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.GOODS_GROUP)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsId)
                .addParams("sign", "")
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
                                    , Contants.GOODS_GROUP
                                    , mGson.fromJson(s, GoodsGroupModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }

    /**
     * 请求商品图文详情信息（HTML5）
     *
     * @param goodsId
     */
    public void getGoodsPicDetail(String goodsId) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.GOODS_PICDETAIL)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsId)
                .addParams("sign", "")
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
                                    , Contants.GOODS_PICDETAIL
                                    , mGson.fromJson(s, GoodsPicDetailModel.class)));
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.GOODS_RELATED)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("itemcode", itemcode)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", "")
                .tag(Contants.GOODS_RELATED)
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
                                    , Contants.GOODS_RELATED
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.GOODS_COLLECT)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsID)
                .addParams("sign", "")
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
     * 取消收藏商品
     *
     * @param goodsID 商品ID
     */
    public void doUnCollectGoods(String goodsID) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.GOODS_UNCOLLECT)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsID)
                .addParams("sign", "")
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
     * 获取商品评论列表
     *
     * @param goodsid 商品ID
     * @param page    分页
     * @param size    数量
     */
    public void getCommentList(String goodsid, String page, String size) {
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.COMMENT_LIST)
                .addParams("time", new Date().toString())
                .addParams("userid", APP.sUserid)
                .addParams("goodsid", goodsid)
                .addParams("page", page)
                .addParams("size", size)
                .addParams("sign", "")
                .tag(Contants.COMMENT_LIST)
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
                                    , Contants.COMMENT_LIST
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.SHOPCART)
                .addParams("userid", APP.sUserid)
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
                                    , Contants.SHOPCART
                                    , mGson.fromJson(s, ShopCartModel.class)));
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.ADDSHOPPINGCAR)
                .addParams("userid", APP.sUserid)
                .addParams("shopid", shopid)
                .addParams("goodsstandardid", goodsstandardid)
                .addParams("count", "1")
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
                                    , Contants.ADDSHOPPINGCAR
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.DELSHOPPINGCAR)
                .addParams("userid", APP.sUserid)
                .addParams("carids", carids)
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
                                    , Contants.DELSHOPPINGCAR
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.UPDATESHOPPINGCAR)
                .addParams("userid", APP.sUserid)
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
                                    , Contants.UPDATESHOPPINGCAR
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
        OkHttpUtils
                .post()
                .url(Contants.API + Contants.SHOWGOODSSTANDARD)
                .addParams("goodsid", goodsid)
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
                                    , Contants.SHOWGOODSSTANDARD
                                    , mGson.fromJson(s, GoodStandardModel.class)));
                        } catch (Exception e) {
                            onError(null, e, i);
                        }
                    }
                });
    }
}
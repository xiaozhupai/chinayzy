package com.chinayiz.chinayzy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.entity.request.CommentGoodsModel;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;
import com.chinayiz.chinayzy.ui.activity.LoginActivity;
import com.chinayiz.chinayzy.ui.activity.MineFragment;
import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;
import com.chinayiz.chinayzy.ui.common.MoreRecommendGoodsFragment;
import com.chinayiz.chinayzy.ui.common.StoreFragment;
import com.chinayiz.chinayzy.ui.fragment.ClassifyFragment;
import com.chinayiz.chinayzy.ui.fragment.GoodsSetFragment;
import com.chinayiz.chinayzy.ui.fragment.NewClassifyFragment;
import com.chinayiz.chinayzy.ui.fragment.SearchFragment;
import com.chinayiz.chinayzy.ui.fragment.SearchResultFragment;
import com.chinayiz.chinayzy.ui.fragment.WebPowerFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.PayFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindDetailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.ContentKeepFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.EvalueResultFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.GoodsCommentFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.GoodsKeepFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.MyStepFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.OrderDetailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.OrderFrameworkFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SettingFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SuggestFragment;
import com.chinayiz.chinayzy.ui.fragment.register.DepositFragment;
import com.chinayiz.chinayzy.ui.fragment.register.MemberRuleFragment;

import java.io.Serializable;

import static com.chinayiz.chinayzy.ui.fragment.mine.GoodsCommentFragment.COMMENT_DATA;

/**
 * Created by Administrator on 2017/3/10.
 */

public class Skip {
    public static final String CLASS = "CLASS";

    private static void skip(Context context, Intent intent) {
        if (context instanceof CommonActivity) {
            CommonActivity activity = (CommonActivity) context;
            activity.addtoFragment(intent);
        } else {
            context.startActivity(intent);
        }
    }
    /**
     * 个人中心
     *
     * @param context
     */
    public static void toMine(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, MineFragment.class);
        skip(context, intent);
    }

    /**
     * 个人中心
     *
     * @param context
     */
    public static void toPerson(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, PersonFragment.class);
        skip(context, intent);
    }

    /**
     * 个人设置
     *
     * @param context
     */
    public static void toSetting(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, SettingFragment.class);
        skip(context, intent);
    }

    /**
     * 意见反馈
     *
     * @param context
     */
    public static void toSuggest(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, SuggestFragment.class);
        skip(context, intent);
    }

    /**
     * 购物车
     *
     * @param context
     */
    public static void toShopCart(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, ShopCartFragment.class);
        skip(context, intent);
    }

    /**
     * 结算订单
     *
     * @param context
     * @param params
     */
    public static void toResult(Context context, String params) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, ResultFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("params", params);
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 收货地址列表
     */
    public static void toAddressList(Context context){
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, AddressListFragment.class);
        skip(context, intent);
    }
    /**
     * 我的订单
     *
     * @param context
     * @param code
     */
    public static void toOrder(Context context, int code) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, OrderFrameworkFragment.class);
        Bundle bundle = new Bundle();
        bundle.putInt("orderType", code);
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 订单详情
     *
     * @param context
     * @param orderid 订单编号
     */
    public static void toOrderDetail(Context context, String orderid) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, OrderDetailFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("orderid", orderid);
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 更多推荐商品（商品详情页进入）
     * @param context
     * @param itemCode  类型代码
     */
    public static void toGoodsRecommend(Context context, String itemCode) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, MoreRecommendGoodsFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("itemCode", itemCode);
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 新的商品详情页
     *
     * @param context
     * @param goodId  订单编号
     */
    public static void toNewGoodsDetail(Context context, String goodId) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, GoodsMainFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("goodsID", goodId);
        intent.putExtras(bundle);
        skip(context, intent);
    }
    /**
     * 发现详情
     *
     * @param context
     * @param bean
     */
    public static void toFindDetail(Context context, FindListModel.DataBean bean) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, FindDetailFragment.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", (Serializable) (bean != null ? bean : new FindListModel.DataBean()));
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 评价订单
     *
     * @param context
     * @param orderInfo 订单信息
     */
    public static void toCommentGoods(Context context, CommentGoodsModel orderInfo) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, GoodsCommentFragment.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(COMMENT_DATA, orderInfo);
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 搜索
     *
     * @param context
     */
    public static void toSearch(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, SearchFragment.class);
        skip(context, intent);
    }

    /**
     * 宝贝收藏
     *
     * @param context
     */
    public static void toGoodsCollection(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, GoodsKeepFragment.class);
        skip(context, intent);
    }

    /**
     * 内容收藏
     *
     * @param context
     */
    public static void toContentCollection(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, ContentKeepFragment.class);
        skip(context, intent);
    }

    /**
     * 我的足迹
     *
     * @param context
     */
    public static void toMyStep(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, MyStepFragment.class);
        skip(context, intent);
    }


    /**
     * 支付成功
     *
     * @param context
     */
    public static void toPayResult(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, PayFragment.class);
        skip(context, intent);
    }

    /**
     * 支付结果页面
     *
     * @param context
     */
    public static void toPayResult(Context context, int type, String title) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, PayFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("type", type);
        intent.putExtras(bundle);
        skip(context, intent);
    }
    /**
     * 搜索结果页面
     * @param context
     */
    public static void toSearchResult(Context context, String titel) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, SearchResultFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("titel", titel);
        intent.putExtras(bundle);
        skip(context, intent);
    }
    /**
     * 会员须知
     *
     * @param context
     */
    public static void toMemberRuleFragment(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, MemberRuleFragment.class);
        skip(context, intent);
    }

    /**
     * 电商
     */
    public static void toMail(Context context, String itemCode) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, SearchResultFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("itemCode", itemCode);
        intent.putExtras(bundle);
        skip(context, intent);
    }


    /**
     * 生态农业二级菜单
     */
    public static void toItemMenu(Context context, String itemCode) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, ClassifyFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("itemCode", itemCode);
        intent.putExtras(bundle);
        skip(context, intent);
    }
    /**
     * 生态农业三级菜单商品
     */
    public static void toItemGoosd(Context context, String itemCode) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, NewClassifyFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("itemCode", itemCode);
        intent.putExtras(bundle);
        skip(context, intent);
    }
    /**
     * 充值1350
     *
     * @param context
     */
    public static void toDeposit(Context context) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, DepositFragment.class);
        skip(context, intent);
    }

    /**
     * 登录
     *
     * @param context
     */
    public static void toLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 操作完成页
     *
     * @param context
     * @param type    操作类型
     */
    public static void toSucceePage(Context context, int type) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, EvalueResultFragment.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 请求web页面
     *
     * @param context
     * @param url
     * @param titel
     */
    public static void toWebPage(Context context, String url, String titel) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, WebPowerFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("titel", titel);
        intent.putExtras(bundle);
        skip(context, intent);
    }

    /**
     * 进入店铺首页
     * @param context
     * @param storeID
     */
  public static void toStore(Context context,String storeID){
      Intent intent = new Intent(context, CommonActivity.class);
      intent.putExtra(CLASS, StoreFragment.class);
      Bundle bundle = new Bundle();
      bundle.putString("storeID", storeID);
      intent.putExtras(bundle);
      skip(context, intent);
  }
    /**
     * 分类商品查询结果集
     * @param context
     * @param itemcode
     */
    public static void toGoodsResult(Context context, String itemcode) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CLASS, GoodsSetFragment.class);
        Bundle bundle = new Bundle();
        bundle.putString("itemCode", itemcode);
        intent.putExtras(bundle);
        skip(context, intent);
    }
}

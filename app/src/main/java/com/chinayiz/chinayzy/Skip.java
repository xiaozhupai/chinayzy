package com.chinayiz.chinayzy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.adapter.ShopCartAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;

import com.chinayiz.chinayzy.ui.fragment.SearchFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.PayFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindDetailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.ContentKeepFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.EmailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.GoodsKeepFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.MyStepFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SettingFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SuggestFragment;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/10.
 */

public class Skip {
    public static final String CLASS="CLASS";
        private static void skip(Context context, Intent intent){
        if (context instanceof CommonActivity) {
            CommonActivity activity= (CommonActivity) context;
            activity.addtoFragment(intent);
        } else {
            context.startActivity(intent);
        }
    }

    /**
     * 个人中心
     * @param context
     */
    public static void toPerson(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, PersonFragment.class);
       skip(context,intent);
    }

    /**
     * 个人设置
     * @param context
     */
    public static void toSetting(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, SettingFragment.class);
        skip(context,intent);
    }

    /**
     * 意见反馈
     * @param context
     */
    public static void toSuggest(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, SuggestFragment.class);
        skip(context,intent);
    }

    /**
     * 购物车
     * @param context
     */
    public static void toShopCart(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, ShopCartFragment.class);
        skip(context,intent);
    }

    /**
     * 结算订单
     * @param context
     * @param params
     */
    public static void toResult(Context context,String params) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, ResultFragment.class);
        Bundle bundle=new Bundle();
        bundle.putString("params",params);
        intent.putExtras(bundle);
        skip(context,intent);
    }

    /**
     * 发现详情
     * @param context
     * @param  bean
     */
    public static void toFindDetail(Context context,FindListModel.DataBean bean){
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, FindDetailFragment.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("bean", (Serializable) (bean != null ? bean : new FindListModel.DataBean()));
        intent.putExtras(bundle);
        skip(context,intent);
    }
    /**
     * 搜索
     * @param context
     */
    public static void toSearch(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, SearchFragment.class);
        skip(context,intent);
    }

    /**
     * 宝贝收藏
     * @param context
     */
    public static void toGoodsCollection(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, GoodsKeepFragment.class);
        skip(context,intent);
    }

    /**
     * 内容收藏
     * @param context
     */
    public static void toContentCollection(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, ContentKeepFragment.class);
        skip(context,intent);
    }

    /**
     * 我的足迹
     * @param context
     */
    public static void toMyStep(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, MyStepFragment.class);
        skip(context,intent);
    }


    /**
     * 支付成功
     * @param context
     */
    public static void toPayResult(Context context) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, PayFragment.class);
        skip(context,intent);
    }


}

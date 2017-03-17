package com.chinayiz.chinayzy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindDetailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.OrderDetailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.OrderFrameworkFragment;
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
     * 我的订单
     * @param context
     * @param code
     */
    public static void toOrder(Context context,int code) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, OrderFrameworkFragment.class);
        Bundle bundle=new Bundle();
        bundle.putInt("orderType",code);
        intent.putExtras(bundle);
        skip(context,intent);
    }
    /**
     * 订单详情
     * @param context
     * @param orderid 订单编号
     */
    public static void toOrderDetail(Context context,String orderid) {
        Intent intent=new Intent(context,CommonActivity.class);
        intent.putExtra(CLASS, OrderDetailFragment.class);
        Bundle bundle=new Bundle();
        bundle.putString("orderid",orderid);
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

}

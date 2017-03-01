package com.chinayiz.chinayzy.ui.fragment.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ResultAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.presenter.ResultPresenter;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 结算订单
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends BaseFragment<ResultPresenter> implements View.OnClickListener {
    public ListView lv_result;
    public TextView tv_result_price;
    public TextView tv_result_submit;
    public RelativeLayout result_list;
    public ResultAdaphter adaphter;
    public TextView tv_goods_total;
    public TextView tv_cost;
    public CheckBox cb_check;
    public RelativeLayout rl_payway_boom;
    public CheckImageView iv_pay_ali;
    public CheckImageView iv_pay_wechat;
    public LinearLayout lv_payway;
    public List<ResultModel.DataBean.GoodmessageBean> list;
    public String params;
    public TextView tv_address_name,tv_address_phone,tv_address_text;

    public ResultFragment(String params){
        this.params=params;
        Logger.i("resultFragment"+params);
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, null);
        lv_result = (ListView) view.findViewById(R.id.lv_result);
        tv_result_price = (TextView) view.findViewById(R.id.tv_result_price);
        tv_result_submit = (TextView) view.findViewById(R.id.tv_result_submit);
        tv_result_submit.setOnClickListener(this);
        result_list = (RelativeLayout) view.findViewById(R.id.result_list);

        View head = View.inflate(getActivity(), R.layout.result_head, null);
        tv_address_name= (TextView) head.findViewById(R.id.tv_address_name);
        tv_address_phone= (TextView) head.findViewById(R.id.tv_address_phone);
        tv_address_text= (TextView) head.findViewById(R.id.tv_address_text);
        lv_result.addHeaderView(head);

        View foot = View.inflate(getActivity(), R.layout.result_foot, null);
        tv_goods_total = (TextView) foot.findViewById(R.id.tv_goods_total);
        tv_cost = (TextView) foot.findViewById(R.id.tv_cost);
        cb_check = (CheckBox) foot.findViewById(R.id.cb_check);
        rl_payway_boom = (RelativeLayout) foot.findViewById(R.id.rl_payway_boom);
        iv_pay_ali = (CheckImageView) foot.findViewById(R.id.iv_ali_pay);
        iv_pay_wechat = (CheckImageView) foot.findViewById(R.id.iv_wechat_pay);
        lv_payway = (LinearLayout) foot.findViewById(R.id.lv_payway);
        iv_pay_ali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //支付宝支付
                if (iv_pay_wechat.isCheck){
                    iv_pay_wechat.setCheck(false);
                    iv_pay_ali.setCheck(true);
                }
            }
        });
        iv_pay_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //微信支付
                if (iv_pay_ali.isCheck){
                    iv_pay_ali.setCheck(false);
                    iv_pay_wechat.setCheck(true);
                }
            }
        });
        rl_payway_boom.setOnClickListener(this);
        iv_pay_ali.setCheck(true);
        iv_pay_wechat.setCheck(false);

        lv_result.addFooterView(foot);
        list = new ArrayList();

        adaphter = new ResultAdaphter(getActivity(), list);
        lv_result.setAdapter(adaphter);
//        tv_goods_total.setText("￥"+UpdateTotal());
//        tv_result_price.setText("总计:￥"+UpdateTotal());
        return view;

    }


//    /**
//     * 获得商品总价
//     * @return
//     */
//    public double UpdateTotal(){
//        double total=0.00;
//        for (ShopCartModel.DataBean.ShoplistBean bean:list){
//            total+=bean.getPrice()*bean.getNum();
//        }
//        return total;
//    }


    @Override
    public ResultPresenter initPresenter() {
        return new ResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_result_submit:  //确定支付
             mPresenter.submit();
                break;
            case R.id.rl_payway_boom:    //底部支付方式
                if (lv_payway.getVisibility()==View.VISIBLE){
                    lv_payway.setVisibility(View.GONE);
                }else {
                    lv_payway.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


}

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
    private ListView lv_result;
    private TextView tv_result_price;
    private TextView tv_result_submit;
    private RelativeLayout result_list;
    private ResultAdaphter adaphter;
    private TextView tv_goods_total;
    private TextView tv_cost;
    private CheckBox cb_check;
    private RelativeLayout rl_payway_boom;
    private CheckImageView iv_pay_ali;
    private CheckImageView iv_pay_wechat;
    private LinearLayout lv_payway;
    private List<ShopCartModel> list;

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
        View head = View.inflate(mContext, R.layout.result_head, null);
        lv_result.addHeaderView(head);

        View foot = View.inflate(mContext, R.layout.result_foot, null);
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
        for (int i=0;i<5;i++){
            ShopCartModel model=new ShopCartModel();
            model.setSname("dsds");
            model.setChecked(false);
            model.setNum(2);
            model.setPrice(130.25);
            list.add(model);
        }
        for (int i=0;i<5;i++){
            ShopCartModel model=new ShopCartModel();
            model.setSname("bbb");
            model.setChecked(false);
            model.setNum(1);
            model.setPrice(120.25);
            list.add(model);
        }
        for (int i=0;i<5;i++){
            ShopCartModel model=new ShopCartModel();
            model.setSname("ccc");
            model.setChecked(false);
            model.setNum(1);
            model.setPrice(110.25);
            list.add(model);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).setHead(true);
            } else {
                if (!list.get(i).getSname().equals(list.get(i - 1).getSname())) {
                    list.get(i).setHead(true);
                    Logger.i("头部视图");
                } else {
                    list.get(i).setHead(false);
                    Logger.i("body视图");
                }
            }
        }
        adaphter = new ResultAdaphter(mContext, list);
        lv_result.setAdapter(adaphter);
        tv_goods_total.setText("￥"+UpdateTotal());
        tv_result_price.setText("总计:￥"+UpdateTotal());
        return view;

    }


    /**
     * 获得商品总价
     * @return
     */
    public double UpdateTotal(){
        double total=0.00;
        for (ShopCartModel bean:list){
            total+=bean.getPrice()*bean.getNum();
        }
        return total;
    }


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

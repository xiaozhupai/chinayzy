package com.chinayiz.chinayzy.ui.fragment.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.CommonAdaphter;
import com.chinayiz.chinayzy.adapter.ShopCartAdaphter;
import com.chinayiz.chinayzy.adapter.ShopHeadAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel.ShopCartBean;
import com.chinayiz.chinayzy.presenter.ShopCartPresenter;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.chinayiz.chinayzy.views.PinnedHeaderListView;
import com.chinayiz.chinayzy.views.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.PullableListView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.halfbit.pinnedsection.PinnedSectionListView;


/**
 * 购物车
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends BaseFragment<ShopCartPresenter> implements View.OnClickListener {
    private PullableListView listv_shopcart;
    private CheckImageView iv_shopcart_radio;
    private TextView tv_shopcart_price;
    private TextView tv_shopcart_submit;
    private LinearLayout lv_boom;
    private PullToRefreshLayout pullToRefreshLayout;

    public ShopCartAdaphter adaphter;
    public List<ShopCartBean> list=new ArrayList<>();
    public TextView tv_shopcart_all;

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
        View view = View.inflate(getActivity(), R.layout.fragment_shop_cart, null);
        pullToRefreshLayout=(PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        listv_shopcart = (PullableListView) view.findViewById(R.id.listv_shopcart);
        iv_shopcart_radio = (CheckImageView) view.findViewById(R.id.iv_shopcart_radio);
        tv_shopcart_price = (TextView) view.findViewById(R.id.tv_shopcart_price);
        tv_shopcart_submit = (TextView) view.findViewById(R.id.tv_shopcart_submit);
        lv_boom = (LinearLayout) view.findViewById(R.id.lv_boom);
        tv_shopcart_all= (TextView) view.findViewById(R.id.tv_shopcart_all);
        tv_shopcart_submit.setOnClickListener(this);
        iv_shopcart_radio.setOnClickListener(this);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });

        for (int i=0;i<5;i++){
            ShopCartBean model=new ShopCartBean();
            model.setSname("dsds");
            model.setChecked(false);
            model.setNum(2);
            model.setPrice(130.25);
            list.add(model);
        }
        for (int i=0;i<5;i++){
            ShopCartBean model=new ShopCartBean();
            model.setSname("bbb");
            model.setChecked(false);
            model.setNum(1);
            model.setPrice(120.25);
            list.add(model);
        }
        for (int i=0;i<5;i++){
            ShopCartBean model=new ShopCartBean();
            model.setSname("ccc");
            model.setChecked(false);
            model.setNum(1);
            model.setPrice(110.25);
            list.add(model);
        }

        adaphter=new ShopCartAdaphter(mContext,list,iv_shopcart_radio,tv_shopcart_price);
        listv_shopcart.setAdapter(adaphter);
        adaphter.setData(list);
        return view;
    }

    @Override
    public ShopCartPresenter initPresenter() {
        return new ShopCartPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_shopcart_submit:  //结算或者删除
                startFragment(new ResultFragment());
                break;
            case R.id.iv_shopcart_radio:   //是否全选
                if (iv_shopcart_radio.isCheck){
                    iv_shopcart_radio.setCheck(false);
                }else {
                    iv_shopcart_radio.setCheck(true);
                }
                for (int i=0;i<list.size();i++){
                    if (list.get(i).isHead()){
                        list.get(i).setHeadChecked(iv_shopcart_radio.isCheck);
                    }
                    list.get(i).setChecked(iv_shopcart_radio.isCheck);
                }
                adaphter.setData(list);
                double total=adaphter.UpdateTotal();
                tv_shopcart_price.setText(total+"");
                break;

        }
    }


}

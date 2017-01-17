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
import com.chinayiz.chinayzy.presenter.ShopCartPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * 购物车
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends BaseFragment<ShopCartPresenter> implements View.OnClickListener {
    private ListView listv_shopcart;
    private ImageView iv_shopcart_radio;
    private TextView tv_shopcart_price;
    private TextView tv_shopcart_submit;
    private LinearLayout lv_boom;
    private ShopHeadAdaphter headAdaphter;
    private CommonAdaphter commonAdaphter;
    private ShopCartAdaphter adaphter;

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
        listv_shopcart = (ListView) view.findViewById(R.id.listv_shopcart);
        iv_shopcart_radio = (ImageView) view.findViewById(R.id.iv_shopcart_radio);
        tv_shopcart_price = (TextView) view.findViewById(R.id.tv_shopcart_price);
        tv_shopcart_submit = (TextView) view.findViewById(R.id.tv_shopcart_submit);
        lv_boom = (LinearLayout) view.findViewById(R.id.lv_boom);
        tv_shopcart_submit.setOnClickListener(this);
        List<ShopCartModel> list=new ArrayList();
           for (int i=0;i<5;i++){
               ShopCartModel model=new ShopCartModel();
               model.setSname("dsds");
               list.add(model);
           }
        for (int i=0;i<5;i++){
            ShopCartModel model=new ShopCartModel();
            model.setSname("bbb");
            list.add(model);
        }
        for (int i=0;i<5;i++){
            ShopCartModel model=new ShopCartModel();
            model.setSname("ccc");
            list.add(model);
        }
        ShopCartAdaphter adaphter=new ShopCartAdaphter(mContext,list);
        listv_shopcart.setAdapter(adaphter);
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
            case R.id.tv_shopcart_submit :
                startFragment(new ResultFragment());
                break;
        }
    }
}

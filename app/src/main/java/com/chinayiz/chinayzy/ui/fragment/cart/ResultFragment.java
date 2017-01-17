package com.chinayiz.chinayzy.ui.fragment.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ResultAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.presenter.ResultPresenter;

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
        View view=inflater.inflate(R.layout.fragment_result, null);
        lv_result = (ListView) view.findViewById(R.id.lv_result);
        tv_result_price = (TextView) view.findViewById(R.id.tv_result_price);
        tv_result_submit = (TextView) view.findViewById(R.id.tv_result_submit);
        tv_result_submit.setOnClickListener(this);
        result_list = (RelativeLayout) view.findViewById(R.id.result_list);
        View head=View.inflate(mContext,R.layout.result_head,null);
        lv_result.addHeaderView(head);
        View foot=View.inflate(mContext,R.layout.result_foot,null);
        lv_result.addFooterView(foot);
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
        adaphter=new ResultAdaphter(mContext,list);
        lv_result.setAdapter(adaphter);
        return view;
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
       View view=initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_result_submit:

                break;
        }
    }
}

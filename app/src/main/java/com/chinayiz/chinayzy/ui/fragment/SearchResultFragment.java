package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.presenter.SearchResultPresenter;
import com.chinayiz.chinayzy.views.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.PullableGridView;
import com.chinayiz.chinayzy.views.PullableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果
 */
public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements View.OnClickListener {
    private TextView tv_hot;
    private TextView tv_sale;
    private TextView tv_price;
    private PullableGridView pullgd;
    private PullableListView pulllv;
    private PullToRefreshLayout pullrefresh;
    public SearchResultAdaphter adaphter;

    public SearchResultFragment() {

    }


    @Override
    protected void onVisible() {

    }
    @Override
    protected void onInvisible() {


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, null);
        tv_hot = (TextView) view.findViewById(R.id.tv_hot);
        tv_hot.setOnClickListener(this);
        tv_sale = (TextView)view.findViewById(R.id.tv_sale);
        tv_sale.setOnClickListener(this);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_price.setOnClickListener(this);
        pullgd = (PullableGridView) view.findViewById(R.id.pullgd);
        pulllv = (PullableListView) view.findViewById(R.id.pulllv);
        pullrefresh = (PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        pullrefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                 pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
        List<SearchFarmModel.DataBean> list=new ArrayList();

        adaphter=new SearchResultAdaphter(list,1,mContext);
        pulllv.setAdapter(adaphter);
        return view;
    }

    @Override
    public SearchResultPresenter initPresenter() {
        return new SearchResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_hot:

                break;
            case R.id.tv_sale:

                break;
            case R.id.tv_price:

                break;
        }
    }
}

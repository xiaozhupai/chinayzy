package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.GoodsKeepAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.GoodsKeepPresenter;
import com.chinayiz.chinayzy.views.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.PullableListView;

import java.util.ArrayList;
import java.util.List;

/**   宝贝收藏
 * A simple {@link Fragment} subclass.
 */
public class GoodsKeepFragment extends BaseFragment<GoodsKeepPresenter> {
    public PullableListView lv_list;
    public PullToRefreshLayout pullrefresh;
    public GoodsKeepAdaphter adaphter;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view=   initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content_keep, container, false);
        pullrefresh= (PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        lv_list   =(PullableListView) view.findViewById(R.id.lv_list);
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
        adaphter=new GoodsKeepAdaphter();
        lv_list.setAdapter(adaphter);
        List list=new ArrayList();
        for (int i=0;i<10;i++){
            list.add("");
        }
        adaphter.setData(list);
        return view;
    }

    @Override
    public GoodsKeepPresenter initPresenter() {
        return new GoodsKeepPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

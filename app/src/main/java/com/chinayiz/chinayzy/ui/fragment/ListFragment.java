package com.chinayiz.chinayzy.ui.fragment;


import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.BaseInectAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ListPresenter;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableListView;
import com.orhanobut.logger.Logger;

/**
 * A simple {@link Fragment} subclass.  列表通用布局
 */
@SuppressLint("ValidFragment")
public class ListFragment extends BaseFragment<ListPresenter> {
    private PullableListView pull_listview;
    private PullToRefreshLayout pullrefresh;
    public BaseInectAdaphter adapter;
    public LinearLayout ll_none;
    public TextView tv_none;
    public ImageView iv_none;

    public ListFragment(BaseInectAdaphter adapter) {
        this.adapter=adapter;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        pull_listview = (PullableListView) view.findViewById(R.id.pull_listview);
        pullrefresh = (PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        ll_none= (LinearLayout) view.findViewById(R.id.ll_none);
        tv_none= (TextView) view.findViewById(R.id.tv_none);
        iv_none= (ImageView) view.findViewById(R.id.iv_none);
        pull_listview.setAdapter(adapter);
        adapter.setListview(pull_listview);
        adapter.setRefreshLayout(pullrefresh);
        adapter.setFragment(this);
        pullrefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                 adapter.onRefresh();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                 adapter.LoadMore();
            }
        });

        return view;
    }



    @Override
    public ListPresenter initPresenter() {
        return new ListPresenter();
    }

    @Override
    protected void onVisible() {
    if (isInit){
        adapter.getdata(1);
    }
        isInit=false;
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onDestroy() {
        adapter.onDestory();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        adapter.onRefresh();
        Logger.i("onResume");
        super.onResume();
    }
}

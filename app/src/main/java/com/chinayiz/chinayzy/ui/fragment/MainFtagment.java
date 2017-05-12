package com.chinayiz.chinayzy.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.MainHomeRecylAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.MainsPresenter;
import com.chinayiz.chinayzy.utils.BarUtils;
import com.chinayiz.chinayzy.views.refreshView.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.refreshView.PullableRecycleView;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 9:35
 * Class MainFtagment
 */

public class MainFtagment extends BaseFragment<MainsPresenter> implements View.OnClickListener, PullableRecycleView.RefreshListner {
    public PullableRecycleView home_recyclerLayout;
    public PullToRefreshLayout refresh_view;
    private boolean fristLaod = true;
    public MainHomeRecylAdapter mRecylAdapter;


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initWidget(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fristLaod) {
            fristLaod = false;
        }
    }

    private void initWidget(View view) {
        View iv_seek = view.findViewById(R.id.iv_seek);
        View iv_services = view.findViewById(R.id.iv_services);
        home_recyclerLayout = (PullableRecycleView) view.findViewById(R.id.nongye_home_recyclerLayout);
        refresh_view = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(mPresenter);
        home_recyclerLayout.setListner(this);
        mRecylAdapter = new MainHomeRecylAdapter(this);
        home_recyclerLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        home_recyclerLayout.setAdapter(mRecylAdapter);
        iv_seek.setOnClickListener(this);
        iv_services.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_seek:
                Skip.toSearch(getActivity());
                break;
            case R.id.iv_services:
                BaseActivity.showToast(getActivity(), "暂无消息");
                break;
        }
    }

    @Override
    public MainsPresenter initPresenter() {
        return new MainsPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        Logger.i("初始化actionbar");
        try {
            activity.mActionBar.setVisibility(View.GONE);
            BarUtils.setColor(activity,  Color.rgb(218, 22, 47));
        } catch (Exception e) {
            Logger.i("actionbar设置异常" + e);
        }
        super.onInitActionBar(activity);
    }

    public static MainFtagment getInstance() {
        return new MainFtagment();
    }

    @Override
    /**
     * 是否可以加载更多
     */
    public boolean canLoad() {
        return false;
    }
}

package com.chinayiz.chinayzy.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.MainHomeRecylAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.MainsPresenter;
import com.chinayiz.chinayzy.utils.BarUtils;
import com.chinayiz.chinayzy.utils.DynamicTimeFormat;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 9:35
 * Class MainFtagment
 */
@SuppressLint("ValidFragment")
public class MainFtagment extends BaseFragment<MainsPresenter> implements View.OnClickListener {
    public RecyclerView home_recyclerLayout;
    public SmartRefreshLayout mSmartRefreshLayout;
    public ClassicsHeader mClassicsHeader;
    private boolean fristLaod = true;
    public MainHomeRecylAdapter mRecylAdapter;
    public View mSarechBar;


    public MainFtagment() {
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initWidget(view);
        return view;
    }


    private void initWidget(View view) {
        View iv_seek = view.findViewById(R.id.iv_seek);
        View iv_services = view.findViewById(R.id.iv_services);
        mSarechBar = view.findViewById(R.id.main_searchBar);
        home_recyclerLayout = (RecyclerView) view.findViewById(R.id.nongye_home_recyclerLayout);
        mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        mSmartRefreshLayout.getLayout().setBackgroundResource(android.R.color.transparent);
        mSmartRefreshLayout.setPrimaryColorsId(android.R.color.transparent, android.R.color.tertiary_text_dark);
        mClassicsHeader = (ClassicsHeader)mSmartRefreshLayout.getRefreshHeader();
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        mClassicsHeader.setTimeFormat(new DynamicTimeFormat("更新于 %s"));

        mRecylAdapter = new MainHomeRecylAdapter(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        home_recyclerLayout.setLayoutManager(layoutManager);
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
        if (fristLaod) {
            mPresenter.mNet.getBanner(Commons.MAIN_BANNER);
            mPresenter.mRequestUtils.getHomeModel();
            mPresenter.mRequestUtils.getHomeMainActivitys();
            mPresenter.mRequestUtils.getHomeNews();
            mPresenter.mRequestUtils.getHomeZhongChuo();
            Logger.i("获取数据");
            fristLaod=false;
        }
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

}

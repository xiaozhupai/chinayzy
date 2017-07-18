package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.chinayiz.chinayzy.adapter.MainHomeRecylAdapter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.MainFtagment;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 9:38
 * Class MainsPresenter
 */

public class MainsPresenter extends BasePresenter<MainFtagment> implements OnRefreshListener, OnLoadmoreListener {
    /**
     * 加载更多标记
     */
    public static final String LOAD_SIGN = "MainsPresenter_SIGN";
    public static final String SHOW_ACTIONBAR = "MainsPresenter_SHOW";

    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public RefreshLayout mRefreshLayout;
    public Net mNet = Net.getNet();
    private int pager = 1;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.MAIN_BANNER: {// banner图
                mView.mRecylAdapter.addDate(Commons.MAIN_BANNER, message.getData());
                mView.mRecylAdapter.notifyItemChanged(0);
                return;
            }
            case Commons.HONE_ACTIVITYS: {//  首页主要主题（新人）
                mView.mRecylAdapter.addDate(Commons.HONE_ACTIVITYS, message.getData());
                mView.mRecylAdapter.notifyItemChanged(1);
                mView.home_recyclerLayout.scrollToPosition(0);
                return;
            }
            case Commons.HOME_MODEL: {// 板块按钮
                mView.mRecylAdapter.addDate(Commons.HOME_MODEL, message.getData());
                mView.mRecylAdapter.notifyItemChanged(2);
                if (mRefreshLayout != null) {
                    mRefreshLayout.finishRefresh(true);
                }
                return;
            }
            case Commons.HOME_NEWS: { // 首页亿众头条
                mView.mRecylAdapter.addDate(Commons.HOME_NEWS, message.getData());
                mView.mRecylAdapter.notifyItemChanged(3);
                return;
            }
            case Commons.HOME_ZHONGCHOU: { // 众筹倒计时抢购商品
                mView.mRecylAdapter.addDate(Commons.HOME_ZHONGCHOU, message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT, LOAD_SIGN, ""));
                return;
            }
            case Commons.HOME_THEME1: {// 主题一 （原生态主题）
                mView.mRecylAdapter.addDate(Commons.HOME_THEME1, message.getData());
                mView.mRecylAdapter.notifyItemChanged(5);
                return;
            }
            case Commons.HOME_LIST1: { // 横向主题商品一 （原生态主题）
                mView.mRecylAdapter.addDate(Commons.HOME_LIST1, message.getData());
                mView.mRecylAdapter.notifyItemChanged(6);
                return;
            }
            case Commons.HOME_THEME2: { // 主题二 （吃货必备主题）
                mView.mRecylAdapter.addDate(Commons.HOME_THEME2, message.getData());
                mView.mRecylAdapter.notifyItemChanged(7);
                return;
            }
            case Commons.HOME_LIST2: {// 横向主题商品二 （吃货必备主题）
                mView.mRecylAdapter.addDate(Commons.HOME_LIST2, message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                return;
            }
            case MainHomeRecylAdapter.TYPE_LOVEFS: {// 主题商品 （爱时尚主题）
                mView.mRecylAdapter.addDate(MainHomeRecylAdapter.TYPE_LOVEFS, message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                return;
            }
            case MainHomeRecylAdapter.TYPE_XIINFO: { // 主题商品 （世界硒都主题）
                mView.mRecylAdapter.addDate(MainHomeRecylAdapter.TYPE_XIINFO, message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                return;
            }
            case MainHomeRecylAdapter.TYPE_LUXURY: {// 主题商品 （奢侈品主题）
                mView.mRecylAdapter.addDate(MainHomeRecylAdapter.TYPE_LUXURY, message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                return;
            }
            case Commons.HOME_REXIAO: {// 主题商品 （为你推荐无限上拉）
                mRefreshLayout.finishLoadmore(true);
                mView.mRecylAdapter.loadData((HomeHotGoodsModel) message.getData());
                pager++;
                return;
            }
            case SHOW_ACTIONBAR: {
                mView.mSarechBar.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {
            case LOAD_SIGN: {
                try {
                    Thread.sleep(2000);
                    EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT, SHOW_ACTIONBAR, ""));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate() {
        mView.mSmartRefreshLayout.setOnRefreshListener(this);
        mView.mSmartRefreshLayout.setOnLoadmoreListener(this);
    }

    private void loadDatas() {
        mRequestUtils.getHomeHotGoods(String.valueOf(pager), "10");
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            if (mRefreshLayout != null && message.getData() instanceof BaseResponseModel) {
                mRefreshLayout.finishRefresh(false);
                mRefreshLayout.finishLoadmore(false);
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {//组件通讯消息
            disposeInfoMsg(message);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mRefreshLayout = refreshlayout;
        mNet.getBanner(Commons.MAIN_BANNER);
        mRequestUtils.getHomeModel();
        mRequestUtils.getHomeMainActivitys();
        mRequestUtils.getHomeNews();
        mRequestUtils.getHomeZhongChuo();
        Logger.i("下拉刷新 隐藏搜索栏");
        mView.mSarechBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        Logger.i("上拉加载更多" + pager);
        mRefreshLayout = refreshlayout;
        if (pager > 50) {//限制加载最大数
            mRefreshLayout.finishLoadmore(false);
            mRefreshLayout.setEnableLoadmore(false);
            Snackbar.make(mView.getView(), "到底啦，没有更多了", Snackbar.LENGTH_LONG).show();
            return;
        }
        loadDatas();
    }
}

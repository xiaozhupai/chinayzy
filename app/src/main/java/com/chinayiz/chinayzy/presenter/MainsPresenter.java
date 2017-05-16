package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.MainFtagment;
import com.chinayiz.chinayzy.views.refreshView.PullToRefreshLayout;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 9:38
 * Class MainsPresenter
 */

public class MainsPresenter extends BasePresenter<MainFtagment> implements PullToRefreshLayout.OnRefreshListener {
    /**
     * 加载更多标记
     */
    public static final String LOAD_SIGN="MainsPresenter_SIGN";
    public PullToRefreshLayout mToRefreshLayout;
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public Net mNet = Net.getNet();
    private int pager = 1;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.MAIN_BANNER: {
                mView.mRecylAdapter.addDate(Commons.MAIN_BANNER, message.getData());
                mView.mRecylAdapter.notifyItemChanged(0);
                break;
            }
            case Commons.HOME_MODEL: {
                mView.mRecylAdapter.addDate(Commons.HOME_MODEL, message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            }
            case Commons.HOME_THEME1: {
                mView.mRecylAdapter.addDate(Commons.HOME_THEME1, message.getData());
                mView.mRecylAdapter.notifyItemChanged(2);
                break;
            }
            case Commons.HOME_LIST1: {
                mView.mRecylAdapter.addDate(Commons.HOME_LIST1, message.getData());
                mView.mRecylAdapter.notifyItemChanged(3);
                break;
            }
            case Commons.HOME_THEME2: {
                mView.mRecylAdapter.addDate(Commons.HOME_THEME2, message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            }
            case Commons.HOME_LIST2: {
                mView.mRecylAdapter.addDate(Commons.HOME_LIST2, message.getData());
                mView.mRecylAdapter.notifyItemChanged(5);
                mView.home_recyclerLayout.scrollToPosition(0);
                break;
            }
            case Commons.HOME_REXIAO: {
                if (pager==1){
                    mView.mRecylAdapter.addDate(Commons.HOME_REXIAO, message.getData());
                }else {
                    mView.mRecylAdapter.loadData((HomeHotGoodsModel) message.getData());
                }
                pager++;
                break;
            }

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case LOAD_SIGN:{
                try {
                    Thread.sleep(2500);
                    mView.canLoad=true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate() {
        mNet.getBanner(Commons.MAIN_BANNER);
        mRequestUtils.getHomeModel();
        mRequestUtils.getHomeTheme(Commons.HOME_THEME1);
        mRequestUtils.getHomeList(Commons.HOME_LIST1);
        mRequestUtils.getHomeTheme(Commons.HOME_THEME2);
        mRequestUtils.getHomeList(Commons.HOME_LIST2);
        Logger.i("获取数据");
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
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        mToRefreshLayout = pullToRefreshLayout;
        mNet.getBanner(Commons.MAIN_BANNER);
        mRequestUtils.getHomeModel();
        mRequestUtils.getHomeTheme(Commons.HOME_THEME1);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        mToRefreshLayout=pullToRefreshLayout;
        mView.canLoad=false;
        if (pager>8){
            mToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
            Snackbar.make(mView.getView(),"到底啦，没有更多了",Snackbar.LENGTH_LONG).show();
            return;
        }
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,LOAD_SIGN,""));
        loadDatas();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
            if (mToRefreshLayout != null) {
                mToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                mToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            if (mToRefreshLayout != null) {
                mToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
                mToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
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
}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.MainFtagment;
import com.chinayiz.chinayzy.views.refreshView.PullToRefreshLayout;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 9:38
 * Class MainsPresenter
 */

public class MainsPresenter extends BasePresenter<MainFtagment> implements PullToRefreshLayout.OnRefreshListener {
    public PullToRefreshLayout mToRefreshLayout;
    public CommonRequestUtils mRequestUtils=CommonRequestUtils.getRequestUtils();
    public Net mNet = Net.getNet();


    @Override
    public void disposeNetMsg(EventMessage message) {
    switch (message.getDataType()){
        case Commons.MAIN_BANNER:{
            mView.mRecylAdapter.addDate(Commons.MAIN_BANNER,message.getData());
            mView.mRecylAdapter.notifyItemChanged(0);
            break;
        }
        case Commons.HOME_MODEL:{
            mView.mRecylAdapter.addDate(Commons.HOME_MODEL,message.getData());
            mView.mRecylAdapter.notifyDataSetChanged();
            break;
        }
        case Commons.HOME_THEME1:{
            mView.mRecylAdapter.addDate(Commons.HOME_THEME1,message.getData());
            mView.mRecylAdapter.notifyItemChanged(2);
            break;
        }
        case Commons.HOME_LIST1:{
            mView.mRecylAdapter.addDate(Commons.HOME_LIST1,message.getData());
            mView.mRecylAdapter.notifyItemChanged(3);
            break;
        }
        case Commons.HOME_THEME2:{
            mView.mRecylAdapter.addDate(Commons.HOME_THEME2,message.getData());
            mView.mRecylAdapter.notifyDataSetChanged();
            break;
        }
        case Commons.HOME_LIST2:{
            mView.mRecylAdapter.addDate(Commons.HOME_LIST2,message.getData());
            mView.mRecylAdapter.notifyItemChanged(5);
            break;
        }
        case Commons.HOME_REXIAO:{
            mView.mRecylAdapter.addDate(Commons.HOME_REXIAO,message.getData());
            mView.home_recyclerLayout.scrollToPosition(0);
            mView.mRecylAdapter.notifyDataSetChanged();
            break;
        }

    }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    protected void onCreate() {
        mNet.getBanner(Commons.MAIN_BANNER);
        mRequestUtils.getHomeModel();
        mRequestUtils.getHomeTheme(Commons.HOME_THEME1);
        mRequestUtils.getHomeList(Commons.HOME_LIST1);
        mRequestUtils.getHomeTheme(Commons.HOME_THEME2);
        mRequestUtils.getHomeList(Commons.HOME_LIST2);
        mRequestUtils.getHomeHotGoods("1","10");
        Logger.i("获取数据");
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        mToRefreshLayout=pullToRefreshLayout;
        mNet.getBanner(Commons.MAIN_BANNER);
        mRequestUtils.getHomeModel();
        mRequestUtils.getHomeTheme(Commons.HOME_THEME1);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        pullToRefreshLayout.loadmoreFinish(1);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
            if (mToRefreshLayout!=null) {
                mToRefreshLayout.refreshFinish(1);
            }
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            if (mToRefreshLayout!=null) {
                mToRefreshLayout.refreshFinish(1);
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

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_HomeBanner;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.HomeFragment;
import com.chinayiz.chinayzy.views.refreshView.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.refreshView.PullableRecycleView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:21
 * Class NongYe_homePresenter  生态农业首页presenter
 */
public class homePresenter extends BasePresenter<HomeFragment> implements PullToRefreshLayout.OnRefreshListener, PullableRecycleView.RefreshListner {
    public int count = 0;
    private Net mNet = Net.getNet();
    private List<NY_EatItemModel.DataBean> eatList = new ArrayList<>(5);
    private NY_EatItemModel model;
    private Thread mThread;
    private int pager;
    private boolean canLoad = true;
    private CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public PullToRefreshLayout mToRefreshLayout;

    @Override
    //刷新数据
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        Logger.i("刷新数据=" + count);
        mToRefreshLayout = pullToRefreshLayout;
    }

    @Override
    //加载更多
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        mToRefreshLayout = pullToRefreshLayout;
        canLoad = false;
        Logger.i("去加载数据");
        getData(count);
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.NY_BANNER:
                mView.mRecylAdapter.setData(1, message.getData());
                break;
            case Commons.NY_RECOMMENT:
                mView.mRecylAdapter.setData(2, message.getData());
                break;
            case Commons.NY_FEATURE:
                mView.mRecylAdapter.setData(3, message.getData());
                break;
            case Commons.NY_EATTHEME:
                mView.mRecylAdapter.setData(4, message.getData());
                break;
            case Commons.NY_EATITEM://爱吃商品
                NY_EatItemModel model = (NY_EatItemModel) message.getData();
                if (mToRefreshLayout!=null) mToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                eatList.addAll(model.getData());
                for (NY_EatItemModel.DataBean eatItem : eatList) {
                    mView.mRecylAdapter.setData(count++, eatItem);
                }
                EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT,NongYeHomeRecylAdapter.REFRESH,""));
                break;
            case Commons.ADDSHOPPINGCAR://添加购物车成功
                BaseActivity.showToast(mView.getActivity(), "添加购物超成功");
                break;
        }
    }

    /**
     * 获取数据
     */
    public void getData(int position) {
        switch (position) {
            case 1://请求banner图
                mNet.getBanner(Commons.NY_BANNER);
                break;
            case 2://请求精选推荐版块
                mNet.getRecomment();
                break;
            case 3://请求特色购版块
                mNet.getFeature();
                break;
            case 4://请求爱吃主题
                mNet.getEatTheme();
                break;
            default:
                pager = eatList.size() / 5;
                if (model != null) {//是否首次加载
                    if (model.getData().size() == 5) {//是否还有数据
                        mNet.getEatItem(String.valueOf(pager + 1), "5");
                    } else {
                        mToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
                        BaseActivity.showToast(mView.getActivity(), "没有更多啦，到底啦！");
                    }
                } else {
                    pager++;
                    mNet.getEatItem(String.valueOf(pager), "5");
                }
                break;

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        NY_EatItemModel.DataBean data;
        switch (message.getDataType()) {
            case NY_HomeBanner.SEARCH://搜索
                Skip.toSearch(mView.getActivity());
                break;
            case Commons.ADD_CAR://添加购物车
                if (!"0".equals(APP.sUserid)){//是否登录
                    data = (NY_EatItemModel.DataBean) message.getData();
                    mRequestUtils.getJoinCart(String.valueOf(data.getShopid()),
                            String.valueOf(data.getGoodsstandardid()), "1");
                }else {
                    Skip.toLogin(mView.getActivity());
                }
                break;
            case NongYeHomeRecylAdapter.CLICK_GOODS://首页商品点击事件
                mView.openGoodesDetail(message.getData().toString());
                break;
            case NongYeHomeRecylAdapter.CLICK_MENU://首页菜单点击事件
                String code=message.getData().toString();
                    mView.openClassify(code);
                break;
            case NongYeHomeRecylAdapter.REFRESH://数据刷新成功
                mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        canLoad = true;
                    }
                });
                mThread.start();
                break;
        }
    }

    @Override
    public void onCreate() {
        count = 6;
        for (int i = 1; i < count; i++) {
            getData(i);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            mToRefreshLayout.loadmoreFinish(1);
            mToRefreshLayout.refreshFinish(1);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {//组件通讯消息
            disposeInfoMsg(message);
        }
    }


    public void onDestroy() {
        mNet = null;
        mThread = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean canLoad() {
        return canLoad;
    }
}

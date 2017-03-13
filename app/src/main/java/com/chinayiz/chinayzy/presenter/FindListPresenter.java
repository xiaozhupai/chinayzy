package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.find.FindListFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class FindListPresenter extends BasePresenter<FindListFragment> {
    public List<FindListModel.DataBean> lists;
    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            if (message.getDataType().equals(mView.type)) {
                FindListModel model = (FindListModel) message.getData();
                lists=model.getData();
                mView.adaphter.setData(model.getData());
              mView.pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }
    }


    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {

        }
    }

    public void getData(){
        Net.getNet().getFindBlogByType(mView.type);
    }
}
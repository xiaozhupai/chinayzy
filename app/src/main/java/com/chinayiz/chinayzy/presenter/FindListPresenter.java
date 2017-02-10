package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.util.Log;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.find.FindListFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/1/16.
 */

public class FindListPresenter extends BasePresenter<FindListFragment> {
    @Override
    public void onCreate() {
        new Net().getFindBlogByType(mView.type);
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
                com.orhanobut.logger.Logger.i("FindListPresenter disposeNetMsg");
                FindListModel model = (FindListModel) message.getData();
                mView.adaphter.setData(model.getData());
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
}
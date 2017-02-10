package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.adapter.PagerAdaphter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SearchListFragment;
import com.chinayiz.chinayzy.ui.fragment.SearchResultFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/2/8.
 */

public class SearchResultPresenter extends BasePresenter<SearchResultFragment> {
    public String title;
    @Override
    protected void onCreate() {
        getData();
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
        Logger.i("SearchResultPresenter");
        if (message.getEventType()==EventMessage.NET_EVENT){
         SearchFarmModel model= (SearchFarmModel) message.getData();
            mView.adaphter.setData(model.getData(),1);
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType()== EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);


        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        if (message.getDataType()==SearchPresenter.TITLE){

        }
    }

    public void getData(){
        new Net().getSearchFarm(mView.title,"1","10",mView.index+"");
    }
}

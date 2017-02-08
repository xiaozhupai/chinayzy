package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SearchFragment;
import com.chinayiz.chinayzy.ui.fragment.SearchResultFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindListFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/2/8.
 */

public class SearchResultPresenter extends BasePresenter<SearchResultFragment> {
    private Net net=new Net();
    @Override
    protected void onCreate() {

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
             if (message.getDataType()==Contants.SEARCHFARM){
                 SearchFarmModel model= (SearchFarmModel) message.getData();
                 mView.adaphter.setData(model.getData(),1);
             }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
             if (message.getDataType()== SearchFragment.TITLE){
                String title= (String) message.getData();
                 getData(title);
             }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void getData(String title){
        net.getSearchFarm(title,"0","10");
    }
}

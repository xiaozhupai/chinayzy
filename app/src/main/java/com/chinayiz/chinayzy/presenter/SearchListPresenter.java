package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SearchListFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class SearchListPresenter extends BasePresenter<SearchListFragment> {
    private Net net=Net.getNet();
    private List<SearchFarmModel.DataBean> lists;

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
        Logger.i("SearchListPresenter MAIN");
   if (message.getEventType()==EventMessage.NET_EVENT){
      SearchFarmModel model= (SearchFarmModel) message.getData();
       lists=model.getData();
       mView.adaphter.setData(model.getData(),1);

   }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        Logger.i("SearchListPresenter  BACKGROUND");
        if (message.getEventType()==EventMessage.INFORM_EVENT){


        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void getData(){
        if (lists==null){

        }

    }


}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.HomeFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:21
 * Class NongYe_homePresenter  生态农业首页presenter
 */
public class NongYehomePresenter extends BasePresenter<HomeFragment> {
    private Net mNet =Net.getNet();
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Contants.NY_BANNER:
                mView.mDateList.put(2,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_RECOMMENT:
                mView.mDateList.put(3,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_FEATURE:
                mView.mDateList.put(4,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_EATTHEME:
                mView.mDateList.put(5,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_EATITEM:
                mView.mDateList.put(6,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case NongYeHomeRecylAdapter.CLICK_GOODS://商品点击事件
                mView.openGoodesDetail(message.getData().toString());
                break;
        }
    }
    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case NongYeHomeRecylAdapter.GTE_DATA:
                getData(message.getData().toString());
                break;

        }
    }
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {//网络请求回调消息
            disposeNetMsg(message);
        }

    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {//组件通讯消息
            Logger.i("组件通讯消息:" + message.toString());
            disposeInfoMsg(message);
        }
    }
    /**
     * 获取数据
     */
    public void getData(String api) {
        switch (api) {
            case Contants.NY_BANNER://请求banner图
                mNet.getBanner(Contants.NY_BANNER);
                break;
            case Contants.NY_RECOMMENT://请求精选推荐版块
                mNet.getRecomment();
                break;
            case Contants.NY_FEATURE://请求特色购版块
                mNet.getFeature();
                Logger.i("请求特色购版块");
                break;
            case Contants.NY_EATTHEME://请求爱吃主题
                mNet.getEatTheme();
                Logger.i("请求爱吃主题板块");
                break;
            case Contants.NY_EATITEM://请求爱吃商品
                mNet.getEatItem("1", "10");
                Logger.i("请求爱吃商品");
                break;
        }
    }

    @Override
    public void onCreate() {
    }
    public void onDestroy() {
        mNet = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }


}

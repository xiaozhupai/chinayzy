package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.adapter.NongYe_HomeRecyl_Adapter;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_HomeBanner;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.NongYe_homeFragment;
import com.chinayiz.chinayzy.ui.fragment.SearchFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:21
 * Class NongYe_homePresenter
 */
public class NongYe_homePresenter extends BasePresenter<NongYe_homeFragment> {
    private Net mNet = new Net();
    private Map<String,Boolean> mCount=new HashMap<>();
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Contants.NY_BANNER:
                mView.mDateList.put(1,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_RECOMMENT:
                mView.mDateList.put(2,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_FEATURE:
                mView.mDateList.put(3,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_EATTHEME:
                mView.mDateList.put(4,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Contants.NY_EATITEM:
                mView.mDateList.put(5,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
        }
    }
    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case NongYe_HomeRecyl_Adapter.GTE_DATA:
                getData(message.getData().toString());
                break;
            case NY_HomeBanner.CLICK_SEARCH:
                mView.startFragment(new SearchFragment());
                break;
        }
    }
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {//网络请求回调消息
            Logger.i("网络请求回调消息" + message.toString());
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {//组件通讯消息
            Logger.i("后台线程执行" + message.toString());
            disposeInfoMsg(message);
        }
    }
    /**
     * 获取数据
     */
    public void getData(String api) {
        switch (api) {
            case Contants.NY_BANNER://请求banner图
               if (mCount.containsKey(Contants.NY_BANNER)&&mCount.get(Contants.NY_BANNER)){
                   return;
               }
                mNet.getBanner();
                Logger.i("请求banner图");
                mCount.put(Contants.NY_BANNER,true);
                break;
            case Contants.NY_RECOMMENT://请求精选推荐版块
                if (mCount.containsKey(Contants.NY_RECOMMENT)&&mCount.get(Contants.NY_RECOMMENT)){
                    return;
                }
                mNet.getRecomment();
                Logger.i("请求精选推荐版块");
                mCount.put(Contants.NY_RECOMMENT,true);
                break;
            case Contants.NY_FEATURE://请求特色购版块
                if (mCount.containsKey(Contants.NY_FEATURE)&&mCount.get(Contants.NY_FEATURE)){
                    return;
                }
                mNet.getFeature();
                Logger.i("请求特色购版块");
                mCount.put(Contants.NY_FEATURE,true);
                break;
            case Contants.NY_EATTHEME://请求爱吃主题
                if (mCount.containsKey(Contants.NY_EATTHEME)&&mCount.get(Contants.NY_EATTHEME)){
                    return;
                }
                mNet.getEatTheme();
                mCount.put(Contants.NY_EATTHEME,true);
                break;
            case Contants.NY_EATITEM://请求爱吃商品
                mNet.getEatItem("1", "10");
                Logger.i("请求爱吃商品");
                break;
        }
    }

    @Override
    public void onCreate() {
        getData(Contants.NY_BANNER);
        getData(Contants.NY_RECOMMENT);
    }

    public void onDestroy() {
        mNet = null;
        mCount=null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }


}

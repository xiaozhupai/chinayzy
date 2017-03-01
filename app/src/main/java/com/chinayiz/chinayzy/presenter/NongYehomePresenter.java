package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_HomeBanner;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.HomeFragment;
import com.chinayiz.chinayzy.ui.fragment.SearchFragment;
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
    private CommonRequestUtils mRequestUtils=CommonRequestUtils.getRequestUtils();
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.NY_BANNER:
                mView.mDateList.put(2,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Commons.NY_RECOMMENT:
                mView.mDateList.put(3,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Commons.NY_FEATURE:
                mView.mDateList.put(4,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Commons.NY_EATTHEME:
                mView.mDateList.put(5,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case Commons.NY_EATITEM:
                mView.mDateList.put(6,message.getData());
                mView.mRecylAdapter.notifyDataSetChanged();
                break;
            case NongYeHomeRecylAdapter.CLICK_GOODS://首页商品点击事件
                mView.openGoodesDetail(message.getData().toString());
                break;
            case GoodsDetailGridAdpter.CLICK_GOODS://商品详情页相关商品点击
//                mView.onBack();
//                mView.openGoodesDetail(message.getData().toString());
                break;
            case Commons.ADDSHOPPINGCAR://添加购物车成功
                Logger.i("加入购物车成功了");
                BaseActivity.showToast(mView.getActivity(),"添加购物超成功");
                break;
            case NongYeHomeRecylAdapter.CLICK_MENU://首页菜单点击事件
               mView.openClassify(message.getData().toString());
                break;
        }
    }
    @Override
    public void disposeInfoMsg(EventMessage message) {
        NY_EatItemModel.DataBean data;
        switch (message.getDataType()){
            case NongYeHomeRecylAdapter.GTE_DATA://动态获取数据
                getData(message.getData().toString());
                break;
            case NY_HomeBanner.SEARCH://搜索
            mView.startFragment(new SearchFragment(),"SearchFragment");
                break;
            case Commons.ADD_CAR ://添加购物车
                Logger.i("添加购物车");
                data= (NY_EatItemModel.DataBean) message.getData();
                mRequestUtils.getJoinCart(String.valueOf(data.getShopid()),
                        String.valueOf(data.getGoodsstandardid()),"1");
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
            case Commons.NY_BANNER://请求banner图
                mNet.getBanner(Commons.NY_BANNER);
                break;
            case Commons.NY_RECOMMENT://请求精选推荐版块
                mNet.getRecomment();
                break;
            case Commons.NY_FEATURE://请求特色购版块
                mNet.getFeature();
                Logger.i("请求特色购版块");
                break;
            case Commons.NY_EATTHEME://请求爱吃主题
                mNet.getEatTheme();
                Logger.i("请求爱吃主题板块");
                break;
            case Commons.NY_EATITEM://请求爱吃商品
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

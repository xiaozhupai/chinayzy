package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.BrandModel;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SearchResultFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.widget.SearchPopuwindow;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.  搜索结果
 */

public class SearchResultPresenter extends BasePresenter<SearchResultFragment> {
    public List <SearchFarmModel.DataBean> data;
    public List<BrandModel.DataBean> list_brands;

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
        if (message.getEventType()== EventMessage.NET_EVENT){
            disposeNetMsg(message);
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
        switch (message.getDataType()){
            case Commons.SEARCHFARM:   //搜索结果
                SearchFarmModel model= (SearchFarmModel) message.getData();
                if (mView.page==1){ //下拉刷新
                    if (mView.type==1){
                        data=model.getData();
                        mView.adaphter.setData(model.getData(),mView.type);
                    }else {
                        mView.adaphter2.setData(model.getData(),mView.type);
                    }
//                    mView.refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
                    mView.mSmartRefresh.finishRefresh();

                }else {   //上拉加载
                    data.addAll(model.getData());
                    if (mView.type==1){
                        mView.adaphter.AddData(model.getData(),mView.type);
                    }else {
                        mView.adaphter2.AddData(model.getData(),mView.type);
                    }
//                    mView.refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    mView.mSmartRefresh.finishLoadmore();
                }
                if (model.getData().size()<10){
//                    mView.refresh_view.loadmoreView.setVisibility(View.GONE);
//                    mView.refresh_view.setLoadMoreVisiable(false);

                    mView.mSmartRefresh.setEnableLoadmore(false);

                }else {
//                    mView.refresh_view.loadmoreView.setVisibility(View.VISIBLE);
//                    mView.refresh_view.setLoadMoreVisiable(true);

                    mView.mSmartRefresh.setEnableLoadmore(true);
                }

                if (model.getData().size()==0){
                    mView.ll_none.setVisibility(View.VISIBLE);
                }else {
                    mView.ll_none.setVisibility(View.GONE);
                }
                break;
            case Commons.ADDSHOPPINGCAR:   //加入购物车
//                animation();
//                BaseResponseModel model1= (BaseResponseModel) message.getData();
//                BaseActivity.showToast(mView.getActivity(),model1.getMsg());
                break;
            case Commons.SEARCHMALLGOODS:
                SearchFarmModel model2= (SearchFarmModel) message.getData();
                if (mView.page==1){ //下拉刷新
                    if (mView.type==1){
                        data=model2.getData();
                        mView.adaphter.setData(model2.getData(),mView.type);
                    }else {
                        mView.adaphter2.setData(model2.getData(),mView.type);
                    }
//                    mView.refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
                    mView.mSmartRefresh.finishRefresh();

                }else {   //上拉加载
                    data.addAll(model2.getData());
                    if (mView.type==1){
                        mView.adaphter.AddData(model2.getData(),mView.type);
                    }else {
                        mView.adaphter2.AddData(model2.getData(),mView.type);
                    }
//                    mView.refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    mView.mSmartRefresh.finishLoadmore();
                }
                if (model2.getData().size()<10){
//                    mView.refresh_view.loadmoreView.setVisibility(View.GONE);
//                    mView.refresh_view.setLoadMoreVisiable(false);

                    mView.mSmartRefresh.setEnableLoadmore(false);
                }else {
//                    mView.refresh_view.loadmoreView.setVisibility(View.VISIBLE);
//                    mView.refresh_view.setLoadMoreVisiable(true);
                    mView.mSmartRefresh.setEnableLoadmore(true);
                }

                if (model2.getData().size()==0){
                    mView.ll_none.setVisibility(View.VISIBLE);
                }else {
                    mView.ll_none.setVisibility(View.GONE);
                }
                break;
        }
    }

    //抖动动画
    public void animation(){
        TranslateAnimation animation = new TranslateAnimation(0, -5, 0, 0);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(50);
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);
        mView.mActivity.mIvActionBarCart.startAnimation(animation);
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case SearchResultAdaphter.JOINCART:
                SearchFarmModel.DataBean bean= (SearchFarmModel.DataBean) message.getData();
                CommonRequestUtils.getRequestUtils().getJoinCart(bean.getShopid()+"",bean.getGoodsstandardid()+"","1");
                break;
            case SearchPopuwindow.CALL_BACK:
                StringBuilder sb=new StringBuilder();
          list_brands= (List<BrandModel.DataBean>) message.getData();
                for (int i = 0; i <list_brands.size() ; i++) {
                BrandModel.DataBean beans=list_brands.get(i);
                    if (beans.isChecked()){
                    sb.append(beans.getBrand());
                        sb.append(",");
                    }
                }
                mView.brands=sb.toString();
                getData();
                break;
        }
    }

    public void getData(){
        Logger.i("brands="+mView.brands);
        if (mView.isMail){
            CommonRequestUtils.getRequestUtils().getSearchMallGoods(mView.page+"","10",mView.index+"",mView.isself,mView.credit,mView.brands);
        }else {
            Net.getNet().getSearchFarm(mView.title,mView.page+"","10",mView.index+"",mView.isself,mView.credit,mView.brands);
        }
    }

}

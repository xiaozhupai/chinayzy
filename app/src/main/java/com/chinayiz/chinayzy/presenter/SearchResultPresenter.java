package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.widget.Toast;

import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SearchResultFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.  搜索结果
 */

public class SearchResultPresenter extends BasePresenter<SearchResultFragment> {
    public String title;
    public List <SearchFarmModel.DataBean> data;

    private static final int HOT=2;
    private static final int SALE_DOWN=3;
    private static final int SALE_UP=4;
    private static final int PRICE_DOWN=5;
    private static final int PRICE_UP=6;

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

                if (mView.page==1){
                    data=model.getData();
                    mView.refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
                }else {
                    data.addAll(model.getData());
                    mView.refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
                if (mView.type==1){
                    mView.gd_list.setSelection(mView.mPostion);
                    mView.adaphter.setData(data,mView.type);
                    mView.gd_list.setAdapter(mView.adaphter);
                }else {
                    mView.gd_list.setSelection(mView.mPostion);
                    mView.adaphter2.setData(data,mView.type);
                    mView.gd_list.setAdapter(mView.adaphter2);
                }


                break;
            case Commons.ADDSHOPPINGCAR:   //加入购物车
                BaseResponseModel model1= (BaseResponseModel) message.getData();
                Toast.makeText(mView.getActivity(),model1.getMsg(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        if (message.getDataType()== SearchResultAdaphter.JOINCART){
            SearchFarmModel.DataBean bean= (SearchFarmModel.DataBean) message.getData();
            CommonRequestUtils.getRequestUtils().getJoinCart(bean.getShopid()+"",bean.getGoodsstandardid()+"","1");

        }
    }

    public void getData(){
        Net.getNet().getSearchFarm(mView.title,mView.page+"","10",mView.index+"");
    }

}

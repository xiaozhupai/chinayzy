package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.widget.Toast;

import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SearchResultFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.  搜索结果
 */

public class SearchResultPresenter extends BasePresenter<SearchResultFragment> {
    public String title;
    private List <SearchFarmModel.DataBean> data_hot;
    private List<SearchFarmModel.DataBean> data_sale;
    private List<SearchFarmModel.DataBean> data_price;
    private static final int HOT=1;
    private static final int SALE=2;
    private static final int PRICE=3;

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
            case Contants.SEARCHFARM:   //搜索结果
                SearchFarmModel model= (SearchFarmModel) message.getData();
                mView.adaphter.setData(model.getData(),1);
                mView.adaphter2.setData(model.getData(),2);
                switch (mView.index){
                    case HOT:
                        data_hot=model.getData();
                        break;
                    case SALE:
                        data_sale=model.getData();
                        break;
                    case PRICE:
                        data_price=model.getData();
                        break;
                }
                break;
            case Contants.ADDSHOPPINGCAR:   //加入购物车
                BaseResponseModel model1= (BaseResponseModel) message.getData();
                Toast.makeText(mView.getActivity(),model1.getMsg(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        if (message.getDataType()== SearchResultAdaphter.JOINCART){
            SearchFarmModel.DataBean bean= (SearchFarmModel.DataBean) message.getData();

//           Net.getNet().getJoinCart();
        }
    }

    public void getData(){
        switch (mView.index){
            case HOT: //热卖
                if (data_hot==null){
                     Net.getNet().getSearchFarm(mView.title,"1","10",mView.index+"");
                }
                break;
            case SALE:   //销量
                if (data_sale==null){
                    Net.getNet().getSearchFarm(mView.title,"1","10",mView.index+"");
                }
                break;
            case PRICE: //价格
                if (data_price==null){
                    Net.getNet().getSearchFarm(mView.title,"1","10",mView.index+"");
                }
                break;
        }

    }
}

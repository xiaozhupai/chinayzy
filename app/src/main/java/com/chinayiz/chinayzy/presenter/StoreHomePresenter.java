package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;

import com.chinayiz.chinayzy.adapter.GoodsTypeMeunAdapter;
import com.chinayiz.chinayzy.adapter.StoreHomeAdapter;
import com.chinayiz.chinayzy.adapter.viewHolder.StoreHomeHead;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.StoreInfo;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.ui.fragment.StoreHomeFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/4 10:31
 * Class StorePresenter   商铺首页
 */
public class StoreHomePresenter extends BasePresenter<StoreHomeFragment> implements EventBusCallback {
    public StoreInfoModel mStoreInfoModel;
    private CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    private List<StoreGoodsListModel.DataBean> mDataList = new ArrayList<>();
    private StoreInfo mStoreInfo;

    @Override
    protected void onCreate() {
        mRequestUtils.getStoerInfo(mView.mStoreID);
        mView.mAdapter = new StoreHomeAdapter();
        mView.mAdapter.setOnItemClickListener(mView);
    }
    @Override
    protected void onDestroy() {
        mDataList=null;
        EventBus.getDefault().unregister(this);
        mRequestUtils = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.STORE_HOME: {
                mStoreInfoModel = (StoreInfoModel) message.getData();
                mView.mGoodsTypeMeunAdapter = new GoodsTypeMeunAdapter(mView.getActivity());
                //逆向排序分类数据
                Collections.reverse(mStoreInfoModel.getData().getTypecodelist());
                mView.mGoodsTypeMeunAdapter.setTypecodeList(mStoreInfoModel.getData().getTypecodelist());
                mView.mGoodsTypeMeunAdapter.notifyDataSetChanged();
                if (!mStoreInfoModel.getData().getTypecodelist().isEmpty()){
                    Logger.i("类型列表为空");
                    doFilterGoodsList(0);
                }
                mStoreInfo = new StoreInfo(mStoreInfoModel.getData().getIsself()
                        , mStoreInfoModel.getData().getIsattention()
                        , mStoreInfoModel.getData().getPic()
                        , mStoreInfoModel.getData().getBigicon()
                        , mStoreInfoModel.getData().getSname()
                        , mStoreInfoModel.getData().getIntroduction());
                mView.mAdapter.setData(mDataList, mStoreInfo);
                mView.mAdapter.notifyDataSetChanged();
                mView.mRvStoreHome.setAdapter(mView.mAdapter);
                break;
            }
            case Commons.FORTYPEBY_GOODSS: {
                StoreGoodsListModel model = (StoreGoodsListModel) message.getData();
                mDataList = model.getData();
                mView.mAdapter.setData(mDataList);
                mView.mAdapter.notifyDataSetChanged();
                break;
            }
            //悬浮头视图点击事件
            case StoreHomeHead.CLICK: {
                mView.onClick((View) message.getData());
                break;
            }

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {
            case StoreHomeHead.CHECKED: {//关注商店
                if ((boolean) message.getData()) {
                    doAttentionStore();
                } else {
                    doUnAttentionStore();
                }
                break;
            }

        }
    }

    /**
     * 关注店铺
     */
    public void doAttentionStore() {
        mRequestUtils.doAttentionStore(mView.mStoreID);
    }

    /**
     * 取消关注
     */
    public void doUnAttentionStore() {
        mRequestUtils.doUnAttentionStore(mView.mStoreID);
    }

    /**
     * 过滤商品类型列表
     * @param position 选择类型的角标
     */
    public void doFilterGoodsList(int position) {
        mRequestUtils.getGoodsListByPosition(mView.mStoreID
                , mView.mGoodsTypeMeunAdapter.getTypecodeList().get(position).getTypecode(),"1","16");
    }

}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;

import com.chinayiz.chinayzy.adapter.GoodsTypeMeunAdapter;
import com.chinayiz.chinayzy.adapter.viewHolder.StoreHomeHead;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.StoreInfo;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.common.StoreFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/5 11:16
 * Class StorePresenter1
 */

public class StorePresenter extends BasePresenter<StoreFragment> {
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.STORE_HOME: {
                mView.mStoreInfoModel = (StoreInfoModel) message.getData();
                mView.mGoodsTypeMeunAdapter = new GoodsTypeMeunAdapter(mView.getActivity());
                //逆向排序分类数据
                Collections.reverse(mView.mStoreInfoModel.getData().getTypecodelist());
                mView.mGoodsTypeMeunAdapter.setTypecodeList(mView.mStoreInfoModel.getData().getTypecodelist());
                mView.mGoodsTypeMeunAdapter.notifyDataSetChanged();
                if (!mView.mStoreInfoModel.getData().getTypecodelist().isEmpty()) {
                    mRequestUtils.getGoodsListByPosition(mView.mStoreID
                            , mView.mGoodsTypeMeunAdapter.getTypecodeList().get(0).getTypecode(), "1", "16");
                }
                mView.mStoreInfo = new StoreInfo(mView.mStoreInfoModel.getData().getIsself()
                        , mView.mStoreInfoModel.getData().getIsattention()
                        , mView.mStoreInfoModel.getData().getPic()
                        , mView.mStoreInfoModel.getData().getBigicon()
                        , mView.mStoreInfoModel.getData().getSname()
                        , mView.mStoreInfoModel.getData().getIntroduction());
                //标题设置加粗
                TextPaint tp = mView.mActivity.mTvActionBarTitle.getPaint();
                tp.setFakeBoldText(true);
                mView.mActivity.mTvActionBarTitle.setText(mView.mStoreInfo.getSname());
                mView.mAdapter.setData(mView.mDataList, mView.mStoreInfo);
                mView.mAdapter.notifyDataSetChanged();
                mView.mRvStoreHome.setAdapter(mView.mAdapter);
                break;
            }
            case Commons.FORTYPEBY_GOODSS: {
                mView.mProgress.setVisibility(View.GONE);
                StoreGoodsListModel model = (StoreGoodsListModel) message.getData();
                mView.mDataList = model.getData();
                mView.mAdapter.setData(mView.mDataList);
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
                    mRequestUtils.doAttentionStore(mView.mStoreID);
                } else {
                    mRequestUtils.doUnAttentionStore(mView.mStoreID);
                }
                break;
            }
        }
    }

    @Override
    protected void onCreate() {
        mRequestUtils.getStoerInfo(mView.mStoreID);
    }

    @Override
    protected void onDestroy() {
        mRequestUtils = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (EventMessage.NET_EVENT == message.getEventType()) {
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (EventMessage.INFORM_EVENT == message.getEventType()) {
            disposeInfoMsg(message);
        }
    }
}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;

import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.common.GoodsFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/17 19:21
 * Class GoodsPresenter
 */

public class GoodsPresenter extends BasePresenter<GoodsFragment> {
    public CommonRequestUtils mRequestUtils;
    public GoodsDetailModel model = null;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.GOODS_DETAIL: {//商品详情简要信息
                Logger.i("商品详情简要信息");
                model = (GoodsDetailModel) message.getData();
                mView.storeID = String.valueOf(model.getData().getShopid());
                mView.setGoodsInfo(model);
                break;
            }
            case Commons.COMMENT_LIST: {//评论列表
                mView.mScroll_Group.setVisibility(View.GONE);
                mView.mCommentFragment.setCommentData(message);
                break;
            }
            case Commons.GOODS_RELATED: {//相关商品
                RelatedGoodsModel model = (RelatedGoodsModel) message.getData();
                mView.mGoodsHolder.mAdapter.setData(model);
                mView.mPregress.setVisibility(View.GONE);
                break;
            }

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {
            case GoodsDetailGridAdpter.CLICK_GOODS: {
                String goodsID = message.getData().toString();
                Logger.i("相关商品被点击" + goodsID);
                Skip.toGoodsDetail(mView.getActivity(), goodsID);
                break;
            }
        }
    }

    @Override
    protected void onCreate() {
        mRequestUtils = CommonRequestUtils.getRequestUtils();
        mRequestUtils.getGoodsDetail(mView.goodsID);
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
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(mView.getActivity(), "未知错误，请重试");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }
}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.ui.common.GoodsFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/17 19:21
 * Class GoodsPresenter
 */

public class GoodsPresenter  extends BasePresenter<GoodsFragment>{
    @Override
    public void disposeNetMsg(EventMessage message) {
//        switch (message.getDataType()) {
//            case Commons.GOODS_DETAIL: {//商品详情简要信息
//                Logger.i("商品详情简要信息");
//                mMlMcoySnapPageLayout.setVisibility(View.VISIBLE);
//                mView.isFirst = false;
//                model = (GoodsDetailModel) message.getData();
//                storeID = String.valueOf(model.getData().getShopid());
//                setGoodsInfo(model);
//                break;
//            }
//            case Commons.COMMENT_LIST: {//评论列表
//                mMlMcoySnapPageLayout.setVisibility(View.GONE);
//                mCommentFragment.setCommentData(message);
//                break;
//            }
//            case REFRESH: {//再次请求数据
//                goodsID=message.getData().toString();
//                isRefresh=true;
//                break;
//            }
//            case GoodsDetailGridAdpter.CLICK_GOODS:{
//                goodsID=message.getData().toString();
//                refreshView();
//
//                break;
//            }
//        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

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

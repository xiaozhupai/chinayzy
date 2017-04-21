package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.GoodsSetFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/19 15:14
 * Class GoodsSetPresenter 商品分类code查询结果
 */

public class GoodsSetPresenter extends BasePresenter<GoodsSetFragment> {
    public CommonRequestUtils mRequestUtils;
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.GOODS_RELATED:{
                Logger.i("二级菜单数据返回="+message.getData().toString());
                break;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    protected void onCreate() {
        mRequestUtils=CommonRequestUtils.getRequestUtils();
        mRequestUtils.getRelatedGoods(mView.itemCode,"1","20");
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

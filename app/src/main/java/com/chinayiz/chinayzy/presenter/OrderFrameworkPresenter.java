package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.mine.OrderFrameworkFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.chinayiz.chinayzy.ui.fragment.mine.OrderFragment.GET_DATA;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/15 17:07
 * Class OrderFrameworkPresenter  订单详情
 */

public class OrderFrameworkPresenter extends BasePresenter<OrderFrameworkFragment> {
    public CommonRequestUtils mRequestUtils=CommonRequestUtils.getRequestUtils();
    public int index;
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.ORDER_STATE:{
                OrderListModel mpdel= (OrderListModel) message.getData();
                mView.mFragments.get(index).setOrderListModel(mpdel);
                break;
            }
        }
    }


    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case GET_DATA:{
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doGteOrderList(message.getData().toString(),Integer.parseInt(message.getData().toString()));
                break;
            }
        }
    }

    @Override
    protected void onCreate() {
        mRequestUtils.getImOrder(String.valueOf(mView.orderType));
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

    public void doGteOrderList(String code, int position) {
        index=position;
        mRequestUtils.getImOrder(code);
    }
}

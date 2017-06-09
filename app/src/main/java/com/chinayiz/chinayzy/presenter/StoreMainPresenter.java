package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.StoreHomeTabModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.StoreRequestUtils;
import com.chinayiz.chinayzy.ui.activity.StoreMainActivity;
import com.chinayiz.chinayzy.utils.StrCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/6/5 10:19
 * Class StoreMainPresenter
 */

public class StoreMainPresenter extends BasePresenter<StoreMainActivity> {
    public StoreRequestUtils mRequestUtils =StoreRequestUtils.getRequestUtils();
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.STORE_HOME_TABS:{//主题名
                mView.mTabsModel= (StoreHomeTabModel) message.getData();
                mView.setTabs();
            }
        }
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
        if (StrCallback.RESPONSE_CODE_USER_OUT.equals(message.getDataType())){
            mView.showUserOut();
            return;
        }
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(mView,"未知错误，请重试");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {//组件通讯消息
            disposeInfoMsg(message);
        }
    }
}

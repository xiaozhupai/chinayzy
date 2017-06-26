package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ShareCrowdModel;
import com.chinayiz.chinayzy.ui.fragment.flexible.ActivitySuccessFragment;
import com.chinayiz.chinayzy.widget.ArrayAlertDialog;
import com.chinayiz.chinayzy.widget.ShareDialog2;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/6/21.
 */

public class ActivitySuccessPresenter extends BasePresenter<ActivitySuccessFragment>{
    private  ArrayAlertDialog dialog;
    public static final String  ActivitySuccess_SHARE="ActivitySuccess_SHARE";
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
    switch (message.getDataType()){
        case ActivitySuccess_SHARE:
            Logger.i(ActivitySuccess_SHARE);
            ShareCrowdModel model2= (ShareCrowdModel) message.getData();
            final ShareCrowdModel.DataBean bean= model2.getData();
            ShareDialog2 dialog2=new ShareDialog2(mView.getActivity(),bean.getImage(),bean.getWebpageUrl(),bean.getTitle(),bean.getContent());
            dialog2.show();
            break;
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
}

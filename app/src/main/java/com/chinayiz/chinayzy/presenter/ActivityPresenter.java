package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.ui.fragment.ActivityFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/21 10:04
 * Class ActivityPresenter 生态农业活动presenter
 */
public class ActivityPresenter extends BasePresenter<ActivityFragment> {
    /**
     * 接受到新的Url地址
     */
    public static final String NEW_URL="ActivityPresenter_URL";

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
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
     if (message.getEventType()==EventMessage.INFORM_EVENT){
         disposeInfoMsg(message);
     }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case ActivityResultPresenter.ACTIVITY_REUSLT_BACK:{

                String crowid= (String) message.getData();
                Skip.toActivitySuccess(mView.getActivity(),crowid);
                break;
            }
            case NEW_URL:{
                mView.url=message.getData().toString();
                Logger.i("wbv新地址="+mView.url);
                EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,NewMainPresenter.EVENT_SELECT,"2"));
                break;
            }
        }

    }
}

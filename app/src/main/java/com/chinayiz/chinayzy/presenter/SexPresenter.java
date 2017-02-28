package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.SexFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/2/23.
 */

public class SexPresenter extends BasePresenter<SexFragment> {
    private UserNet net=UserNet.getNet();
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
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        if (message.getDataType()==UserNet.SEX){
          BaseResponseModel model= (BaseResponseModel) message.getData();
            BaseActivity.showToast(mView.getActivity(),model.getMsg());
            if (model.getCode().equals("100")){
                String param;
                if (mView.iv_sex_man.getVisibility()==View.VISIBLE){
                    param="男";
                }else {
                    param="女";
                }
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.SEX,param));
                mView.activity.onBackPressed();
            }
        }


    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit(){
        if (mView.iv_sex_man.getVisibility()== View.VISIBLE || mView.iv_sex_woman.getVisibility()==View.VISIBLE){
            String param;
            if (mView.iv_sex_man.getVisibility()==View.VISIBLE){
                param="0";
            }else {
                param="1";
            }
            net.getEditerUser(UserNet.SEX,param);
        }else {
            BaseActivity.showToast(mView.getActivity(),"请选择您的性别");
        }
    }
}

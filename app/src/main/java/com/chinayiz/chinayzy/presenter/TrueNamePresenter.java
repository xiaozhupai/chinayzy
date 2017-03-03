package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.UserModel;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.TrueNameFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2017/2/23.
 */

public class TrueNamePresenter extends BasePresenter<TrueNameFragment> {
    public UserNet net=UserNet.getNet();
    public       String truename;
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
        if (message.getDataType()==UserNet.TRUENAME){
            BaseResponseModel model= (BaseResponseModel) message.getData();
            BaseActivity.showToast(mView.getActivity(),model.getMsg());
            if (model.getCode().equals("100")){
                mView.mineActivity.onBackPressed();
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.TRUENAME,truename));
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit() {
        // validate
        truename =mView. et_username.getText().toString().trim();
        if (TextUtils.isEmpty(truename)) {
            Toast.makeText(mView.getActivity(), "真实姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        net.getEditerUser(UserNet.TRUENAME,truename);
        // TODO validate success, do something
    }
}

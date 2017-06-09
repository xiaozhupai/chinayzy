package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AuthModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.TrueNameFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        if (message.getDataType()== Commons.AUTHIDCARD){
            AuthModel model= (AuthModel) message.getData();
            BaseActivity.showToast(mView.getActivity(),model.getMsg());
            if (model.getCode().equals("100")){
                UserSeeion.setSys_auth(mView.getActivity(),model.getData().getSys_auth());
                Skip.toDeposit(mView.getActivity());
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit() {
        // validate
        String truename =mView. mEtRegisterTruename.getText().toString().trim();
        if (TextUtils.isEmpty(truename)) {
            Toast.makeText(mView.getActivity(), "请输入真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String card = mView.mEtRegisterCard.getText().toString().trim();
        if (TextUtils.isEmpty(card)) {
            Toast.makeText(mView.getActivity(), "请输入身份证", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mView.mCivCheck.isCheck){
            Toast.makeText(mView.getActivity(), "请", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
      UserNet.getNet().getAuthidcard(card,truename);

    }
}

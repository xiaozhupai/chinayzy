package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.CardFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**身份证
 * Created by Administrator on 2017/1/21.
 */

public class CardPresenter extends BasePresenter<CardFragment> {
    public UserNet net=UserNet.getNet();
    public String card;
    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

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
        if (message.getDataType()==UserNet.IDCARD){
            BaseResponseModel model= (BaseResponseModel) message.getData();
            BaseActivity.showToast(mView.getActivity(),model.getMsg());
            if (model.getCode().equals("100")){
                mView.mineActivity.OnBackPressed();
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.IDCARD,card));
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit() {
        // validate
        card = mView.et_card.getText().toString().trim();
        if (TextUtils.isEmpty(card)) {
            Toast.makeText(mView.getActivity(), "身份证不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

     Pattern pattern=Pattern.compile("^\\d{15}|\\d{18}$");
       Matcher matcher=pattern.matcher(card);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的身份证");
            return;
        }

        net.getEditerUser(UserNet.IDCARD,card);
        // TODO validate success, do something


    }
}

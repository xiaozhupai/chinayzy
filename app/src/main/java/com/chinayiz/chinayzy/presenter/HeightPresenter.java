package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.UserModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.HeightFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**身高
 * Created by Administrator on 2017/1/21.
 */

public class HeightPresenter extends BasePresenter<HeightFragment> {
    private UserNet net=UserNet.getNet();
    public String height;
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
      if (message.getDataType()==UserNet.HEIGHT){
                BaseResponseModel model= (BaseResponseModel) message.getData();
          BaseActivity.showToast(mView.getActivity(),model.getMsg());
          if (model.getCode().equals("100")){
              mView.activity.onBackPressed();
              EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.HEIGHT,height));
          }
      }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit() {
        // validate
         height =mView. et_height.getText().toString().trim();
        if (TextUtils.isEmpty(height)) {
            Toast.makeText(mView.getActivity(), "身高不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        net.getEditerUser(UserNet.HEIGHT,height);
        // TODO validate success, do something
    }
}

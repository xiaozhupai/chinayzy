package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.EmailFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.ProcessingInstruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  邮箱地址
 * Created by Administrator on 2017/1/21.
 */

public class EmailPresenter extends BasePresenter<EmailFragment> {
    private UserNet net=UserNet.getNet();
    private String email;
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
        if (message.getDataType()== UserNet.EMAIL){
             BaseResponseModel model= (BaseResponseModel) message.getData();
            Toast.makeText(mView.getActivity(),model.getMsg(),Toast.LENGTH_SHORT).show();
            if (model.getCode().equals("100")){
                mView.mineActivity.onBackPressed();
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.EMAIL,email));
            }

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit() {
        // validate
         email = mView.et_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(mView.getActivity(), "email不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern pattern=Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        Matcher matcher=pattern.matcher(email);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的Emial");
            return;
        }
         net.getEditerUser(UserNet.EMAIL,email);
        // TODO validate success, do something
    }

}

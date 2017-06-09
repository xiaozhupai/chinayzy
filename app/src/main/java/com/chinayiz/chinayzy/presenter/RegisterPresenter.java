package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.RegisterModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.Login.LoginNet;
import com.chinayiz.chinayzy.ui.fragment.register.RegisterFragment;
import com.chinayiz.chinayzy.utils.TimeUntils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.chinayiz.chinayzy.presenter.RegisterInfoPresenter.REGISTERINFO_BACK;

/**注册
 * Created by Administrator on 2017/3/23.
 */

public class RegisterPresenter extends BasePresenter<RegisterFragment> implements Handler.Callback {
    public int num;
    private static final int MSG_NUM=5;
    public Handler handler;
    private String phone;


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
         if (message.getDataType()== Commons.REGISTER){
                 RegisterModel model= (RegisterModel) message.getData();
                 BaseActivity.showToast(mView.getActivity(),model.getMsg());
                 if (model.getCode().equals("100")){
                     UserSeeion.setPhone(mView.getActivity(),phone);
                     mView.mActivity.finish();
                     EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,REGISTERINFO_BACK,model.getData().getUserid()+""));
                 }
         }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    protected void onCreate() {
        handler=new Handler(this);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    //提交
    public void submit() {
//        // validate
         phone = mView.et_register_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mView.getActivity(), "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }



        String message = mView.et_register_message.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(mView.getActivity(), "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = mView.et_register_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mView.getActivity(), "请输入6-12位密码", Toast.LENGTH_SHORT).show();
            return;
        }
//        String truename = mView.et_register_truename.getText().toString().trim();
//        if (TextUtils.isEmpty(truename)) {
//            Toast.makeText(mView.getActivity(), "请输入真实姓名", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        String card = mView.et_register_card.getText().toString().trim();
//        if (TextUtils.isEmpty(card)) {
//            Toast.makeText(mView.getActivity(), "请输入身份证号码", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Pattern pattern_card= Pattern.compile("^\\d{15}|\\d{18}$");
//        Matcher matcher_card=pattern_card.matcher(card);
//        if (!matcher_card.find()){
//            BaseActivity.showToast(mView.getActivity(),"请输入正确的身份证号码");
//            return;
//        }
        if (!mView.civ_check.isCheck){
            Toast.makeText(mView.getActivity(), "请确认用户协议", Toast.LENGTH_SHORT).show();
            return;
        }

     String recommendcard=mView.et_register_recommendcard.getText().toString().trim();
            LoginNet.getLoginNet().toRegister(phone,message,password,recommendcard);

    }

    /**
     * 发送验证码
     */
    public void sendMessage(){
        String phone =mView.  et_register_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mView.getActivity(), "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern pattern=Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0-9]|18[0|1|2|3|5|6|7|8|9]|19[0-9])\\d{8}$");
        Matcher matcher=pattern.matcher(phone);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的手机号码");
            return;
        }

        if (!mView.tv_register_sendmessage.isClickable()){
            return;
        }
        new LoginNet().toSendMessage(phone);
        TimeUntils timeUntils=new TimeUntils(handler);
        timeUntils.RunTimer();


    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what==MSG_NUM){
            num=msg.arg1;
            if (num==0){
                mView.tv_register_sendmessage.setText("发送验证码");
                mView.tv_register_sendmessage.setClickable(true);
            }else {
                mView.tv_register_sendmessage.setText(num+"后重新获取");
                mView.tv_register_sendmessage.setClickable(false);
            }
        }
        return false;

    }
}

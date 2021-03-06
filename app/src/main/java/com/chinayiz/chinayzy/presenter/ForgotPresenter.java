package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.Login.LoginNet;
import com.chinayiz.chinayzy.ui.activity.ForgotActivity;
import com.chinayiz.chinayzy.utils.TimeUntils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ForgotPresenter extends BasePresenter<ForgotActivity> implements Handler.Callback {
    public int num;
    private static final int MSG_NUM=5;
    public Handler handler;
    @Override
    public void onCreate() {
        handler=new Handler(this);
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
        if (message.getEventType() == EventMessage.NET_EVENT) {//网络请求回调消息
            Logger.i("网络请求回调消息" + message.toString());
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.BACKPWD:  //找回密码
                BaseResponseModel model= (BaseResponseModel) message.getData();
                if (model.getCode().equals("100")){
                    mView.finish();
                }
                Toast.makeText(mView,model.getMsg(),Toast.LENGTH_LONG).show();
                break;
            case Commons.SRYCODE:

                break;
        }

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what==MSG_NUM){
            num=msg.arg1;
            if (num==0){
                mView.tv_forgot_sendmessage.setText("发送验证码");
                mView.tv_forgot_sendmessage.setClickable(true);
            }else {
                mView.tv_forgot_sendmessage.setText(num+"后重新获取");
                mView.tv_forgot_sendmessage.setClickable(false);
            }
        }
        return false;
    }

    /**
     * 发送验证码
     */
    public void sendMessage(){
        String phone =mView.  et_forgot_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mView, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern pattern=Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0-9]|18[0|1|2|3|5|6|7|8|9]|19[0-9])\\d{8}$");
        Matcher matcher=pattern.matcher(phone);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的手机号码");
            return;
        }

        if (!mView.tv_forgot_sendmessage.isClickable()){
            return;
        }
        new LoginNet().toSendMessage(phone);
        TimeUntils timeUntils=new TimeUntils(handler);
        timeUntils.RunTimer();


    }

    /**
     * 忘记密码提交
     */
    public void submit() {
        // validate
        String phone =mView.  et_forgot_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mView, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String message =mView.  et_forgot_input_message.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(mView, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = mView. et_forgot_input_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mView, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length()<6){
            Toast.makeText(mView, "请输入6-12位密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String newpassword =mView.  et_forgot_input_newpassword.getText().toString().trim();
        if (TextUtils.isEmpty(newpassword)) {
            Toast.makeText(mView, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(newpassword)){
            Toast.makeText(mView, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern pattern=Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher=pattern.matcher(phone);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的手机号码");
            return;
        }
        new LoginNet().toBackpwd(phone,newpassword,message);
    }
}

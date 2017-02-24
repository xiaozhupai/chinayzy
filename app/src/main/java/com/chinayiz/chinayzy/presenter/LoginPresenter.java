package com.chinayiz.chinayzy.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.LoginModel;
import com.chinayiz.chinayzy.entity.response.ThirdModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.Login.LoginNet;
import com.chinayiz.chinayzy.ui.activity.ForgotActivity;
import com.chinayiz.chinayzy.ui.activity.LoginActivity;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.utils.TimeUntils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**登录
 * Created by Administrator on 2017/1/3.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements PlatformActionListener,Handler.Callback {
    private static final int MSG_SMSSDK_CALLBACK = 1;
    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR = 3;
    private static final int MSG_AUTH_COMPLETE = 4;
    private static final int MSG_NUM=5;
    private Handler handler;
    private int num;
    public String logintype;

    @Override
    public void onCreate() {
        handler = new Handler(this);
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
            case Commons.LOGIN:  //登录
                LoginModel model= (LoginModel) message.getData();
                if (model.getCode().equals("100")){
                  int userid=model.getData().getUserid();
                    SaveData(userid);
                }
                Toast.makeText(mView,model.getMsg(),Toast.LENGTH_LONG).show();
                break;
            case Commons.REGISTER:  //注册
                BaseResponseModel model2= (BaseResponseModel) message.getData();
                Toast.makeText(mView,model2.getMsg(),Toast.LENGTH_LONG).show();
                break;
            case Commons.THIRD:   //第三方登录
              ThirdModel model3= (ThirdModel) message.getData();
                 ThirdModel.DataBean dataBean=model3.getData();
                SaveData(dataBean.getUserid());
                break;
            case Commons.SRYCODE:   //发送验证码

                break;
        }

    }

    /**
     *  本地存储
     * @param userid   用户登录成功后的ID
     */
    private void SaveData(int userid){
        SharedPreferences sharedPreferences = mView.getSharedPreferences("login", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("userid",userid);
        editor.commit();//提交修改
        APP.sUserid=userid+"";
        mView.finish();
        Intent intent=new Intent(mView, MineActivity.class);
        mView.startActivity(intent);
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    /**
     * 左边视图显示
     */
    public void showLeft(){
        mView.ivlogo.setVisibility(View.VISIBLE);
        mView. lv_login.setVisibility(View.VISIBLE);
        mView.  lv_register.setVisibility(View.GONE);
        mView. tv_left_login.setTextColor(Color.WHITE);
        mView.   tv_right_register.setTextColor(Color.BLACK);
        mView.  v_left_line.setVisibility(View.VISIBLE);
        mView.  v_right_line.setVisibility(View.GONE);
    }
    /**
     * 右边视图显示
     */
    public void showRight(){
        mView.   lv_register.setVisibility(View.VISIBLE);
        mView.   ivlogo.setVisibility(View.INVISIBLE);
        mView. lv_login.setVisibility(View.GONE);
        mView. tv_left_login.setTextColor(Color.BLACK);
        mView. tv_right_register.setTextColor(Color.WHITE);
        mView. v_left_line.setVisibility(View.GONE);
        mView.  v_right_line.setVisibility(View.VISIBLE);
    }

    public void sendMessage(){
        String phone = mView.et_register_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(mView, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mView.tv_register_sendmessage.isClickable()){
            return;
        }
        TimeUntils timeUntils=new TimeUntils(handler);
        timeUntils.RunTimer();
        new LoginNet().toSendMessage(phone);
    }

    /**
     * 跳转忘记密码
     */
    public void toForgot(){
        Intent intent=new Intent(mView,ForgotActivity.class);
        mView.startActivity(intent);
    }

    public void toQQ(){
        //qq
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        authorize(qq);
        logintype="1";
    }

    public void toWechat(){
        //微信
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        authorize(wechat);
        logintype="2";
    }

    public void toWeibo(){
        //新浪微博
        Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
        authorize(sina);
        logintype="3";
    }

    /**
     * 登录
     */
    public void login() {
        String phone = mView.ev_login_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)  ) {
            Toast.makeText(mView, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = mView.et_login_input_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mView, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern pattern=Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher=pattern.matcher(phone);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的手机号码");
            return;
        }
        new LoginNet().toLogin(phone,password);
    }

    /**
     * 注册
     */
    public void register() {
        String phone =mView. et_register_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(mView, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = mView.et_register_input_message.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(mView, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password =mView. et_register_input_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mView, "请输入6-12位密码", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern pattern=Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher=pattern.matcher(phone);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的手机号码");
            return;
        }
       new LoginNet().toRegister(phone,password,message);
    }

    //执行授权,获取用户信息
    //文档：http://wiki.mob.com/Android_%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E8%B5%84%E6%96%99
    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    /**
     * 回调完成
     * @param platform
     * @param action
     * @param res
     */
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            msg.obj = new Object[]{platform, res};
            handler.sendMessage(msg);
        }
    }

    /**
     * 回调错误
     * @param platform
     * @param action
     * @param t
     */
    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        t.printStackTrace();
    }


    /**
     * 回调取消
     * @param platform
     * @param action
     */
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_AUTH_CANCEL: {
                //取消授权
                Toast.makeText(mView, "取消授权", Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_ERROR: {
                //授权失败
                Toast.makeText(mView, "授权失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_COMPLETE: {
                //授权成功
                Toast.makeText(mView, "授权成功", Toast.LENGTH_SHORT).show();
                Object[] objs = (Object[]) msg.obj;
                Platform platform = (Platform) objs[0];
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                PlatformDb platDB = platform.getDb();//获取数平台数据DB
                System.out.print( platDB.getToken());
                System.out.print( platDB.getUserIcon());
                System.out.print(   platDB.getUserId());
                System.out.print(  platDB.getUserName());
                String sex;
                if (platDB.getUserGender().equals("m")){
                    sex="0";
                }else {
                    sex="1";
                }
                new LoginNet().toThird(platDB.getUserId(),logintype,platDB.getUserIcon(),platDB.getUserName(),sex);
            }
            break;
            case MSG_NUM:{
                num=msg.arg1;
                if (num==0){
                    mView. tv_register_sendmessage.setText("发送验证码");
                    mView. tv_register_sendmessage.setClickable(true);
                }else {
                    mView. tv_register_sendmessage.setText(num+"后重新获取");
                    mView.   tv_register_sendmessage.setClickable(false);
                }
            }
            break;
        }
        return false;
    }
}

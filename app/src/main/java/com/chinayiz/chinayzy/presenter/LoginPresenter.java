package com.chinayiz.chinayzy.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.LoginModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.Login.LoginNet;
import com.chinayiz.chinayzy.ui.activity.ForgotActivity;
import com.chinayiz.chinayzy.ui.activity.LoginActivity;
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
    private String phone;
    private int num;
    public String logintype;
    private String registerphone;
    public static final int RESULT_DETAIL=1;

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
        if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.LOGIN:  //登录
                LoginModel model= (LoginModel) message.getData();
                if (model.getCode().equals("100")){
                  int userid=model.getData().getUserid();
                    String isMember=model.getData().getIsmember();
                    SaveData(userid,isMember);
                }
                Toast.makeText(mView,model.getMsg(),Toast.LENGTH_LONG).show();
                break;

//            case Commons.THIRD:   //第三方登录
//              ThirdModel model3= (ThirdModel) message.getData();
//                 ThirdModel.DataBean dataBean=model3.getData();
//                SaveData(dataBean.getUserid());
//                break;
        }
    }

    /**
     *  本地存储
     * @param userid   用户登录成功后的ID
     */
    private void SaveData(int userid,String isMember){
        SharedPreferences sharedPreferences = mView.getSharedPreferences("login", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("userid",userid);
        editor.putString("ismember",isMember);
        editor.putString("phone",phone);
        editor.commit();//提交修改
        APP.sUserid=userid+"";
        mView.finish();
        if (isMember.equals("0")){
            Skip.toDeposit(mView.getActivity());
        }
//       Intent intent_data=mView.getIntent();
//        if (intent_data.getStringExtra("FindDetailFragment")!=null){
//            EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, FindDetailFragment.FIND_DETAIL,""));
//        }else {
////            Intent intent=new Intent(mView, MineActivity.class);
////            mView.startActivity(intent);
//            mView.finish();
//        }

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case RegisterInfoPresenter.REGISTERINFO_BACK:
                Skip.toDeposit(mView.getActivity());
                break;
        }
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
        phone = mView.mEvLoginInputPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)  ) {
            Toast.makeText(mView, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = mView.mEtLoginInputPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mView, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern pattern=Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0-9]|18[0|1|2|3|5|6|7|8|9]|19[0-9])\\d{8}$");
        Matcher matcher=pattern.matcher(phone);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的手机号码");
            return;
        }
        new LoginNet().toLogin(phone,password);
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

        }
        return false;
    }
}

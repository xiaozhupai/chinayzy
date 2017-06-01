package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.UserFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/2/27.
 */

public class UserNamePresenter extends BasePresenter<UserFragment> {
    public String policatical;
    public String education;
    public String height;
    public String weight;
    public String username;
    public String birthday;
    public String marriage;
    public String usualplace;
    private String specificaddress;
    public static final String BACK="UserNamePresenter";
    public UserNet net=UserNet.getNet();
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
            disposeNetMsg(message);
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
    }

    @Override
    public void disposeNetMsg(EventMessage message) {

       if (message.getDataType()==AddAddressPresenter.PICKVIEW){
           specificaddress= (String) message.getData();
           String a[]= specificaddress.split(",");
           mView.et_usualplace.setText(a[0]+a[1]+a[2]);
       }else {
           BaseResponseModel model= (BaseResponseModel) message.getData();
           BaseActivity.showToast(mView.getActivity(),model.getMsg());
           if (model.getCode().equals("100")){
               mView.mActivity.onBackPressed();
               EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,BACK,""));
           }
       }

//        switch (message.getDataType()){
//            case UserNet.NICKNAME:
//                if (model.getCode().equals("100")){
//                    mView.mActivity.onBackPressed();
//                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.NICKNAME,username));
//                }
//                break;
//            case UserNet.SEX:
//                if (model.getCode().equals("100")){
//                    String param;
//                    if (mView.iv_sex_man.getVisibility()==View.VISIBLE){
//                        param="男";
//                    }else {
//                        param="女";
//                    }
//                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.SEX,param));
//                    mView.mActivity.onBackPressed();
//                }
//                break;
//            case UserNet.HEIGHT:
//                if (model.getCode().equals("100")){
//                    mView.mActivity.onBackPressed();
//                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.HEIGHT,height));
//                }
//                break;
//            case UserNet.WEIGHT:
//                if (model.getCode().equals("100")){
//                    mView.mActivity.onBackPressed();
//                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.WEIGHT,weight));
//                }
//                break;
//            case UserNet.BIRTHDAY:
//                if (model.getCode().equals("100")){
//                    mView.mActivity.onBackPressed();
//                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.BIRTHDAY,weight));
//                }
//                break;
//            case UserNet.EDUCATIONAL:
//                if (model.getCode().equals("100")){
//                    mView.mActivity.onBackPressed();
//                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,UserNet.EDUCATIONAL,weight));
//                }
//                break;
//            case UserNet.POLITICS:
//
//                break;
//            case UserNet.USUALPLACE:
//
//                break;
//            case UserNet.ISMARRIAGE:
//
//                break;
//
//        }

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
       switch (message.getDataType()){

       }
    }

    public void submit() {
        switch (mView.index){
            case 0:
                // validate
                username = mView.et_username.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(mView.getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (username.length()>18 || username.length()<1){
//                    Toast.makeText(mView.getActivity(), "用户名1-18位数", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                net.getEditerUser(UserNet.NICKNAME,username);
                break;
            case 1:
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
                break;
            case 2:
                birthday=mView.et_birthday.getText().toString().trim();
                if (TextUtils.isEmpty(birthday)) {
                    Toast.makeText(mView.getActivity(), "出生日期不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
          net.getEditerUser(UserNet.BIRTHDAY,birthday);
                break;
            case 3:
                usualplace=mView.et_usualplace.getText().toString().trim();
                if (TextUtils.isEmpty(usualplace)) {
                    Toast.makeText(mView.getActivity(), "常驻地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                net.getEditerUser(UserNet.USUALPLACE,usualplace);
                break;
            case 4:
                marriage=mView.et_marriage.getText().toString().trim();
                if (TextUtils.isEmpty(marriage)) {
                    Toast.makeText(mView.getActivity(), "婚姻状况不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                net.getEditerUser(UserNet.ISMARRIAGE,mView.id);
                break;
            case 5:
                height=mView.et_height.getText().toString().trim();
                if (TextUtils.isEmpty(height)) {
                    Toast.makeText(mView.getActivity(), "身高不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                net.getEditerUser(UserNet.HEIGHT,height);
                break;
            case 6:
                weight=mView.et_weight.getText().toString().trim();
                if (TextUtils.isEmpty(weight)) {
                    Toast.makeText(mView.getActivity(), "体重不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                net.getEditerUser(UserNet.WEIGHT,weight);
                break;
            case 7:
                education=mView.et_education.getText().toString().trim();
                if (TextUtils.isEmpty(education)) {
                    Toast.makeText(mView.getActivity(), "学历不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                net.getEditerUser(UserNet.EDUCATIONAL,mView.id);
                break;
            case 8:
                policatical=mView.et_policatical.getText().toString().trim();
                if (TextUtils.isEmpty(policatical)) {
                    Toast.makeText(mView.getActivity(), "政治面貌不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                net.getEditerUser(UserNet.POLITICS,mView.id);
                break;
        }


    }
}

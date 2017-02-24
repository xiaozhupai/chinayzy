package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.PersonalModel;

import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**  个人中心
 * Created by Administrator on 2017/1/9.
 */

public class MinePresenter extends BasePresenter<MineActivity> {
    private UserNet net=UserNet.getNet();
    @Override
    public void onCreate() {
        net.getPersonalCenter();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    public void toSuggest(){
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
        switch (message.getDataType()){
            case Commons.GETPERSONALCENTER:   //个人中心接口
                PersonalModel model= (PersonalModel) message.getData();
                PersonalModel.DataBean dataBean=model.getData();
                mView.tv_user_username.setText(dataBean.getNickname());
                Glide.with(mView.getActivity()).load(dataBean.getPic()).into(mView.iv_mine_user_logo);
                if (!TextUtils.isEmpty(dataBean.getSex())){
                    if (dataBean.getSex().equals("0")){
                        mView.iv_mine_user_sex.setImageResource(R.mipmap.icon_man_sex);
                    }else {
                        mView.iv_mine_user_sex.setImageResource(R.mipmap.icon_woman_sex);
                    }
                }

                if (TextUtils.isEmpty(dataBean.getNickname())){
                    mView.iv_username_right.setVisibility(View.GONE);
                }else {
                    mView.iv_username_right.setVisibility(View.VISIBLE);
                }
                if (dataBean.getWaittakecount()>0){
                    mView.tv_wait_pay_count.setVisibility(View.VISIBLE);
                    mView.tv_wait_pay_count.setText(dataBean.getWaittakecount());
                }
                if (dataBean.getWaitpaycount()>0){
                    mView.tv_wait_goods_count.setVisibility(View.VISIBLE);
                    mView.tv_wait_goods_count.setText(dataBean.getWaitpaycount());
                }
                if (dataBean.getWaitdelivercount()>0){
                    mView.tv_wait_accept_goods_count.setVisibility(View.VISIBLE);
                    mView.tv_wait_accept_goods_count.setText(dataBean.getWaitdelivercount());
                }
                if (dataBean.getAftercount()>0){
                    mView.tv_after_sale_count.setVisibility(View.VISIBLE);
                    mView.tv_after_sale_count.setText(dataBean.getAftercount());
                }
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
    }



}

package com.chinayiz.chinayzy.presenter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.PersonalModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.activity.MineFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**  个人中心
 * Created by Administrator on 2017/1/9.
 */

public class MinePresenter extends BasePresenter<MineFragment> {
    private UserNet net=UserNet.getNet();
    public static final String UPDATEMINE="UPDATEMINE";
    @Override
    public void onCreate() {

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
        if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.GETPERSONALCENTER:   //个人中心接口
                mView.pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                PersonalModel model = (PersonalModel) message.getData();
                PersonalModel.DataBean dataBean = model.getData();
                if (!TextUtils.isEmpty(dataBean.getNickname())){
                    mView.tv_user_username.setText(dataBean.getNickname());
                }

                mView.tv_has_user.setText(String.format("已有%d个用户", dataBean.getRelationshipcount()));
                if (!TextUtils.isEmpty(dataBean.getPic())) {
                    Glide.with(mView.getActivity()).load(dataBean.getPic()).into(mView.iv_mine_user_logo);
                }

                if (TextUtils.isEmpty(dataBean.getSex())) {
                    mView.iv_mine_user_sex.setImageResource(R.mipmap.icon_man_sex);
                } else {
                    if (dataBean.getSex().equals("0")) {
                        mView.iv_mine_user_sex.setImageResource(R.mipmap.icon_man_sex);
                    } else {
                        mView.iv_mine_user_sex.setImageResource(R.mipmap.icon_woman_sex);
                    }


                    Drawable nav_up = mView.getResources().getDrawable(R.mipmap.back_arrow_white);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    if (TextUtils.isEmpty(dataBean.getNickname())) {
                        mView.tv_user_username.setCompoundDrawables(null, null, null, null);
                    } else {
                        mView.tv_user_username.setCompoundDrawables(null, null, nav_up, null);
                    }
                    if (dataBean.getWaittakecount() > 0) {  //待收货
                        mView.tv_wait_accept_goods_count.setVisibility(View.VISIBLE);
                        mView.tv_wait_accept_goods_count.setText(dataBean.getWaittakecount() + "");
                    }
                    if (dataBean.getWaitpaycount() > 0) {   //待付款
                        mView.tv_wait_pay_count.setVisibility(View.VISIBLE);
                        mView.tv_wait_pay_count.setText(dataBean.getWaitpaycount() + "");
                    }
                    if (dataBean.getWaitdelivercount() > 0) {  //待发货
                        mView.tv_wait_goods_count.setVisibility(View.VISIBLE);
                        mView.tv_wait_goods_count.setText(dataBean.getWaitdelivercount() + "");
                    }
                    if (dataBean.getAftercount() > 0) {   //售后
                        mView.tv_after_sale_count.setVisibility(View.VISIBLE);
                        mView.tv_after_sale_count.setText(dataBean.getAftercount() + "");
                    }
                    if (!TextUtils.isEmpty(dataBean.getPid())) {
                        mView.tv_recommend.setText(dataBean.getPid());
                    }
                    mView.pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    break;
                }
        }
    }


    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case UPDATEMINE:
                getData();
                break;
        }
    }

    public void getData(){
        net.getPersonalCenter();
    }



}

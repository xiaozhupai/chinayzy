package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ActivityDetailModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.flexible.ActivityDetailFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ActivityDetailPresenter extends BasePresenter<ActivityDetailFragment> {
    public   ActivityDetailModel.DataBean bean;
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
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
            case Commons.WINNERDETAIL:   //获奖详情
                ActivityDetailModel model1= (ActivityDetailModel) message.getData();
                if (model1.getCode().equals("100")){
                     bean=model1.getData();
                    Glide.with(mView.getActivity()).load(bean.getIcon()).into(mView.iv_pic);
                    mView.tv_title.setText(bean.getGname());
                    mView.tv_param1.setText("总需人数:"+bean.getAllcount());
                    mView.tv_param2.setText("邀请人数:"+bean.getInvitecount());
                    String phone="获奖号码:"+"<font color='#ff3951'> "+bean.getPhone()+"</font>";
                    mView.tv_param3.setText(Html.fromHtml(phone));
                    mView.tv_param4.setText("揭晓时间:"+bean.getWinnertime());
                }
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    protected void onCreate() {
        getData();
    }

    private void getData() {
        CommonRequestUtils.getRequestUtils().getActivityDetail(mView.crowdfid);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}

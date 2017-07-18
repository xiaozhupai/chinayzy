package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ShareVipModel;
import com.chinayiz.chinayzy.entity.response.ActivityMainModel;
import com.chinayiz.chinayzy.entity.response.RecommendCodeModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;
import com.chinayiz.chinayzy.ui.fragment.ActivityFragment;
import com.chinayiz.chinayzy.ui.fragment.WebPowerFragment;
import com.chinayiz.chinayzy.utils.StrCallback;
import com.chinayiz.chinayzy.widget.MainActivityDialog;
import com.chinayiz.chinayzy.widget.ShareDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/3/4.
 */

public class CommonPresenter extends BasePresenter<CommonActivity> {
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public ShareDialog mShareDialog;
    private long time = 0;

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
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (StrCallback.RESPONSE_CODE_USER_OUT.equals(message.getDataType())) {
            mView.showUserOut();
            return;
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (EventMessage.INFORM_EVENT == message.getEventType()) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.RECOMMEND_INFO: {
                Logger.i("CommonPresenter 设置分享内容");
                //推荐好友信息
                RecommendCodeModel model = (RecommendCodeModel) message.getData();
                //设置分享内容
                if (mShareDialog != null) {
                    if (mShareDialog.isShowing()) {
                    } else {
                        mShareDialog.show();
                    }
                } else {
                    mShareDialog = new ShareDialog(mView, model.getData().getImage(),
                            model.getData().getWebpageUrl(), model.getData().getTitle(),
                            model.getData().getContent());
                }
                break;
            }
            case WebPowerFragment.SHARE: { //分享点击
                mRequestUtils.getRecommendInfo();
                Logger.i("CommonPresenter 分享点击");
                break;
            }

            case ActivityFragment.SHARE_VIP: { //设置分享内容
                ShareVipModel shareVipModel = (ShareVipModel) message.getData();
                mShareDialog = new ShareDialog(mView, shareVipModel.getIcon(),
                        shareVipModel.getUrl(), shareVipModel.getTheme(),
                        shareVipModel.getIntroduce());
                mShareDialog.show();

                break;
            }

            case Commons.OPENWINNER: {//显示活动dilog
                ActivityMainModel model4 = (ActivityMainModel) message.getData();
                if (model4.getData().getCrowdfid() != null) {
                    MainActivityDialog dialog = new MainActivityDialog(mView.getActivity(), model4.getData().getGname(), model4.getData().getCrowdfid());
                    dialog.show();
                }
                break;
            }
        }
    }
}

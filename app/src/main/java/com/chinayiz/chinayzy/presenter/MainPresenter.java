package com.chinayiz.chinayzy.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.MainActivity;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.autoUpdate.UpdateService;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AppUpdataModel;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.RecommendCodeModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.ui.fragment.WebPowerFragment;
import com.chinayiz.chinayzy.widget.ShareDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/10 14:06
 * Class MainPresenter 主页persenter
 */
public class MainPresenter extends BasePresenter<MainActivity> {
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public AppUpdataModel.DataBean info;
    private Intent intent;
    public ShareDialog mShareDialog;
    public boolean isLoad;
    public String apkPath;
    private Net mNet = Net.getNet();
    private Activity messageData;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.MAIN_BANNER: {
                mView.setData((NY_BannerModel) message.getData());
                break;
            }
            case Commons.UPDATA: {
                isLoad = mView.getSharedPreferences("update", Context.MODE_PRIVATE).getBoolean("isLoad", false);
                apkPath = mView.getSharedPreferences("update", Context.MODE_PRIVATE).getString("apkPath", "-1");
                AppUpdataModel model = (AppUpdataModel) message.getData();
                info = model.getData();
                mView.dowloadUrl = info.getUrl();
                switch (info.getNeedupdate()) {
                    case "1": {//需要更新
                        mView.updataVersion(1);
                        break;
                    }
                    case "2": {//强制更新
                        mView.updataVersion(2);
                        break;
                    }
                    case "0": {//不需要更新
                        break;
                    }
                }
            }
            break;
            case UpdateService.UPDATA_APK_FINISH: {//下载完成
                isLoad = mView.getSharedPreferences("update", Context.MODE_PRIVATE).getBoolean("isLoad", false);
                apkPath = mView.getSharedPreferences("update", Context.MODE_PRIVATE).getString("apkPath", "-1");
                mView.showProgerss(-10);
            }
            case UpdateService.UPDATA_APK_ON: {//下载中
                int progers = (int) message.getData();
                mView.showProgerss(progers);
            }
            break;
            case Commons.RECOMMEND_INFO: //推荐好友信息
                RecommendCodeModel model = (RecommendCodeModel) message.getData();
                //设置分享内容
                mShareDialog = new ShareDialog(messageData, model.getData().getImage(),
                        model.getData().getWebpageUrl(), model.getData().getTitle(),
                        model.getData().getContent());

                mShareDialog.show();
                break;
            case WebPowerFragment.SHARE://分享点击
                messageData = (Activity) message.getData();
                mRequestUtils.getRecommendInfo();
                break;
        }
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
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(mView.getActivity(), "未知错误，请重试");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void onCreate() {
        mNet.getBanner(Commons.MAIN_BANNER);
    }

    @Override
    public void onDestroy() {
        mShareDialog = null;
        intent = null;
        mNet = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 启动农业板块
     */
    public void doStartNongye() {
        intent = new Intent(mView, NongYeMainActivity.class);
        mView.startActivity(intent);
    }

    /**
     * 启动个人中心
     */
    public void doStartMine() {
        if (UserSeeion.isLogin(mView.getActivity())) {
            if (UserSeeion.isMember(mView.getActivity())) {
                Skip.toMine(mView);
            }
        }
    }

}

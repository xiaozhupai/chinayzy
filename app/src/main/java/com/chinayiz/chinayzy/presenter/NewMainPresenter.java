package com.chinayiz.chinayzy.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.NewMainActivity;
import com.chinayiz.chinayzy.autoUpdate.UpdateService;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ShareVipModel;
import com.chinayiz.chinayzy.entity.response.ActivityMainModel;
import com.chinayiz.chinayzy.entity.response.AppUpdataModel;
import com.chinayiz.chinayzy.entity.response.BasedataModel;
import com.chinayiz.chinayzy.entity.response.RecommendCodeModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.entity.response.ShoppingCarCountModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.Login.LoginNet;
import com.chinayiz.chinayzy.ui.fragment.ActivityFragment;
import com.chinayiz.chinayzy.ui.fragment.WebPowerFragment;
import com.chinayiz.chinayzy.utils.StrCallback;
import com.chinayiz.chinayzy.widget.MainActivityDialog;
import com.chinayiz.chinayzy.widget.ShareDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/8 17:12
 * Class NewMainPresenter 新应用首页
 */
public class NewMainPresenter extends BasePresenter<NewMainActivity> {
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public AppUpdataModel.DataBean info;
    public ShareDialog mShareDialog;
    private Activity messageData;
    public boolean isLoad;
    public String apkPath,isMember,sys_auth,isresearch;
    public BasedataModel model;
    public int count;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.UPDATA: {
                isLoad = false;
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
            case SettingPresenter.LOGOUT:
                UserSeeion.isLogin(mView.getActivity());
                mView.mRadioButton.setChecked(true);
                break;
            case ActivityFragment.SHARE_VIP:
                ShareVipModel shareVipModel= (ShareVipModel) message.getData();
                //设置分享内容
                mShareDialog = new ShareDialog(mView, shareVipModel.getIcon(),
                        shareVipModel.getUrl(), shareVipModel.getTheme(),
                        shareVipModel.getIntroduce());
                mShareDialog.show();

                break;
            case Commons.OPENWINNER:
              ActivityMainModel model4= (ActivityMainModel) message.getData();
                if (model4.getData().getCrowdfid()!=null){
                    MainActivityDialog dialog=new MainActivityDialog(mView.getActivity(),model4.getData().getGname(),model4.getData().getCrowdfid());
                    dialog.show();
                }
                break;
            case Commons.SHOPPINGCARTCOUNT://获取购物车数量
                ShoppingCarCountModel model5= (ShoppingCarCountModel) message.getData();
                count= model5.getData();
                mView.getCount(count);
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case LoginPresenter.GET_AWARD:
                CommonRequestUtils.getRequestUtils().getActivityMain();
                break;
            case Commons.BASEDATA: //更新用户基础数据
                model= (BasedataModel) message.getData();
                if (model.getCode().equals("100")){
                    isMember=model.getData().getIsmember();
                    sys_auth=model.getData().getSys_auth();
                    isresearch=model.getData().getIsresearch();
                    Update();
                }
                break;
        }
    }

    /**
     * 更新用户基础数据
     */
    private void Update() {
        SharedPreferences sharedPreferences = mView.getSharedPreferences("login", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("ismember",isMember);
        editor.putString("sys_auth",sys_auth);
        editor.putString("isresearch",isresearch);
        editor.commit();//提交修改
    }

    @Override
    protected void onCreate() {
        mRequestUtils.getCanUpdata(APP.Version);
        mRequestUtils.getActivityMain();
        mRequestUtils.getShoppingCarCount();
        LoginNet.getLoginNet().toGetBasedata();
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState){}

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (StrCallback.RESPONSE_CODE_USER_OUT.equals(message.getDataType())){
            mView.showUserOut();
            return;
        }
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(mView,"未知错误，请重试");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {//组件通讯消息
            disposeInfoMsg(message);
        }
    }

}

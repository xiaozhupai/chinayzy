package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SettingFragment;
import com.chinayiz.chinayzy.widget.MessageDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**  设置
 * Created by Administrator on 2017/1/10.
 */

public class SettingPresenter extends BasePresenter<SettingFragment> {
    private MessageDialog dialog;
    public static final String LOGOUT="LOGOUT";
    @Override
    public void onCreate() {
        mView.tv_cache_data.setText("(有"+ APP.cacheUtil.getCacheSize(mView.getActivity())+"缓存)");
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

    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    /**
     * 退出登录
     */

    public void logout(){
        if (dialog==null){
            dialog=new MessageDialog(mView.getActivity());
            dialog.setMessage("你确定要退出吗?");
            dialog.setButton1("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.setButton2("确定", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    APP.sUserid="0";
                    UserSeeion.logout(mView.getActivity());
                  APP.exit();
                    EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,LOGOUT,""));
                }
            });
        }
        dialog.show();

    }

    /**
     * 清除缓存
     */

    public void clearCache(){
        APP.cacheUtil.clearImageAllCache(mView.getActivity());
        mView.tv_cache_data.setText("(有0.0Byte缓存)");
        Toast.makeText(mView.getActivity(),"清除成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 去个人资料
     */
    public void toPerson(){
       mView.mActivity.addFragment(new PersonFragment());
    }

    /**
     * 关于我们
     */
    public void toAboutUs() {
        Skip.toWebPage(mView.getActivity(), Commons.API+Commons.ABOUTUS,"关于我们");
    }

    public void tofxcode() {
        Skip.toWebPage(mView.getActivity(), Commons.API+Commons.FXCODE+"?type=android","分享二维码");
    }
}

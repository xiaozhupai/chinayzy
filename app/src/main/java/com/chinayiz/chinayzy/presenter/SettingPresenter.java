package com.chinayiz.chinayzy.presenter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SettingFragment;
import com.chinayiz.chinayzy.utils.CaCheUntil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**  设置
 * Created by Administrator on 2017/1/10.
 */

public class SettingPresenter extends BasePresenter<SettingFragment> {
    @Override
    public void onCreate() {
        mView.tv_cache_data.setText("(有"+CaCheUntil.getCacheSize()+"M缓存)");
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
        UserSeeion.logout(mView.getActivity());
        mView.getActivity().finish();
    }

    /**
     * 清除缓存
     */

    public void clearCache(){
        if (CaCheUntil.clearCacheDiskSelf()){
            mView.tv_cache_data.setText("(有0M缓存)");
            Toast.makeText(mView.getActivity(),"清除成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mView.getActivity(),"清除失败",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 去个人资料
     */
    public void toPerson(){
        MineActivity activity= (MineActivity) mView.getActivity();
        activity.addFragment(new PersonFragment());
    }
}

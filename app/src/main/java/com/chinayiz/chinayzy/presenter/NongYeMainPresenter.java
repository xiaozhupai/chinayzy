package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.MainActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 10:57
 * Class MainPresenter
 */
public class NongYeMainPresenter extends BasePresenter<NongYeMainActivity> {
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    public void onCreate() {
        mView.getActivity();

    }

    @Override
    public void onDestroy() {
        mView.getFragmentManager().removeOnBackStackChangedListener(mView);
        mView.mHomeFragment = null;
        mView.mFindFragment = null;
        mView.mActivityFragment = null;
        mView.mCartFragment = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 启动商城首页
     */
    public void doStart_MainPager() {
        Intent intent = new Intent(mView, MainActivity.class);
        mView.startActivity(intent);
    }

}

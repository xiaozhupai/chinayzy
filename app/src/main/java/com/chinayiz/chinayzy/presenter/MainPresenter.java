package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.MainActivity;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/10 14:06
 * Class MainPresenter
 */
public class MainPresenter extends BasePresenter<MainActivity> {
    private Intent intent;

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

    }

    @Override
    public void onDestroy() {
        intent=null;
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
        intent = new Intent(mView, MineActivity.class);
        mView.startActivity(intent);
    }

}

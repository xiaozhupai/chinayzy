package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.MainActivity;
import com.chinayiz.chinayzy.NongYe_MainActivity;
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

    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
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

    @Override
    public void onCreate() {

    }
    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 启动农业板块
     */
    public void doStart_Nongye(){
        Intent intent=new Intent(mView, NongYe_MainActivity.class);
        mView.startActivity(intent);
    }
}

package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.chinayiz.chinayzy.MainActivity;
import com.chinayiz.chinayzy.NongYe_MainActivity;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.base.FragmentAlternate;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 10:57
 * Class MainPresenter
 */
public class NongYe_MainPresenter extends BasePresenter <NongYe_MainActivity> implements FragmentAlternate{
    private Net mNet=new Net();
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

    @Override
    public void onCreate() {
        mView.getActivity();

    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 启动商城首页
     */
    public void doStart_MainPager(){
        Intent intent=new Intent(mView, MainActivity.class);
        mView.startActivity(intent);
    }

    @Override
    public void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if(mView.mCurrentFragment==fragment){
            Logger.i("两个对象相等");
            return;
        }
            Logger.i("两个对象不相等");
        if (!fragment.isAdded()){//判断是否添加到FragmentManager
            if(mView.mCurrentFragment==null){
                transaction.add(R.id.fl_nongye,fragment).commit();
            }else {
                transaction.hide(mView.mCurrentFragment).add(R.id.fl_nongye,fragment).commit();
            }
        }else {
            transaction.hide(mView.mCurrentFragment).show(fragment).commit();
        }
        mView.mCurrentFragment= (BaseFragment) fragment;
    }
}

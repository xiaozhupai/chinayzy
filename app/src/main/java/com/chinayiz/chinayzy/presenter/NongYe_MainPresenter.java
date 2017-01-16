package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.MainActivity;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.ui.activity.NongYe_MainActivity;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 10:57
 * Class MainPresenter
 */
public class NongYe_MainPresenter extends BasePresenter <NongYe_MainActivity>{

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
}

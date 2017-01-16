package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.chinayiz.chinayzy.MainActivity;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.ui.activity.NongYe_MainActivity;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/10 14:06
 * Class MainPresenter
 */
public class MainPresenter extends BasePresenter<MainActivity> {

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

package com.chinayiz.chinayzy.base;

import android.os.Bundle;

import com.chinayiz.chinayzy.net.callback.EventBusCallback;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 9:56
 * Class BasePresenter Presenter基类
 */
public abstract class BasePresenter<T extends BaseView> implements EventBusCallback {
    protected T mView;

    /**
     * 绑定View
     */
    public void onAttch(T view) {
        this.mView = view;
    }

    /**
     * 做初始化的操作,需要在View初始化完成之后才能调用
     * presenter进行初始化.
     */
    public void onStart(){
        EventBus.getDefault().register(this);
        onCreate();
    }
    /**
     *  在这里结束异步操作,反注册Event bus
     */
    public void onStop(){
        EventBus.getDefault().unregister(this);
        onDestroy();
    }
    /**
     * 做初始化的操作,需要在V的视图初始化完成之后才能调用
     * presenter进行初始化.
     */
    protected abstract void onCreate();
    /**
     * 在这里结束异步操作
     */
    protected abstract void onDestroy();
    /**
     * 在View销毁的时候调用,解除绑定
     */
    public void onDetach() {
        mView = null;
    }
    /**
     * 被回收掉时保存数据
     */
    public abstract void onSaveInstanceState(Bundle outState);

}

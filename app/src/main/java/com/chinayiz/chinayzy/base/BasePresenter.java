package com.chinayiz.chinayzy.base;

import android.os.Bundle;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 9:56
 * Class BasePresenter
 */

public abstract class BasePresenter<T extends BaseView> {
    protected T mView;

    /**
     * 绑定View
     */
    public void onAttch(T view) {
        this.mView = view;
    }
    /**
     * 做初始化的操作,需要在V的视图初始化完成之后才能调用
     * presenter进行初始化.
     */
    public abstract void onCreate();
    /**
     * 在这里结束异步操作
     */
    public void onDestroy(){

    }
    /**
     * 在View销毁的时候调用,解除绑定
     */
    public void onDetach() {
        mView = null;
    }
    /**
     * 容易被回收掉时保存数据
     */
    public abstract void onSaveInstanceState(Bundle outState);
}

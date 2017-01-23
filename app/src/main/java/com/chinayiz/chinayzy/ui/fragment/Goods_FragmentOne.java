package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.Goods_PresenterOne;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/16 17:01
 * Class Goods_FragmentOne
 */
public class Goods_FragmentOne extends BaseFragment<Goods_PresenterOne> {

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public Goods_PresenterOne initPresenter() {
        return new Goods_PresenterOne();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.Goods_PresenterTwo;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/16 17:02
 * Class Goods_FragmentTwo
 */
public class Goods_FragmentTwo extends BaseFragment<Goods_PresenterTwo> {

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
    public Goods_PresenterTwo initPresenter() {
        return new Goods_PresenterTwo();
    }


    @Override
    public void isNightMode(boolean isNight) {

    }
}

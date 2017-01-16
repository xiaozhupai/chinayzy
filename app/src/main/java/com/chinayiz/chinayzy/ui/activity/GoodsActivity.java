package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.GoodsPresenter;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/13 9:59
 * Class GoodsActivity 商品详情页 activitty
 */
public class GoodsActivity extends BaseActivity<GoodsPresenter>{

    @Override
    protected GoodsPresenter initPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}

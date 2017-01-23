package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.GoodsPresenter;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/16 13:53
 * Class GoodsActivity 商品详情 activity
 */
public class GoodsActivity extends BaseActivity<GoodsPresenter> {

    @Override
    protected GoodsPresenter initPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
    setContentView(R.layout.activity_goods);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}

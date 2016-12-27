package com.chinayiz.chinayzy;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }


}

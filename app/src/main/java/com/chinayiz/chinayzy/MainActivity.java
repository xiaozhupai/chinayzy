package com.chinayiz.chinayzy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener {

    private ConvenientBanner mBannerMainPager;
    private LinearLayout mBtnNongYe;
    private LinearLayout mBtnCityWide;
    private LinearLayout mBtnStore;
    private LinearLayout mBtnIm;
    private LinearLayout mBtnLvYou;
    private LinearLayout mBtnZhongChou;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
//        setStatuBarColor(MainActivity.this, Color.rgb(218, 22, 47));
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void initView() {
        mBannerMainPager = (ConvenientBanner) findViewById(R.id.banner_MainPager);
        mBtnNongYe = (LinearLayout) findViewById(R.id.btn_NongYe);
        mBtnCityWide = (LinearLayout) findViewById(R.id.btn_CityWide);
        mBtnStore = (LinearLayout) findViewById(R.id.btn_Store);
        mBtnIm = (LinearLayout) findViewById(R.id.btn_im);
        mBtnLvYou = (LinearLayout) findViewById(R.id.btn_LvYou);
        mBtnZhongChou = (LinearLayout) findViewById(R.id.btn_ZhongChou);

        mBtnNongYe.setOnClickListener(this);
        mBtnCityWide.setOnClickListener(this);
        mBtnStore.setOnClickListener(this);
        mBtnIm.setOnClickListener(this);
        mBtnLvYou.setOnClickListener(this);
        mBtnZhongChou.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_NongYe://农业
                mPresenter.doStart_Nongye();
                showToast(this, "农业");
                break;
            case R.id.btn_CityWide://同城
                showToast(this, "同城");
                break;
            case R.id.btn_Store://商城
                showToast(this, "商城");
                break;
            case R.id.btn_im://个人中心
                showToast(this, "个人中心");
                mPresenter.doStart_Person();
                break;
            case R.id.btn_LvYou://旅游
                showToast(this, "旅游");
                break;
            case R.id.btn_ZhongChou://众筹
                showToast(this, "众筹");
                break;
        }
    }
}

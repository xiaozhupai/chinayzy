package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NongYe_MainPager_Adapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.NongYe_MainPresenter;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindFragment;
import com.chinayiz.chinayzy.views.MainViewPager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/4 14:59
 * Class NongYe_MainActivity  生态农业activity
 */

public class NongYe_MainActivity extends BaseActivity<NongYe_MainPresenter> implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private List<Fragment> mFragments = new ArrayList<>();
    private ImageView mIvBackButton;
    private TextView mTvActionBarTitle;
    private ImageView mIvMoreButton;
    private RadioGroup mRgNongyeMenu;
    private NongYe_MainPager_Adapter mPager_adapter;
    private FindFragment mFindFragment;
    private ShopCartFragment mShopCartFragment;
    private FragmentTransaction transaction;
    private FragmentManager  fragmentManager;


    //    private List
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected NongYe_MainPresenter initPresenter() {
        return new NongYe_MainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
//        setStatuBarColor(NongYe_MainActivity.this, Color.rgb(109, 180, 48));
        setContentView(R.layout.nongye_activity_main);
        fragmentManager=getSupportFragmentManager();
        initView();
    }

    private void initView() {
        mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_action_bar_title);
        TextPaint paint = mTvActionBarTitle.getPaint();
        paint.setFakeBoldText(true);
        mTvActionBarTitle.setText("生态农业");
        mIvMoreButton = (ImageView) findViewById(R.id.iv_more_button);

        mRgNongyeMenu = (RadioGroup) findViewById(R.id.rg_nongye_menu);
        mRgNongyeMenu.setOnCheckedChangeListener(this);
        mIvBackButton.setOnClickListener(this);
        mIvMoreButton.setOnClickListener(this);
//        mPager_adapter=new NongYe_MainPager_Adapter(getSupportFragmentManager(),mFragments);
//        mFragments.add(new NongYe_homeFragment());
//        mVpgerNongyePager.setAdapter(mPager_adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button://返回首页
                mPresenter.doStart_MainPager();
                break;
            case R.id.iv_more_button://更多
                Logger.i("更多");
                break;
        }
    }

    /**
     * 隐藏所有Fragment
     *
     * @param transaction transaction
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (mFindFragment != null) {
            transaction.hide(mFindFragment);
            Logger.i("隐藏find");
        }
        if (mShopCartFragment!=null){
            transaction.hide(mShopCartFragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (i) {
            case R.id.rb_nongye_home://首页
                mTvActionBarTitle.setText("首页");
                Logger.i("首页");
                break;
            case R.id.rb_nongye_find://发现
                Logger.i("发现");
                mTvActionBarTitle.setText("发现");
                if (mFindFragment==null){
                    mFindFragment=new FindFragment();
                    transaction.add(R.id.fl_nongye,mFindFragment);
                }else {
                    transaction.show(mFindFragment);
                }
                break;
            case R.id.rb_nongye_activi://活动
                mTvActionBarTitle.setText("活动");
                Logger.i("活动");
                break;
            case R.id.rb_nongye_cart://购物车
                Logger.i("购物车");
                mTvActionBarTitle.setText("购物车");
                if (mShopCartFragment==null){
                    mShopCartFragment=new ShopCartFragment();
                    transaction.add(R.id.fl_nongye,mShopCartFragment);
                }else {
                    transaction.show(mShopCartFragment);
                }
                break;
        }
      transaction.commitAllowingStateLoss();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}

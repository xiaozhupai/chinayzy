package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NyMainPagerAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.presenter.NongYeMainPresenter;
import com.chinayiz.chinayzy.ui.fragment.ActivityFragment;
import com.chinayiz.chinayzy.ui.fragment.HomeFragment;
import com.chinayiz.chinayzy.ui.fragment.WebFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindFragment;
import com.chinayiz.chinayzy.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/4 14:59
 * Class NongYe_MainActivity   生态农业MainActivity
 */

public class NongYeMainActivity extends BaseActivity<NongYeMainPresenter> implements
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    /**
     * 生态农业底部导航
     */
    protected RadioGroup mRgNongyeMenu;
    /**
     * 适配器
     */
    private NyMainPagerAdapter mPagerAdapter;
    /**
     * 禁止左右滑动的viewPager
     */
    private NoScrollViewPager mViewPager;
    private List<Fragment> mFragments;
    private RadioButton mRadioButton;
    private ActivityFragment mActivityFragment;
    private ShopCartFragment mShopCartFragment;
    private int commitID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APP.register(this);
    }


    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        mWebFragment = new WebFragment();
        setStatuBarColor(this, Color.rgb(255, 255, 255));
        setContentView(R.layout.activity_main_nongye);
        initView();
    }

    private void initView() {
        mFragments = new ArrayList<>(4);
        mFragments.add(HomeFragment.getInstance());
        mFragments.add(FindFragment.getInstance());
        mActivityFragment=ActivityFragment.getInstance();
        mFragments.add(mActivityFragment);
        mShopCartFragment = ShopCartFragment.getInstance();
        mFragments.add(mShopCartFragment);

        initActionBar();
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_nyHome);
        //设置缓存其他页面
        mViewPager.setOffscreenPageLimit(3);
        mPagerAdapter = new NyMainPagerAdapter(getFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);

        mRgNongyeMenu = (RadioGroup) findViewById(R.id.rg_nongye_menu);
        mRadioButton = (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nongye_home);
        mRgNongyeMenu.setOnCheckedChangeListener(this);
        //默认选中农业首页
        mRadioButton.setChecked(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button://返回首页
                mPresenter.doStart_MainPager();
                finish();
                break;
            case R.id.iv_more_button://更多
                showToast(this, "更多");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i != R.id.rb_nongye_cart) {
            mIvActionBarMore.setVisibility(View.VISIBLE);
            mCbActionBarEdit.setVisibility(View.GONE);
        } else {
            mIvActionBarMore.setVisibility(View.GONE);
            mCbActionBarEdit.setVisibility(View.VISIBLE);
        }
        switch (i) {
            case R.id.rb_nongye_home://首页
                commitID=0;
                mViewPager.setCurrentItem(0);
                mTvActionBarTitle.setText("首页");
//                mIvBackButton.setOnClickListener(this);
                break;
            case R.id.rb_nongye_find://发现
                commitID=1;
                mViewPager.setCurrentItem(1);
                mTvActionBarTitle.setText("发现");
//                mIvBackButton.setOnClickListener(this);
                break;
            case R.id.rb_nongye_activi://活动
                commitID=2;
                mViewPager.setCurrentItem(2);
                mTvActionBarTitle.setText("活动");
//                mIvBackButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mActivityFragment.webCanback();
//                    }
//                });
                break;
            case R.id.rb_nongye_cart://购物车
                commitID=3;
//                mIvBackButton.setOnClickListener(this);
                if (UserSeeion.isLogin(this)) {
                    mViewPager.setCurrentItem(3);
                    mTvActionBarTitle.setText("购物车");
                }else {
                    showToast(this,"请登录");
                    mRadioButton.setChecked(true);
                }
                break;
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (mActivityFragment.wv_view!=null&&commitID==2){//判断当前是否在活动页面
//            if (keyCode == KeyEvent.KEYCODE_BACK && mActivityFragment.wv_view.canGoBack()) {
//                mActivityFragment.wv_view.goBack();// 返回前一个页面
//                return true;
//            }
//            return super.onKeyDown(keyCode, event);
//        }else {
//            return super.onKeyDown(keyCode, event);
//        }
//    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected NongYeMainPresenter initPresenter() {
        return new NongYeMainPresenter();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mShopCartFragment.onCheckedChanged(buttonView,isChecked);
    }

}

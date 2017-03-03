package com.chinayiz.chinayzy.ui.activity;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.FragmentAlternate;
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.presenter.NongYeMainPresenter;
import com.chinayiz.chinayzy.ui.fragment.ActivityFragment;
import com.chinayiz.chinayzy.ui.fragment.GoodsFragment;
import com.chinayiz.chinayzy.ui.fragment.HomeFragment;
import com.chinayiz.chinayzy.ui.fragment.WebFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;


/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/4 14:59
 * Class NongYe_MainActivity   生态农业MainActivity
 */

public class NongYeMainActivity extends BaseActivity<NongYeMainPresenter> implements
        View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        FragmentAlternate, FragmentManager.OnBackStackChangedListener {
    /**
     * 生态农业底部导航
     */
    protected RadioGroup mRgNongyeMenu;

    /**
     * 隐藏ActionBar
     */
    public final static int HIDE_ACTIONBAR = 0;
    /**
     * 隐藏ActionBar和Navigtion
     */
    public final static int HIDE_ALL = 1;
    /**
     * 隐藏Navigtion
     */
    public final static int HIDE_NAVIGTION = 3;

    /**
     * 显示ActionBar
     */
    public final static int SHOW_ACTIONBAR = 4;
    /**
     * 显示ActionBar和Navigtion
     */
    public final static int SHOW_ALL = 5;
    /**
     * 显示Navigtion
     */
    public final static int SHOW_NAVIGTION = 6;
    public final static String NYMAIN_ACTIONBAR="NongYeMainActivity_change";
    public BaseFragment mCurrentFragment = null;
    public HomeFragment mHomeFragment = null;
    public FindFragment mFindFragment = null;
    public ActivityFragment mActivityFragment = null;
    public ShopCartFragment mCartFragment = null;
    public int frgmtCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoodsFragment = new GoodsFragment();
    }


    @Override
    protected NongYeMainPresenter initPresenter() {
        return new NongYeMainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        mGoodsFragment=new GoodsFragment();
        mWebFragment=new WebFragment();
        setStatuBarColor(NongYeMainActivity.this, Color.rgb(255, 255, 255));
        setContentView(R.layout.nongye_activity_main);
        getFragmentManager().addOnBackStackChangedListener(this);
        initView();
    }

    private void initView() {
        initActionBar();
        mRgNongyeMenu = (RadioGroup) findViewById(R.id.rg_nongye_menu);
        RadioButton radioButton = (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nongye_home);
        mRgNongyeMenu.setOnCheckedChangeListener(this);
        //默认选中农业首页
        radioButton.setChecked(true);
    }

    @Override
    public void onBackStackChanged() {
        if (getFragmentManager().getBackStackEntryCount()==3||getFragmentManager().getBackStackEntryCount()>2){
            frgmtCount=getFragmentManager().getBackStackEntryCount();
        }
        if (getFragmentManager().getBackStackEntryCount()>1&&getFragmentManager().getBackStackEntryCount()%2==0){
            setAcctionBar(HIDE_ALL);
        }else if (getFragmentManager().getBackStackEntryCount()==0){
            setAcctionBar(SHOW_ALL);
        }
        if (frgmtCount==3){
            if (getFragmentManager().getBackStackEntryCount()==1) {
                setAcctionBar(SHOW_ALL);
                getFragmentManager().popBackStack();
                frgmtCount=1;
            }
        }
        Logger.i("NYmian栈变化="+getFragmentManager().getBackStackEntryCount() );
    }

    /**
     * 隐藏/显示 actionbar和底部导航栏
     */
    public void setAcctionBar(int code) {
        switch (code){
            case HIDE_ALL:{
                mActionBar.setVisibility(View.GONE);
                mRgNongyeMenu.setVisibility(View.GONE);
                break;
            }
            case HIDE_ACTIONBAR:{
                mActionBar.setVisibility(View.GONE);
                break;
            }
            case HIDE_NAVIGTION:{
                mRgNongyeMenu.setVisibility(View.GONE);
                break;
            }
            case SHOW_ALL:{
                mActionBar.setVisibility(View.VISIBLE);
                mRgNongyeMenu.setVisibility(View.VISIBLE);
                break;
            }
            case SHOW_ACTIONBAR:{
                mActionBar.setVisibility(View.VISIBLE);
                break;
            }
            case SHOW_NAVIGTION:{
                mRgNongyeMenu.setVisibility(View.VISIBLE);
                break;
            }
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button://返回首页
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    finish();
                    mPresenter.doStart_MainPager();
                } else {
                    onBackPressed();
                }
                break;
            case R.id.iv_more_button://更多
                showToast(this, "更多");
                break;
            case R.id.iv_shopcart://购物车
                if (getFragmentManager().getBackStackEntryCount()>0){
                    for (int i=getFragmentManager().getBackStackEntryCount();i!=0;i--){
                        getFragmentManager().popBackStack();
                    }
                }
                setAcctionBar(SHOW_ALL);
                showFragment(3);
                EventBus.getDefault()
                        .post(new EventMessage(BaseMessage
                                .NET_EVENT,NYMAIN_ACTIONBAR,
                                new ActionBarControlModel(1,0,0,1,"购物车")));
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_nongye_home://首页
                showFragment(0);
                break;
            case R.id.rb_nongye_find://发现
                showFragment(1);
                break;
            case R.id.rb_nongye_activi://活动
                showFragment(2);
                break;
            case R.id.rb_nongye_cart://购物车
                showFragment(3);
                break;
        }
    }

    /**
     * @param index Tab页角标
     */
    private void showFragment(int index) {
        switch (index) {
            case 0: //显示首页
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
                Logger.i("启动首页");
                addOrShowFragment(getFragmentManager().beginTransaction(), mHomeFragment);
                break;
            case 1://显示发现
                if (mFindFragment == null) {
                    mFindFragment = new FindFragment();
                }
                addOrShowFragment(getFragmentManager().beginTransaction(), mFindFragment);
                Logger.i("启动发现");
                break;
            case 2://显示活动
                if (mActivityFragment == null) {
                    mActivityFragment = new ActivityFragment();
                }
                addOrShowFragment(getFragmentManager().beginTransaction(), mActivityFragment);
                Logger.i("启动活动");
                break;
            case 3://显示购物车
                if (mCartFragment == null) {
                    mCartFragment = new ShopCartFragment(0);
                }
                addOrShowFragment(getFragmentManager().beginTransaction(), mCartFragment);
                Logger.i("启动活动");
                break;
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (mCurrentFragment == fragment) {//判断是否与当前显示的为同一个fragment
            Logger.i("是同一个=" + fragment.isHidden());
            return;
        }
        Logger.i("不是同一个=" + fragment.isHidden());
        if (!fragment.isAdded()) {//判断是否已添加到FragmentManager
            Logger.i("未添加");
            if (mCurrentFragment == null) {
                transaction.add(R.id.fl_nongye, fragment).commit();
            } else {
                transaction.remove(mCurrentFragment).add(R.id.fl_nongye, fragment).commit();
            }
        } else {
            if (getFragmentManager().getBackStackEntryCount() != 0) {
                for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
                    getFragmentManager().popBackStack();
                }
            }
            transaction.remove(mCurrentFragment).show(fragment).commit();
        }
        mCurrentFragment = (BaseFragment) fragment;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    /**
     *  AcctionBar 编辑按钮监听回调
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            buttonView.setText("完成");
            Logger.i("完成");
        } else {
            buttonView.setText("编辑");
            Logger.i("编辑");
        }
    }
}

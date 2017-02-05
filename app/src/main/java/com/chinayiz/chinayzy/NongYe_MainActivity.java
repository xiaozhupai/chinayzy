package com.chinayiz.chinayzy;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.NongYe_MainPresenter;
import com.chinayiz.chinayzy.ui.fragment.NongYe_homeFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindFragment;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/4 14:59
 * Class NongYe_MainActivity   生态农业MainActivity
 */

public class NongYe_MainActivity extends BaseActivity<NongYe_MainPresenter> implements
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private TextView mTvActionBarTitle;
    private ImageView mIvMoreButton;
    private NongYe_homeFragment mHomeFragment;
    private FindFragment mFindFragment;
    public BaseFragment mCurrentFragment;

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
        setStatuBarColor(NongYe_MainActivity.this, Color.rgb(255, 255, 255));
        setContentView(R.layout.nongye_activity_main);
        initView();
    }

    private void initView() {
        ImageView mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_action_bar_title);
        TextPaint paint = mTvActionBarTitle.getPaint();
        paint.setFakeBoldText(true);
        mTvActionBarTitle.setText("生态农业");

        mIvMoreButton = (ImageView) findViewById(R.id.iv_more_button);
        RadioGroup mRgNongyeMenu = (RadioGroup) findViewById(R.id.rg_nongye_menu);
        mRgNongyeMenu.setOnCheckedChangeListener(this);
        RadioButton radioButton= (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nongye_home);
        //默认选中农业首页
        radioButton.setChecked(true);
        mIvBackButton.setOnClickListener(this);
        mIvMoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button://返回首页
                mPresenter.doStart_MainPager();
                break;
            case R.id.iv_more_button://更多
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
     *
     * @param index Tab页角标
     */
    private void showFragment(int index) {
        switch (index){
            case 0:
                if (mHomeFragment==null){
                    Logger.i("等于空");
                    mHomeFragment=new NongYe_homeFragment();
                }
                mPresenter.addOrShowFragment(getSupportFragmentManager().beginTransaction(),mHomeFragment);
                break;
            case 1:
                if (mFindFragment==null){
                    Logger.i("等于空");
                    mFindFragment=new FindFragment();
                }
                mPresenter.addOrShowFragment(getSupportFragmentManager().beginTransaction(),mFindFragment);
                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}

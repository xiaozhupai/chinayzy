package com.chinayiz.chinayzy.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chinayiz.chinayzy.utils.SPUtils;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 9:59
 * Class BaseActivity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseActivityView {
    protected SPUtils mSPUtils;
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        mPresenter = initPresenter();
        //类似fragment的绑定.拿到引用
        mPresenter.onAttch(this);
        //初始化acitivity,
        onCreateActivity(savedInstanceState);
        //初始化Presenter
        mPresenter.onCreate();
        //初始化PSUtils
        mSPUtils=SPUtils.getInsance(this,"UserPreferences");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }

    /**
     * 创建prensenter
     * @return <T extends BasePresenter> 必须是BasePresenter的子类
     */
    protected abstract T initPresenter();

    /**
     * 子类必须实现,并初始化Activity,比如setContentView()
     */
    protected abstract void onCreateActivity(Bundle savedInstanceState);

    @Override
    public void isNightMode(boolean isNight) {

    }
}
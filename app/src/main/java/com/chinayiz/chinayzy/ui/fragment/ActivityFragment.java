package com.chinayiz.chinayzy.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ActivityPresenter;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/21 10:03
 * Class ActivityFragment 生态农业活动页面
 */
@SuppressLint("ValidFragment")
public class ActivityFragment extends BaseFragment<ActivityPresenter> {

    @Override
    protected void onVisible() {

    }

    public static ActivityFragment getInstance() {
        return new ActivityFragment();
    }
    @Override
    protected void onInvisible() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity,container,false);
        return view;
    }

    @Override
    public ActivityPresenter initPresenter() {
        return new ActivityPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

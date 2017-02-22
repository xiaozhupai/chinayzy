package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.MyStepPresenter;

/**我的足迹
 * A simple {@link Fragment} subclass.
 */

public class MyStepFragment extends BaseFragment<MyStepPresenter> {
    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_step, container, false);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public MyStepPresenter initPresenter() {
        return new MyStepPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

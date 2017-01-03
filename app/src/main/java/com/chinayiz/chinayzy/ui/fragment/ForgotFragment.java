package com.chinayiz.chinayzy.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotFragment extends BaseFragment {

    @Override
    protected void onVisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot,null);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

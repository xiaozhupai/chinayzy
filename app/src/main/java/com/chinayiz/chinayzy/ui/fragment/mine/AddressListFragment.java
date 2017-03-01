package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.presenter.AddressListPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressListFragment extends BaseFragment<AddressListPresenter> {


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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public AddressListPresenter initPresenter() {
        return new AddressListPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

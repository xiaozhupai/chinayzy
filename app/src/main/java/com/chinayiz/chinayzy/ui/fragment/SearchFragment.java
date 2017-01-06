package com.chinayiz.chinayzy.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SearchPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<SearchPresenter> {


    @Override
    protected void onVisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,null);
    }

    @Override
    public SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

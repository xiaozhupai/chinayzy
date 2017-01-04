package com.chinayiz.chinayzy.ui.fragment.find;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.FindDetailPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindDetailFragment extends BaseFragment<FindDetailPresenter> {


    @Override
    protected void onVisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find_detail,null);
    }

    @Override
    public FindDetailPresenter initPresenter() {
        return new FindDetailPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

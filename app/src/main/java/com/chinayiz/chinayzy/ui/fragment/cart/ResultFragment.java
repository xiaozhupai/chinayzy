package com.chinayiz.chinayzy.ui.fragment.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ResultPresenter;

/**结算订单
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends BaseFragment<ResultPresenter> {
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
        return inflater.inflate(R.layout.fragment_result,null);
    }

    @Override
    public ResultPresenter initPresenter() {
        return new ResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.EvalueResultPresenter;

/**评价结果
 * A simple {@link } subclass.
 */
public class EvalueResultFragment extends BaseFragment<EvalueResultPresenter> {


    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evalue_result, container, false);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public EvalueResultPresenter initPresenter() {
        return new EvalueResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

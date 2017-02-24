package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.WeightPresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;

/**
 * A simple {@link Fragment} subclass.  体重
 */
public class WeightFragment extends BaseFragment<WeightPresenter> {
    public String param;
    public EditText et_weight;
    public TextView tv_right;
    public MineActivity activity;

    public WeightFragment(String param) {
        this.param = param;
    }

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

        return initView(inflater,container,savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weight,null);
        et_weight = (EditText) view.findViewById(R.id.et_weight);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        activity= (MineActivity) getActivity();
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("完成");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.submit();
            }
        });
        return view;

    }

    @Override
    public WeightPresenter initPresenter() {
        return new WeightPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }


}

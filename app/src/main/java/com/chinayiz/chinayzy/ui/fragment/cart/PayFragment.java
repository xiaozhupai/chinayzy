package com.chinayiz.chinayzy.ui.fragment.cart;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.CommonPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PayFragment extends BaseFragment<CommonPresenter> implements View.OnClickListener {
    private TextView tv_two;

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("交易成功");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, null);

        tv_two = (TextView) view.findViewById(R.id.tv_two);
        tv_two.setOnClickListener(this);
        return view;
    }

    @Override
    public CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_two:
                APP.exit();
                break;
        }
    }
}

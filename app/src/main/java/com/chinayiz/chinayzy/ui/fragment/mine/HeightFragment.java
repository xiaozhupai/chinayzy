package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.HeightPresenter;
import com.orhanobut.logger.Logger;

/**
 * 身高
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HeightFragment extends BaseFragment<HeightPresenter> {
    public String param;
    public EditText et_height;
    public TextView tv_right;


    public HeightFragment(String param) {
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
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("身高");
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("完成");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.i("完成");
                mPresenter.submit();
            }
        });

    }


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_height,null);
        et_height = (EditText) view.findViewById(R.id.et_height);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        et_height.setText(param);

        return view;
    }

    @Override
    public HeightPresenter initPresenter() {
        return new HeightPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }


}

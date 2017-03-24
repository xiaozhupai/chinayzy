package com.chinayiz.chinayzy.ui.fragment.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.RegisterInfoPresenter;

/*  注册信息
 * A simple {@link Fragment} subclass.
 */
public class RegisterInfoFragment extends BaseFragment<RegisterInfoPresenter> {
    private String code;
    private String phone;
    private String password;

    public RegisterInfoFragment(String code, String phone, String password) {
        this.code = code;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("用户注册信息");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public RegisterInfoPresenter initPresenter() {
        return null;
    }

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
        return inflater.inflate(R.layout.fragment_register_info, container, false);
    }

}

package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.presenter.UserNamePresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;

/**    用户名
 * A simple {@link Fragment} subclass.
 */
public class UserNameFragment extends BaseFragment<UserNamePresenter> {
    public String params;
    public EditText et_username;
    public MineActivity activity;


    public UserNameFragment(String params) {
        this.params = params;
    }


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_name2,null);
        et_username = (EditText)view.findViewById(R.id.et_username);
        activity= (MineActivity) getActivity();
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("完成");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.submit();
            }
        });
        et_username.setText(params);
        return view;
    }

    @Override
    public UserNamePresenter initPresenter() {
        return new UserNamePresenter();
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
        return initView(inflater,container,savedInstanceState);
    }

    @Override
    public void isNightMode(boolean isNight) {

    }


}

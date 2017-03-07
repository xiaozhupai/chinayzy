package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.UserNamePresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.orhanobut.logger.Logger;

/**    用户名
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class UserNameFragment extends BaseFragment<UserNamePresenter> {
    public String params;
    public EditText et_username;
    public MineActivity mineActivity;



    public UserNameFragment(String params) {
        this.params = params;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
       mineActivity= (MineActivity) activity;
        mineActivity.mTvActionBarTitle.setText("用户名");
        mineActivity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        mineActivity.mCbActionBarEdit.setText("完成");
        mineActivity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               mPresenter.submit();
            }
        });
        Logger.i("UserNameFragment-----onInitActionBar  ");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_name2,null);
        et_username = (EditText)view.findViewById(R.id.et_username);
        et_username.setText(params);
        Logger.i("UserNameFragment---------initView");
        return view;
    }

    @Override
    public UserNamePresenter initPresenter() {
        Logger.i("UserNameFragment---------initPresenter");
        return new UserNamePresenter();
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

}

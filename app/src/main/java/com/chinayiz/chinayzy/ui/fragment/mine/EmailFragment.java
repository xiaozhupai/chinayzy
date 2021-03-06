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
import com.chinayiz.chinayzy.presenter.EmailPresenter;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;
import com.orhanobut.logger.Logger;

/**
 * 邮箱地址
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class EmailFragment extends BaseFragment<EmailPresenter> {
    public EditText et_email;
    public CommonActivity mineActivity;
    public String param;


    public EmailFragment(String param){
        this.param=param;
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
      mineActivity= (CommonActivity) activity;
        mineActivity.mTvActionBarTitle.setText("邮箱地址");
        mineActivity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        mineActivity.mCbActionBarEdit.setText("完成");
        mineActivity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.i("完成");
                mPresenter.submit();
            }
        });

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_email, null);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_email.setText(param);


        return view;

    }

    @Override
    public EmailPresenter initPresenter() {
        return new EmailPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }


}

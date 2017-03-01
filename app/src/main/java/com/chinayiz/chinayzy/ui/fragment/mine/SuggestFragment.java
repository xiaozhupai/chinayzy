package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SuggestPresenter;

/**
 * 意见反馈
 * A simple {@link Fragment} subclass.
 */
public class SuggestFragment extends BaseFragment<SuggestPresenter> implements View.OnClickListener {


    public EditText et_suggest_title;
    public EditText et_suggest_content;
    public TextView tv_suggest_submit;

    @Override
    protected void onVisible() {
        BaseActivity activity= (BaseActivity) getActivity();
        activity.mTvActionBarTitle.setText("意见反馈");
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_suggest, null);
        et_suggest_title = (EditText) view.findViewById(R.id.et_suggest_title);
        et_suggest_title.setOnClickListener(this);
        et_suggest_content = (EditText) view.findViewById(R.id.et_suggest_content);
        et_suggest_content.setOnClickListener(this);
        tv_suggest_submit = (TextView) view.findViewById(R.id.tv_suggest_submit);
        tv_suggest_submit.setOnClickListener(this);
        return view;

    }

    @Override
    public SuggestPresenter initPresenter() {
        return new SuggestPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_suggest_submit:  //提交意见反馈
                mPresenter.submit();

                break;
        }
    }



}

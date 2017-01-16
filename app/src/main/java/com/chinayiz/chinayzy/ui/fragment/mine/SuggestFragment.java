package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SuggestPresenter;

/**
 * 意见反馈
 * A simple {@link Fragment} subclass.
 */
public class SuggestFragment extends BaseFragment<SuggestPresenter> implements View.OnClickListener {


    private EditText et_suggest_title;
    private EditText et_suggest_content;
    private TextView tv_suggest_submit;

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

        return inflater.inflate(R.layout.fragment_suggest, container, false);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_suggest, container);
        et_suggest_title = (EditText) inflater.inflate(R.layout.fragment_suggest, container, false).findViewById(R.id.et_suggest_title);
        et_suggest_title.setOnClickListener(this);
        et_suggest_content = (EditText) inflater.inflate(R.layout.fragment_suggest, container, false).findViewById(R.id.et_suggest_content);
        et_suggest_content.setOnClickListener(this);
        tv_suggest_submit = (TextView) inflater.inflate(R.layout.fragment_suggest, container, false).findViewById(R.id.tv_suggest_submit);
        tv_suggest_submit.setOnClickListener(this);
        return view;

    }

    @Override
    public SuggestPresenter initPresenter() {
        return null;
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_suggest_submit:  //提交意见反馈

                break;
        }
    }

    private void submit() {
        // validate
        String title = et_suggest_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getContext(), "输入主要问题模块,如收藏模块", Toast.LENGTH_SHORT).show();
            return;
        }

        String content = et_suggest_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(getContext(), "问题详细描述", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}

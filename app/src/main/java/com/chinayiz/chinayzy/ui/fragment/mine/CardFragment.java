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
import com.chinayiz.chinayzy.presenter.CardPresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;

/**
 * 身份证
 * A simple {@link Fragment} subclass.
 */

public class CardFragment extends BaseFragment<CardPresenter> {
    public String param;
    public EditText et_card;
    public MineActivity activity;

    public CardFragment(String param) {
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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_card,null);
        et_card = (EditText) view.findViewById(R.id.et_card);
        et_card.setText(param);
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
    public CardPresenter initPresenter() {
        return new CardPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }


}

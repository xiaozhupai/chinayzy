package com.chinayiz.chinayzy.ui.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.LabelPresenter;
import com.chinayiz.chinayzy.widget.Tag;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18. 个性标签
 */

public class LabelFragment extends BaseFragment<LabelPresenter> {
    public String param;

    public LabelFragment(String  param) {
        this.param = param;
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater,container,savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_label,null);
    }

    @Override
    public LabelPresenter initPresenter() {
        return new LabelPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

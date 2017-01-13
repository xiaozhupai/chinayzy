package com.chinayiz.chinayzy.ui.fragment.find;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;

/** 发现列表
 * A simple {@link Fragment} subclass.
 */
public class FindListFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private GridView gd_find_list;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.fragment_find_list, container, false);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gd_find_list = (GridView) inflater.inflate(R.layout.fragment_find_list, container, false).findViewById(R.id.gd_find_list);
        gd_find_list.setOnItemClickListener(this);
        View view=inflater.inflate(R.layout.fragment_find_list, container, false);
        return view;

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

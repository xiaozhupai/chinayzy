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
import com.chinayiz.chinayzy.adapter.FindAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.presenter.FindListPresenter;

import java.util.ArrayList;
import java.util.List;

/** 发现列表
 * A simple {@link Fragment} subclass.
 */
public class FindListFragment extends BaseFragment<FindListPresenter> implements AdapterView.OnItemClickListener {
    private GridView gd_find_list;
    private FindAdaphter adaphter;

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
     View view=initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gd_find_list = (GridView) inflater.inflate(R.layout.fragment_find_list, container, false);
        gd_find_list.setOnItemClickListener(this);
        List list=new ArrayList();
        for (int i=0;i<10;i++){
            list.add("dsds");
        }
        adaphter=new FindAdaphter(mContext,list);
        gd_find_list.setAdapter(adaphter);
        return gd_find_list;
    }

    @Override
    public FindListPresenter initPresenter() {
        return new FindListPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startFragment(new FindDetailFragment());
    }
}

package com.chinayiz.chinayzy.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.presenter.SearchListPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.搜索结果列表
 */
public class SearchListFragment extends BaseFragment<SearchListPresenter> {
    private ListView lv_list;
    public SearchResultAdaphter adaphter;
    public List<SearchFarmModel.DataBean> lists=new ArrayList<>();
    public String title;
    public int index;

    public SearchListFragment(String title,int index){
        this.title=title;
        this.index=index;
    }


    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {
        mPresenter.getData();
        Logger.i("SearchListFragment lazyLoad" );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initView(inflater,container,savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_list, container, false);
        lv_list= (ListView) view.findViewById(R.id.lv_list);
        adaphter=new SearchResultAdaphter(lists,1,getActivity());
        lv_list.setAdapter(adaphter);
      ViewGroup parent= (ViewGroup) lv_list.getParent();
        if (parent!=null){
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public SearchListPresenter initPresenter() {
        return new SearchListPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

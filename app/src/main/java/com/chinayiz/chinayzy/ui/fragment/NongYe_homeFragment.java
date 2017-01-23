package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NongYe_HomeRecyl_Adapter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.NongYe_homePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:19
 * Class NongYe_homeFragment 农业首页
 */
public class NongYe_homeFragment extends BaseFragment<NongYe_homePresenter> {
    public RecyclerView mNongyeHomeRecyclerLayout;
    public NongYe_HomeRecyl_Adapter mRecylAdapter;
    public Map<Integer,Object> mDateList=new HashMap<>();
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nongye_fragment_home, container,false);
        initWidget(view);
        return view;
    }
    private void initWidget(View view) {
        mNongyeHomeRecyclerLayout = (RecyclerView) view.findViewById(R.id.nongye_home_recyclerLayout);
        mNongyeHomeRecyclerLayout.setLayoutManager(new LinearLayoutManager(mContext));
        mDateList.put(0,9);//初始化RecyclerView 的item条数
        mRecylAdapter=new NongYe_HomeRecyl_Adapter(mDateList,this);
        mNongyeHomeRecyclerLayout.setAdapter(mRecylAdapter);
    }
    @Override
    public NongYe_homePresenter initPresenter() {
        return new NongYe_homePresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    protected void onInvisible() {
    }
    @Override
    protected void onVisible() {
    }

}

package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ClassifyPresenter;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/27 15:54
 * Class ClassifyFragment 生态农业首页二级分类菜单
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenter> {
    public RecyclerView mRecyclerView;
    public ListView mListView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classify,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rv_typeGrid);
        mListView= (ListView) view.findViewById(R.id.lv_typeList);
        return view;
    }

    @Override
    public ClassifyPresenter initPresenter() {
        return new ClassifyPresenter();
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

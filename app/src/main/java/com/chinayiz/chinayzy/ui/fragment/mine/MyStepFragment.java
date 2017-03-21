package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.MyStepAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.MyStepModel;
import com.chinayiz.chinayzy.presenter.MyStepPresenter;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableListView;
import com.orhanobut.logger.Logger;

/**我的足迹
 * A simple {@link Fragment} subclass.
 */

public class MyStepFragment extends BaseFragment<MyStepPresenter> implements AdapterView.OnItemClickListener {
    public PullableListView pull_listview;
    public PullToRefreshLayout pullrefresh;
    public MyStepAdaphter adapter;
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
        activity.mTvActionBarTitle.setText("我的足迹");
    }

    @Override
    public void onInintData(Bundle bundle) {
        adapter=new MyStepAdaphter(mActivity,null);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_step,null);
        pull_listview = (PullableListView) view.findViewById(R.id.pull_listview);
        pullrefresh = (PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        pull_listview.setAdapter(adapter);
        adapter.setRefreshLayout(pullrefresh);
        pull_listview.setOnItemClickListener(this);
        pullrefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                adapter.onRefresh();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                adapter.LoadMore();
            }
        });
        return view;
    }

    @Override
    public MyStepPresenter initPresenter() {
        return new MyStepPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           MyStepModel.DataBean.FootmarklistBean bean= (MyStepModel.DataBean.FootmarklistBean) adapterView.getItemAtPosition(i);
        Logger.i("点击每一个商品");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.onDestory();
    }
}

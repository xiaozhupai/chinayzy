package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.MyStepAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.MyStepModel;
import com.chinayiz.chinayzy.presenter.MyStepPresenter;
import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableListView;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**我的足迹
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MyStepFragment extends BaseFragment<MyStepPresenter> implements AdapterView.OnItemClickListener {
    public PullableListView pull_listview;
    public SmartRefreshLayout mSmartRefresh;
    public MyStepAdaphter adapter;
    public LinearLayout ll_none;
    public TextView tv_none;
    public ImageView iv_none;

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
        mSmartRefresh = (SmartRefreshLayout) view.findViewById(R.id.pullrefresh);
        ll_none= (LinearLayout) view.findViewById(R.id.ll_none);
        iv_none= (ImageView) view.findViewById(R.id.iv_none);
        tv_none= (TextView) view.findViewById(R.id.tv_none);
        pull_listview.setAdapter(adapter);
        adapter.setFragment(this);
        adapter.setRefreshLayout(mSmartRefresh);
        pull_listview.setOnItemClickListener(this);
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                adapter.onRefresh();
            }
        });
        mSmartRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                adapter.LoadMore();
            }
        });
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));
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
        Skip.toNewGoodsDetail(getActivity(),bean.getGoodsid()+"", GoodsMainFragment.COMMON);
        Logger.i("点击每一个商品");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.onDestory();
    }

}

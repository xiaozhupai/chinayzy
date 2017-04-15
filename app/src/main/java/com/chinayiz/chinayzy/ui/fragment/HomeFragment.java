package com.chinayiz.chinayzy.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.homePresenter;
import com.chinayiz.chinayzy.views.refreshView.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.refreshView.PullableRecycleView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:19
 * Class NongYe_homeFragment 农业首页
 */
@SuppressLint("ValidFragment")
public class HomeFragment extends BaseFragment<homePresenter> {
    public PullableRecycleView mNongyeHomeRecyclerLayout;
    public NongYeHomeRecylAdapter mRecylAdapter;
    public List<String> isLoad = new ArrayList<>();
    private PullToRefreshLayout mRefreshLayout;

    @Override

    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nongye_fragment_home, container, false);
        initWidget(view);
        return view;
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }
    private void initWidget(View view) {
        mNongyeHomeRecyclerLayout = (PullableRecycleView) view.findViewById(R.id.nongye_home_recyclerLayout);
        mRefreshLayout= (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        mRefreshLayout.setOnRefreshListener(mPresenter);
        mNongyeHomeRecyclerLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecylAdapter = new NongYeHomeRecylAdapter( this);
        mNongyeHomeRecyclerLayout.setListner(mPresenter);
        mNongyeHomeRecyclerLayout.setAdapter(mRecylAdapter);
    }

    /**
     * 打开商品详情
     *
     * @param goodsId 商品ID
     */
    public void openGoodesDetail(String goodsId) {
        Skip.toNewGoodsDetail(mActivity, goodsId);
    }

    /**
     * 打开二级商品分类
     *
     * @param code
     */
    public void openClassify(String code) {
        Skip.toItemMenu(getActivity(), code);
    }


    @Override
    public homePresenter initPresenter() {
        return new homePresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("当前视图"+getClass().getSimpleName());
    }

    @Override
    protected void onInvisible() {
    }

    protected void onVisible() {

    }
}

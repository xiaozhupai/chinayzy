package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.MyNavigatorAdapter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SelfTeaPresenter;
import com.chinayiz.chinayzy.utils.magicindicator.MagicIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableScrollView;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/9 11:57
 * Class SelfTeaFragment  自营茶叶Fragment
 */

public class SelfTeaFragment extends BaseFragment<SelfTeaPresenter> implements ViewPager.OnPageChangeListener
        , OnItemClickListener, MyNavigatorAdapter.OnTitleViewClickListener {
    private PullableScrollView mScrollView;
    public ConvenientBanner mBannerSelfTea;
    public PullToRefreshLayout mRefreshLayout;
    public CommonNavigator mNavigator;
    public MagicIndicator mIndxMagicIndicator;
    public ViewPager mVpTeaList;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selftea, container,false);
        init(view);
        return view;
    }
    private void init(View view) {
        mRefreshLayout=((PullToRefreshLayout) view.findViewById(R.id.pull_Refresh));
                //设置刷新加载监听器
        mRefreshLayout.setOnRefreshListener(mPresenter);
        mScrollView= (PullableScrollView) view.findViewById(R.id.scroll_ScrollView);

        mBannerSelfTea = (ConvenientBanner) view.findViewById(R.id.banner_selfTea);
        mBannerSelfTea.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        mBannerSelfTea.setOnItemClickListener(this);

        mIndxMagicIndicator = (MagicIndicator) view.findViewById(R.id.indx_magicIndicator);
        mVpTeaList = (ViewPager) view.findViewById(R.id.vp_teaList);
        mNavigator= new CommonNavigator(getActivity());
    }

    @Override
    public SelfTeaPresenter initPresenter() {
        return new SelfTeaPresenter();
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mNavigator.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        mNavigator.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mNavigator.onPageScrollStateChanged(state);
    }

    @Override
    public void onItemClick(int position) {
        Logger.i("点击了广告图="+position);
    }


    @Override
    public void titleClick(int index) {
        mVpTeaList.setCurrentItem(index);
        mPresenter.mPagerAdapter.notifyDataSetChanged();
    }

}

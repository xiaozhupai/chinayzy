package com.chinayiz.chinayzy.ui.fragment.find;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.chinayiz.chinayzy.NongYe_MainActivity;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.PagerAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.FindPresenter;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现
 * A simple {@link Fragment} subclass.
 */

public class FindFragment extends BaseFragment<FindPresenter>{
    public ViewPager vp_find;

//    public LinearLayout ll_top;
    private static final int SLIDE=123;
    public MagicIndicator magic_indicator;
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT"};


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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         NongYe_MainActivity activity= (NongYe_MainActivity) getActivity();
        activity.mIvMoreButton.setVisibility(View.GONE);
        activity.mTvActionBarTitle.setText("发现");

        View view=inflater.inflate(R.layout.fragment_find,container,false);
        magic_indicator= (MagicIndicator) view.findViewById(R.id.magic_indicator);
        vp_find = (ViewPager) view.findViewById(R.id.vp_find);

        return view;
    }

    @Override
    public FindPresenter initPresenter() {
        return new FindPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view=initView(inflater,container,savedInstanceState);
        return view;
    }

}

package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ArticleAdaphter;
import com.chinayiz.chinayzy.adapter.GoodsKeepAdaphter;
import com.chinayiz.chinayzy.adapter.PagerAdaphter;
import com.chinayiz.chinayzy.adapter.ShopKeepAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ContentKeepPresenter;
import com.chinayiz.chinayzy.ui.fragment.ListFragment;
import com.chinayiz.chinayzy.utils.magicindicator.MagicIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.ViewPagerHelper;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;
import com.chinayiz.chinayzy.views.XViewPager;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 博文收藏
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ContentKeepFragment extends BaseFragment<ContentKeepPresenter> {
    private MagicIndicator magic_indicator;
    private PullableListView pull_listview;
    private PullToRefreshLayout pullrefresh;
    private String [] titles=new String[]{"商品","店铺","博文"};
    private int type=0;   //0  店铺收藏 1博文收藏 2商品
    public ViewPager v_pager;

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
        activity.mTvActionBarTitle.setText("我的收藏");
    }

    @Override
    public void onInintData(Bundle bundle) {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_keep, container, false);
        magic_indicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        v_pager= (ViewPager) view.findViewById(R.id.v_pager);
        List<BaseFragment> fragments=new ArrayList<>();
        fragments.add(new ListFragment(new GoodsKeepAdaphter(getActivity(),null)));
        fragments.add(new ListFragment(new ShopKeepAdaphter(getActivity(),null)));
         fragments.add(new ListFragment(new ArticleAdaphter(getActivity(),null)));
        v_pager.setAdapter(new PagerAdaphter(getChildFragmentManager(),fragments));
        final CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                final SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setNormalColor(Color.parseColor("#6e6e6e"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#ff3952"));
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {  //标题点击事件
                        type=index;
                        v_pager.setCurrentItem(index);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);
                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_STYLE);
                indicator.setColors(Color.parseColor("#ff3952"));
                return indicator;
            }
        });
        magic_indicator.setNavigator(commonNavigator);
//        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
//        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
////        titleContainer.setDividerDrawable(new ColorDrawable() {
////            @Override
////            public int getIntrinsicWidth() {
////                return UIUtil.dip2px(getActivity(), 1);
////            }
////        });
        ViewPagerHelper.bind(magic_indicator,v_pager);
        return view;


    }

    @Override
    public ContentKeepPresenter initPresenter() {
        return new ContentKeepPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

}

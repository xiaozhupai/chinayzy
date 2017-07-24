package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.AwardRecordAdaphter;
import com.chinayiz.chinayzy.adapter.PagerAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.CardPresenter;
import com.chinayiz.chinayzy.ui.fragment.ListFragment;
import com.chinayiz.chinayzy.utils.magicindicator.MagicIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.ViewPagerHelper;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.UIUtil;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;
import com.chinayiz.chinayzy.views.XViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * 获奖记录
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AwardRecordFragment extends BaseFragment<CardPresenter> {
    public String param;
    private MagicIndicator mMagicIndicator;
    private ViewPager mVpRecord;
    public PagerAdaphter adaphter;
    private String [] titles=new String[]{"全部","进行中","已揭晓"};
    public List<ListFragment> lists=new ArrayList<>();




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
    public void onInintData(Bundle bundle) {

    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("获奖记录");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_award_record, null);
        mMagicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        mVpRecord = (ViewPager) view.findViewById(R.id.vp_record);
        for (int i=1;i<4;i++){
            ListFragment fragment=new ListFragment(new AwardRecordAdaphter(getActivity(),i+""));
            lists.add(fragment);
        }
        //指示器
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setNormalColor(Color.parseColor("#505050"));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.red));
                simplePagerTitleView.setText(titles[index]);

                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVpRecord.setCurrentItem(index);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);
                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                linePagerIndicator.setColors(getResources().getColor(R.color.red));
                return linePagerIndicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return UIUtil.dip2px(getActivity(), 15);
            }
        });
        //viewpager
        adaphter=new PagerAdaphter(getChildFragmentManager(),lists);
        mVpRecord.setAdapter(adaphter);
        mVpRecord.setOffscreenPageLimit(2);
        ViewPagerHelper.bind(mMagicIndicator,mVpRecord);
        return view;
    }

    @Override
    public CardPresenter initPresenter() {
        return new CardPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }



}

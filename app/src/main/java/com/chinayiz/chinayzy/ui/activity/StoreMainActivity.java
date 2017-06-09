package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ViewPagerAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.response.StoreHomeTabModel;
import com.chinayiz.chinayzy.presenter.StoreMainPresenter;
import com.chinayiz.chinayzy.ui.fragment.StoreTabFragment;
import com.chinayiz.chinayzy.utils.magicindicator.MagicIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.ViewPagerHelper;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/6/5 10:18
 * Class StoreMainActivity
 */

public class StoreMainActivity extends BaseActivity<StoreMainPresenter> {
    private View mBtnBack;
    private View mIvSeek;
    private View mBtnMore;
    private ViewPagerAdapter mAdapter;
    private List<StoreTabFragment> mTabFragments;
    public MagicIndicator mMiIndex;
    public ViewPager mVpStoreContent;
    public CommonNavigator mCommonNavigator;
    public StoreHomeTabModel mTabsModel;

    @Override
    protected StoreMainPresenter initPresenter() {
        return new StoreMainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_store);
        setStatuBarColor(this, ContextCompat.getColor(this, R.color.Background));
        initView();
    }

    private void initView() {
        mBtnBack = findViewById(R.id.btn_back);
        mIvSeek = findViewById(R.id.iv_seek);
        mBtnMore = findViewById(R.id.btn_more);
        mMiIndex = (MagicIndicator) findViewById(R.id.mi_index);
        mVpStoreContent = (ViewPager) findViewById(R.id.vp_storeContent);
        mCommonNavigator = new CommonNavigator(this);

        mBtnBack.setOnClickListener(this);
        mIvSeek.setOnClickListener(this);
        mBtnMore.setOnClickListener(this);
        mTabFragments = new ArrayList<>();
        mPresenter.mRequestUtils.getStoreHomeThemeName();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:

                break;
            case R.id.iv_seek:

                break;
            case R.id.btn_more:

                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public void setTabs() {
        StoreHomeTabModel.DataBean.ToplistBean  bean=null;
        for (int i = 0; i < mTabsModel.getData().getToplist().size(); i++) {
            bean=mTabsModel.getData().getToplist().get(i);
            if (StoreTabFragment.TAB_HOME_NAME.equals(bean.getItemname())) {
                mTabFragments.add(StoreTabFragment.getInstance(StoreTabFragment.TAB_TYPE_HOME,
                        bean.getItemcode() + "," + bean.getItemname(),i,mTabsModel.getData()));
            } else {
                mTabFragments.add(StoreTabFragment.getInstance(StoreTabFragment.TAB_TYPE_THEME,
                        bean.getItemcode() + "," + bean.getItemname(),i,mTabsModel.getData()));
            }
        }

        mAdapter = new ViewPagerAdapter(getFragmentManager(), mTabFragments);
        mVpStoreContent.setAdapter(mAdapter);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTabsModel == null ? 0 : mTabsModel.getData().getToplist().size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(ContextCompat.getColor(StoreMainActivity.this, R.color.classifyText));
                colorTransitionPagerTitleView.setSelectedColor(ContextCompat.getColor(StoreMainActivity.this, R.color.search_selected));
                colorTransitionPagerTitleView.setTextSize(13);
                colorTransitionPagerTitleView.setText(mTabsModel.getData().getToplist().get(index).getItemname());
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mVpStoreContent.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(ContextCompat.getColor(StoreMainActivity.this, R.color.search_selected));
                indicator.setLineHeight(8);
                indicator.setMode(LinePagerIndicator.MODE_STYLE);
                return indicator;
            }
        });
        mMiIndex.setNavigator(mCommonNavigator);
        ViewPagerHelper.bind(mMiIndex, mVpStoreContent);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}

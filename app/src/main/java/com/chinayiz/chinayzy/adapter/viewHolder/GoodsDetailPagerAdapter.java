package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/18 15:52
 * Class GoodsDetailPagerAdapter 商品图文详情适配器
 */

public class GoodsDetailPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    public GoodsDetailPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}

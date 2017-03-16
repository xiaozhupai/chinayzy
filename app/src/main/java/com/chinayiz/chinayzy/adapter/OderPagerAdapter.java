package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.chinayiz.chinayzy.ui.fragment.mine.OrderFragment;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/14 9:45
 * Class OderPagerAdapter  订单页适配器
 */

public class OderPagerAdapter extends FragmentStatePagerAdapter {
    private List<OrderFragment> mFragments;

    public OderPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public OderPagerAdapter(FragmentManager fm, List<OrderFragment> fragments) {
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

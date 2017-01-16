package com.chinayiz.chinayzy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 13:58
 * Class NongYe_Home_Adapter // 农业板块ViewPager适配器
 */
public class NongYe_MainPager_Adapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    /**
     *
     * @param fm Fragment 管理器
     * @param fragments fragments集合
     */
    public NongYe_MainPager_Adapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragmentList=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}

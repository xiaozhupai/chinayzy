package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 13:58
 * Class NongYe_Home_Adapter // 农业板块ViewPager适配器
 */
public class NyMainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    /**
     *
     * @param fm Fragment 管理器
     * @param fragments fragments集合
     */
    public NyMainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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

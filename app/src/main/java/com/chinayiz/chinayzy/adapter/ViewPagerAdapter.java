package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.chinayiz.chinayzy.ui.fragment.StoreTabFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/1/6.
 * 懒加载viewpag适配器
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<StoreTabFragment> lists;

    public ViewPagerAdapter(FragmentManager fm, List<StoreTabFragment> lists) {
        super(fm);
        this.lists = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

}

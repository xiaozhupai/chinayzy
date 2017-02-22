package com.chinayiz.chinayzy.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/9 15:31
 * Class SelfTeaPagerAdapter
 */
public class SelfTeaPagerAdapter extends PagerAdapter {
    private List<GridView> mGridViews;
    public SelfTeaPagerAdapter() {

    }
    public void setGridViews(List<GridView> gridViews) {
        mGridViews = gridViews;
    }
    @Override
    public int getCount() {
        return mGridViews==null? 0:mGridViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mGridViews.get(position));
        return mGridViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mGridViews.get(position));
       mGridViews.remove(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}

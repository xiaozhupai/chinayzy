package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.chinayiz.chinayzy.entity.response.TypeListModel;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/9 15:20
 * Class MyNavigatorAdapter
 */
public class MyNavigatorAdapter extends CommonNavigatorAdapter {
    private List<TypeListModel.DataBean> mDataList;

    private OnTitleViewClickListener mTitleViewClickListener;

    public void setTitleViewClickListener(OnTitleViewClickListener titleViewClickListener) {
        mTitleViewClickListener = titleViewClickListener;
    }
    public MyNavigatorAdapter(List<TypeListModel.DataBean> data) {
        this.mDataList=data;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
        clipPagerTitleView.setText(mDataList.get(index).getItemname());
        clipPagerTitleView.setTextColor(Color.BLACK);
        clipPagerTitleView.setClipColor(Color.GREEN);
        clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mTitleViewClickListener.titleClick(index);
            }
        });
        return clipPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        return indicator;
    }
    public interface OnTitleViewClickListener{
        void titleClick(int index);
    }
}

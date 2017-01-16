package com.chinayiz.chinayzy.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/4 14:59
 * Class MainViewPager 自定义viewPager 禁止左右滑动
 */
public class MainViewPager extends ViewPager {

    public MainViewPager(Context context) {
        super(context);
    }

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 消费滑动事件，禁止左右滑动
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}

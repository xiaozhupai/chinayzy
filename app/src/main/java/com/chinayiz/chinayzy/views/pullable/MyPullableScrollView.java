package com.chinayiz.chinayzy.views.pullable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/13 14:26
 * Class MyPullableScrollView
 */
public class MyPullableScrollView extends PullableScrollView {

    public MyPullableScrollView(Context context) {
        super(context);
    }

    public MyPullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getScrollY()>=360){
            return false;
        }
        return super.onTouchEvent(ev);
    }
}

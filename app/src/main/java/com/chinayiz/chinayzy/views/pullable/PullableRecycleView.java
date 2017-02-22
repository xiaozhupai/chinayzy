package com.chinayiz.chinayzy.views.pullable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/9 14:10
 * Class MyRecycleView
 */
public class PullableRecycleView extends RecyclerView implements Pullable {

    public PullableRecycleView(Context context) {
        super(context);
    }

    public PullableRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
            return true;
        else
            return false;
    }
}

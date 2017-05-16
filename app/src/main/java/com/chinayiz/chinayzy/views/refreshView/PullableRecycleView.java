package com.chinayiz.chinayzy.views.refreshView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.chinayiz.chinayzy.views.pullable.Pullable;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/9 14:10
 * Class MyRecycleView
 */
public class PullableRecycleView extends RecyclerView implements Pullable {
    private RefreshListner mListner;

    public RefreshListner getListner() {
        return mListner;
    }

    public void setListner(RefreshListner listner) {
        mListner = listner;
    }

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
        if (mListner==null)return false;
        return mListner.canRefesh();
    }

    @Override
    public boolean canPullUp() {
        if (mListner==null)return false;
        if (mListner.canLoad()){
            View view = getChildAt(getChildCount() - 1);
            if (view!=null&&view.getBottom() == getMeasuredHeight())
                return true;
            else
                return false;
        }else {
            return false;
        }
    }

    public interface RefreshListner {
        boolean canLoad();
        boolean canRefesh();
    }
}

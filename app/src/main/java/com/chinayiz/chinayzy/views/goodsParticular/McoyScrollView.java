package com.chinayiz.chinayzy.views.goodsParticular;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.chinayiz.chinayzy.views.pullable.Pullable;


public class McoyScrollView extends ScrollView implements Pullable {

    // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;

    public McoyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;
                }
        }
        boolean onIntercept = super.onInterceptTouchEvent(ev);
        return onIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean touchEvent = super.onTouchEvent(ev);
        return touchEvent;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollListener != null) {
            onScrollListener.onScroll(x, y, oldx, oldy);
        }
    }

    private OnJDScrollListener onScrollListener;

    public OnJDScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnJDScrollListener(OnJDScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() ==0){
            return true;
        } else return false;
    }

    @Override
    public boolean canPullUp() {
        return false;
    }

    public interface OnJDScrollListener {
        void onScroll(int x, int y, int oldx, int oldy);
    }
}

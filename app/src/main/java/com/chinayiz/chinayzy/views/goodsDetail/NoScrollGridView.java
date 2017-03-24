package com.chinayiz.chinayzy.views.goodsDetail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/17 18:06
 * Class MyGridView
 */
public class NoScrollGridView extends GridView {

    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置不滚动
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}

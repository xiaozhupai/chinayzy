package com.chinayiz.chinayzy.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.chinayiz.chinayzy.R;

/**
 * Created by Administrator on 2017/1/19.
 */

public class CheckImageView extends ImageView {
    public boolean isCheck=false;
    public CheckImageView(Context context) {
        super(context);
    }

    public CheckImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCheck(boolean isCheck){
        this.isCheck=isCheck;
        if (isCheck){
            this.setImageResource(R.mipmap.radio_selected);
        }else {
            this.setImageResource(R.mipmap.radio_normal);
        }
    }



}

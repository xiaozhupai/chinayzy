package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/1 9:15
 * Class ClassifyHeadHolder
 */
public class ClassifyHeadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView mImageView;

    public ClassifyHeadHolder(View itemView) {
        super(itemView);
        mImageView= (ImageView) itemView.findViewById(R.id.iv_classifyHead);
        mImageView.setOnClickListener(this);
    }

    /**
     * 设置主题头数据
     * @param fragment
     * @param url 地址
     */
    public void setDatas(Fragment fragment,String url){
        Glide.with(fragment)
                .load(url)
                .into(mImageView);
    }
    @Override
    public void onClick(View v) {
        Logger.i("点击三级分类主题");
    }
}

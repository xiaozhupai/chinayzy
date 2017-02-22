package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 17:02
 * Class NongYe_Home_EatItem 生态农业首页爱吃列表item
 */
public class NY_Home_EatItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView mIvGoodItemIcon;
    public TextView mTvGoodItemTitle;
    public TextView mTvGoodItemPrice;

    private NY_EatItemModel.DataBean data;
    public NY_Home_EatItem(View itemView) {
        super(itemView);
        mIvGoodItemIcon = (ImageView) itemView.findViewById(R.id.iv_goodItemIcon);
        mTvGoodItemTitle = (TextView) itemView.findViewById(R.id.tv_goodItemTitle);
        mTvGoodItemPrice = (TextView) itemView.findViewById(R.id.tv_goodItemPrice);
        ImageView mIvAddCart = (ImageView) itemView.findViewById(R.id.iv_addCart);

        mIvGoodItemIcon.setOnClickListener(this);
        mTvGoodItemTitle.setOnClickListener(this);
        mIvAddCart.setOnClickListener(this);
    }

    public void setData(NY_EatItemModel ny_eatItemModel, Fragment fragment,int position) {
        data=ny_eatItemModel.getData().get(position%6);
        Glide.with(fragment).load(data.getIcon()).into(mIvGoodItemIcon);
        mTvGoodItemTitle.setText(data.getGname());
        mTvGoodItemPrice.setText(data.getPrice());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goodItemIcon://查看商品
                Logger.i("头像查看商品");
                break;
            case R.id.tv_goodItemTitle://查看商品
                Logger.i("标题查看商品");
                break;
            case R.id.iv_addCart://加入购物车
                Logger.i("加入购物车");
                break;
        }
    }
}

package com.chinayiz.chinayzy.adapter.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/6 10:32
 * Class StoreHomeItem
 */
public class StoreHomeItem extends RecyclerView.ViewHolder{
    public static final String CLICK="StoreHomeItem";
    private ImageView mIvGoodsPic;
    private TextView mTvGoodsName;
    private TextView mTvGoodsPrice;
    private View mViewGoods;
    private StoreInfoModel.DataBean.GoodsBean mBean;

    public StoreHomeItem(View itemView) {
        super(itemView);
        init(itemView);
    }

    private void init(View itemView) {
        mIvGoodsPic = (ImageView) itemView.findViewById(R.id.iv_goodsPic);
        mTvGoodsName = (TextView) itemView.findViewById(R.id.tv_goodsName);
        mTvGoodsPrice = (TextView) itemView.findViewById(R.id.tv_goodsPrice);
        itemView.findViewById(R.id.iv_addCart).setVisibility(View.GONE);
        mViewGoods =itemView;

    }
    public void setData(Context context, StoreInfoModel.DataBean.GoodsBean goodsBean) {
        mBean=goodsBean;
        mViewGoods.setTag(mBean.getGoodsid());
        Glide.with(context).load(mBean.getIcon()).into(mIvGoodsPic);
        mTvGoodsName.setText(mBean.getGname());
        mTvGoodsPrice.setText(mBean.getPrice());
    }
}

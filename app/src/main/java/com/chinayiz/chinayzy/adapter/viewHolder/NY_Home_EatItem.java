package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

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
        if (ny_eatItemModel.getData().size()>position){
            Logger.i("数据位置="+position);
            data=ny_eatItemModel.getData().get(position);
            Glide.with(fragment).load(data.getIcon()).into(mIvGoodItemIcon);
            mIvGoodItemIcon.setTag(R.id.tag_click,data.getGoodsid());
            mTvGoodItemTitle.setText(data.getGname());
            mTvGoodItemTitle.setTag(data.getGoodsid());
            mTvGoodItemPrice.setText(data.getPrice());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goodItemIcon:
                if (v.getTag()!=null){
                    EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,
                            NongYeHomeRecylAdapter.CLICK_GOODS,v.getTag(R.id.tag_click)));
                }
                break;
            case R.id.tv_goodItemTitle:
                if (v.getTag()!=null){
                    EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,
                            NongYeHomeRecylAdapter.CLICK_GOODS,v.getTag(R.id.tag_click)));
                }
                break;
            case R.id.iv_addCart://加入购物车
                Logger.i("加入购物车");
                break;
        }
    }
}

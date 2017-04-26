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

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/24 15:16
 * Class GoodsGridHolder 商品集合item
 */

public class GoodsSetItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView mIvGoodItemIcon;
    private TextView mTvGoodItemTitle, mTvcommentCuon, mTvgoodComment;
    private TextView mTvGoodItemPrice, mTvGoodItemPrice1;
    private View isSelf, rootView;

    /**
     * @param itemView
     */
    public GoodsSetItemHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        mIvGoodItemIcon = (ImageView) rootView.findViewById(R.id.iv_goodItemIcon);
        mTvGoodItemTitle = (TextView) rootView.findViewById(R.id.tv_goodItemTitle);
        mTvGoodItemPrice = (TextView) rootView.findViewById(R.id.tv_goodItemPrice);
        mTvcommentCuon = (TextView) rootView.findViewById(R.id.tv_CommentCount);
        mTvgoodComment = (TextView) rootView.findViewById(R.id.tv_goodComment);
        mTvGoodItemPrice1 = (TextView) rootView.findViewById(R.id.tv_goodItemPrice1);
        isSelf = rootView.findViewById(R.id.view_isSelf);
        rootView.setOnClickListener(this);
    }

    /**
     * @param data     数据
     * @param fragment 上下文
     */
    public void setData(NY_EatItemModel.DataBean data, Fragment fragment) {
        Glide.with(fragment).load(data.getIcon()).into(mIvGoodItemIcon);
        mIvGoodItemIcon.setTag(R.id.tag_click, data.getGoodsid());
        mTvGoodItemTitle.setText(data.getGname());
        mTvGoodItemTitle.setTag(R.id.tag_click, data.getGoodsid());
        if (!"0".equals(data.getCommenttotal())) {
            mTvcommentCuon.setText(data.getCommenttotal() + "条评价");
            mTvgoodComment.setText(data.getPraise() + "好评率");
        }
        if ("0".equals(data.getIsself())) {//是否自营
            isSelf.setVisibility(View.GONE);
        }
        String price = data.getPrice();
        if (price.contains("-")) {
            String[] strings = price.split("-");
            strUtil(strings[0]);
        } else {
            strUtil(price);
        }
        rootView.setTag(R.id.tag_click, data.getGoodsid());
    }

    private void strUtil(String str) {
        if (str.contains(".")) {
            String[] strings = str.split("\\.");
            mTvGoodItemPrice.setText(" " + strings[0] + ".");
            mTvGoodItemPrice1.setText(strings[strings.length - 1]);
        } else {
            mTvGoodItemPrice.setText(" " + str + ".");
            mTvGoodItemPrice1.setText("00");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_goodsItem:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,
                        NongYeHomeRecylAdapter.CLICK_GOODS, v.getTag(R.id.tag_click)));
                break;
        }
    }
}
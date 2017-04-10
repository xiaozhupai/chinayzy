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
import com.chinayiz.chinayzy.net.Commons;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 17:02
 * Class NongYe_Home_EatItem 生态农业首页爱吃列表item
 */
public class NY_Home_EatItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView mIvGoodItemIcon;
    private TextView mTvGoodItemTitle, mTvcommentCuon, mTvgoodComment;
    private TextView mTvGoodItemPrice, mTvGoodItemPrice1;
    private ImageView mIvAddCart;
    private View isSelf;

    public NY_Home_EatItem(View itemView) {
        super(itemView);
        mIvGoodItemIcon = (ImageView) itemView.findViewById(R.id.iv_goodItemIcon);
        mTvGoodItemTitle = (TextView) itemView.findViewById(R.id.tv_goodItemTitle);
        mTvGoodItemPrice = (TextView) itemView.findViewById(R.id.tv_goodItemPrice);
        mTvcommentCuon = (TextView) itemView.findViewById(R.id.tv_CommentCount);
        mTvgoodComment = (TextView) itemView.findViewById(R.id.tv_goodComment);
        mTvGoodItemPrice1 = (TextView) itemView.findViewById(R.id.tv_goodItemPrice1);
        mIvAddCart = (ImageView) itemView.findViewById(R.id.iv_addCart);
        isSelf = itemView.findViewById(R.id.view_isSelf);
        mIvGoodItemIcon.setOnClickListener(this);
        mTvGoodItemTitle.setOnClickListener(this);
        mIvAddCart.setOnClickListener(this);
    }

    public void setData(NY_EatItemModel.DataBean data, Fragment fragment) {

        Glide.with(fragment).load(data.getIcon()).into(mIvGoodItemIcon);
        mIvGoodItemIcon.setTag(R.id.tag_click, data.getGoodsid());
        mTvGoodItemTitle.setText(data.getGname());
        mTvGoodItemTitle.setTag(R.id.tag_click, data.getGoodsid());
        if (!"0".equals(data.getCommenttotal())) {
            mTvcommentCuon.setText(data.getCommenttotal()+"条评价");
            mTvgoodComment.setText(data.getPraise()+"好评率");
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
        mIvAddCart.setTag(R.id.tag_click, data);
    }

    private void strUtil(String str) {
    if (str.contains(".")){
        String[] strings = str.split("\\.");
        mTvGoodItemPrice.setText(" " + strings[0]+".");
        mTvGoodItemPrice1.setText(strings[strings.length-1]);
    }else {
        mTvGoodItemPrice.setText(" " + str+".");
        mTvGoodItemPrice1.setText("00");
    }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goodItemIcon:
                if (v.getTag(R.id.tag_click) != null) {
                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,
                            NongYeHomeRecylAdapter.CLICK_GOODS, v.getTag(R.id.tag_click)));
                }
                break;
            case R.id.tv_goodItemTitle:
                if (v.getTag(R.id.tag_click) != null) {
                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,
                            NongYeHomeRecylAdapter.CLICK_GOODS, v.getTag(R.id.tag_click)));
                }
                break;
            case R.id.iv_addCart://加入购物车
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,
                        Commons.ADD_CAR, v.getTag(R.id.tag_click)));
                break;
        }
    }
}

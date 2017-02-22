package com.chinayiz.chinayzy.adapter.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.StoreInfo;
import com.chinayiz.chinayzy.views.GlideRoundTransform;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/6 10:32
 * Class StoreHomeHead
 */
public class StoreHomeHead extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public static final String CLICK = "StoreHomeHead_CLICK";
    public static final String CHECKED = "StoreHomeHead_CHECKED";
    private ImageView mIvStoreLogo;
    private TextView mTvStareName;
    private ImageView mIvBrand;
    private CheckBox mIvSign;
    private TextView mTvSort;
    private TextView mTvGoodsType;
    private ImageView mIvIndicate;
    private View mNvGoodsMenu1;
    private StoreInfo mStoreInfo;

    public StoreHomeHead(View itemView) {
        super(itemView);
        init(itemView);
    }

    private void init(View view) {
        mIvStoreLogo = (ImageView) view.findViewById(R.id.iv_storeLogo);
        mTvStareName = (TextView) view.findViewById(R.id.tv_stareName);
        mIvBrand = (ImageView) view.findViewById(R.id.iv_brand);
        mIvSign = (CheckBox) view.findViewById(R.id.btn_sign);
        mTvSort = (TextView) view.findViewById(R.id.tv_sort);
        mTvGoodsType = (TextView) view.findViewById(R.id.tv_goodsType);
        mIvIndicate = (ImageView) view.findViewById(R.id.iv_indicate);
        mNvGoodsMenu1 = view.findViewById(R.id.nv_goodsMenu1);
        mIvBrand.setOnClickListener(this);
        mTvSort.setOnClickListener(this);
        mTvGoodsType.setOnClickListener(this);
    }
    public void setType(String name){
        mTvGoodsType.setText(name);
        mTvGoodsType.setTextColor(Color.rgb(121, 202, 52));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_brand:
                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT, CLICK, v));
                break;
            case R.id.tv_sort:
                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT, CLICK, v));
                break;
            case R.id.tv_goodsType:
                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT, CLICK, v));
                break;
        }
    }

    public void setData(Context context, StoreInfo storeInfo) {
        mStoreInfo = storeInfo;
        Glide.with(context
        ).load(storeInfo.getPic())
                .transform(new GlideRoundTransform(context,8))
                .into(mIvStoreLogo);
        mTvStareName.setText(storeInfo.getSname());
        if ("1".equals(storeInfo.getIsattention())) {
            mIvSign.setChecked(true);
        }
        mIvSign.setOnCheckedChangeListener(this);
    }

    @Override
    /**
     * 关注店铺
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            mStoreInfo.setIsattention("1");
        }else {
            mStoreInfo.setIsattention("0");
        }
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, CHECKED, isChecked));
    }

}

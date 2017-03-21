package com.chinayiz.chinayzy.adapter.viewHolder;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.hedgehog.ratingbar.RatingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/15 14:46
 * Class GoodsHolder  商品详情的ViewHolder
 */
public class GoodsHolder {
    public ConvenientBanner mVpagerBanner;
    public RadioGroup mGoodsMeun;
    public RadioButton mRbDetail;
    public TextView mTvShareBtn;
    public TextView mTvGoodsTitle;
    public TextView mTvGoodsPrice;
    public TextView mTvGoodsPostage;
    public TextView mTvGoodsSales;
    public TextView mTvGoodsOrigin;
    public List<Holder> mServiceList=new ArrayList<>(4);
    public TextView mTvGoodstype;
    public TextView mTvCommentCount;
    public RatingBar mRbGoodsGrade;
    public ImageView mIvUserIcon;
    public TextView mTvUserName;
    public TextView mTvCommentContent;
    public TextView mTvMoreComment;
    public ImageView mIvStoreLogo;
    public TextView mTvStoreName;
    public ImageView mIvStoreType;
    public ImageView mIvInStore;
    public TextView mTvGoodsCount;
    public TextView mTvFansCount;
    public TextView mPullLoadMore;
    public  static class Holder{
        public ImageView mIvService;
        public TextView mTvService;

        public Holder(ImageView ivService, TextView tvService) {
            mIvService = ivService;
            mTvService = tvService;
        }
    }
}

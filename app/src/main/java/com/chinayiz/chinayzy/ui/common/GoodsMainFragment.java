package com.chinayiz.chinayzy.ui.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.NyMainPagerAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.presenter.GoodsMainPresenter;
import com.chinayiz.chinayzy.views.goodsDetail.ScrollViewContainer;
import com.chinayiz.chinayzy.widget.GoodsStandard2;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/13 13:47
 * Class GoodsMainFragment
 */

public class GoodsMainFragment extends BaseFragment<GoodsMainPresenter> implements View.OnClickListener
        , CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener
        , ViewPager.OnPageChangeListener, ScrollViewContainer.PageChangeListener {
    public ViewHolder mViewHolder;
    public NyMainPagerAdapter mPagerAdapter;
    private GoodsStandard2 mGoodsStandard2;
    public GoodsDetailModel.DataBean mDataBean;
    public static int startSum = 0;
    public View mPregess;
    private List<Fragment> mFragments;
    public GoodsDetailFragment mDetailFragment;
    public PicDetailFragment mPicDetailFragment;
    public CommentsFragment mCommentsFragment;
    public String goodsID, storeID;
    public View mView;
    private int pegerIdex = 0;
    private int mOnwPager = 0;
    private GoodsStandard2 goodsStandard2;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_goods_main, container, false);
        mFragments = new ArrayList<>(3);
        initViews(mView);
        return mView;
    }

    private void initViews(View view) {
        mViewHolder = new ViewHolder(view);
        mViewHolder.rg_goodsTab.setOnCheckedChangeListener(this);
        mViewHolder.rg_goodsMeun.setOnCheckedChangeListener(this);
        mViewHolder.rg_goodsMeun.setVisibility(View.GONE);
        mViewHolder.rg_goodsMeun.setClickable(false);

        mDetailFragment = GoodsDetailFragment.getInstance();
        mDetailFragment.setGoodsID(goodsID);
        mPregess = view.findViewById(R.id.ll_progress);
        mPregess.setVisibility(View.VISIBLE);
        mPicDetailFragment = PicDetailFragment.getInstance();
        mCommentsFragment = CommentsFragment.getInstance();
        mCommentsFragment.setGoodsID(goodsID);

        mFragments.add(mDetailFragment);
        mFragments.add(mPicDetailFragment);
        mFragments.add(mCommentsFragment);

        mPagerAdapter = new NyMainPagerAdapter(getChildFragmentManager(), mFragments);
        mViewHolder.vp_goodsGetail.setOffscreenPageLimit(3);
        mViewHolder.vp_goodsGetail.setAdapter(mPagerAdapter);
        mViewHolder.vp_goodsGetail.addOnPageChangeListener(this);
        mDetailFragment.setChangeListener(this);

        mViewHolder.iv_back_btn.setOnClickListener(this);
        mViewHolder.iv_more_btn.setOnClickListener(this);
        mViewHolder.iv_share.setOnClickListener(this);
        mViewHolder.tv_store.setOnClickListener(this);
        mViewHolder.cb_favorite.setOnCheckedChangeListener(this);
        mViewHolder.tv_cart.setOnClickListener(this);
        mViewHolder.tv_addCart.setOnClickListener(this);
        mPresenter.mRequestUtils = CommonRequestUtils.getRequestUtils();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_btn://返回
                Logger.i("返回");
                getActivity().onBackPressed();
                break;
            case R.id.iv_more_btn://更多
                Logger.i("更多");
                break;
            case R.id.iv_share://分享
                Logger.i("分享");
                break;
            case R.id.tv_store://店铺
                Logger.i("店铺");
                if (mPresenter.model != null) {
                    Skip.toStore(getActivity(), mPresenter.model.getData().getShopid());
                }
                break;
            case R.id.tv_cart://购物车
                Logger.i("购物车");
                if (UserSeeion.isLogin(getActivity())) {
                    Skip.toShopCart(getActivity());
                } else {
                    BaseActivity.showToast(getActivity(), "您还未登录");
                }
                break;
            case R.id.tv_addCart://添加购物车
                Logger.i("点击添加购物车");
                if (goodsStandard2 == null) {
                    goodsStandard2 = new GoodsStandard2(getActivity(), mPresenter.model.getData().getGoodsid(),mPresenter.model.getData().getShopid());
                }
                goodsStandard2.show();
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0://商品信息
                mViewHolder.rb_tabDetail.setChecked(true);
                break;
            case 1://商品详情
                mViewHolder.rb_tabNorms.setChecked(true);
                break;
            case 2://全部评论
                mViewHolder.rb_tabComments.setChecked(true);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_tabDetail://商品信息
                Logger.i("商品信息");
                mViewHolder.vp_goodsGetail.setCurrentItem(0);
                if (mOnwPager == 1) {
                    mViewHolder.rg_goodsMeun.setClickable(true);
                    mViewHolder.rg_goodsMeun.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rb_tabNorms://商品详情
                Logger.i("商品详情");
                mViewHolder.vp_goodsGetail.setCurrentItem(1);
                mViewHolder.rg_goodsMeun.setClickable(false);
                mViewHolder.rg_goodsMeun.setVisibility(View.GONE);
                mPicDetailFragment.setPager();
                titelShowOrhide(0);
                break;
            case R.id.rb_tabComments://全部评论
                Logger.i("全部评论");
                mViewHolder.rg_goodsMeun.setClickable(false);
                mViewHolder.rg_goodsMeun.setVisibility(View.GONE);
                mViewHolder.vp_goodsGetail.setCurrentItem(2);
                mCommentsFragment.setPager(goodsID);
                titelShowOrhide(0);
                break;
            case R.id.rb_goodsDetail://图文详情
                Logger.i("图文详情");
                mDetailFragment.onCheckedChanged(group, checkedId);
                break;
            case R.id.rb_goodsNorms://参数、规格
                Logger.i("参数、规格");
                mDetailFragment.onCheckedChanged(group, checkedId);
                break;
            case R.id.rb_goodsRelated://相关商品
                Logger.i("相关商品");
                mDetailFragment.onCheckedChanged(group, checkedId);
                break;
        }
    }

    @Override
    /**
     * 商品是否被收藏
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mPresenter.mRequestUtils.doCollectGoods(goodsID);
        } else {
            mPresenter.mRequestUtils.doUnCollectGoods(goodsID);
        }
    }

    @Override
    /**
     * 翻页成功
     */
    public void onPageChanged(int derection) {
        Logger.i("翻页成功=" + derection);
        mOnwPager = derection;
        switch (derection) {
            case 1://第二页
                mViewHolder.rg_goodsMeun.setClickable(true);
                mViewHolder.rg_goodsMeun.setVisibility(View.VISIBLE);
                mViewHolder.rb_goodsDetail.setChecked(true);
                titelShowOrhide(mOnwPager);
                break;
            case 0://第一页
                mViewHolder.rg_goodsMeun.setClickable(false);
                mViewHolder.rg_goodsMeun.setVisibility(View.GONE);
                titelShowOrhide(mOnwPager);
                break;

        }
    }

    public void titelShowOrhide(int code) {
        if (code == 0) {
            mViewHolder.rg_goodsTab.setClickable(true);
            mViewHolder.rg_goodsTab.setVisibility(View.VISIBLE);
            mViewHolder.tv_title.setVisibility(View.GONE);
        } else {
            mViewHolder.rg_goodsTab.setClickable(false);
            mViewHolder.rg_goodsTab.setVisibility(View.GONE);
            mViewHolder.tv_title.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onInintData(Bundle bundle) {
        goodsID = bundle.getString("goodsID", "-1");
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mActionBar.setClickable(false);
        activity.mActionBar.setVisibility(View.GONE);
    }

    @Override
    public GoodsMainPresenter initPresenter() {
        return new GoodsMainPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        startSum--;
        goodsID="";
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setFavorite(String iscollect) {
        if ("1".equals(iscollect)) {
            mViewHolder.cb_favorite.setChecked(true);
        } else {
            mViewHolder.cb_favorite.setChecked(false);
        }
    }

    private class ViewHolder {
        public View rootView;
        public ViewPager vp_goodsGetail;
        public RadioGroup rg_goodsTab;
        public RadioButton rb_tabDetail;
        public RadioButton rb_tabNorms;
        public RadioButton rb_tabComments;
        public RadioGroup rg_goodsMeun;

        public RadioButton rb_goodsDetail;
        public RadioButton rb_goodsNorms;
        public RadioButton rb_goodsRelated;
        public ImageView iv_back_btn;
        public View tv_title;
        public ImageView iv_more_btn;
        public ImageView iv_share;
        public View view_menuLine;
        public RelativeLayout view_actionBar;
        public TextView tv_store;
        public CheckBox cb_favorite;
        public TextView tv_cart;
        public TextView tv_addCart;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.vp_goodsGetail = (ViewPager) rootView.findViewById(R.id.vp_goodsGetail);
            this.iv_back_btn = (ImageView) rootView.findViewById(R.id.iv_back_btn);

            this.rg_goodsMeun = (RadioGroup) rootView.findViewById(R.id.rg_goodsMeun);
            this.rb_goodsDetail = (RadioButton) rootView.findViewById(R.id.rb_goodsDetail);
            this.rb_goodsNorms = (RadioButton) rootView.findViewById(R.id.rb_goodsNorms);
            this.rb_goodsRelated = (RadioButton) rootView.findViewById(R.id.rb_goodsRelated);

            this.rg_goodsTab = (RadioGroup) rootView.findViewById(R.id.rg_goodsTab);
            this.rb_tabDetail = (RadioButton) rootView.findViewById(R.id.rb_tabDetail);
            this.rb_tabNorms = (RadioButton) rootView.findViewById(R.id.rb_tabNorms);
            this.rb_tabComments = (RadioButton) rootView.findViewById(R.id.rb_tabComments);

            this.iv_more_btn = (ImageView) rootView.findViewById(R.id.iv_more_btn);
            this.tv_title = rootView.findViewById(R.id.tv_title);
            this.iv_share = (ImageView) rootView.findViewById(R.id.iv_share);
            this.view_menuLine = (View) rootView.findViewById(R.id.view_menuLine);
            this.view_actionBar = (RelativeLayout) rootView.findViewById(R.id.view_actionBar);
            this.tv_store = (TextView) rootView.findViewById(R.id.tv_store);
            this.cb_favorite = (CheckBox) rootView.findViewById(R.id.cb_favorite);
            this.tv_cart = (TextView) rootView.findViewById(R.id.tv_cart);
            this.tv_addCart = (TextView) rootView.findViewById(R.id.tv_addCart);
        }

    }
}

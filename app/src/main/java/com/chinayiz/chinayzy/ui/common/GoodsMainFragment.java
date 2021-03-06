package com.chinayiz.chinayzy.ui.common;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
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
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.views.BadgeView;
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

    //红包商品详情
    public static final int REDPACKET = 0;
    //普通商品详情
    public static final int COMMON = 1;
    public int goodsType = COMMON;
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
    public BadgeView badge1;
    private View v_view;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_goods_main, container, false);
        mFragments = new ArrayList<>(3);
        CommonRequestUtils.getRequestUtils().getShoppingCarCount();
        initViews(mView);
        return mView;
    }

    public GoodsMainFragment() {
    }

    public GoodsMainFragment(int type) {
        this.goodsType = type;
    }

    public static GoodsMainFragment getInstance(int type) {
        return new GoodsMainFragment(type);
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
//        mViewHolder.iv_more_btn.setVisibility(View.VISIBLE);
        Logger.i("分享键隐藏");
        mViewHolder.iv_share.setOnClickListener(this);
        mViewHolder.tv_store.setOnClickListener(this);
        mViewHolder.cb_favorite.setOnCheckedChangeListener(this);
        mViewHolder.tv_cart.setOnClickListener(this);
        mViewHolder.tv_addCart.setOnClickListener(this);
        mPresenter.mRequestUtils = CommonRequestUtils.getRequestUtils();

        v_view = view.findViewById(R.id.v_view);
        remind(v_view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_btn://返回
                Logger.i("返回");
                getActivity().onBackPressed();
                break;
            case R.id.iv_more_btn://分享
                Logger.i("分享");
                if (!TextUtils.isEmpty(goodsID)) {
                    mPresenter.mRequestUtils.getGoodsShareInfo(goodsID);
                }
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
                    if (goodsType == COMMON) {
                        //普通购物车
                        Skip.toShopCart(getActivity(), ShopCartFragment.COMMON);
                    } else if (goodsType == REDPACKET) {
                        //红包购物车
                        Skip.toShopCart(getActivity(), ShopCartFragment.REDPACKET);
                    }
                } else {
                    BaseActivity.showToast(getActivity(), "您还未登录");
                }
                break;
            case R.id.tv_addCart://添加购物车
                Logger.i("点击添加购物车");
                if (goodsStandard2 == null) {
                    if (mPresenter.model == null) return;
                    if (goodsType == COMMON){
                        goodsStandard2 = new GoodsStandard2(getActivity(), mPresenter.model.getData().getGoodsstandardid(), mPresenter.model.getData().getShopid(), mPresenter.model.getData().getGoodsid(),COMMON);
                    }else if (goodsType == REDPACKET){
                        goodsStandard2 = new GoodsStandard2(getActivity(), mPresenter.model.getData().getGoodsstandardid(), mPresenter.model.getData().getShopid(), mPresenter.model.getData().getGoodsid(),REDPACKET);
                    }
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
        goodsType = bundle.getInt("goodType", COMMON);
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
        goodsID = "";
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

    /**
     * 购物车小红点
     */
    public void remind(View view) { //BadgeView的具体使用
        badge1 = new BadgeView(getActivity(), view);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge1.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 显示的位置.右上角,BadgeView.POSITION_BOTTOM_LEFT,下左，还有其他几个属性
        badge1.setTextColor(Color.WHITE); // 文本颜色
        badge1.setBadgeBackgroundColor(ContextCompat.getColor(getActivity(), R.color.classifyText_pre)); // 提醒信息的背景颜色，自己设置
        badge1.setTextSize(10); // 文本大小
        //badge1.setBadgeMargin(3, 3); // 水平和竖直方向的间距
        badge1.setBadgeMargin(5); //各边间隔
    }

    /**
     * 购物车的数量
     */
    public void getCount(int count) {
        // badge1.toggle(); //显示效果，如果已经显示，则影藏，如果影藏，则显示
        if (count == 0) {
            badge1.hide();//影藏显示
        } else if (count > 99) {
            badge1.setText("99+");
        } else {
            badge1.setText("" + mPresenter.count); // 需要显示的提醒类容
            Logger.i("购物车数量........." + mPresenter.count);
            badge1.show();// 只有显示
        }
    }
}

package com.chinayiz.chinayzy.ui.common;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.CreatePhotosHolder;
import com.chinayiz.chinayzy.adapter.viewHolder.GoodsDetailPagerAdapter;
import com.chinayiz.chinayzy.adapter.viewHolder.GoodsHolder;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.GoodsPresenter;
import com.chinayiz.chinayzy.ui.fragment.CommentsFragment;
import com.chinayiz.chinayzy.ui.fragment.GoodsListFragment;
import com.chinayiz.chinayzy.views.GlideCircleTransform;
import com.chinayiz.chinayzy.views.NoScrollViewPager;
import com.chinayiz.chinayzy.views.goodsParticular.BotContentPage;
import com.chinayiz.chinayzy.views.goodsParticular.McoySnapPageLayout;
import com.chinayiz.chinayzy.views.goodsParticular.TopDetailInfoPage;
import com.chinayiz.chinayzy.widget.GoodsStandard2;
import com.chinayiz.chinayzy.widget.ShareDialog;
import com.hedgehog.ratingbar.RatingBar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/17 19:20
 * Class GoodsFragment
 */

public class GoodsFragment extends BaseFragment<GoodsPresenter> implements BotContentPage.ScrollListener
        , McoySnapPageLayout.PageSnapedListener, View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener
        , CommentsFragment.StateListener {
    public final static String REFRESH = "GoodsActivity_refresh";
    public GoodsListFragment mGoodssFragment;
    public CommentsFragment mCommentFragment;
    public PartWebFragment mPartWebFragment;
    public List<Fragment> fragments=new ArrayList<>();
    private GoodsDetailPagerAdapter mPagerAdapter;
    public McoySnapPageLayout mMlMcoySnapPageLayout = null;
    private FragmentManager mFragmentManager;
    private TopDetailInfoPage mTopPage = null;
    private BotContentPage mBottomPage = null;
    private NoScrollViewPager mViewPager;
    private GoodsHolder mGoodsHolder = null;
    private List<String> urls = new ArrayList<>();
    private boolean isShowComments = false;
    private CheckBox mRbFavorite;
    private GoodsDetailModel model = null;
    private ImageView mIvBackBtn;
    private int sumComment = 0;
    private View mPregress;
    public String goodsID, storeID;
    private int comitsID = 0;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_goods, container, false);
        mFragmentManager=getFragmentManager();
        initViews(view);
        return view;
    }


    @Override
    public void onInintData(Bundle bundle) {
        goodsID = bundle.getString("goodsID", "-1");
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mActionBar.setVisibility(View.GONE);
    }

    @Override
    public GoodsPresenter initPresenter() {
        return new GoodsPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoodssFragment = null;
        mCommentFragment = null;
        mPartWebFragment = null;
    }

    @Override
    public void onSnapedCompleted(int derection) {
        if (derection == 1) {
            mGoodsHolder.mPullLoadMore.setText("继续下拉，返回上一页");
            mGoodsHolder.mRbDetail.setChecked(true);
        } else {
            mGoodsHolder.mPullLoadMore.setText("继续上拉，查看图文详情");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_server:
                Logger.i("客服");
                break;
            case R.id.tv_store:
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("storeID", storeID);
                startActivity(intent);
                break;
            case R.id.tv_addCart:
                Logger.i("加入购物车");
                if (model != null) {
                    GoodsStandard2 goodsStandard2 = new GoodsStandard2(getActivity(), model.getData());
                    goodsStandard2.show();
                }
                break;
            case R.id.iv_back_btn:
                Logger.i("返回");
                if (isShowComments) {
                  mFragmentManager
                          .beginTransaction()
                          .remove(mCommentFragment).commit();
                    Logger.i("移除评论列表");
                }else {
                    Logger.i("退出详情");
                    getActivity().onBackPressed();
                }
                break;
            case R.id.iv_more_btn:
                Logger.i("更多消息");
                break;
            case R.id.iv_cart_btn:
                Logger.i("购物车");
                break;
            case R.id.tv_share_btn:
                Logger.i("分享");
                ShareDialog dialog = new ShareDialog(getActivity());
                dialog.show();
                break;
            case R.id.tv_goodstype:
                Logger.i("选择套餐");
                if (model != null) {
                    GoodsStandard2 goodsStandard2 = new GoodsStandard2(getActivity(), model.getData());
                    goodsStandard2.show();
                }
                break;
            case R.id.tv_moreComment://更多评论
                Logger.i("更多评论");
                if (sumComment < 2) {
                    BaseActivity.showToast(getActivity(), "没有更多评论");
                } else {
                    if (mCommentFragment == null) {
                        mCommentFragment = new CommentsFragment();
                        mCommentFragment.setStateListener(this);
                    }
                    mFragmentManager.beginTransaction()
                            .add(R.id.fl_commentList, mCommentFragment, "CommentsFragment")
                            .commit();
                    mPresenter.mRequestUtils.getCommentList(goodsID, "1", "10");
                }
                break;
            case R.id.iv_InStore:
                Logger.i("启动店铺主页");
                Intent intent2 = new Intent(getActivity(), StoreActivity.class);
                intent2.putExtra("storeID", storeID);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_goodsDetail: {
                comitsID = 1;
                Logger.i("图文详情");
                mPartWebFragment.setUrl(Commons.API + Commons.GOODS_PICDETAIL,goodsID);
                mViewPager.setCurrentItem(0);
                break;
            }
            case R.id.rb_goodsNorms: {
                comitsID = 2;
                Logger.i("商品规格");
                mPartWebFragment.setUrl(Commons.API + Commons.GOODS_CPGG,goodsID);
                mViewPager.setCurrentItem(0);
                break;
            }
            case R.id.rb_goodsRelated: {
                Logger.i("相关商品");
                comitsID = 3;
                mViewPager.setCurrentItem(1);
                mPresenter.mRequestUtils.getRelatedGoods(model.getData().getItemcode(), "1", "14");
                break;
            }
        }
    }

    /**
     * @return 子视图是否滑动到顶部
     */
    @Override
    public boolean isAtTop() {
        //判断相关商品视图是否滑到顶部
        switch (comitsID) {
            case 1: {

            }
            case 2: {
                if (mPartWebFragment.getScrollY() <= 1) {
                    return true;
                } else {
                    return false;
                }
            }
            case 3: {
                if (mGoodssFragment != null && mGoodssFragment.getScrllY() <= 1) {
                    return true;
                }
                break;
            }
            default: {
                return false;
            }
        }
        return false;
    }

    /**
     * 是否收藏商品
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (goodsID == null) {
            return;
        }
        if (isChecked) {
            mPresenter.mRequestUtils.doCollectGoods(goodsID);
        } else {
            mPresenter.mRequestUtils.doUnCollectGoods(goodsID);
        }
    }

    /**
     * 设置商品信息
     *
     * @param model
     */
    public void setGoodsInfo(GoodsDetailModel model) {
        this.model=model;
        int sum = 0;
        //设置banner图
        for (String str : model.getData().getGpic().split(",")) {
            urls.add(str);
        }
        mPregress.setVisibility(View.GONE);
        mGoodsHolder.mVpagerBanner.setPages(new CreatePhotosHolder(), urls);
        mGoodsHolder.mTvGoodsTitle.setText(model.getData().getGname());
        mGoodsHolder.mTvGoodsPrice.setText(model.getData().getPrice());
        mGoodsHolder.mTvGoodsPostage.setText("运费：" + model.getData().getCarriage() + "元");
        mGoodsHolder.mTvGoodsSales.setText("月销量：" + model.getData().getSalesvolume());
        mGoodsHolder.mTvGoodsOrigin.setText(model.getData().getProductarea());
        String[] mService = model.getData().getService().split(",");
        for (int i = 0; i < mService.length; i++) {
            mGoodsHolder.mServiceList.get(i).mIvService.setVisibility(View.VISIBLE);
            mGoodsHolder.mServiceList.get(i).mTvService.setVisibility(View.VISIBLE);
            mGoodsHolder.mServiceList.get(i).mTvService.setText(mService[i]);
        }
        mGoodsHolder.mTvCommentCount.setText("（" + String.valueOf(model.getData().getCommentnum()) + "条评论）");
        sumComment = model.getData().getCommentlist().size();
        if (sumComment != 0) {//判断商品是否有评论
            sum = model.getData().getCommentlist().get(0).getDescpoint() +
                    model.getData().getCommentlist().get(0).getDeliverypoint() +
                    model.getData().getCommentlist().get(0).getServicepoint();
            Logger.i("评分总分：=" + sum);
            mGoodsHolder.mRbGoodsGrade.setStar(sum / 3);//设置综合分数
            Glide.with(this).load(model.getData().getCommentlist().get(0).getPic())
                    .transform(new GlideCircleTransform(getActivity()))
                    .into(mGoodsHolder.mIvUserIcon);
            String userName = model.getData().getCommentlist().get(0).getNickname();
            if ("0".equals(model.getData().getCommentlist().get(0).getIsanonymity())) {
                mGoodsHolder.mTvUserName.setText(userName);
            } else {
                char[] chars = userName.toCharArray();
                userName = chars[0] + "***" + chars[chars.length - 1];
                mGoodsHolder.mTvUserName.setText(userName);
            }
            mGoodsHolder.mTvCommentContent.setText(model.getData().getCommentlist().get(0).getCommentscontent());

        } else {
            mGoodsHolder.mRbGoodsGrade.setVisibility(View.GONE);
            mGoodsHolder.mTvCommentContent.setVisibility(View.GONE);
            mGoodsHolder.mTvUserName.setVisibility(View.GONE);
        }
        Glide.with(this).load(model.getData().getSpic())
                .transform(new GlideCircleTransform(getActivity()))
                .into(mGoodsHolder.mIvStoreLogo);
        mGoodsHolder.mTvStoreName.setText(model.getData().getSname());
        if ("0".equals(model.getData().getIsself())) {//是否是自营店铺
            mGoodsHolder.mIvStoreType.setVisibility(View.GONE);
        }
        mGoodsHolder.mTvGoodsCount.setText(String.valueOf(model.getData().getGoodsnum()));
        mGoodsHolder.mTvFansCount.setText(String.valueOf(model.getData().getCollectnum()));
        if ("1".equals(model.getData().getIscollect())) {//是否收藏
            mRbFavorite.setChecked(true);
        }
    }

    /**
     * 评论列表状态改变回调
     *
     * @param stateCode
     */
    @Override
    public void stateChange(int stateCode) {
        if (stateCode == CommentsFragment.START) {
            mMlMcoySnapPageLayout.setVisibility(View.GONE);
            isShowComments=true;
        } else {
            mMlMcoySnapPageLayout.setVisibility(View.VISIBLE);
            isShowComments=false;
        }
    }

    private void initViews(View view) {
        mPartWebFragment=PartWebFragment.newInstance();
        mGoodssFragment=GoodsListFragment.newInstance();
        fragments.add(mPartWebFragment);
        fragments.add(mGoodssFragment);
        mPagerAdapter=new GoodsDetailPagerAdapter(mFragmentManager,fragments);

        mMlMcoySnapPageLayout = (McoySnapPageLayout) view.findViewById(R.id.ml_mcoySnapPageLayout);
        mPregress = view.findViewById(R.id.ll_progress);
        mTopPage = new TopDetailInfoPage(getActivity(), View.inflate(getActivity(), R.layout.goods_one, null), R.id.scolv_goods);
        mBottomPage = new BotContentPage(getActivity(), View.inflate(getActivity(), R.layout.goods_two, null));
        mBottomPage.setScrollListener(this);

        View topView = mTopPage.getRootView();
        initTopPage(topView);

        View bottomView = mBottomPage.getRootView();
        initBottomPage(bottomView);

        mMlMcoySnapPageLayout.setSnapPages(mTopPage, mBottomPage);
        mMlMcoySnapPageLayout.setPageSnapListener(this);

        mIvBackBtn = (ImageView) view.findViewById(R.id.iv_back_btn);
        ImageView mIvMoreBtn = (ImageView) view.findViewById(R.id.iv_more_btn);
        ImageView mIvCartBtn = (ImageView) view.findViewById(R.id.iv_cart_btn);
        TextView mTvServer = (TextView) view.findViewById(R.id.tv_server);
        TextView mTvStore = (TextView) view.findViewById(R.id.tv_store);
        TextView mTvAddCart = (TextView) view.findViewById(R.id.tv_addCart);
        mRbFavorite = (CheckBox) view.findViewById(R.id.cb_favorite);
        mRbFavorite.setOnCheckedChangeListener(this);
        mIvBackBtn.setOnClickListener(this);
        mIvMoreBtn.setOnClickListener(this);
        mIvCartBtn.setOnClickListener(this);
        mTvServer.setOnClickListener(this);
        mTvStore.setOnClickListener(this);
        mTvAddCart.setOnClickListener(this);
    }

    private void initTopPage(View topView) {
        mGoodsHolder = new GoodsHolder();
        mGoodsHolder.mVpagerBanner = (ConvenientBanner) topView.findViewById(R.id.vpager_Banner);
        mGoodsHolder.mVpagerBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});


        mGoodsHolder.mTvShareBtn = (TextView) topView.findViewById(R.id.tv_share_btn);
        mGoodsHolder.mTvGoodsTitle = (TextView) topView.findViewById(R.id.tv_goodsTitle);
        mGoodsHolder.mTvGoodsPrice = (TextView) topView.findViewById(R.id.tv_goodsPrice);
        mGoodsHolder.mTvGoodsPostage = (TextView) topView.findViewById(R.id.tv_goodsPostage);
        mGoodsHolder.mTvGoodsSales = (TextView) topView.findViewById(R.id.tv_goodsSales);
        mGoodsHolder.mTvGoodsOrigin = (TextView) topView.findViewById(R.id.tv_goodsOrigin);
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service1)
                , (TextView) topView.findViewById(R.id.tv_service1)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service2)
                , (TextView) topView.findViewById(R.id.tv_service2)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service3)
                , (TextView) topView.findViewById(R.id.tv_service3)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service4)
                , (TextView) topView.findViewById(R.id.tv_service4)));
        mGoodsHolder.mTvGoodstype = (TextView) topView.findViewById(R.id.tv_goodstype);
        mGoodsHolder.mTvCommentCount = (TextView) topView.findViewById(R.id.tv_CommentCount);
        mGoodsHolder.mRbGoodsGrade = (RatingBar) topView.findViewById(R.id.rb_goodsGrade);
        mGoodsHolder.mIvUserIcon = (ImageView) topView.findViewById(R.id.iv_UserIcon);
        mGoodsHolder.mTvUserName = (TextView) topView.findViewById(R.id.tv_UserName);
        mGoodsHolder.mTvCommentContent = (TextView) topView.findViewById(R.id.tv_CommentContent);
        mGoodsHolder.mTvMoreComment = (TextView) topView.findViewById(R.id.tv_moreComment);
        mGoodsHolder.mIvStoreLogo = (ImageView) topView.findViewById(R.id.iv_storeLogo);
        mGoodsHolder.mTvStoreName = (TextView) topView.findViewById(R.id.tv_storeName);
        mGoodsHolder.mIvStoreType = (ImageView) topView.findViewById(R.id.iv_StoreType);
        mGoodsHolder.mIvInStore = (ImageView) topView.findViewById(R.id.iv_InStore);
        mGoodsHolder.mTvGoodsCount = (TextView) topView.findViewById(R.id.tv_goodsCount);
        mGoodsHolder.mTvFansCount = (TextView) topView.findViewById(R.id.tv_fansCount);
        mGoodsHolder.mPullLoadMore = (TextView) topView.findViewById(R.id.tv_warn);

        mGoodsHolder.mTvShareBtn.setOnClickListener(this);
        mGoodsHolder.mTvGoodstype.setOnClickListener(this);
        mGoodsHolder.mTvMoreComment.setOnClickListener(this);
        mGoodsHolder.mIvInStore.setOnClickListener(this);
    }

    private void initBottomPage(View bottomView) {
        mGoodsHolder.mGoodsMeun = (RadioGroup) bottomView.findViewById(R.id.rg_goodsMeun);
        mGoodsHolder.mRbDetail = (RadioButton) mGoodsHolder.mGoodsMeun.findViewById(R.id.rb_goodsDetail);
        mViewPager= (NoScrollViewPager) bottomView.findViewById(R.id.vp_goodsPicDetail);
        mViewPager.setAdapter(mPagerAdapter);
        mGoodsHolder.mGoodsMeun.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

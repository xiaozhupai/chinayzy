package com.chinayiz.chinayzy.ui.common;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.CreatePhotosHolder;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.adapter.viewHolder.GoodsHolder;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.GoodsPresenter;
import com.chinayiz.chinayzy.ui.fragment.CommentsFragment;
import com.chinayiz.chinayzy.views.GlideCircleTransform;
import com.chinayiz.chinayzy.views.goodsDetail.GradationScrollView;
import com.chinayiz.chinayzy.views.goodsDetail.NoScrollGridView;
import com.chinayiz.chinayzy.views.goodsDetail.ScrollViewContainer;
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

public class GoodsFragment extends BaseFragment<GoodsPresenter> implements
        ScrollViewContainer.PageChangeListener, CompoundButton.OnCheckedChangeListener,
        View.OnClickListener, CommentsFragment.StateListener,
        GradationScrollView.ScrollViewListener, RadioGroup.OnCheckedChangeListener {
    /**
     * 刷新数据
     */
    public final static String REFRESH = "GoodsActivity_refresh";
    public CommentsFragment mCommentFragment;
    private List<String> urls = new ArrayList<>();
    private FragmentManager mFragmentManager;
    public ScrollViewContainer mScroll_Group;
    public GoodsHolder mGoodsHolder = null;
    private boolean isShowComments = false;
    private GoodsStandard2 goodsStandard2;
    private boolean isFristLod=true;
    private GoodsDetailModel model = null;
    private CheckBox mRbFavorite;
    private ImageView mIvBackBtn;
    private int height;
    public View mPregress;
    public String goodsID, storeID;
    private int sumComment = 0;
    private int pegerIdex=0;
    private int comitsID = 1;


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        mFragmentManager = getFragmentManager();
        mGoodsHolder = new GoodsHolder();
        initHolder(view);
        return view;
    }
    private void initHolder(View view) {
        //外层
        mPregress=view.findViewById(R.id.ll_progress);
        mScroll_Group = (ScrollViewContainer) view.findViewById(R.id.scroll_Group);
        mScroll_Group.setPageChangeListener(this);

        //标题栏/底部操作栏 控件初始化
        mIvBackBtn = (ImageView) view.findViewById(R.id.iv_back_btn);
        mGoodsHolder.mActionBar=view.findViewById(R.id.view_actionBar);
        ImageView mIvMoreBtn = (ImageView) view.findViewById(R.id.iv_more_btn);
        ImageView mIvCartBtn = (ImageView) view.findViewById(R.id.iv_cart_btn);
        mGoodsHolder.mTitel = (TextView) view.findViewById(R.id.tv_title);
        mGoodsHolder.isSelf=view.findViewById(R.id.view_isSelf);
        mGoodsHolder.mLine=view.findViewById(R.id.view_menuLine);
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
        //top页控件初始化
        mGoodsHolder.mScrollView = (GradationScrollView) view.findViewById(R.id.scroll_top);

        mGoodsHolder.mVpagerBanner = (ConvenientBanner) view.findViewById(R.id.vpager_Banner);
        mGoodsHolder.mVpagerBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});

        ViewTreeObserver vto =  mGoodsHolder.mVpagerBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGoodsHolder.mActionBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height =  mGoodsHolder.mVpagerBanner.getHeight();
                mGoodsHolder.mScrollView.setScrollViewListener(GoodsFragment.this);
            }
        });

        mGoodsHolder.mTvShareBtn = (TextView) view.findViewById(R.id.tv_share_btn);
        mGoodsHolder.mTvGoodsTitle = (TextView) view.findViewById(R.id.tv_goodsTitle);
        mGoodsHolder.mTvGoodsPrice = (TextView) view.findViewById(R.id.tv_goodsPrice);
        mGoodsHolder.mTvGoodsPostage = (TextView) view.findViewById(R.id.tv_goodsPostage);
        mGoodsHolder.mTvGoodsSales = (TextView) view.findViewById(R.id.tv_goodsSales);
        mGoodsHolder.mTvGoodsOrigin = (TextView) view.findViewById(R.id.tv_goodsOrigin);
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) view.findViewById(R.id.iv_service1)
                , (TextView) view.findViewById(R.id.tv_service1)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) view.findViewById(R.id.iv_service2)
                , (TextView) view.findViewById(R.id.tv_service2)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) view.findViewById(R.id.iv_service3)
                , (TextView) view.findViewById(R.id.tv_service3)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) view.findViewById(R.id.iv_service4)
                , (TextView) view.findViewById(R.id.tv_service4)));

        mGoodsHolder.mTvGoodstype = (TextView) view.findViewById(R.id.tv_goodstype);
        mGoodsHolder.mTvCommentCount = (TextView) view.findViewById(R.id.tv_CommentCount);
        mGoodsHolder.mRbGoodsGrade = (RatingBar) view.findViewById(R.id.rb_goodsGrade);
        mGoodsHolder.mIvUserIcon = (ImageView) view.findViewById(R.id.iv_UserIcon);
        mGoodsHolder.mTvUserName = (TextView) view.findViewById(R.id.tv_UserName);
        mGoodsHolder.mTvCommentContent = (TextView) view.findViewById(R.id.tv_CommentContent);
        mGoodsHolder.mTvMoreComment = (TextView) view.findViewById(R.id.tv_moreComment);
        mGoodsHolder.mIvStoreLogo = (ImageView) view.findViewById(R.id.iv_storeLogo);
        mGoodsHolder.mTvStoreName = (TextView) view.findViewById(R.id.tv_storeName);
        mGoodsHolder.mIvStoreType = (ImageView) view.findViewById(R.id.iv_StoreType);
        mGoodsHolder.mIvInStore = (ImageView) view.findViewById(R.id.iv_InStore);
        mGoodsHolder.mTvGoodsCount = (TextView) view.findViewById(R.id.tv_goodsCount);
        mGoodsHolder.mTvFansCount = (TextView) view.findViewById(R.id.tv_fansCount);
        mGoodsHolder.mPullLoadMore = (TextView) view.findViewById(R.id.tv_warn);

        mGoodsHolder.mTvShareBtn.setOnClickListener(this);
        mGoodsHolder.mTvGoodstype.setOnClickListener(this);
        mGoodsHolder.mTvMoreComment.setOnClickListener(this);
        mGoodsHolder.mIvInStore.setOnClickListener(this);
        //bottom控件初始化
        mGoodsHolder.mGoodsMeun = (RadioGroup) view.findViewById(R.id.rg_goodsMeun);
        mGoodsHolder.mWebView = (WebView) view.findViewById(R.id.wv_view);
        mGoodsHolder.mRbDetail = (RadioButton) mGoodsHolder.mGoodsMeun.findViewById(R.id.rb_goodsDetail);
        mGoodsHolder.mAdapter=new GoodsDetailGridAdpter(getActivity());
        mGoodsHolder.mGridView = (NoScrollGridView) view.findViewById(R.id.gv_goodsList);
        mGoodsHolder.mGridView.setAdapter(mGoodsHolder.mAdapter);
        mGoodsHolder.mGoodsMeun.setClickable(false);
        WebSettings settings = mGoodsHolder.mWebView.getSettings();
        // 缩放至屏幕的大小/将图片调整到适合webview的大小
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        //开启 Application Caches 功能
        settings.setAppCacheEnabled(true);
        //支持js交互
        settings.setJavaScriptEnabled(true);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //多窗口
        settings.supportMultipleWindows();
        WebViewClient client = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mPregress.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mPregress.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        };
        mGoodsHolder.mWebView.setWebViewClient(client);
    }



    /**
     * 设置商品信息
     *
     * @param model
     */
    public void setGoodsInfo(GoodsDetailModel model) {
        this.model = model;
        isFristLod=false;
        //设置banner图
        urls.clear();
        for (String str : model.getData().getGpic().split(",")) {
            urls.add(str);
        }
        mGoodsHolder.mVpagerBanner.setPages(new CreatePhotosHolder(), urls);
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
            mGoodsHolder.mRbGoodsGrade.setStar(model.getData().getCommentlist().get(0).getDescpoint());//设置综合分数
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
            mGoodsHolder.isSelf.setVisibility(View.GONE);
            mGoodsHolder.mTvGoodsTitle.setText(model.getData().getGname());
        }else {
            mGoodsHolder.mTvGoodsTitle.setText("\t\t\t\t\t\t"+model.getData().getGname());
        }
        mGoodsHolder.mTvGoodsCount.setText(String.valueOf(model.getData().getGoodsnum()));
        mGoodsHolder.mTvFansCount.setText(String.valueOf(model.getData().getCollectnum()));
        mPregress.setVisibility(View.GONE);
        if ("1".equals(model.getData().getIscollect())) {//是否收藏
            mRbFavorite.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_server:
                break;
            case R.id.tv_store:
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("storeID", storeID);
                startActivity(intent);
                break;
            case R.id.tv_addCart:
                if (model != null) {
                    if (goodsStandard2==null){
                        goodsStandard2=new GoodsStandard2(getActivity(), model.getData());
                    }
                    goodsStandard2.show();
                }
                break;
            case R.id.iv_back_btn:
                if (isShowComments) {
                    mFragmentManager
                            .beginTransaction()
                            .remove(mCommentFragment).commit();
                    Logger.i("移除评论列表");
                } else {
                    Logger.i("退出详情");
                    mActivity.finishFragment();
                }
                break;
            case R.id.iv_more_btn:
                Logger.i("更多消息");
                break;
            case R.id.iv_cart_btn:
                Skip.toShopCart(getActivity());
                break;
            case R.id.tv_share_btn:
                Logger.i("分享");
                ShareDialog dialog = new ShareDialog(getActivity());
                dialog.show();
                break;
            case R.id.tv_goodstype:
                Logger.i("选择套餐");
                if (model != null) {
                    if (model != null) {
                        if (goodsStandard2==null){
                            goodsStandard2=new GoodsStandard2(getActivity(), model.getData());
                        }
                        goodsStandard2.show();
                    }
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
                    mPresenter.mRequestUtils.getCommentList(goodsID, "1", "20");
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
    //bottom 菜单选择
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_goodsDetail: {
                comitsID = 1;
                clickPicGateil(1);
                break;
            }
            case R.id.rb_goodsNorms: {
                comitsID = 2;
                clickPicGateil(0);
                break;
            }
            case R.id.rb_goodsRelated: {
                comitsID = 3;
                mPregress.setVisibility(View.VISIBLE);
                mGoodsHolder.mWebView.setClickable(false);
                mGoodsHolder.mWebView.setVisibility(View.GONE);
                mGoodsHolder.mGridView.setClickable(true);
                mGoodsHolder.mGridView.setVisibility(View.VISIBLE);
                mPresenter.mRequestUtils.getRelatedGoods(model.getData().getItemcode(), "1", "14");
                break;
            }
        }

    }

    /**
     * 点击图文详情/商品规格
     */
    private void clickPicGateil(int code) {
        String url;
        if(code==1){
            url=Commons.API+Commons.GOODS_PICDETAIL;
        }else {
            url=Commons.API+Commons.GOODS_CPGG;
        }
        mGoodsHolder.mWebView.setClickable(true);
        mGoodsHolder.mWebView.setVisibility(View.VISIBLE);
        mGoodsHolder.mGridView.setClickable(false);
        mGoodsHolder.mGridView.setVisibility(View.GONE);
        mGoodsHolder.mWebView.loadUrl(url+"?goodsid="+goodsID);
    }

    @Override
    public void onPageChanged(int derection) {
        if (derection==1) {
            if (pegerIdex!=1){//防止翻页失败重新加载数据
                mGoodsHolder.mTitel.setText("商品详情");
                mGoodsHolder.mRbDetail.setChecked(true);
                clickPicGateil(1);
                mGoodsHolder.mLine.setVisibility(View.VISIBLE);
                mGoodsHolder.mGoodsMeun.setClickable(true);
                mGoodsHolder.mGoodsMeun.setVisibility(View.VISIBLE);
                mGoodsHolder.mGoodsMeun.setOnCheckedChangeListener(this);
                pegerIdex=1;
            }
        }else {
            if (pegerIdex!=0){
                mGoodsHolder.mTitel.setText("");
                mGoodsHolder.mGoodsMeun.setClickable(false);
                mGoodsHolder.mGoodsMeun.setVisibility(View.GONE);
                mGoodsHolder.mLine.setVisibility(View.GONE);
                pegerIdex=0;
            }
        }
    }

    @Override
    public int getScorllY() {
        if (comitsID==1||comitsID==2){
            Logger.i("大英="+mGoodsHolder.mWebView.getScrollY());
            return (int) mGoodsHolder.mWebView.getScaleY();
        }else {
            return mGoodsHolder.mGridView.getScrollY();
        }
    }

    @Override
    public GoodsPresenter initPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            mGoodsHolder.mActionBar.setBackgroundColor(Color.argb((int) 0, 255,255,255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            mGoodsHolder.mActionBar.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
        } else {    //滑动到banner下面设置普通颜色
            mGoodsHolder.mActionBar.setBackgroundColor(Color.argb((int) 255, 255,255,255));
        }
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mActionBar.setVisibility(View.GONE);
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

    @Override
    public void stateChange(int stateCode) {
        if (stateCode == CommentsFragment.START) {
            mScroll_Group.setVisibility(View.GONE);
            isShowComments = true;
        } else {
            mScroll_Group.setVisibility(View.VISIBLE);
            isShowComments = false;
        }
    }

    @Override
    public void onInintData(Bundle bundle) {
        goodsID = bundle.getString("goodsID", "-1");
        Logger.i("商品详情onInintData="+goodsID);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFristLod) {
            urls.clear();
            mPresenter.mRequestUtils.getGoodsDetail(goodsID);
        }
        Logger.i("商品详情=onResume="+goodsID);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoodsHolder.mWebView.destroy();
        mGoodsHolder=null;
        mFragmentManager=null;
    }
}

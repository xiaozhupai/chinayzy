package com.chinayiz.chinayzy.ui.fragment;

import android.app.FragmentManager;
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
import android.widget.RatingBar;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.GoodsHolder;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.Goods_Presenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.views.goodsParticular.BotContentPage;
import com.chinayiz.chinayzy.views.goodsParticular.McoyScrollView;
import com.chinayiz.chinayzy.views.goodsParticular.McoySnapPageLayout;
import com.chinayiz.chinayzy.views.goodsParticular.TopDetailInfoPage;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.widget.ShareDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/16 17:01
 * Class GoodsFragment 商品详情页
 */
public class GoodsFragment extends BaseFragment<Goods_Presenter> implements View.OnClickListener
        , McoySnapPageLayout.PageSnapedListener, CompoundButton.OnCheckedChangeListener
        , RadioGroup.OnCheckedChangeListener, BotContentPage.ScrollListener, FragmentManager.OnBackStackChangedListener {
    public String goodsID;
    public String storeID;
    public String goodsCode;
    public boolean isShowComments=false;
    public int sumComment=0;
    public int comitsID=0;
    public CheckBox mRbFavorite;
    private GoodsListFragment mListFragment;
    public CommentsFragment mCommentFragment;
    private McoySnapPageLayout mMlMcoySnapPageLayout=null;
    public PullToRefreshLayout mRefreshLayout;
    private TopDetailInfoPage mTopPage = null;
    private BotContentPage mBottomPage = null;
    public GoodsHolder mGoodsHolder=null;
    private McoyScrollView mMcoyScrollView;
    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_goodsdetail,container,false);
        init(view);
        return view;
    }
    private void init(View view) {
        mMlMcoySnapPageLayout = (McoySnapPageLayout) view.findViewById(R.id.ml_mcoySnapPageLayout);
        mTopPage=new TopDetailInfoPage(getActivity(),View.inflate(getActivity(),R.layout.goods_one,null),R.id.scolv_goods);
        mBottomPage=new BotContentPage(getActivity(),View.inflate(getActivity(),R.layout.goods_two,null));
        mBottomPage.setScrollListener(this);

        View topView=mTopPage.getRootView();
        mMcoyScrollView=mTopPage.getMcoyScrollView();
        initTopPage(topView);

        View bottomView=mBottomPage.getRootView();
        initBottomPage(bottomView);

        mMlMcoySnapPageLayout.setSnapPages(mTopPage,mBottomPage);
        mMlMcoySnapPageLayout.setPageSnapListener(this);

        ImageView mIvBackBtn=(ImageView) view.findViewById(R.id.iv_back_btn);
        ImageView mIvMoreBtn=(ImageView) view.findViewById(R.id.iv_more_btn);
        ImageView mIvCartBtn=(ImageView) view.findViewById(R.id.iv_cart_btn);
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

    @Override
    public void onResume() {
        mPresenter.mRequestUtils.getGoodsDetail(goodsID);
        super.onResume();
        mMcoyScrollView.scrollTo(0,0);
        if (mListFragment==null){
            mListFragment=new GoodsListFragment(goodsCode);
        }
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR,new ActionBarControlModel(NongYeMainActivity.HIDE_ALL)));
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR,new ActionBarControlModel(NongYeMainActivity.SHOW_ALL,"首页",1,0,0,1)));
    }
    private void initTopPage(View topView) {
        mGoodsHolder=new GoodsHolder();
        mRefreshLayout= (PullToRefreshLayout) topView.findViewById(R.id.pull_goodsRefresh);
        //设置刷新加载监听器
        mRefreshLayout.setOnRefreshListener(mPresenter);

        mGoodsHolder.mVpagerBanner = (ConvenientBanner) topView.findViewById(R.id.vpager_Banner);
        mGoodsHolder.mVpagerBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});


        mGoodsHolder.mTvShareBtn = (TextView) topView.findViewById(R.id.tv_share_btn);
        mGoodsHolder.mTvGoodsTitle = (TextView) topView.findViewById(R.id.tv_goodsTitle);
        mGoodsHolder.mTvGoodsPrice = (TextView) topView.findViewById(R.id.tv_goodsPrice);
        mGoodsHolder.mTvGoodsPostage = (TextView) topView.findViewById(R.id.tv_goodsPostage);
        mGoodsHolder.mTvGoodsSales = (TextView) topView.findViewById(R.id.tv_goodsSales);
        mGoodsHolder.mTvGoodsOrigin = (TextView) topView.findViewById(R.id.tv_goodsOrigin);
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service1)
                ,(TextView) topView.findViewById(R.id.tv_service1)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service2)
                ,(TextView) topView.findViewById(R.id.tv_service2)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service3)
                ,(TextView) topView.findViewById(R.id.tv_service3)));
        mGoodsHolder.mServiceList.add(new GoodsHolder.Holder((ImageView) topView.findViewById(R.id.iv_service4)
                ,(TextView) topView.findViewById(R.id.tv_service4)));
        mGoodsHolder.mTvGoodstype = (TextView) topView.findViewById(R.id.tv_goodstype);
        mGoodsHolder.mTvCommentCount = (TextView) topView.findViewById(R.id.tv_CommentCount);
        mGoodsHolder.mRbGoodsGrade = (RatingBar) topView.findViewById(R.id.rb_goodsGrade);
        mGoodsHolder.mIvUserIcon = (ImageView) topView.findViewById(R.id.iv_UserIcon);
        mGoodsHolder.mTvUserName = (TextView)topView.findViewById(R.id.tv_UserName);
        mGoodsHolder.mTvCommentContent = (TextView) topView.findViewById(R.id.tv_CommentContent);
        mGoodsHolder.mTvMoreComment = (TextView) topView.findViewById(R.id.tv_moreComment);
        mGoodsHolder.mIvStoreLogo = (ImageView) topView.findViewById(R.id.iv_storeLogo);
        mGoodsHolder.mTvStoreName = (TextView) topView.findViewById(R.id.tv_storeName);
        mGoodsHolder.mIvStoreType = (ImageView) topView.findViewById(R.id.iv_StoreType);
        mGoodsHolder.mIvInStore = (ImageView) topView.findViewById(R.id.iv_InStore);
        mGoodsHolder.mTvGoodsCount = (TextView) topView.findViewById(R.id.tv_goodsCount);
        mGoodsHolder.mTvFansCount = (TextView) topView.findViewById(R.id.tv_fansCount);
        mGoodsHolder.mPullLoadMore= (TextView) topView.findViewById(R.id.tv_warn);

        mGoodsHolder.mTvShareBtn.setOnClickListener(this);
        mGoodsHolder.mTvGoodstype.setOnClickListener(this);
        mGoodsHolder.mTvMoreComment.setOnClickListener(this);
        mGoodsHolder.mIvInStore.setOnClickListener(this);
    }
    private void initBottomPage(View bottomView) {
        mGoodsHolder.mGoodsMeun= (RadioGroup) bottomView.findViewById(R.id.rg_goodsMeun);
        mGoodsHolder.mRbDetail= (RadioButton) mGoodsHolder.mGoodsMeun.findViewById(R.id.rb_goodsDetail);
        mGoodsHolder.mGoodsMeun.setOnCheckedChangeListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_server:
                Logger.i("客服");
                break;
            case R.id.tv_store:
                startStoreHome();
                break;
            case R.id.tv_addCart:
                Logger.i("加入购物车");
                break;
            case R.id.iv_back_btn:
                Logger.i("返回");
                if (isShowComments){
                    onBack();
                    mMlMcoySnapPageLayout.setVisibility(View.VISIBLE);
                }else {
                    getFragmentManager().beginTransaction().remove(this).commit();
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
                ShareDialog dialog=new ShareDialog(getActivity());
                dialog.show();
                break;
            case R.id.tv_goodstype:
                Logger.i("选择套餐");
                break;
            case R.id.tv_moreComment:
                startComments();
                break;
            case R.id.iv_InStore:
                startStoreHome();
                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        if (mCommentFragment!=null){
            if (mCommentFragment.isAdded()){
                isShowComments=true;
            }
        }
        if (!mCommentFragment.isAdded()||getFragmentManager().getBackStackEntryCount()==1){
                mMlMcoySnapPageLayout.setVisibility(View.VISIBLE);
                isShowComments=false;
        }
    }

    /**
     * 启动评论列表
     */
    private void startComments() {
        if (sumComment==0){
            BaseActivity.showToast(getActivity(),"没有更多评论");
            return;
        }
        mPresenter.mRequestUtils.getCommentList(goodsID,"1","10");
        getFragmentManager().addOnBackStackChangedListener(this);
        if (mCommentFragment==null){
            mCommentFragment=new CommentsFragment();
        }
        mMlMcoySnapPageLayout.setVisibility(View.GONE);
        getFragmentManager().beginTransaction()
                .addToBackStack("GoodsFragment")
                .add(R.id.fl_commentList,mCommentFragment,"CommentsFragment")
                .commit();
    }

    /**
     * 启动店铺首页
     */
    private void startStoreHome() {
        getFragmentManager().beginTransaction()
                .addToBackStack("GoodsFragment")
                .add(R.id.fl_nongye,mStoreHomeFragment,"StoreHomeFragment")
                .commit();
    }

    @Override
    public boolean isAtTop() {
        //判断相关商品视图是否滑到顶部
       switch (comitsID){
           case 1:{

           }
           case 2:{
               if (mActivity.mWebFragment.getScrollY()==0) {
                   return true;
               }else {
                   return false;
               }
           }
           case  3:{
               if (mListFragment!=null&&mListFragment.getScrllY()==0){
                   return true;
               }
               break;
           }
           default:{
               return false;
           }
       }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (goodsID==null){
            if (isChecked){
                buttonView.setChecked(false);
            }else {
                buttonView.setChecked(true);
            }
            return;
        }
        if (isChecked){
            mPresenter.doCollect();
        }else {
            mPresenter.doUnCollect();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (mListFragment==null){
            mListFragment=new GoodsListFragment(goodsCode);
        }
        switch (checkedId){
            case R.id.rb_goodsDetail:{
                comitsID=1;
                Logger.i("图文详情");
                if (goodsID==null){
                    return;
                }
                if (mActivity.mWebFragment.isAdded()){

                    if (mListFragment.isAdded()){
                        getFragmentManager().beginTransaction().hide(mListFragment).show(mActivity.mWebFragment).commit();
                    }else {
                        getFragmentManager().beginTransaction().show(mActivity.mWebFragment).commit();
                    }
                    mActivity.mWebFragment.setWebView(Commons.API+Commons.GOODS_PICDETAIL,goodsID);
                }else {
                    mActivity.mWebFragment.setWebView(Commons.API+Commons.GOODS_PICDETAIL,goodsID);
                    getFragmentManager()
                            .beginTransaction()
                            .add(R.id.fl_goodsDetail,mActivity.mWebFragment,"WebFragments")
                            .commit();
                }
                break;
            }
            case R.id.rb_goodsNorms:{
                comitsID=2;
                Logger.i("参数信息");
                if (mActivity.mWebFragment.isAdded()){
                    if (mListFragment.isAdded()){
                        getFragmentManager().beginTransaction().hide(mListFragment).show(mActivity.mWebFragment).commit();
                    }else {
                        getFragmentManager().beginTransaction().show(mActivity.mWebFragment).commit();
                    }
                    mActivity.mWebFragment.setWebView(Commons.API+Commons.GOODS_CPGG,goodsID);
                }else {
                    getFragmentManager()
                            .beginTransaction()
                            .add(R.id.fl_goodsDetail,mActivity.mWebFragment,"WebFragments")
                            .commit();
                    mActivity.mWebFragment.setWebView(Commons.API+Commons.GOODS_CPGG,goodsID);
                }
                break;
            }
            case R.id.rb_goodsRelated:{
                Logger.i("相关商品");
                comitsID=3;
                if (goodsCode==null){
                    return;
                }
                CommonRequestUtils.getRequestUtils().getRelatedGoods(goodsCode, "1", "14");
                if (mListFragment.isAdded()){
                    if (mActivity.mWebFragment.isAdded()) {
                        getFragmentManager().beginTransaction().hide(mActivity.mWebFragment).show(mListFragment).commit();
                    }else {
                        getFragmentManager().beginTransaction().show(mListFragment).commit();
                    }

                }else {
                    getFragmentManager()
                            .beginTransaction()
                            .add(R.id.fl_goodsDetail,mListFragment,"ListFragment")
                            .commit();
                }
                break;
            }
        }
    }

    @Override
    public Goods_Presenter initPresenter() {
        return new Goods_Presenter();
    }

    @Override
    public void onSnapedCompleted(int derection) {
        if (derection==1){
            mGoodsHolder.mPullLoadMore.setText("继续下拉，返回上一页");
            mGoodsHolder.mRbDetail.setChecked(true);
        }else {
            mGoodsHolder.mPullLoadMore.setText("继续上拉，查看图文详情");
        }
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
        getFragmentManager().removeOnBackStackChangedListener(this);
        getFragmentManager().popBackStack();
        mListFragment=null;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

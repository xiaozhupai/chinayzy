package com.chinayiz.chinayzy.ui.common;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.CreatePhotosHolder;
import com.chinayiz.chinayzy.adapter.viewHolder.GoodsHolder;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.ui.fragment.CommentsFragment;
import com.chinayiz.chinayzy.ui.fragment.GoodsListFragment;
import com.chinayiz.chinayzy.views.GlideCircleTransform;
import com.chinayiz.chinayzy.views.goodsParticular.BotContentPage;
import com.chinayiz.chinayzy.views.goodsParticular.McoySnapPageLayout;
import com.chinayiz.chinayzy.views.goodsParticular.TopDetailInfoPage;
import com.chinayiz.chinayzy.widget.GoodsStandard2;
import com.chinayiz.chinayzy.widget.ShareDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends AppCompatActivity implements BotContentPage.ScrollListener
        , McoySnapPageLayout.PageSnapedListener, View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener
        , EventBusCallback, CommentsFragment.StateListener, OnItemClickListener {
    private GoodsListFragment mGoodssFragment;
    private CommentsFragment mCommentFragment;
    private PartWebFragment mPartWebFragment;
    private CommonRequestUtils mRequestUtils;
    private McoySnapPageLayout mMlMcoySnapPageLayout = null;
    private FragmentManager mFragmentManager;
    private TopDetailInfoPage mTopPage = null;
    private BotContentPage mBottomPage = null;
    private GoodsHolder mGoodsHolder = null;
    private  List<String> urls = new ArrayList<>();
    private boolean isFirst=true;
    private boolean isShowComments;
    private CheckBox mRbFavorite;
    private GoodsDetailModel model = null;
    private ImageView mIvBackBtn;
    private int sumComment = 0;
    private View mPregress;
    private String goodsID;
    private int comitsID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        initView();
        EventBus.getDefault().register(this);
        mFragmentManager = getFragmentManager();
        mRequestUtils = CommonRequestUtils.getRequestUtils();
        mPartWebFragment = new PartWebFragment(Commons.API + Commons.GOODS_PICDETAIL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        goodsID = intent.getStringExtra("goodsID");
        if (goodsID == null) {
            throw new RuntimeException("商品ID为空");
        }
        if (isFirst) {
            mRequestUtils.getGoodsDetail(goodsID);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.GOODS_DETAIL: {//商品详情简要信息
                mMlMcoySnapPageLayout.setVisibility(View.VISIBLE);
                mPregress.setVisibility(View.GONE);
                isFirst=false;
                model = (GoodsDetailModel) message.getData();
                setGoodsInfo(model);
                break;
            }
            case Commons.GOODS_GROUP: {//相关商品

                break;
            }
            case Commons.COMMENT_LIST: {//评论列表
                mMlMcoySnapPageLayout.setVisibility(View.GONE);
                mCommentFragment.setCommentData(message);
                break;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

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
                Logger.i("启动店铺主页");
                break;
            case R.id.tv_addCart:
                Logger.i("加入购物车");

                break;
            case R.id.iv_back_btn:
                Logger.i("返回");
                onBackPressed();
                break;
            case R.id.iv_more_btn:
                Logger.i("更多消息");
                break;
            case R.id.iv_cart_btn:
                Logger.i("购物车");
                break;
            case R.id.tv_share_btn:
                Logger.i("分享");
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
                break;
            case R.id.tv_goodstype:
                Logger.i("选择套餐");
                if (model!=null){
                    GoodsStandard2 goodsStandard2=new GoodsStandard2(this,model.getData());
                    goodsStandard2.show();
                }
                break;
            case R.id.tv_moreComment://更多评论
                Logger.i("更多评论");
                if (sumComment < 2) {
                    BaseActivity.showToast(this, "没有更多评论");
                } else {
                    if (mCommentFragment == null) {
                        mCommentFragment = new CommentsFragment();
                        mCommentFragment.setStateListener(this);
                    }
                    mMlMcoySnapPageLayout.setVisibility(View.GONE);
                    mFragmentManager.beginTransaction()
                            .addToBackStack("CommentsFragment")
                            .add(R.id.fl_commentList, mCommentFragment, "CommentsFragment")
                            .commit();
                    mRequestUtils.getCommentList(goodsID, "1", "10");
                }
                break;
            case R.id.iv_InStore:
                Logger.i("启动店铺主页");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_goodsDetail: {
                comitsID = 1;
                Logger.i("图文详情");
                startWebView(Commons.API + Commons.GOODS_PICDETAIL);
                break;
            }
            case R.id.rb_goodsNorms: {
                comitsID = 2;
                Logger.i("商品规格");
                startWebView(Commons.API + Commons.GOODS_CPGG);
                break;
            }
            case R.id.rb_goodsRelated: {
                Logger.i("相关商品");
                comitsID = 3;
                if (mGoodssFragment == null) {
                    mGoodssFragment = new GoodsListFragment();
                }
                if (mGoodssFragment.isAdded()) {
                    if (mPartWebFragment.isAdded()) {
                        mFragmentManager.beginTransaction().hide(mPartWebFragment).show(mGoodssFragment).commit();
                    }
                } else {
                    mFragmentManager
                            .beginTransaction()
                            .add(R.id.fl_goodsDetail, mGoodssFragment, "mGoodssFragment")
                            .commit();
                }
                CommonRequestUtils.getRequestUtils().getRelatedGoods(model.getData().getItemcode(), "1", "14");
                break;
            }
        }
    }

    private void setGoodsInfo(GoodsDetailModel model) {
        int sum = 0;
        //设置banner图
        for (String str : model.getData().getGpic().split(",")) {
            urls.add(str);
        }
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
            mGoodsHolder.mRbGoodsGrade.setRating(sum / 3);//设置综合分数
            Glide.with(this).load(model.getData().getCommentlist().get(0).getPic())
                    .transform(new GlideCircleTransform(this))
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
                .transform(new GlideCircleTransform(this))
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
     * 商品图文详情/规格
     *
     * @param url
     */
    private void startWebView(String url) {
        if (mPartWebFragment.isAdded()) {
            if (mGoodssFragment != null) {
                if (mGoodssFragment.isAdded()) {
                    mFragmentManager.beginTransaction().hide(mGoodssFragment).show(mPartWebFragment).commit();
                } else {
                    mFragmentManager.beginTransaction().show(mPartWebFragment).commit();
                }
            }
            mPartWebFragment.setUrl(goodsID, url);
        } else {
            mFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_goodsDetail, mPartWebFragment, "mPartWebFragment")
                    .commit();
            mPartWebFragment.setUrl(goodsID, url);
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
                if (mPartWebFragment.getScrollY()<=1) {
                    return true;
                }else {
                    return false;
                }
            }
            case 3: {
                if (mGoodssFragment != null && mGoodssFragment.getScrllY() <=1) {
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
            mRequestUtils.doCollectGoods(goodsID);
        } else {
            mRequestUtils.doUnCollectGoods(goodsID);
        }
    }

    private void initView() {
        mMlMcoySnapPageLayout = (McoySnapPageLayout) findViewById(R.id.ml_mcoySnapPageLayout);
        mPregress = findViewById(R.id.ll_progress);
        mTopPage = new TopDetailInfoPage(this, View.inflate(this, R.layout.goods_one, null), R.id.scolv_goods);
        mBottomPage = new BotContentPage(this, View.inflate(this, R.layout.goods_two, null));
        mBottomPage.setScrollListener(this);

        View topView = mTopPage.getRootView();
        initTopPage(topView);

        View bottomView = mBottomPage.getRootView();
        initBottomPage(bottomView);

        mMlMcoySnapPageLayout.setSnapPages(mTopPage, mBottomPage);
        mMlMcoySnapPageLayout.setPageSnapListener(this);

        mIvBackBtn = (ImageView) findViewById(R.id.iv_back_btn);
        ImageView mIvMoreBtn = (ImageView) findViewById(R.id.iv_more_btn);
        ImageView mIvCartBtn = (ImageView) findViewById(R.id.iv_cart_btn);
        TextView mTvServer = (TextView) findViewById(R.id.tv_server);
        TextView mTvStore = (TextView) findViewById(R.id.tv_store);
        TextView mTvAddCart = (TextView) findViewById(R.id.tv_addCart);
        mRbFavorite = (CheckBox) findViewById(R.id.cb_favorite);
        mRbFavorite.setOnCheckedChangeListener(this);

        mIvBackBtn.setOnClickListener(this);
        mIvMoreBtn.setOnClickListener(this);
        mIvCartBtn.setOnClickListener(this);
        mTvServer.setOnClickListener(this);
        mTvStore.setOnClickListener(this);
        mTvAddCart.setOnClickListener(this);
    }

    /**
     * banner图点击监听
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Logger.i("点击细节图="+position);
            if (urls.size()>0){
                Intent intent = new Intent(this, PictureActivity.class);
                intent.putExtra("images", (ArrayList<String>) urls);//非必须
                intent.putExtra("position", position);
                int[] location = new int[2];
                mIvBackBtn.getLocationOnScreen(location);
                intent.putExtra("locationX", location[0]);//必须
                intent.putExtra("locationY", location[1]);//必须

                intent.putExtra("width", mIvBackBtn.getWidth());//必须
                intent.putExtra("height", mIvBackBtn.getHeight());//必须
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
    }

    private void initTopPage(View topView) {
        mGoodsHolder = new GoodsHolder();
        mGoodsHolder.mVpagerBanner = (ConvenientBanner) topView.findViewById(R.id.vpager_Banner);
        mGoodsHolder.mVpagerBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
//        mGoodsHolder.mVpagerBanner.setOnItemClickListener(this);

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
        mGoodsHolder.mGoodsMeun.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mGoodssFragment = null;
        mCommentFragment = null;
        mPartWebFragment = null;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        switch (message.getEventType()) {
            case EventMessage.NET_EVENT: {
                disposeNetMsg(message);
                break;
            }
            case EventMessage.ERROR_EVENT: {
                Logger.e("商品详情请求错误");
                break;
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (EventMessage.INFORM_EVENT == message.getEventType()) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isShowComments){
            mMlMcoySnapPageLayout.setVisibility(View.VISIBLE);
            mFragmentManager.beginTransaction().remove(mCommentFragment).commit();
        }else {
            finish();
        }
    }

    @Override
    public void stateChange(int stateCode) {
        if (stateCode==CommentsFragment.START){
            isShowComments=false;
        }else {
            isShowComments=true;
        }
    }
}

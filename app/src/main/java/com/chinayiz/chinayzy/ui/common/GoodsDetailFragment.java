package com.chinayiz.chinayzy.ui.common;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.AddressAdaphter;
import com.chinayiz.chinayzy.adapter.CommentListAdapter;
import com.chinayiz.chinayzy.adapter.CreatePhotosHolder;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.CommentListModel;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.NewGoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.views.GlideCircleTransform;
import com.chinayiz.chinayzy.views.goodsDetail.GradationScrollView;
import com.chinayiz.chinayzy.views.goodsDetail.NoScrollGridView;
import com.chinayiz.chinayzy.views.goodsDetail.NoScrollListView;
import com.chinayiz.chinayzy.views.goodsDetail.ScrollViewContainer;
import com.chinayiz.chinayzy.widget.GoodsStandard2;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/13 9:40
 * Class GoodsDetailFrgment 商品详情（上下两部分视图）
 */

public class GoodsDetailFragment extends AbsFragment implements View.OnClickListener {
    private ScrollViewContainer.PageChangeListener mChangeListener;
    /**
     * 点击进入切换到评论列表
     */
    public static final String CLICK_COMMENTS = "tv_moreComment";
    private NewGoodsDetailModel.DataBean mDetailModel;
    private ViewHolder mViewHolder;
    private int mCommentCount;
    private int comitsID;
    private RelatedGoodsModel mRelatedGoodslist;
    private GoodsStandard2 goodsStandard2;
    private boolean isSetData = true;
    private boolean RelatGoodsisSetData = true;
    private String goodsID, address;
    private boolean selectdAddress = false;
    private View mView;

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_goods_detail, container, false);
        initView(mView);
        return mView;
    }

    @Override
    protected void initView(View view) {
        mViewHolder = new ViewHolder();
        mViewHolder.mProgerss = view.findViewById(R.id.ll_progress);
        mViewHolder.vpager_Banner = (ConvenientBanner) view.findViewById(R.id.vpager_Banner);
        mViewHolder.vpager_Banner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        mViewHolder.view_isSelf = (TextView) view.findViewById(R.id.view_isSelf);
        mViewHolder.tv_goodsTitle = (TextView) view.findViewById(R.id.tv_goodsTitle);
        mViewHolder.tv_goodsPrice = (TextView) view.findViewById(R.id.tv_goodsPrice);
        mViewHolder.tv_dobPrice = (TextView) view.findViewById(R.id.tv_dobPrice);
        mViewHolder.iv_goodsRMB = (ImageView) view.findViewById(R.id.iv_goodsRMB);

        mViewHolder.services.add((TextView) view.findViewById(R.id.tv_service1));
        mViewHolder.services.add((TextView) view.findViewById(R.id.tv_service2));
        mViewHolder.services.add((TextView) view.findViewById(R.id.tv_service3));

        mViewHolder.ll_servicLis = (LinearLayout) view.findViewById(R.id.ll_servicLis);
        mViewHolder.tv_goodstype = (TextView) view.findViewById(R.id.tv_goodstype);
        mViewHolder.tv_deals = (TextView) view.findViewById(R.id.tv_deals);
        mViewHolder.ll_goodstype = (LinearLayout) view.findViewById(R.id.ll_goodstype);
        mViewHolder.tv_address = (TextView) view.findViewById(R.id.tv_address);
        mViewHolder.tv_yunfei = (TextView) view.findViewById(R.id.tv_yunfei);
        mViewHolder.tv_commentCount = (TextView) view.findViewById(R.id.tv_commentCount);
        mViewHolder.tv_goodComment = (TextView) view.findViewById(R.id.tv_goodComment);
        mViewHolder.tv_goodsComments = (LinearLayout) view.findViewById(R.id.tv_goodsComments);
        mViewHolder.lv_comments = (NoScrollListView) view.findViewById(R.id.lv_comments);
        mViewHolder.tv_moreComment = (TextView) view.findViewById(R.id.tv_moreComment);
        mViewHolder.iv_storeLogo = (ImageView) view.findViewById(R.id.iv_storeLogo);
        mViewHolder.tv_storeName = (TextView) view.findViewById(R.id.tv_storeName);
        mViewHolder.iv_StoreType = (TextView) view.findViewById(R.id.iv_StoreType);
        mViewHolder.iv_InStore = (ImageView) view.findViewById(R.id.iv_InStore);
        mViewHolder.tv_goodsCount = (TextView) view.findViewById(R.id.tv_goodsCount);
        mViewHolder.tv_fansCount = (TextView) view.findViewById(R.id.tv_fansCount);
        mViewHolder.tv_StoreGoodss = (TextView) view.findViewById(R.id.tv_StoreGoodss);
        mViewHolder.gv_Goodss = (NoScrollGridView) view.findViewById(R.id.gv_Goodss);
        mViewHolder.tv_moreGoods = (TextView) view.findViewById(R.id.tv_moreGoods);
        mViewHolder.scroll_top = (GradationScrollView) view.findViewById(R.id.scroll_top);
        mViewHolder.tv_alte = (TextView) view.findViewById(R.id.tv_alte);
        mViewHolder.gv_goodsList = (NoScrollGridView) view.findViewById(R.id.gv_goodsList);
        mViewHolder.scroll_Group = (ScrollViewContainer) view.findViewById(R.id.scroll_Group);
        mViewHolder.wv_view = (WebView) view.findViewById(R.id.wv_view);

        mViewHolder.ll_goodstype.setOnClickListener(this);
        mViewHolder.tv_address.setOnClickListener(this);
        mViewHolder.tv_goodsComments.setOnClickListener(this);
        mViewHolder.tv_moreComment.setOnClickListener(this);
        mViewHolder.iv_InStore.setOnClickListener(this);
        mViewHolder.tv_moreGoods.setOnClickListener(this);

        WebSettings settings = mViewHolder.wv_view.getSettings();
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
                mViewHolder.mProgerss.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mViewHolder.mProgerss.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        };
        mViewHolder.wv_view.setWebViewClient(client);
        mViewHolder.scroll_Group.setPageChangeListener(new ScrollViewContainer.PageChangeListener() {
            @Override
            public void onPageChanged(int derection) {
                mChangeListener.onPageChanged(derection);
                switch (derection) {
                    case 0:
                        mViewHolder.tv_alte.setText("继续上拉，查看商品详情~");
                        break;
                    case 1:
                        mViewHolder.tv_alte.setText("继续下拉，返回上一页~");
                        break;
                }
            }
        });
        mRequestUtils.getGoodsDetail(goodsID);
    }


    public void setChangeListener(ScrollViewContainer.PageChangeListener changeListener) {
        mChangeListener = changeListener;
    }

    public static GoodsDetailFragment getInstance() {
        return new GoodsDetailFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_goodstype://选择套餐
                Logger.i("选择套餐");
                if (goodsStandard2 == null) {
                    goodsStandard2 = new GoodsStandard2(getActivity(), mDetailModel.getGoodsstandardid(), mDetailModel.getShopid(), mDetailModel.getGoodsid());
                }
                goodsStandard2.show();
                break;
            case R.id.tv_address://选择地址
                selectdAddress = true;
                if ("0".equals(APP.sUserid)) {
                    BaseActivity.showToast(getActivity(), "请先进行登录");
                    Skip.toLogin(getActivity());
                } else {
                    if (TextUtils.isEmpty(mDetailModel.getAddress())) {
                        Skip.toAddressList(getActivity());
                    }
                }
                break;
            case R.id.tv_goodsComments://全部评论
            case R.id.tv_moreComment://全部评论
                if (mCommentCount < 1) {
                    BaseActivity.showToast(getActivity(), "没有更多评论");
                } else {
                    EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT, CLICK_COMMENTS, ""));
                }
                break;
            case R.id.iv_InStore://进入店铺
                Logger.i("进入店铺");
                Skip.toStore(getActivity(), mDetailModel.getShopid());
                break;
            case R.id.tv_moreGoods://更多推荐
                Logger.i("更多推荐");
                Skip.toGoodsRecommend(getActivity(), mDetailModel.getItemcode());
                break;
        }
    }

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
                mViewHolder.wv_view.setClickable(false);
                mViewHolder.wv_view.setVisibility(View.GONE);
                mViewHolder.gv_goodsList.setClickable(true);
                mViewHolder.gv_goodsList.setVisibility(View.VISIBLE);
                if (mRelatedGoodslist == null) {
                    mViewHolder.mProgerss.setVisibility(View.VISIBLE);
                    mRequestUtils.getRelatedGoods(mDetailModel.getItemcode(), "1", "14");
                } else {
                    setRelatGoodsList(mRelatedGoodslist);
                }
                break;
            }
        }

    }

    /**
     * 点击图文详情/商品规格
     */
    private void clickPicGateil(int code) {
        String url;
        if (code == 1) {
            url = Commons.API + Commons.GOODS_PICDETAIL;
        } else {
            url = Commons.API + Commons.GOODS_CPGG;
        }
        mViewHolder.wv_view.setClickable(true);
        mViewHolder.wv_view.setVisibility(View.VISIBLE);
        mViewHolder.gv_goodsList.setClickable(false);
        mViewHolder.gv_goodsList.setVisibility(View.GONE);
        mViewHolder.wv_view.loadUrl(url + "?goodsid=" + mDetailModel.getGoodsid());
    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    public void setRelatGoodsList(RelatedGoodsModel relatedGoodsModel) {
        if (!RelatGoodsisSetData) {
            return;
        }
        RelatGoodsisSetData = false;
        mRelatedGoodslist = relatedGoodsModel;
        GoodsDetailGridAdpter detailGridAdpter = new GoodsDetailGridAdpter(getActivity());
        detailGridAdpter.setData(mRelatedGoodslist);
        mViewHolder.gv_goodsList.setAdapter(detailGridAdpter);
        mViewHolder.mProgerss.setVisibility(View.GONE);
    }

    public void setData(NewGoodsDetailModel model) {
        if (!isSetData) {
            return;
        }
        isSetData = false;
        mDetailModel = model.getData();
        goodsID = mDetailModel.getGoodsid();
        List<String> urls = new ArrayList<>();
        for (String str : mDetailModel.getGpic().split(",")) {
            urls.add(str);
        }
        mViewHolder.vpager_Banner.setPages(new CreatePhotosHolder(), urls);
        if ("1".equals(mDetailModel.getIsself())) {//是否自营
            mViewHolder.tv_goodsTitle.setText("\t\t\t\t\t\t" + mDetailModel.getGname());
        } else {
            mViewHolder.view_isSelf.setVisibility(View.GONE);
            mViewHolder.iv_StoreType.setVisibility(View.GONE);
            mViewHolder.tv_goodsTitle.setText(mDetailModel.getGname());
        }
        /**
         * 价格处理段
         */
        setPrice(mDetailModel.getPrice());
        String deale="\t成功购买此商品可获得约 "+ mDetailModel.getRebate()+ " 元分红";
        mViewHolder.tv_deals.setText(deale);
        int cnt = 0;
        int offset = 0;
        while ((offset = mDetailModel.getService().indexOf(",", offset)) != -1) {
            offset = offset + 1;
            cnt++;
        }
        if (cnt == 0) {
            mViewHolder.ll_servicLis.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < cnt; i++) {
                mViewHolder.services.get(i).setVisibility(View.VISIBLE);
            }
        }
        mViewHolder.tv_goodstype.setText(mDetailModel.getStandardname());
        mViewHolder.tv_address.setText("    ");
        mViewHolder.tv_yunfei.setText("");
        mCommentCount = Integer.valueOf(mDetailModel.getCommentnum());
        mViewHolder.tv_commentCount.setText("商品评价（" + mCommentCount + "条评论）");
        if (!"暂无".equals(mDetailModel.getPraise())) {
            mViewHolder.tv_goodComment.setText(mDetailModel.getPraise() );
        }
        if (mDetailModel.getCommentlist().size() == 0) {//判断有没有评论
            mViewHolder.tv_moreComment.setText("暂时没有评论");
        } else {
            List<CommentListModel.DataBean.CommentlistBean> mDataList = new ArrayList<>();
            for (NewGoodsDetailModel.DataBean.CommentlistBean bean : mDetailModel.getCommentlist()) {
                CommentListModel.DataBean.CommentlistBean comment =
                        new CommentListModel.DataBean.CommentlistBean(bean.getIsanonymity(), bean.getCreatetime(),
                                bean.getServicepoint(), bean.getCommentscontent(), bean.getNickname()
                                , bean.getCpic(), bean.getDescpoint(), bean.getPic(), bean.getCid()
                                , bean.getDeliverypoint());
                mDataList.add(comment);
            }
            CommentListAdapter mCommentListAdapter = new CommentListAdapter(this);
            mCommentListAdapter.setCommentDatas(mDataList);
            mViewHolder.lv_comments.setAdapter(mCommentListAdapter);
        }
        Glide.with(this)
                .load(mDetailModel.getSpic())
                .bitmapTransform(new GlideCircleTransform(getActivity()))
                .into(mViewHolder.iv_storeLogo);
        mViewHolder.tv_storeName.setText(mDetailModel.getSname());
        if ("0".equals(APP.sUserid)) {//未登录
            mViewHolder.tv_address.setText("    未登录");
            mViewHolder.tv_yunfei.setText("   待定");
        } else {
            if (TextUtils.isEmpty(mDetailModel.getAddress())) {
                mViewHolder.tv_address.setText("    添加默认地址");
            } else {
                mViewHolder.tv_address.setText("    " + mDetailModel.getAddress());
                mViewHolder.tv_yunfei.setText(mDetailModel.getCarriage());
            }
        }
        if (TextUtils.isEmpty(mDetailModel.getGoodsnum())) {
            mViewHolder.tv_goodsCount.setText("0");
        } else {
            mViewHolder.tv_goodsCount.setText(mDetailModel.getGoodsnum());
        }
        if (TextUtils.isEmpty(mDetailModel.getCollectnum())) {
            mViewHolder.tv_fansCount.setText("0");
        } else {
            mViewHolder.tv_fansCount.setText(mDetailModel.getCollectnum());
        }
        GoodsDetailGridAdpter detailGridAdpter = new GoodsDetailGridAdpter(getActivity());
        List<RelatedGoodsModel.DataBean> mBeanList = new ArrayList<>();
        for (NewGoodsDetailModel.DataBean.RelateGoodsBean goodsBean : mDetailModel.getRelateGoods()) {
            RelatedGoodsModel.DataBean bean = new RelatedGoodsModel.DataBean(goodsBean.getIcon(), goodsBean.getUnit()
                    , goodsBean.getPrice(), Integer.parseInt(goodsBean.getGoodsid()), goodsBean.getGname(),
                    goodsBean.getBrand(), goodsBean.getGoodsdesc()
                    , goodsBean.getItemcode(), goodsBean.getIsself());
            mBeanList.add(bean);
        }
        detailGridAdpter.setData(mBeanList);
        mViewHolder.gv_Goodss.setAdapter(detailGridAdpter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (address != null) {
            mViewHolder.tv_address.setText("    " + address);
        }
        if (selectdAddress) {
            mRequestUtils.getGoodsDetail(goodsID);
            selectdAddress = false;
        }
    }

    public void setPrice(String price) {
        String priceInfo = price;
        if (priceInfo.contains("-")) {
            String[] prices = priceInfo.split("-");
            if (prices[0].contains(".")) {
                String[] price1 = prices[0].split("\\.");
                mViewHolder.tv_goodsPrice.setText(price1[0]);
                mViewHolder.tv_dobPrice.setText("." + price1[1]);
            } else {
                mViewHolder.tv_goodsPrice.setText(prices[0]);
                mViewHolder.tv_dobPrice.setText(".00");
            }
        } else {
            if (priceInfo.contains(".")) {
                String[] price1 = priceInfo.split("\\.");
                mViewHolder.tv_goodsPrice.setText(price1[0]);
                mViewHolder.tv_dobPrice.setText("." + price1[1]);
            } else {
                mViewHolder.tv_goodsPrice.setText(priceInfo);
                mViewHolder.tv_dobPrice.setText(".00");
            }
        }

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        if (message.getDataType().equals(AddressAdaphter.CLICK_ADDRESS)) {
            address = message.getData().toString();
            getActivity().onBackPressed();
        }

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(getActivity(), "未知错误，请重试");
        }
        if (message.getDataType().equals(GoodsStandard2.STANDAR_INFO)) {
            GoodsDetailModel.DataBean bean = (GoodsDetailModel.DataBean) message.getData();
            setPrice(bean.getPrice());
            Logger.i("更新处理分红信息");
//            String deale="\t成功购买此商品可获得约 "+ mDetailModel.getRebate()+ "元分红";
//            mViewHolder.tv_deals.setText(deale);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }
    public static class ViewHolder {
        public View mProgerss;
        public ConvenientBanner vpager_Banner;
        public TextView view_isSelf;
        public TextView tv_goodsTitle;
        public TextView tv_goodsPrice;
        public TextView tv_dobPrice;
        public ImageView iv_goodsRMB;
        public List<TextView> services = new ArrayList<>(4);
        public LinearLayout ll_servicLis;
        public TextView tv_goodstype;
        public LinearLayout ll_goodstype;
        public TextView tv_address;
        public TextView tv_deals;
        public TextView tv_yunfei;
        public TextView tv_commentCount;
        public TextView tv_goodComment;
        public LinearLayout tv_goodsComments;
        public NoScrollListView lv_comments;
        public TextView tv_moreComment;
        public ImageView iv_storeLogo;
        public TextView tv_storeName;
        public TextView iv_StoreType;
        public ImageView iv_InStore;
        public TextView tv_goodsCount;
        public TextView tv_fansCount;
        public TextView tv_StoreGoodss;
        public NoScrollGridView gv_Goodss;
        public TextView tv_moreGoods;
        public GradationScrollView scroll_top;
        public TextView tv_alte;
        public NoScrollGridView gv_goodsList;
        public WebView wv_view;
        public ScrollViewContainer scroll_Group;

    }
}

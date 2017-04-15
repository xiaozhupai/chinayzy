package com.chinayiz.chinayzy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chinayiz.chinayzy.views.goodsDetail.GradationScrollView;
import com.chinayiz.chinayzy.views.goodsDetail.NoScrollGridView;
import com.chinayiz.chinayzy.views.goodsDetail.NoScrollListView;
import com.chinayiz.chinayzy.views.goodsDetail.ScrollViewContainer;

public class GenerateLayoutActivity extends AppCompatActivity implements View.OnClickListener {


    private ConvenientBanner vpager_Banner;
    private TextView view_isSelf;
    private TextView tv_goodsTitle;
    private TextView tv_goodsPrice;
    private TextView tv_dobPrice;
    private ImageView iv_goodsRMB;
    private TextView tv_service1;
    private TextView tv_service2;
    private TextView tv_service3;
    private TextView tv_service4;
    private LinearLayout ll_servicLis;
    private View iv_line2;
    private TextView tv_goodstype;
    private LinearLayout ll_goodstype;
    private View iv_line8;
    private TextView textView6;
    private TextView tv_address;
    private TextView textView7;
    private TextView tv_yunfei;
    private View iv_line3;
    private TextView tv_commentCount;
    private TextView tv_goodComment;
    private LinearLayout tv_goodsComments;
    private NoScrollListView lv_comments;
    private View iv_line5;
    private TextView tv_moreComment;
    private View iv_line6;
    private ImageView iv_storeLogo;
    private TextView tv_storeName;
    private TextView iv_StoreType;
    private ImageView iv_InStore;
    private View line3;
    private TextView tv_goodsCount;
    private TextView tv_fansCount;
    private TextView tv_StoreGoodss;
    private TextView tv_StoreFans;
    private View iv_line9;
    private NoScrollGridView gv_Goodss;
    private TextView tv_moreGoods;
    private GradationScrollView scroll_top;
    private TextView tv_alte;
    private NoScrollGridView gv_goodsList;
    private WebView wv_view;
    private TextView tv_good_detail_daodi;
    private ScrollViewContainer scroll_Group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_goods_detail);
        initView();
    }

    private void initView() {
        vpager_Banner = (ConvenientBanner) findViewById(R.id.vpager_Banner);
        view_isSelf = (TextView) findViewById(R.id.view_isSelf);
        tv_goodsTitle = (TextView) findViewById(R.id.tv_goodsTitle);
        tv_goodsPrice = (TextView) findViewById(R.id.tv_goodsPrice);
        tv_dobPrice = (TextView) findViewById(R.id.tv_dobPrice);
        iv_goodsRMB = (ImageView) findViewById(R.id.iv_goodsRMB);
        tv_service1 = (TextView) findViewById(R.id.tv_service1);
        tv_service2 = (TextView) findViewById(R.id.tv_service2);
        tv_service3 = (TextView) findViewById(R.id.tv_service3);
        tv_service4 = (TextView) findViewById(R.id.tv_service4);
        ll_servicLis = (LinearLayout) findViewById(R.id.ll_servicLis);
        iv_line2 = (View) findViewById(R.id.iv_line2);
        tv_goodstype = (TextView) findViewById(R.id.tv_goodstype);
        ll_goodstype = (LinearLayout) findViewById(R.id.ll_goodstype);
        iv_line8 = (View) findViewById(R.id.iv_line8);
        textView6 = (TextView) findViewById(R.id.textView6);
        tv_address = (TextView) findViewById(R.id.tv_address);
        textView7 = (TextView) findViewById(R.id.textView7);
        tv_yunfei = (TextView) findViewById(R.id.tv_yunfei);
        iv_line3 = (View) findViewById(R.id.iv_line3);
        tv_commentCount = (TextView) findViewById(R.id.tv_commentCount);
        tv_goodComment = (TextView) findViewById(R.id.tv_goodComment);
        tv_goodsComments = (LinearLayout) findViewById(R.id.tv_goodsComments);
        lv_comments = (NoScrollListView) findViewById(R.id.lv_comments);
        iv_line5 = (View) findViewById(R.id.iv_line5);
        tv_moreComment = (TextView) findViewById(R.id.tv_moreComment);
        iv_line6 = (View) findViewById(R.id.iv_line6);
        iv_storeLogo = (ImageView) findViewById(R.id.iv_storeLogo);
        tv_storeName = (TextView) findViewById(R.id.tv_storeName);
        iv_StoreType = (TextView) findViewById(R.id.iv_StoreType);
        iv_InStore = (ImageView) findViewById(R.id.iv_InStore);
        line3 = (View) findViewById(R.id.line3);
        tv_goodsCount = (TextView) findViewById(R.id.tv_goodsCount);
        tv_fansCount = (TextView) findViewById(R.id.tv_fansCount);
        tv_StoreGoodss = (TextView) findViewById(R.id.tv_StoreGoodss);
        tv_StoreFans = (TextView) findViewById(R.id.tv_StoreFans);
        iv_line9 = (View) findViewById(R.id.iv_line9);
        gv_Goodss = (NoScrollGridView) findViewById(R.id.gv_Goodss);
        tv_moreGoods = (TextView) findViewById(R.id.tv_moreGoods);
        scroll_top = (GradationScrollView) findViewById(R.id.scroll_top);
        tv_alte = (TextView) findViewById(R.id.tv_alte);
        gv_goodsList = (NoScrollGridView) findViewById(R.id.gv_goodsList);
        wv_view = (WebView) findViewById(R.id.wv_view);
        tv_good_detail_daodi = (TextView) findViewById(R.id.tv_good_detail_daodi);
        scroll_Group = (ScrollViewContainer) findViewById(R.id.scroll_Group);

        ll_goodstype.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        tv_goodsComments.setOnClickListener(this);
        tv_moreComment.setOnClickListener(this);
        iv_InStore.setOnClickListener(this);
        tv_moreGoods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_goodstype:

                break;
            case R.id.tv_address:

                break;
            case R.id.tv_goodsComments:

                break;
            case R.id.tv_moreComment:

                break;
            case R.id.iv_InStore:

                break;
            case R.id.tv_moreGoods:

                break;
        }
    }
}

package com.chinayiz.chinayzy.ui.common;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.net.Commons;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.chinayiz.chinayzy.R.id.rb_goodsDetail;
import static com.chinayiz.chinayzy.R.id.rb_goodsNorms;
import static com.chinayiz.chinayzy.R.id.rb_goodsRelated;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/13 14:28
 * Class PicDetailFragment 图文详情
 */

public class PicDetailFragment extends AbsFragment implements RadioGroup.OnCheckedChangeListener {
    private View mProgerss;
    private RadioGroup rg_goodsMeun;
    private RadioButton rb1, rb2, rb3;
    private GridView gv_goodsList;
    private WebView wv_view;
    private RelatedGoodsModel mRelatedGoodslist;
    private String goodsid, itemCode;
    private int comitsID = 0;


    public void setGoodsid(String goodsid, String itemcode) {
        this.goodsid = goodsid;
        this.itemCode = itemcode;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_pic, container, false);
        initView(view);
        return view;
    }

    public static PicDetailFragment getInstance() {
        return new PicDetailFragment();
    }

    @Override
    protected void initView(View view) {
        mProgerss = view.findViewById(R.id.ll_progress);
        rb1 = (RadioButton) view.findViewById(rb_goodsDetail);
        rb2 = (RadioButton) view.findViewById(rb_goodsNorms);
        rb3 = (RadioButton) view.findViewById(rb_goodsRelated);

        rg_goodsMeun = (RadioGroup) view.findViewById(R.id.rg_goodsMeun);
        rg_goodsMeun.setOnCheckedChangeListener(this);
        gv_goodsList = (GridView) view.findViewById(R.id.gv_goodsList);
        wv_view = (WebView) view.findViewById(R.id.wv_view);
        WebSettings settings = wv_view.getSettings();
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
                mProgerss.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgerss.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        };
        wv_view.setWebViewClient(client);

    }

    public void setPager() {
        switch (comitsID) {
            case 0:
                if (rb1 != null) {
                    rb1.setChecked(true);
                }
                break;
            case 1:
                if (rb1 != null) {
                    rb2.setChecked(true);
                }
                break;
            case 2:
                if (rb1 != null) {
                    rb3.setChecked(true);
                }
                break;
        }

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

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
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    public void setRelatGoodsList(RelatedGoodsModel relatedGoodsModel) {
        mRelatedGoodslist = relatedGoodsModel;
        GoodsDetailGridAdpter detailGridAdpter = new GoodsDetailGridAdpter(getActivity());
        detailGridAdpter.setData(mRelatedGoodslist);
        gv_goodsList.setAdapter(detailGridAdpter);
        mProgerss.setVisibility(View.GONE);
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
        wv_view.setClickable(true);
        wv_view.setVisibility(View.VISIBLE);
        gv_goodsList.setClickable(false);
        gv_goodsList.setVisibility(View.GONE);
        wv_view.loadUrl(url + "?goodsid=" + goodsid);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case rb_goodsDetail: {
                comitsID = 0;
                clickPicGateil(1);
                break;
            }
            case rb_goodsNorms: {
                comitsID = 1;
                clickPicGateil(0);
                break;
            }
            case rb_goodsRelated: {
                comitsID = 2;
                wv_view.setClickable(false);
                wv_view.setVisibility(View.GONE);
                gv_goodsList.setClickable(true);
                gv_goodsList.setVisibility(View.VISIBLE);
                if (mRelatedGoodslist == null) {
                    mProgerss.setVisibility(View.VISIBLE);
                    mRequestUtils.getRelatedGoods(itemCode, "1", "14");
                }
                break;
            }
        }
    }
}

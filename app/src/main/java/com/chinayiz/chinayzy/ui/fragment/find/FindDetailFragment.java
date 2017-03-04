package com.chinayiz.chinayzy.ui.fragment.find;


import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.FindDetailPresenter;
import com.chinayiz.chinayzy.widget.ShareDialog;

/**发现详情
 * A simple {@link Fragment} subclass.
 */
public class FindDetailFragment extends BaseFragment<FindDetailPresenter> implements View.OnClickListener {
    private ImageView iv_love;
    private LinearLayout lv_love;
    private LinearLayout lv_keep;
    private LinearLayout lv_share;
    private LinearLayout lv_find_detail_bottom;
    private ShareDialog dialog;
    private boolean isLove;
    private String bid;
    public WebView wv_view;
    public String url;
    public ProgressBar progressbar;

    public FindDetailFragment(String bid) {
        this.bid = bid;
        url= Commons.API+Commons.FXXQ+"?bid="+bid;
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }


    @Override
    protected void lazyLoad() {

    }



    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find_detail, null);
        progressbar= (ProgressBar) view.findViewById(R.id.progressbar);
        wv_view= (WebView) view.findViewById(R.id.wv_view);
        iv_love = (ImageView) view.findViewById(R.id.iv_love);
        iv_love.setOnClickListener(this);
        lv_love = (LinearLayout) view.findViewById(R.id.lv_love);
        lv_love.setOnClickListener(this);
        lv_keep = (LinearLayout) view.findViewById(R.id.lv_keep);
        lv_keep.setOnClickListener(this);
        lv_share = (LinearLayout) view.findViewById(R.id.lv_share);
        lv_share.setOnClickListener(this);
        lv_find_detail_bottom = (LinearLayout) view.findViewById(R.id.lv_find_detail_bottom);
        lv_find_detail_bottom.setOnClickListener(this);
        initWebview();
        return view;

    }

    @Override
    public FindDetailPresenter initPresenter() {
        return new FindDetailPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    public void initWebview(){
        wv_view.loadUrl(url);
        wv_view.   setScrollbarFadingEnabled(true);
        wv_view.   setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings settings = wv_view.getSettings();
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(false);
        //JS交互
        settings.setJavaScriptEnabled(true);
        //设置缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置是否支持变焦
        settings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 让网页自适应屏幕宽度
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        }
        settings.setUseWideViewPort(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        wv_view.   setScrollbarFadingEnabled(true);
        wv_view.   setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        final WebSettings msettings = wv_view.getSettings();
        msettings.setAllowFileAccess(true);
        msettings.setBuiltInZoomControls(false);
        //JS交互
        msettings.setJavaScriptEnabled(true);
        //设置缓存
        msettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置是否支持变焦
        msettings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 让网页自适应屏幕宽度
            msettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        }
        msettings.setUseWideViewPort(true);
        msettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置WebViewClient
        wv_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                msettings.setBlockNetworkImage(true);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                msettings.setBlockNetworkImage(false);

            }
        });
        wv_view.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
                if (newProgress==100){
                    progressbar.setVisibility(View.GONE);
                }else {
                    progressbar.setProgress(newProgress);
                    progressbar.setVisibility(View.VISIBLE);
                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_love:  //点赞
                if (isLove){
                    iv_love.setImageResource(R.mipmap.icon_love_normal);
                    isLove=false;
                }else {
                    iv_love.setImageResource(R.mipmap.icon_love_selected);
                    isLove=true;
                }


                break;
            case R.id.lv_keep:  //收藏


                break;
            case R.id.lv_share:   //分享
                if (dialog==null){
                    dialog=new ShareDialog(getActivity());
                }
                dialog.show();

                break;
        }
    }
}

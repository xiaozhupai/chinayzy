package com.chinayiz.chinayzy.ui.fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.chinayiz.chinayzy.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass. 公共的fragment
 */
public class WebFragment extends Fragment {
    public WebView wv_view;
    private String title;
    private String  goodsid;
    private String url;
    public ProgressBar progressbar;
    private Map<String,String> extraHeaders;

    public void setWebView(String title,String url,String goodsid){
        this.title=title;
        this.url=url;
        this.goodsid=goodsid;
    }
    public void setWebView(String url,String goodsid){
        this.url=url;
        this.goodsid=goodsid;
        if (wv_view!=null){
            wv_view.loadUrl(url+"?goodsid="+goodsid);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_web,null);
        progressbar= (ProgressBar) view.findViewById(R.id.progressbar);
        wv_view= (WebView) view.findViewById(R.id.wv_view);
        return view;
    }
    public int getScrollY(){
        return wv_view.getScrollY();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (title==null){
            wv_view.loadUrl(url+"?goodsid="+goodsid);
            wv_view.   setScrollbarFadingEnabled(true);
            wv_view.   setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
            final WebSettings settings = wv_view.getSettings();
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
            extraHeaders = new HashMap<String, String>();
            extraHeaders.put("goodsid", goodsid);
            wv_view.loadUrl(goodsid,extraHeaders);
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
        }else {
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
            extraHeaders = new HashMap<String, String>();
            extraHeaders.put("goodsid", goodsid);
            wv_view.loadUrl(goodsid,extraHeaders);
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

    }
}

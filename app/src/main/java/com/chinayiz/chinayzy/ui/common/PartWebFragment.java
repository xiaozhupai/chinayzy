package com.chinayiz.chinayzy.ui.common;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chinayiz.chinayzy.R;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/4 15:32
 * Class WebFragment
 */
public class PartWebFragment extends Fragment{
    private WebView wv_view;
    private String  goodsid="-1";
    private String url="-1";

    public void setUrl(String goodsid, String url){
        this.goodsid = goodsid;
        this.url = url;
        try {
            Logger.i("访问新页面ID="+goodsid+"WBE等于="+(wv_view==null));
            wv_view.loadUrl(url+"?goodsid="+goodsid);
        }catch (Exception e){
            if (wv_view!=null){
                wv_view.loadUrl(url+"?goodsid="+goodsid);
            }else {
                Logger.e("WEB还是等于空=catch");
            }
        }finally {
            if (wv_view!=null){
                wv_view.loadUrl(url+"?goodsid="+goodsid);
            }else {
                Logger.e("WEB还是等于空=finally");
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_web,null);
        wv_view= (WebView) view.findViewById(R.id.wv_view);
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
        return view;
    }
    public int getScrollY(){
        return wv_view.getScrollY();
    }
    @Override
    public void onResume() {
        super.onResume();
        wv_view.loadUrl(url+"?goodsid="+goodsid);
    }
}

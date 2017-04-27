package com.chinayiz.chinayzy.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.ActivityPresenter;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/21 10:03
 * Class ActivityFragment 生态农业活动页面
 */
@SuppressLint("ValidFragment")
public class ActivityFragment extends BaseFragment<ActivityPresenter> {
    public ProgressBar progressbar;
    public WebView wv_view;
    private String titel;
    private String url;
    private boolean fristLoad=true;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, null);
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        wv_view = (WebView) view.findViewById(R.id.wv_view);
        titel = "活动";
        url = Commons.API + "/h5/activity?devicetype=android&userid=" + APP.sUserid;
        return view;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText(titel);
        activity.mCbActionBarEdit.setVisibility(View.GONE);
        activity.mIvActionBarMore.setVisibility(View.GONE);
        activity.mIvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webCanback();
            }
        });
    }

    public void webCanback() {
        if (wv_view.canGoBack()) {
            wv_view.goBack();
        } else {
            getActivity().onBackPressed();
        }
    }

    @Override
    public ActivityPresenter initPresenter() {
        return new ActivityPresenter();
    }

    @Override
    protected void onVisible() {

    }

    public static ActivityFragment getInstance() {
        return new ActivityFragment();
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (fristLoad) {
            wv_view.loadUrl(url);
            fristLoad=false;
        }
        wv_view.setScrollbarFadingEnabled(true);
        wv_view.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        wv_view.addJavascriptInterface(ActivityFragment.this, "android");
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

        wv_view.setScrollbarFadingEnabled(true);
        wv_view.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
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
        wv_view.setWebViewClient(new WebViewClient() {

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
        wv_view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    progressbar.setProgress(newProgress);
                    progressbar.setVisibility(View.VISIBLE);
                }
            }

        });
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    //由于安全原因 需要加 @JavascriptInterface
    @JavascriptInterface
    public void submitFunction() {
        Logger.i("被JS调用");
        // 传递参数调用,被JS调用的函数执行在非UI线程内
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (UserSeeion.isLogin(getActivity())) {
                    wv_view.loadUrl("javascript:validate(" + APP.sUserid + ")");
                } else {
                    wv_view.loadUrl("javascript:validate(" + APP.sUserid + ")");
                    BaseActivity.showToast(getActivity(), "请先进行登录");
                }
            }
        });


    }

}

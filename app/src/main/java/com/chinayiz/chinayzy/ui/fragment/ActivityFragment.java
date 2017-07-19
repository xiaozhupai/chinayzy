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
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ShareVipModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.ActivityPresenter;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/21 10:03
 * Class ActivityFragment 生态农业活动页面
 */
@SuppressLint("ValidFragment")
public class ActivityFragment extends BaseFragment<ActivityPresenter> {
    private boolean fristLoad = true;
    public WebView wv_view;
    public static final String SHARE = "分享推荐码";
    public static final String SHARE_VIP = "分享vip";
    public static final String ERROR_TITLE = "网页";
    public ProgressBar progressbar;
    private View netErrorView;
    public String url = Commons.API + "/h5/activity?devicetype=android&userid=" + APP.sUserid;

    public ActivityFragment() {
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, null);
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        wv_view = (WebView) view.findViewById(R.id.wv_view);
        netErrorView = view.findViewById(R.id.view_netWorkError);
        netErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv_view.reload();
                Logger.i("重新加载");
            }
        });

        return view;
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (fristLoad) {
            Logger.i("重新加载");
            fristLoad = false;
            wv_view.setScrollbarFadingEnabled(true);
            wv_view.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
            wv_view.addJavascriptInterface(ActivityFragment.this, "android");
            final WebSettings settings = wv_view.getSettings();
            settings.setAllowFileAccess(true);
            settings.setBuiltInZoomControls(false);
            //JS交互
            settings.setJavaScriptEnabled(true);
            //设置缓存
            settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            // 设置是否支持变焦
            settings.setSupportZoom(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 让网页自适应屏幕宽度
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            }
            settings.setUseWideViewPort(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setAllowFileAccess(true);
            settings.setBuiltInZoomControls(false);
            settings.setUseWideViewPort(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            wv_view.setScrollbarFadingEnabled(true);
            wv_view.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);

            //设置WebViewClient
            wv_view.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    settings.setBlockNetworkImage(true);

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    settings.setBlockNetworkImage(false);
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    netErrorView.setVisibility(View.VISIBLE);
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

                @Override
                public void onReceivedTitle(WebView view, String title) {

                    //判断标题 title 中是否包含有“error”字段，如果包含“error”字段，则设置加载失败，显示加载失败的视图
                    if (title.contains(ActivityFragment.ERROR_TITLE)) {
                        Logger.i("网页访问错误=" + title);
                        netErrorView.setVisibility(View.VISIBLE);
                    } else {
                        netErrorView.setVisibility(View.GONE);
                    }

                }

           });
        }
        Logger.i("开始加载:"+url);
        wv_view.loadUrl(url);
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mCbActionBarEdit.setVisibility(View.GONE);
        activity.mIvActionBarMore.setVisibility(View.GONE);
    }

    @Override
    public ActivityPresenter initPresenter() {
        return new ActivityPresenter();
    }

    @Override
    protected void onVisible() {
        initWebView();
    }


    //由于安全原因 需要加 @JavascriptInterface

    @JavascriptInterface
    public void backAndroid(String link) {
        Skip.toWebPage(getActivity(), link, "活动详情");
    }


    /**
     * 分享
     */
    @JavascriptInterface
    public void startFunction() {
        Logger.i("被JS调用");
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT, SHARE, getActivity()));
    }

    /**
     * 分享
     */
    @JavascriptInterface
    public void startFunction(String theme, String introduce, String icon, String url) {
        ShareVipModel model = new ShareVipModel(theme, introduce, icon, url);
        Logger.i("被jiS 调用了 数据=" + model.toString());
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT, SHARE_VIP, model));
    }

    /**
     * 提交活动奖励表单
     */
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

    public static ActivityFragment getInstance() {
        return new ActivityFragment();
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }


}

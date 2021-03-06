package com.chinayiz.chinayzy.ui.common;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.ui.activity.GoldActivity;
import com.chinayiz.chinayzy.ui.fragment.ActivityFragment;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/9 14:42
 * Class CommonWebFragment
 */
public class CommonWebFragment extends Fragment implements View.OnClickListener,GoldActivity.canGoBack {
    private ImageView iv_back_button;
    private TextView tv_actionbar_title;
    private ImageView iv_more_button;
    private View netErrorView;
    private ProgressBar progressbar;
    private WebView wv_view;
    private String title;
    private String url;

    /**
     * 构造WEB
     * @param title  标题
     * @param url  链接地址
     */
    public CommonWebFragment(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_common_web,null);
        ((GoldActivity)getActivity()).setBack(this);
        initview(view);
        return view;
    }

    private void initview(View view) {
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        iv_back_button.setOnClickListener(this);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setText(title);
        tv_actionbar_title.setTextColor(Color.BLACK);
        Paint paint = tv_actionbar_title.getPaint();
        paint.setFakeBoldText(false);
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);
        progressbar= (ProgressBar) view.findViewById(R.id.progressbar);
        wv_view= (WebView) view.findViewById(R.id.wv_view);
        netErrorView=view.findViewById(R.id.view_netWorkError);
        netErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv_view.reload();
            }
        });
        wv_view.   setScrollbarFadingEnabled(true);
        wv_view.   setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
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
        wv_view.   setScrollbarFadingEnabled(true);
        wv_view.   setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        wv_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }

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
        wv_view.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress==100){
                    progressbar.setVisibility(View.GONE);
                }else {
                    progressbar.setProgress(newProgress);
                    progressbar.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {

                //判断标题 title 中是否包含有“error”字段，如果包含“error”字段，则设置加载失败，显示加载失败的视图
                if (title.contains(ActivityFragment.ERROR_TITLE)){
                    Logger.i("网页访问错误=" + title);
                    netErrorView.setVisibility(View.VISIBLE);
                }else {
                    netErrorView.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wv_view.setVisibility(View.GONE);
        wv_view=null;
    }

    public boolean canKeyBacck(){
        if (wv_view!=null){
            return  wv_view.canGoBack();
        }
        return false;
    }
    public void goBacck(){
        if (wv_view!=null){
            wv_view.goBack();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        wv_view.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        if (canKeyBacck()) {
            wv_view.goBack();
        }else {
            getFragmentManager().popBackStack();
        }
    }
}

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
    private boolean fristLoad=true;
    public WebView wv_view;
    public static final String SHARE="分享推荐码";
    public static final String SHARE_VIP="分享vip";
    public ProgressBar progressbar;
    public String url= Commons.API + "/h5/activity?devicetype=android&userid=" + APP.sUserid;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, null);
        progressbar= (ProgressBar) view.findViewById(R.id.progressbar);
        wv_view= (WebView) view.findViewById(R.id.wv_view);
        return view;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void onResume() {
        super.onResume();
        if (fristLoad) {
            fristLoad=false;
            wv_view.   setScrollbarFadingEnabled(true);
            wv_view.   setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
            wv_view.addJavascriptInterface(ActivityFragment.this,"android");
            WebSettings settings = wv_view.getSettings();
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

            wv_view.   setScrollbarFadingEnabled(true);
            wv_view.   setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
            final WebSettings msettings = wv_view.getSettings();
            msettings.setAllowFileAccess(true);
            msettings.setBuiltInZoomControls(false);
            //JS交互
            msettings.setJavaScriptEnabled(true);
            //设置缓存
            msettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
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
                    if (newProgress==100){
                        progressbar.setVisibility(View.GONE);
                    }else {
                        progressbar.setProgress(newProgress);
                        progressbar.setVisibility(View.VISIBLE);
                    }
                }

            });
        }
//        wv_view.loadUrl(url);
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

    }



    //由于安全原因 需要加 @JavascriptInterface

    @JavascriptInterface
    public void backAndroid(String link){
        Skip.toWebPage(getActivity(),link,"活动详情");
    }


    /**
     * 分享
     */
    @JavascriptInterface
    public void startFunction(){
        Logger.i("被JS调用");
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,SHARE,getActivity()));
    }

    /**
     * 分享
     */
    @JavascriptInterface
    public void startFunction(String theme,String introduce,String icon,String url){
        ShareVipModel model=new ShareVipModel(theme,introduce,icon,url);
        Logger.i("被jiS 调用了 数据="+model.toString());
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,SHARE_VIP,model));
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

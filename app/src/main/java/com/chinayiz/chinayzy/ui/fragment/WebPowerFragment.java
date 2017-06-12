package com.chinayiz.chinayzy.ui.fragment;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.CommonPresenter;
import com.chinayiz.chinayzy.utils.JavaUntil;
import com.chinayiz.chinayzy.views.GlideLoader;
import com.chinayiz.chinayzy.views.PickView.TimePickerView;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageSelector;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static com.chinayiz.chinayzy.APP.oss;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class WebPowerFragment extends BaseFragment<CommonPresenter> {
    public static final String CLASS_NAME = WebPowerFragment.class.getSimpleName();
    public static final String SHARE = "分享推荐码";
    public static final String ACTIVITY = "活动中心";
    /**
     * 请求身份证图片
     */
    public static final int REQUEST_USER_CODE = 0x0006;
    /**
     * 请求选择图片
     */
    public static final int REQUEST_USER_IMG = 10;
    /**
     * Event事件图片消息回调
     */
    public static final String USER_INFO_IMG="WebPowerFragment_imgs";
    /**
     * 身份证图片回调
     */
    public static final String USER_INFO_DATA="WebPowerFragment_User";
    /**
     * 身份证当前面
     */
    public   String currentPage="0";
    /**
     * 图片远程地址
     */
    public   String mBuffer;
    private ImageConfig imageConfig;
    private boolean fristLoad = true;
    public WebView wv_view;
    private String titel;
    private int flga=0;
    private String url;
    public ProgressBar progressbar;
    private BaseActivity activity;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadMessage;

    public WebPowerFragment(String title, String url) {
        this.titel = title;
        this.url = url;
    }

    public WebPowerFragment() {
    }

    @Override
    public void onInintData(Bundle bundle) {
        this.titel = bundle.getString("titel", "错误...");
        this.url = bundle.getString("url", "-1");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, null);
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        wv_view = (WebView) view.findViewById(R.id.wv_view);
        initWebView();
        return view;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        this.activity = activity;
        activity.mTvActionBarTitle.setText(titel);
        Logger.i("设置标题");
        activity.mCbActionBarEdit.setVisibility(View.GONE);
        if (titel.equals(SHARE)) {
            activity.mIvActionBarMore.setVisibility(View.GONE);
        }
    }

    @Override
    public CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runBgThread(EventMessage message) {
        if (EventMessage.NET_EVENT==message.getEventType()){
            switch (message.getDataType()){
                case WebPowerFragment.USER_INFO_DATA:{
                    /**
                     * 上传身份证图片
                     */
                    upDataImag(message);
                    break;
                }
                case WebPowerFragment.USER_INFO_IMG:{
                    Logger.i("图片传回WebView");
                    if (Build.VERSION.SDK_INT >= 21) {//5.0以上版本处理
                        Uri[] uris = (Uri[]) message.getData();
                        mUploadCallbackAboveL.onReceiveValue(uris);
                    } else {//4.4以下处理
                        Uri uri = (Uri) message.getData();
                        mUploadMessage.onReceiveValue(uri);
                    }
                    break;
                }

            }
        }
    }

    /**
     *  上传身份证图片
     * @param message
     */
    private void upDataImag(EventMessage message) {
        String imgFile= (String) message.getData();
        Logger.i("收到身份证图片，开始上传操作"+imgFile);
        final String remotName;
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        String date= DateFormat.format("MMdd",Calendar.getInstance(Locale.CHINA))+"";
        String lastname= flga+".png";
        flga++;
        remotName="v1/"+year+"/"+lastname;
        mBuffer= Commons.HOST+"/"+remotName;
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(APP.testBucket, remotName, imgFile);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Logger.i("OSS成功回调：="+remotName);
                setUserInfoImg(remotName);
            }
            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    @Override
    protected void onInvisible() {

    }

    /**
     * 初始化WebView
     */
    private void initWebView(){
        wv_view.setScrollbarFadingEnabled(true);
        wv_view.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        wv_view.addJavascriptInterface(WebPowerFragment.this, "android");
        final WebSettings settings = wv_view.getSettings();
        //JS交互
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置是否支持变焦
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        //运行webview通过URI获取安卓文件
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 让网页自适应屏幕宽度
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        }
        //设置WebViewClient
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

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                mUploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                mUploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                mUploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                mUploadCallbackAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }
        });
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
                activity.mTvActionBarTitle.setText(titel);

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (fristLoad) {
            wv_view.loadUrl(url);
            Logger.i("打印提交地址="+url);
            fristLoad = false;
        }
    }
    private void openImageChooserActivity() {
        Logger.i("打开相册中");
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_USER_IMG);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WebPowerFragment.REQUEST_USER_IMG) {
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (Build.VERSION.SDK_INT >= 21) {//5.0以上版本处理
                onActivityResultAboveL(requestCode, resultCode, data);

            }else {
                mUploadMessage.onReceiveValue(result);
            }

        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != WebPowerFragment.REQUEST_USER_IMG ) return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
       mUploadCallbackAboveL.onReceiveValue(results);
    }
    @Override
    public void isNightMode(boolean isNight) {

    }

    //由于安全原因 需要加 @JavascriptInterface

    /**
     * 分享
     */
    @JavascriptInterface
    public void startFunction() {
        Logger.i("被JS调用");
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT, SHARE, getActivity()));
    }

    @JavascriptInterface
    public void toGoodsDetail(String goodsid) {
        Logger.i("商品详情页H5");
        Skip.toNewGoodsDetail(getActivity(), goodsid);
    }

    @JavascriptInterface
    public void addimgAndroid(String pageType) {
        Logger.i("JS 调用上传身份证图片");
        currentPage=pageType;
        if (pageType.equals("1")) {
            BaseActivity.showToast(getActivity(), "请选择正面身份证照片");
            openImagSelect();
        } else {
            BaseActivity.showToast(getActivity(), "请选择反面面身份证照片");
            openImagSelect();
        }
    }

    @JavascriptInterface
    public void successFunction() {
        Logger.i("资料提交完毕");
    getActivity().finish();
    }

    public void openImagSelect(){
        imageConfig = new ImageConfig.Builder(
                new GlideLoader())
                .steepToolBarColor( ContextCompat.getColor(getActivity(),R.color.titleBlue))
                .titleBgColor(ContextCompat.getColor(getActivity(),R.color.titleBlue))
                .titleSubmitTextColor(ContextCompat.getColor(getActivity(),R.color.white))
                .titleTextColor(ContextCompat.getColor(getActivity(),R.color.white))
                // 多选时的最大数量   （默认 9 张）
                .mutiSelectMaxSize(1)
                // 设置图片显示容器，参数：（1、显示容器，2、每行显示数量（建议不要超过8个），是否可删除）
                .setContainer(new LinearLayout(getActivity()), 4, true)
                // 拍照后存放的图片路径（默认 /temp/picture）
                .filePath("/temp")
                // 开启拍照功能 （默认关闭）
                .showCamera()
                .requestCode(REQUEST_USER_CODE)
                .build();
        ImageSelector.open(getActivity(), imageConfig);
    }
    public void setUserInfoImg(final String path){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wv_view.loadUrl("javascript:aaaaa("+currentPage+","+path+")");
                Logger.i("Js地址发送成功="+path);
            }
        });
    }
    @JavascriptInterface
    public void toPickView() {
        Logger.i("PickView");
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TimePickerView pvTime = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
                Calendar calendar = Calendar.getInstance();
                pvTime.setRange(1940, calendar.get(Calendar.YEAR));
                pvTime.setTime(new Date());
                pvTime.setCyclic(false);
                pvTime.setCancelable(true);
                // 时间选择后回调
                pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        Logger.i("回调的时间" + JavaUntil.getTime(date));

                        wv_view.loadUrl("javascript:callbackbirthday(" + JavaUntil.getTime(date) + ")");
                    }
                });
                pvTime.show();
            }
        });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.i("移除WEB视图");
    }
}

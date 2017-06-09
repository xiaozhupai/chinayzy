package com.chinayiz.chinayzy.ui.common;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chinayiz.chinayzy.R;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/4 15:32
 * Class WebFragment
 */
public class PartWebFragment extends Fragment{
    private WebView wv_view;
    private String  goodsid="-1";

    public void setUrl(String url,String goodsid) {
        this.url = url;
        this.goodsid=goodsid;
    }

    private String url="-1";
    //fragment 懒加载标志位
    protected boolean isVisible;
    /**
     * 加载商品图文详情
     * @return
     */
    public static PartWebFragment newInstance() {
        Bundle args = new Bundle();
        PartWebFragment fragment = new PartWebFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * 在这里实现Fragment数据的懒加载. ViewPager有效
     *
     * @param isVisibleToUser 通知系统当前fragment是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();//当前可见
        } else {
            isVisible = false;
            onInvisible();//当前不可见
        }
    }

    /**
     * fragment可见时
     */
    protected void onVisible() {
        if (wv_view!=null){
            wv_view.loadUrl(url+"?goodsid="+goodsid);
        }
    }
    /**
     * fragment不可见,且视图有肯能为null
     * lazyLoad延迟到子类一并判断
     */
    protected void onInvisible() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_web,null);
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
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置是否支持变焦
        settings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 让网页自适应屏幕宽度
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (getScrollY()==0){
                    view.setClickable(false);
                }
                return false;
            }
        });
        return view;
    }
    public int getScrollY(){
        if (wv_view==null){
            return 0;
        }
        return wv_view.getScrollY();
    }

}

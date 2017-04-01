package com.chinayiz.chinayzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chinayiz.chinayzy.adapter.viewHolder.CreateBannerHolder;
import com.chinayiz.chinayzy.autoUpdate.UpdateService;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.MainPresenter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener, OnItemClickListener {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditer;
    private ConvenientBanner mBannerMainPager;
    public String dowloadUrl="-1";
    public boolean isYes=false;
    private boolean isNotify=false;
    private View mBtnNongYe,mBtn_asIm;
    private View mBtnCityWide;
    private View mBtnStore;
    private View mBtnIm;
    private View mBtnLvYou;
    private View mBtnZhongChou;
    private NY_BannerModel mModel;
    private List<String> urls=new ArrayList<>();

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setStatuBarColor(MainActivity.this, Color.rgb(218, 22, 47));
        setContentView(R.layout.activity_main);
        mPresenter.mRequestUtils.getCanUpdata(APP.Version);
        initView();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void initView() {
        mBannerMainPager = (ConvenientBanner) findViewById(R.id.banner_MainPager);
        mBannerMainPager.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        mBtnNongYe =  findViewById(R.id.btn_NongYe);
        mBtnCityWide = findViewById(R.id.btn_CityWide);
        mBtnStore = findViewById(R.id.btn_Store);
        mBtnIm = findViewById(R.id.btn_im);
        mBtnLvYou =  findViewById(R.id.btn_LvYou);
        mBtnZhongChou = findViewById(R.id.btn_ZhongChou);
        mBtn_asIm=findViewById(R.id.btn_asIm);

        mBtnNongYe.setOnClickListener(this);
        mBtnCityWide.setOnClickListener(this);
        mBtnStore.setOnClickListener(this);
        mBtnIm.setOnClickListener(this);
        mBtnLvYou.setOnClickListener(this);
        mBtnZhongChou.setOnClickListener(this);
        mBtn_asIm.setOnClickListener(this);
    }

    public void setData(NY_BannerModel model) {
        mModel=model;
        //设置banner图
        urls.clear();
        for (NY_BannerModel.Data data : mModel.getData()) {
            urls.add(data.getShowlink());
        }
        mBannerMainPager.setPages(new CreateBannerHolder(), urls);
        mBannerMainPager.setOnItemClickListener(this);
        mBannerMainPager.startTurning(2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBannerMainPager.setcurrentitem(600);
        mBannerMainPager.startTurning(2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBannerMainPager.setCanLoop(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_NongYe://农业
                mPresenter.doStartNongye();
                break;
            case R.id.btn_CityWide://同城
                Skip.toItemMenu(this,"-1");
                break;
            case R.id.btn_Store://商城
                Skip.toItemMenu(this,"-1");
                break;
            case R.id.btn_im://个人中心
                mPresenter.doStartMine();
                break;
            case R.id.btn_LvYou://旅游
                Skip.toItemMenu(this,"-1");
                break;
            case R.id.btn_ZhongChou://众筹
                Skip.toItemMenu(this,"-1");
                break;
            case R.id.btn_asIm:
                Logger.i("关于我们");
                String url=new String(Commons.API+"/h5/aboutus");
                Skip.toWebPage(this,url,"关于我们");
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        if (mModel==null) {
            return;
        }
        if ("5".equals(mModel.getData().get(position).getType())){
            Skip.toGoodsDetail(this,mModel.getData().get(position).getDetaillink());
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    /**
     * 显示App更新
     * @param type   1 更新   2 强制更新
     */
    public void updataVersion(int type) {
        mPreferences=getSharedPreferences("update", Context.MODE_PRIVATE);
        mEditer=mPreferences.edit();
        isNotify=mPreferences.getBoolean("isWarn",false);
        if (type==1){//普通更新
            if (!isNotify){//是否提示更新
                new MaterialDialog.Builder(this)
                        .iconRes(R.mipmap.ic_launcher)
                        .limitIconToDefaultSize()
                        .title("更新提示")
                        .content(mPresenter.info.getVersiondesc())
                        .positiveText("更新")
                        .negativeText("取消")
                        .titleColor(Color.rgb(0,0,0))
                        .contentColor(Color.rgb(43,43,43))
                        .backgroundColor(Color.rgb(230,230,230))
                        .negativeColor(Color.rgb(0,0,0))
                        .positiveColor(Color.rgb(53,181,74))
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (DialogAction.POSITIVE.toString().equals(which.name())){  //更新/下载
                                    mEditer.putBoolean("isWarn",false);
                                    mEditer.commit();
                                    isYes=true;
                                    if (mPresenter.isLoad){
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setDataAndType(Uri.fromFile(new File(mPresenter.apkPath)), "application/vnd.android.package-archive");
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Intent intent=new Intent(MainActivity.this, UpdateService.class);
                                        intent.putExtra("downloadURI",dowloadUrl);
                                        startService(intent);
                                    }
                                }else if (DialogAction.NEGATIVE.toString().equals(which.name())){ //取消更新/安装
                                    Logger.i("取消");
                                }
                            }
                        })
                        .checkBoxPrompt("不再提示", false, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                Logger.i("是否提醒");
                                mEditer.putBoolean("isWarn",b);
                                mEditer.commit();
                            }
                        })
                        .build()
                        .show();
            }else {
                return;
            }
        }
        if (type==2){//强制更新
            new MaterialDialog.Builder(this)
                    .iconRes(R.mipmap.ic_launcher)
                    .limitIconToDefaultSize()
                    .title("更新提示")
                    .titleColor(Color.rgb(209,97,88))
                    .content(mPresenter.info.getVersiondesc())
                    .negativeText("退出应用")
                    .positiveText("更新")
                    .contentColor(Color.rgb(43,43,43))
                    .backgroundColor(Color.rgb(230,230,230))
                    .negativeColor(Color.rgb(214,101,88))
                    .positiveColor(Color.rgb(57,181,74))
                    .onAny(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (DialogAction.POSITIVE.toString().equals(which.name())){  //更新/下载
                                isYes=true;
                                if (mPresenter.isLoad){
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(new File(mPresenter.apkPath)), "application/vnd.android.package-archive");
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Intent intent=new Intent(MainActivity.this, UpdateService.class);
                                    intent.putExtra("downloadURI",dowloadUrl);
                                    startService(intent);
                                }
                            }else if (DialogAction.NEGATIVE.toString().equals(which.name())){ //取消更新/安装
                                Logger.i("退出应用");
                                finish();
                            }
                        }
                    })
                    .cancelable(false)
                    .build()
                    .show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreferences=null;
        mEditer=null;
    }
}

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
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.MainPresenter;
import com.chinayiz.chinayzy.ui.fragment.WebPowerFragment;
import com.chinayiz.chinayzy.utils.NetworkUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener, OnItemClickListener {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditer;
    private ConvenientBanner mBannerMainPager;
    private MaterialDialog mDilog;
    public String dowloadUrl = "-1";
    private boolean isNotify = false;
    private View mBtnNongYe, mBtn_asIm, mBtnRecommend;
    private View mBtnStore;
    private View mBtnIm;
    private NY_BannerModel mModel;
    private List<String> urls = new ArrayList<>();

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
        mBtnNongYe = findViewById(R.id.btn_NongYe);
        mBtnStore = findViewById(R.id.btn_Store);
        mBtnIm = findViewById(R.id.btn_im);
        mBtn_asIm = findViewById(R.id.btn_asIm);
        mBtnRecommend = findViewById(R.id.btn_Recommend);

        mBtnNongYe.setOnClickListener(this);
//        mBtnCityWide.setOnClickListener(this);
//        mBtnLvYou.setOnClickListener(this);
//        mBtnZhongChou.setOnClickListener(this);
        mBtnStore.setOnClickListener(this);
        mBtnIm.setOnClickListener(this);
        mBtn_asIm.setOnClickListener(this);
        mBtnRecommend.setOnClickListener(this);
    }

    public void setData(NY_BannerModel model) {
        mModel = model;
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
            case R.id.btn_Store://商城
                Skip.toItemMenu(this, "-1");
                break;
            case R.id.btn_im://个人中心
                mPresenter.doStartMine();
                break;
            case R.id.btn_asIm://关于我们
                String url=new String(Commons.API+"/h5/aboutus");
                Skip.toWebPage(this,url,"关于我们");
//                Skip.toGoodsResult(this,"002001,分类菜单");
                break;
            case R.id.btn_Recommend://分享我的二维码
                if (UserSeeion.isLogin(this)) {
                    String uri = new String(Commons.API + "/h5/tuijianma?userid=" + APP.sUserid);
                    Skip.toWebPage(this, uri, WebPowerFragment.SHARE);
                } else {
                    showToast(this, "请先登录");
                }
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        if (mModel == null) {
            return;
        }
        if ("5".equals(mModel.getData().get(position).getType())) {
            Skip.toNewGoodsDetail(this, mModel.getData().get(position).getDetaillink());
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    /**
     * 显示App更新
     *
     * @param type 1 更新   2 强制更新
     */
    public void updataVersion(int type) {
        mPreferences = getSharedPreferences("update", Context.MODE_PRIVATE);
        mEditer = mPreferences.edit();
        isNotify = mPreferences.getBoolean("isWarn", false);
        String actionName;
        if (mPresenter.isLoad) {
            actionName = "安装";
        } else {
            actionName = "下载更新";
        }
        if (type == 1) {//普通更新
            if (!isNotify) {//是否提示更新
                new MaterialDialog.Builder(this)
                        .iconRes(R.mipmap.ic_launcher)
                        .limitIconToDefaultSize()
                        .title("更新提示")
                        .content(mPresenter.info.getVersiondesc())
                        .positiveText(actionName)
                        .negativeText("取消")
                        .titleColor(Color.rgb(0, 0, 0))
                        .contentColor(Color.rgb(43, 43, 43))
                        .backgroundColor(Color.rgb(230, 230, 230))
                        .negativeColor(Color.rgb(0, 0, 0))
                        .positiveColor(Color.rgb(53, 181, 74))
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (DialogAction.POSITIVE.toString().equals(which.name())) {  //更新
                                    mEditer.putBoolean("isWarn", false);
                                    mEditer.commit();
                                    if (mPresenter.isLoad) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setDataAndType(Uri.fromFile(new File(mPresenter.apkPath)), "application/vnd.android.package-archive");
                                        startActivity(intent);
                                        finish();
                                    } else {//开始启动下载的地方

                                        if (NetworkUtils.isWifiConnected(MainActivity.this)) {
                                            Intent intent = new Intent(MainActivity.this, UpdateService.class);
                                            intent.putExtra("downloadURI", dowloadUrl);
                                            startService(intent);
                                            dialog.dismiss();
                                        } else {
                                            ensureDowload();
                                            dialog.dismiss();
                                        }

                                    }
                                } else if (DialogAction.NEGATIVE.toString().equals(which.name())) { //取消更新
                                    Logger.i("取消");
                                }
                            }
                        })
                        .checkBoxPrompt("不再提示", false, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                Logger.i("是否提醒");
                                mEditer.putBoolean("isWarn", b);
                                mEditer.commit();
                            }
                        })
                        .build()
                        .show();
            } else {
                return;
            }
        }
        if (type == 2) {//强制更新
            new MaterialDialog.Builder(this)
                    .iconRes(R.mipmap.ic_launcher)
                    .limitIconToDefaultSize()
                    .title("更新提示")
                    .titleColor(Color.rgb(209, 97, 88))
                    .content(mPresenter.info.getVersiondesc())
                    .negativeText("退出应用")
                    .positiveText(actionName)
                    .contentColor(Color.rgb(43, 43, 43))
                    .backgroundColor(Color.rgb(230, 230, 230))
                    .negativeColor(Color.rgb(214, 101, 88))
                    .positiveColor(Color.rgb(57, 181, 74))
                    .onAny(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (DialogAction.POSITIVE.toString().equals(which.name())) {  //更新/下载
                                if (mPresenter.isLoad) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(new File(mPresenter.apkPath)), "application/vnd.android.package-archive");
                                    startActivity(intent);
                                } else {//开始启动下载的地方

                                    if (NetworkUtils.isWifiConnected(MainActivity.this)) {
                                        Intent intent = new Intent(MainActivity.this, UpdateService.class);
                                        intent.putExtra("downloadURI", dowloadUrl);
                                        startService(intent);
                                        dialog.dismiss();
                                    } else {
                                        ensureDowload();
                                        dialog.dismiss();
                                    }

                                }
                            } else if (DialogAction.NEGATIVE.toString().equals(which.name())) { //取消更新/安装
                                Logger.i("退出应用");
                                dialog.dismiss();
                                finish();
                            }
                        }
                    })
                    .cancelable(false)
                    .build()
                    .show();
        }
    }

    /**
     * 非WiFi网络是否下载
     */
    public void ensureDowload() {
        new MaterialDialog.Builder(this)
                .iconRes(R.mipmap.ic_launcher)
                .limitIconToDefaultSize()
                .title("温馨提示")
                .titleColor(Color.rgb(209, 97, 88))
                .content("你当前不是 WiFi 网络环境，继续下载将会产生手机流量，确定继续？")
                .negativeText("退出应用")
                .positiveText("确定")
                .contentColor(Color.rgb(43, 43, 43))
                .backgroundColor(Color.rgb(230, 230, 230))
                .negativeColor(Color.rgb(214, 101, 88))
                .positiveColor(Color.rgb(57, 181, 74))
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (DialogAction.POSITIVE.toString().equals(which.name())) {  //下载
                            Intent intent = new Intent(MainActivity.this, UpdateService.class);
                            intent.putExtra("downloadURI", dowloadUrl);
                            startService(intent);
                        } else if (DialogAction.NEGATIVE.toString().equals(which.name())) { //取消更新
                            Logger.i("退出应用");
                            finish();
                        }
                    }
                })
                .cancelable(false)
                .build()
                .show();
    }

    /**
     * 显示正在下载的进度
     *
     * @param progers 当前进度
     */
    public void showProgerss(int progers) {
        if (mDilog == null) {
            mDilog = new MaterialDialog.Builder(this)
                    .iconRes(R.mipmap.ic_launcher)
                    .limitIconToDefaultSize()
                    .title("正在下载")
                    .titleColor(Color.rgb(0, 0, 0))
                    .progress(false, 100, true)
                    .progressNumberFormat("%1d/%2d")
                    .contentColor(Color.rgb(43, 43, 43))
                    .backgroundColor(Color.rgb(230, 230, 230))
                    .negativeColor(Color.rgb(214, 101, 88))
                    .positiveColor(Color.rgb(57, 181, 74))
                    .onAny(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (DialogAction.POSITIVE.toString().equals(which.name())) {  //更新/下载
                                Logger.i("点击了安装");
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.fromFile(new File(mPresenter.apkPath)), "application/vnd.android.package-archive");
                                startActivity(intent);
                                mDilog = null;
                                finish();
                            }
                        }
                    })
                    .cancelable(false)
                    .build();
            mDilog.show();
        }
        if (progers == -10) {
            mDilog.setProgress(100);
            mDilog.setTitle("下载完成");
            mDilog.setActionButton(DialogAction.POSITIVE, "安装 ");
        } else {
            mDilog.setProgress(progers);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreferences = null;
        mEditer = null;
    }
}

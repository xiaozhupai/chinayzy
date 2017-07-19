package com.chinayiz.chinayzy;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chinayiz.chinayzy.adapter.NyMainPagerAdapter;
import com.chinayiz.chinayzy.autoUpdate.UpdateService;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.NewMainPresenter;
import com.chinayiz.chinayzy.ui.activity.MineFragment;
import com.chinayiz.chinayzy.ui.fragment.ActivityFragment;
import com.chinayiz.chinayzy.ui.fragment.MainFtagment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindFragment;
import com.chinayiz.chinayzy.utils.NetworkUtils;
import com.chinayiz.chinayzy.utils.StrCallback;
import com.chinayiz.chinayzy.views.BadgeView;
import com.chinayiz.chinayzy.views.NoScrollViewPager;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/8 17:11
 * Class NewMainActivity  新应用首页
 */

public class NewMainActivity extends BaseActivity<NewMainPresenter> implements
        RadioGroup.OnCheckedChangeListener {
    /**
     * 生态农业底部导航
     */
    protected RadioGroup mRgNongyeMenu;
    /**
     *  适配器
     */
    private NyMainPagerAdapter mPagerAdapter;
    /**
     *  禁止左右滑动的viewPager
     */
    private NoScrollViewPager mViewPager;
    private List<Fragment> mFragments;
    public RadioButton mRadioButton;
    private int commitID=0;
    public String dowloadUrl = "-1";
    private MaterialDialog mDilog;
    public boolean isPause=false;
    private ActivityFragment mActivityFragment;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditer;
    private boolean isNotify = false;
    private  ShopCartFragment mShopCartFragment;
    private Button bt_shopcart;
    private BadgeView badge1;


    @Override
    protected NewMainPresenter initPresenter() {
        return new NewMainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_new);
        setStatuBarColor(this,Color.rgb(255, 255, 255));
        login_flag="";
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause=false;
        if (login_flag.equals(StrCallback.RESPONSE_CODE_USER_OUT)) {
            if (mMaterialDialog!=null) {
                mMaterialDialog.dismiss();
            }
            finish();
        }
    }

    @Override
    protected void onPause() {
        isPause=true;
        Logger.i("NewMainActivity切到后台了");
        super.onPause();
    }

    private void initView() {
        mFragments = new ArrayList<>(5);
        mFragments.add(MainFtagment.getInstance());
        mFragments.add(FindFragment.getInstance());
        mActivityFragment=ActivityFragment.getInstance();
        mFragments.add(mActivityFragment);
       mShopCartFragment= ShopCartFragment.getInstance();
        mFragments.add(mShopCartFragment);
        mFragments.add(MineFragment.getInstance());
        initActionBar();
        mTvActionBarTitle.setTextColor(Color.rgb(0,0,0));
        mIvBackButton.setVisibility(View.GONE);
        mIvActionBarMore.setVisibility(View.GONE);
        mActionBar.setVisibility(View.GONE);
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_main_content);
        //设置缓存其他页面
        mViewPager.setOffscreenPageLimit(5);
        mPagerAdapter = new NyMainPagerAdapter(getFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mRgNongyeMenu = (RadioGroup) findViewById(R.id.rg_nongye_menu);
        mRadioButton = (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nav_home);
        bt_shopcart= (Button) findViewById(R.id.bt_shopcart);
        mRgNongyeMenu.setOnCheckedChangeListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
               commitID=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //默认选中农业首页
        mRadioButton.setChecked(true);

        //购物车提示数量
        remind(bt_shopcart);
    }
    public void selectTab(int pistion){
        RadioButton radioButton=null;
        switch (pistion){
            case 0:{
                radioButton= (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nav_home);
                break;
            }
            case 1:{
                radioButton= (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nav_find);
                break;
            }
            case 2:{
                radioButton= (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nav_activi);
                break;
            }
            case 3:{
                radioButton= (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nav_cart);
                break;
            }
            case 4:{
                radioButton= (RadioButton) mRgNongyeMenu.findViewById(R.id.rb_nav_im);
                break;
            }
        }
        radioButton.setChecked(true);
    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (R.id.rb_nav_home==i){
            mActionBar.setVisibility(View.GONE);
        }else {
            mActionBar.setVisibility(View.VISIBLE);
        }

        switch (i) {
            case R.id.rb_nav_home://首页
                commitID=0;
                mViewPager.setCurrentItem(0);
                setStatuBarColor(this,Color.rgb(255, 255, 255));
                mActionBar.setBackgroundColor(Color.rgb(218, 22, 47));
                break;
            case R.id.rb_nav_find://发现
                commitID=1;
                mViewPager.setCurrentItem(1);
                mTvActionBarTitle.setText("发现");
                setStatuBarColor(this,Color.rgb(255, 255, 255));
                mActionBar.setBackgroundColor(Color.rgb(255, 255, 255));
                break;
            case R.id.rb_nav_activi://活动
                commitID=2;
                mViewPager.setCurrentItem(2);
                mTvActionBarTitle.setText("活动");
                setStatuBarColor(this,Color.rgb(255, 255, 255));
                mActionBar.setBackgroundColor(Color.rgb(255, 255, 255));
                mActivityFragment.url= Commons.API + "/h5/activity?devicetype=android&userid=" + APP.sUserid;
                if (mActivityFragment!=null&&mActivityFragment.wv_view!=null){

                    if (mActivityFragment.wv_view.canGoBack()){
                        mActivityFragment.wv_view.goBack();
                    }
                    mActivityFragment.wv_view.loadUrl( mActivityFragment.url);
                }
                break;
            case R.id.rb_nav_cart://购物车
                commitID=3;
                setStatuBarColor(this,Color.rgb(255, 255, 255));
                if (UserSeeion.isLogin(this)) {
                    mViewPager.setCurrentItem(3);
                    mTvActionBarTitle.setText("购物车");
                    mActionBar.setBackgroundColor(Color.rgb(255, 255, 255));
                }else {
                    showToast(this,"请登录");
                    mRadioButton.setChecked(true);
                }
                break;
            case R.id.rb_nav_im://我的
                setStatuBarColor(this,Color.rgb(218, 22, 47));
                mActionBar.setBackgroundColor(Color.rgb(218, 22, 47));
                commitID=4;
                if (UserSeeion.isLogin(this)) {
                    mViewPager.setCurrentItem(4);
                    mTvActionBarTitle.setText("个人中心");
                }else {
                    mRadioButton.setChecked(true);
                }
                break;

        }
        if (i!= R.id.rb_nav_cart) {
            mCbActionBarEdit.setVisibility(View.GONE);
        } else {
            mCbActionBarEdit.setVisibility(View.VISIBLE);
            mCbActionBarEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mShopCartFragment.onCheckedChanged(buttonView,isChecked);
                }
            });
        }
        if (commitID==4){
            mTvActionBarTitle.setTextColor(Color.rgb(255,255,255));
            mIvActionBarMore.setVisibility(View.VISIBLE);
            mIvActionBarMore.setImageResource(R.mipmap.btn_bg_set);
            mIvActionBarMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Skip.toSetting(getActivity());
                }
            });
        }else {
            mIvActionBarMore.setVisibility(View.GONE);
            mTvActionBarTitle.setTextColor(Color.rgb(0,0,0));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mActivityFragment.wv_view!=null&&commitID==2){//判断当前是否在活动页面
            if (keyCode == KeyEvent.KEYCODE_BACK && mActivityFragment.wv_view.canGoBack()) {
                mActivityFragment.wv_view.goBack();// 返回前一个页面
                return true;
            }
            if (commitID==3){
                Logger.i("当前页面是购物车");
                return true;
            }
            exitBy2Click();
            return false;
        }else {
            exitBy2Click();
            return false;
        }
    }

    private static Boolean mIsExit = false;
    private void exitBy2Click() {
        Timer tExit;
        if (!mIsExit) {
            mIsExit = true; // 准备退出
            showToast(this, "再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onClick(View v) {

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
            mEditer=mPreferences.edit();
            mEditer.putBoolean("isLoad",false);
        } else {
            actionName = "下载更新";
        }
        if (type == 1) {//普通更新
            if (normalUpdata(actionName)) return;
        }
        if (type == 2) {//强制更新

            forceUpdata(actionName);
        }
    }

    /**
     * 强制更新
     * @param actionName
     */
    private void forceUpdata(String actionName) {
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
                                if (NetworkUtils.isWifiConnected(NewMainActivity.this)) {
                                    Intent intent = new Intent(NewMainActivity.this, UpdateService.class);
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
    /**
     * 正常更新
     * @param actionName
     */
    private boolean normalUpdata(String actionName) {
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

                                    if (NetworkUtils.isWifiConnected(NewMainActivity.this)) {
                                        Intent intent = new Intent(NewMainActivity.this, UpdateService.class);
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
            return true;
        }
        return false;
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
                            Intent intent = new Intent(NewMainActivity.this, UpdateService.class);
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
    public Activity getActivity() {
        return this;
    }

    /**
     * 购物车小红点
     *
     */
    public void remind(View view) { //BadgeView的具体使用
        badge1= new BadgeView(this,view);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge1.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 显示的位置.右上角,BadgeView.POSITION_BOTTOM_LEFT,下左，还有其他几个属性
        badge1.setTextColor(Color.WHITE); // 文本颜色
        badge1.setBadgeBackgroundColor(ContextCompat.getColor(this,R.color.classifyText_pre)); // 提醒信息的背景颜色，自己设置
        badge1.setTextSize(10); // 文本大小
        //badge1.setBadgeMargin(3, 3); // 水平和竖直方向的间距
        badge1.setBadgeMargin(5); //各边间隔
    }

    /**
     * 购物车的数量
     */
    public void getCount(int count){
        // badge1.toggle(); //显示效果，如果已经显示，则影藏，如果影藏，则显示
        if (count==0){
            badge1.hide();//影藏显示
        }else if (count>99){
            badge1.setText("99+");
        }else {
            badge1.setText( ""+mPresenter.count); // 需要显示的提醒类容
            Logger.i("购物车数量........."+mPresenter.count);
            badge1.show();// 只有显示
        }
    }
}


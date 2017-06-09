package com.chinayiz.chinayzy.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.ui.fragment.WebFragment;
import com.chinayiz.chinayzy.utils.BarUtils;
import com.chinayiz.chinayzy.utils.StrCallback;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 9:59
 * Class BaseActivity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseActivityView, View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, FragmentManager.OnBackStackChangedListener {
    public View mActionBar;
    /**
     * ActionBar标题
     */
    public TextView mTvActionBarTitle;
    public MaterialDialog mMaterialDialog;
    /**
     * 返回按钮；购物车按钮；更多按钮
     */
    public ImageView mIvBackButton, mIvActionBarCart, mIvActionBarMore;
    /**
     * 编辑，或完成（供购物车或个人资料修改使用）
     */
    public CheckBox mCbActionBarEdit;
    protected T mPresenter;
    protected String className = "";
    public static String login_flag="";
    protected static Toast toast;
    public String TAG;
    public FragmentManager fragmentManager;
    private BaseFragment mCurrentFragment;
    protected Fragment mFragment;
    /**
     * web Fragment（通用）；
     */
    public WebFragment mWebFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        mPresenter = initPresenter();
        //类似fragment的绑定.拿到引用
        mPresenter.onAttch(this);
        //初始化acitivity
        onCreateActivity(savedInstanceState);
        //初始化Presenter
        mPresenter.onStart();
        fragmentManager = getFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
    }

    protected void initActionBar() {
        mActionBar = findViewById(R.id.rl_ActionBar);
        mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_actionbar_title);
        mIvActionBarMore = (ImageView) findViewById(R.id.iv_more_button);
        mIvActionBarCart = (ImageView) findViewById(R.id.iv_shopcart);
        mCbActionBarEdit = (CheckBox) findViewById(R.id.cb_edit_button);
        TextPaint paint = mTvActionBarTitle.getPaint();
        paint.setFakeBoldText(true);
        mIvBackButton.setOnClickListener(this);
        mIvActionBarMore.setOnClickListener(this);
        mIvActionBarCart.setOnClickListener(this);
        mCbActionBarEdit.setOnCheckedChangeListener(this);
        mActionBar.setBackgroundColor(Color.WHITE);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onStop();
        super.onDestroy();
    }

    public void showUserOut() {
        final Context context = this;
        UserSeeion.logout(context);
        APP.sUserid="0";
        login_flag= StrCallback.RESPONSE_CODE_USER_OUT;
        mMaterialDialog=new MaterialDialog.Builder(context)
                .limitIconToDefaultSize()
                .title("下线通知")
                .titleColor(Color.rgb(209, 97, 88))
                .content("您的账号在其他设备登录亿众，" +
                        "如果不是你本人操作，你的密码已泄漏。如果对方登录成功，" +
                        "本设备将会被强制退出登录。请立刻前往登录页修改密码，慎防盗号。")
                .negativeText("忽略")
                .positiveText("修改密码")
                .contentColor(Color.rgb(43, 43, 43))
                .backgroundColor(Color.rgb(230, 230, 230))
                .negativeColor(Color.rgb(214, 101, 88))
                .positiveColor(Color.rgb(10, 10, 10))
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (DialogAction.POSITIVE.toString().equals(which.name())) {  //修改密码
                            Skip.toLogin(context);
                        } else if (DialogAction.NEGATIVE.toString().equals(which.name())) { //忽略
                            dialog.dismiss();
                        }
                    }
                })
                .cancelable(false)
                .build();
        mMaterialDialog.show();
    }

    /**
     * 创建prensenter
     *
     * @return <T extends BasePresenter> 必须是BasePresenter的子类
     */
    protected abstract T initPresenter();

    /**
     * 子类必须实现,并初始化Activity,比如setContentView()
     */
    protected abstract void onCreateActivity(Bundle savedInstanceState);

    @Override
    public void isNightMode(boolean isNight) {

    }

    /**
     * 透明/改变通知栏颜色
     *
     * @param activity 需要改变状态栏颜色的activity
     * @param RGBColor 必须使用 Colr.rgb 取值才能取到有效值
     */
    protected void setStatuBarColor(Activity activity, int RGBColor) {
        BarUtils.setColor(activity, RGBColor);
    }

    /**
     * 显示吐司，解决重复延时展示问题
     *
     * @param context
     * @param content
     */
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public void addtoFragment(Intent intent) {
        @SuppressWarnings("unchecked") Class<? extends BaseFragment> clazz = (Class<? extends BaseFragment>) intent.getSerializableExtra(Skip.CLASS);
        try {
            className = clazz.getSimpleName();
            BaseFragment fragment = clazz.newInstance();
            fragment.setArguments(intent.getExtras());
            Bundle bundle = intent.getExtras();
            addFragment(fragment);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

	/* (non-Javadoc)
     * @see cn.stlc.app.BaseActivity#addFragment(android.support.v4.app.Fragment, java.lang.Class, boolean)
	 */

    public void addFragment(BaseFragment fragment) {
        Class<?> classz = fragment.getClass();
        mFragment = fragment;
        try {
            fragmentManager.beginTransaction()
                    .add(R.id.content_frame, fragment, classz.getSimpleName())
                    .addToBackStack(classz.getSimpleName())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            fragmentManager.beginTransaction()
                    .add(R.id.content_frame, fragment, classz.getSimpleName())
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }


    @Override
    public void onBackStackChanged() {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            finish();
            return;
        }
        if (backStackEntryCount > 0) {
            FragmentManager.BackStackEntry backStack = fragmentManager.getBackStackEntryAt(backStackEntryCount - 1);
            String name = backStack.getName();
            mCurrentFragment = (BaseFragment) fragmentManager.findFragmentByTag(name);
            mCbActionBarEdit.setVisibility(View.GONE);
            mIvActionBarCart.setVisibility(View.GONE);
            mIvActionBarMore.setVisibility(View.GONE);
            mIvBackButton.setVisibility(View.VISIBLE);
            mActionBar.setVisibility(View.VISIBLE);
            mCurrentFragment.onInitActionBar(this);

        }
    }
}
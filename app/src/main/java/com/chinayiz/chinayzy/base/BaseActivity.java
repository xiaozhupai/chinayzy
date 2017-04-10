package com.chinayiz.chinayzy.base;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.ui.fragment.WebFragment;
import com.chinayiz.chinayzy.utils.BarUtils;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 9:59
 * Class BaseActivity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseActivityView, View.OnClickListener, CompoundButton.OnCheckedChangeListener, FragmentManager.OnBackStackChangedListener {
    public View mActionBar;
    /**
     * ActionBar标题
     */
    public TextView mTvActionBarTitle;
    /**
     * 返回按钮；购物车按钮；更多按钮
     */
    public ImageView mIvBackButton, mIvActionBarCart, mIvActionBarMore;
    /**
     * 编辑，或完成（供购物车或个人资料修改使用）
     */
    public CheckBox mCbActionBarEdit;
    protected T mPresenter;



    protected static Toast toast;
    public String TAG;
    public FragmentManager fragmentManager;
    private BaseFragment mCurrentFragment;
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
            BaseFragment fragment = clazz.newInstance();
            fragment.setArguments(intent.getExtras());
            Bundle bundle=intent.getExtras();
            addFragment(fragment);
//            if (TextUtils.isEmpty(bundle.getString("goodsID"))){
//
//            }else {
//                addFragment(fragment,bundle.getString("goodsID"));
//            }

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
            mActionBar.setVisibility(View.VISIBLE);
            mCurrentFragment.onInitActionBar(this);
        }
    }
}
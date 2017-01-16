package com.chinayiz.chinayzy.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.utils.BarUtils;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 9:59
 * Class BaseActivity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseActivityView {
    protected T mPresenter;
    protected static Toast toast;
    public FragmentManager fragmentManager;
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        mPresenter = initPresenter();
        //类似fragment的绑定.拿到引用
        mPresenter.onAttch(this);
        //初始化acitivity,
        onCreateActivity(savedInstanceState);
        //初始化Presenter
        mPresenter.onCreate();

        fragmentManager=getSupportFragmentManager();

        TAG= getClass().getSimpleName();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        mPresenter.onDetach();
        super.onDestroy();
    }
    /**
     * 创建prensenter
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
     * @param activity 需要改变状态栏颜色的activity
     * @param RGBColor 必须使用 Colr.rgb 取值才能取到有效值
     */
    protected void setStatuBarColor(Activity activity,int RGBColor) {
        BarUtils.setColor(activity,RGBColor);
    }

    /**
     * 显示吐司，解决重复延时展示问题
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

    /**
     * fragment 跳转
     * @param tofragment  目标fragment
     * @param tag   fragment标记  用于替换或隐藏
     */
    public void addFragment(Fragment tofragment, String tag){
        try {
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.fl_nongye, tofragment)
                    .addToBackStack(tofragment.getTag())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.fl_nongye, tofragment, tofragment.getTag()).addToBackStack(tofragment.getTag()).commitAllowingStateLoss();
        }

    }
}
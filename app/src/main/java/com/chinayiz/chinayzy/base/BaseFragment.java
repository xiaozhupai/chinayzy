package com.chinayiz.chinayzy.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 10:15
 * Class BaseFragment
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseFragmentView {
    protected T mPresenter;
    //fragment 懒加载标志位
    protected boolean isVisible;
    //activity的上下文对象
    protected Context mContext;
    protected Bundle mBundle;
    public BaseActivity mActivity;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
        Logger.e("onSaveInstanceState");
    }
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser 通知系统当前fragment是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();//当前可见
        } else {
            isVisible = false;
            onInvisible();//当前不可见
        }
        Logger.e("setUserVisibleHint"+isVisibleToUser);
    }

    /**
     * fragment可见时
     */
    protected abstract void onVisible();

    /**
     * fragment不可见,且视图有肯能为null
     * lazyLoad延迟到子类一并判断
     */
    protected abstract void onInvisible();

    /**
     * fragment可见,懒加载填充数据
     */
    protected  void lazyLoad(){
        lazyLoad();
        Logger.e("lazyLoad");
    }
    /**
     * 绑定activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity= (BaseActivity) mContext;
        Logger.e("onAttach");
    }

    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
        //创建presenter
        mPresenter = initPresenter();

        Logger.e("onCreate初始化presenter");
    }

    /**
     * 运行在onCreate之后
     * 生成view视图
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.e("onCreateView初始化Fragment视图");
        return initView(inflater, container, savedInstanceState);

    }

    /**
     * 运行在onCreateView之后加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
        mPresenter.onAttch(this);
        mPresenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDestroy();
        super.onDestroyView();
    }
    @Override
    public void onDetach() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    /**
     * 跳转fragment
     * @param tofragment
     */
    @Override
    public void startFragment(Fragment tofragment) {
        startFragment(tofragment, null);
    }

    /**
     * @param tofragment 跳转的fragment
     * @param tag        fragment的标签
     */
    @Override
    public void startFragment(Fragment tofragment, String tag) {
        mActivity.addFragment(tofragment,tag);

    }

    /**
     * 类似Activity的OnBackgress
     * fragment进行回退
     */
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    /**
     * 初始化Fragment应有的视图
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 创建prensenter
     * @return <T extends BasePresenter> 必须是BasePresenter的子类
     */
    public abstract T initPresenter();


    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public Bundle getBundle() {
        return mBundle;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }

}
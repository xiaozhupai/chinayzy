package com.chinayiz.chinayzy.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected T mPresenter;
    public FragmentManager mFragmentManager;
    //fragment 懒加载标志位
    protected boolean isVisible;
    protected Bundle mBundle;
    public String TAG;
    public BaseActivity mActivity;
    //ui是否初始化

    public boolean isInit = true;

    /**
     * 初始化Fragment应有的视图
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 创建prensenter
     *
     * @return <T extends BasePresenter> 必须是BasePresenter的子类
     */
    public abstract T initPresenter();

    /**
     * 绑定activity
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        Logger.e("onSaveInstanceState");
    }

    /**
     * 在这里实现Fragment数据的缓加载. ViewPager有效
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
    protected abstract void onVisible();

    /**
     * fragment不可见,且视图有肯能为null
     * lazyLoad延迟到子类一并判断
     */
    protected abstract void onInvisible();

    /**
     * fragment可见,懒加载填充数据
     */
    protected void lazyLoad() {
        Logger.e("lazyLoad");
    }

    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
        //创建presenter
        mPresenter = initPresenter();
    }

    /**
     * actionbar导航
     * @param activity
     */
    public  void onInitActionBar(BaseActivity activity){
        Logger.i("onInitActionBar");
    }

    /**
     * 运行在onCreate之后
     * 生成view视图
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        TAG = getClass().getSimpleName();
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return view;

    }

    /**
     * 运行在onCreateView之后加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
        mPresenter.onAttch(this);
        mPresenter.onStart();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onStop();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    /**
     * @param tofragment 跳转的fragment
     * @param tag        fragment的标签
     */

    @Override
    public void startFragment(Fragment tofragment, String tag) {
        addFragment(tofragment, tag);
    }

    /**
     * fragment 跳转
     *
     * @param tofragment 目标fragment
     * @param tag        fragment标记  用于替换或隐藏
     */
    public void addFragment(Fragment tofragment, String tag) {
        mFragmentManager = getFragmentManager();
        try {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.fl_nongye, tofragment)
                    .addToBackStack(tofragment.getTag())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.fl_nongye,
                    tofragment, tofragment.getTag())
                    .addToBackStack(tofragment.getTag())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 类似Activity的OnBackgress
     * fragment进行回退
     */
    public void onBack() {
        getFragmentManager().popBackStack();
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
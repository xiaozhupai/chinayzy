package com.chinayiz.chinayzy.base;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/8 10:01
 * Class AbsFragment
 */
@SuppressLint("ValidFragment")
public abstract class AbsFragment extends Fragment implements EventBusCallback{
    protected CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    protected View mProgress;
    public FragmentManager mFragmentManager;
    protected ImageView iv_back_button;
    protected TextView tv_actionbar_title;
    protected ImageView iv_more_button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    protected abstract void initView(View view);
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
            transaction.add(R.id.fl_goldLayout, tofragment)
                    .addToBackStack(tofragment.getTag())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.fl_goldLayout,
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
}

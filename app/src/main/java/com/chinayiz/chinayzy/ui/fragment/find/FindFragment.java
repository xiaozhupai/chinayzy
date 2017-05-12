package com.chinayiz.chinayzy.ui.fragment.find;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.FindPresenter;
import com.chinayiz.chinayzy.utils.BarUtils;
import com.chinayiz.chinayzy.utils.magicindicator.MagicIndicator;
import com.orhanobut.logger.Logger;

/**
 * 发现
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class FindFragment extends BaseFragment<FindPresenter> {
    public ViewPager vp_find;

//    public LinearLayout ll_top;
    private static final int SLIDE=123;
    public MagicIndicator magic_indicator;
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT"};


    @Override
    protected void onVisible() {

    }

    public static FindFragment getInstance() {
        return new FindFragment();
    }
    @Override
    public void onInintData(Bundle bundle) {

    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
            Logger.i("初始化actionbar");
            try {
                activity.mActionBar.setVisibility(View.GONE);
                BarUtils.setColor(activity,  Color.rgb(255, 255, 255));
            } catch (Exception e) {
                Logger.i("actionbar设置异常" + e);
            }
        super.onInitActionBar(activity);
    }

    @Override
    protected void onInvisible() {
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find,container,false);
        magic_indicator= (MagicIndicator) view.findViewById(R.id.magic_indicator);
        vp_find = (ViewPager) view.findViewById(R.id.vp_find);

        return view;
    }

    @Override
    public FindPresenter initPresenter() {
        return new FindPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }



}

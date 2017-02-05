package com.chinayiz.chinayzy.ui.fragment.find;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.PagerAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.FindPresenter;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * 发现
 * A simple {@link Fragment} subclass.
 */

public class FindFragment extends BaseFragment<FindPresenter> implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager vp_find;
    private View v_slide;
    public LinearLayout ll_top;
    private static final int SLIDE=123;

    @Override
    protected void onVisible() {
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
        v_slide=view.findViewById(R.id.v_slide);
        ll_top= (LinearLayout) view.findViewById(R.id.ll_top);

        vp_find = (ViewPager) view.findViewById(R.id.vp_find);
        vp_find.setOnClickListener(this);
        List<Fragment> lists=new ArrayList<>();
        for (int i=0;i<4;i++){
            FindListFragment fragment=new FindListFragment();
            lists.add(fragment);
        }
        vp_find.setAdapter(new PagerAdaphter(getChildFragmentManager(),lists));
        vp_find.setOnPageChangeListener(this);
        return view;

    }

    @Override
    public FindPresenter initPresenter() {
        return new FindPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again

        View view=initView(inflater,container,savedInstanceState);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    //viewpager切换
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    private void setnoSelected(){

    }

    private void doAnimation(int position){
        int oldPosition=vp_find.getCurrentItem();
        int  newPosition=position;
        int traslate;
        if (newPosition>oldPosition){ //向右移动
            traslate=SLIDE*(newPosition-oldPosition);
        }else { //向左移动
            traslate=-SLIDE*(oldPosition-newPosition);
        }
        Animation animation =new TranslateAnimation(position*SLIDE,position*SLIDE+traslate,0,0);
        animation.setFillAfter(true);
        animation.setDuration(100);
        v_slide.startAnimation(animation);

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                setnoSelected();
                doAnimation(0);
                break;
            case 1:
                setnoSelected();
                doAnimation(1);
                break;
            case 2:
                setnoSelected();
                doAnimation(2);
                break;
            case 3:
                setnoSelected();
                doAnimation(3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

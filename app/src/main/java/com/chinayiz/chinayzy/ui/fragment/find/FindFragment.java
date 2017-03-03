package com.chinayiz.chinayzy.ui.fragment.find;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.presenter.FindPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.utils.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;

/**
 * 发现
 * A simple {@link Fragment} subclass.
 */

public class FindFragment extends BaseFragment<FindPresenter> {
    public ViewPager vp_find;

//    public LinearLayout ll_top;
    private static final int SLIDE=123;
    public MagicIndicator magic_indicator;
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT"};


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
        NongYeMainActivity activity= (NongYeMainActivity) getActivity();


        View view=inflater.inflate(R.layout.fragment_find,container,false);
        magic_indicator= (MagicIndicator) view.findViewById(R.id.magic_indicator);
        vp_find = (ViewPager) view.findViewById(R.id.vp_find);

        return view;
    }
    @Override
    public void onResume() {
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR,new ActionBarControlModel(NongYeMainActivity.SHOW_ALL,"发现",1,0,0,1)));
        super.onResume();
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

}

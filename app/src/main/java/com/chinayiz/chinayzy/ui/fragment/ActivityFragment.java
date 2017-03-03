package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.presenter.ActivityPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/21 10:03
 * Class ActivityFragment 生态农业活动页面
 */
public class ActivityFragment extends BaseFragment<ActivityPresenter> {

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity,container,false);
        return view;
    }
    @Override
    public void onResume() {
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR,new ActionBarControlModel(NongYeMainActivity.SHOW_ALL,"活动",1,0,0,1)));
        super.onResume();
    }

    @Override
    public ActivityPresenter initPresenter() {
        return new ActivityPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**  个人中心
 * Created by Administrator on 2017/1/9.
 */

public class MinePresenter extends BasePresenter<MineActivity> {

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    public void toSuggest(){
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
    }



}

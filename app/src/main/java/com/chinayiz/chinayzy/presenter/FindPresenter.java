package com.chinayiz.chinayzy.presenter;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.FindTypeModel;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.find.FindFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class FindPresenter  extends BasePresenter<FindFragment>{
    private Net net=new Net();
    @Override
    public void onCreate() {
       net.getFindType();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {//网络请求回调消息
            Logger.i("网络请求回调消息" + message.toString());
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
          FindTypeModel model= (FindTypeModel) message.getData();
        mView.ll_top.removeAllViews();
        for (FindTypeModel.DataBean bean: model.getData()){
            TextView tv=new TextView(mView.getActivity());
            tv.setText(bean.getTypename());
            tv.setTextSize(14);
            tv.setTextColor( mView.getActivity().getResources().getColor(R.color.find_text));
            LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll.weight=1;
            ll.gravity= Gravity.CENTER;
            tv.setLayoutParams(ll);
            mView.ll_top.addView(tv);
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }
}

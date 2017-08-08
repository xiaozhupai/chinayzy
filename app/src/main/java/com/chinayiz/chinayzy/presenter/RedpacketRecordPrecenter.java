package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ExpenseCalendarModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.mine.RedpacketRecordFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 红包消费记录
 * Created by Administrator on 2017/7/31.
 */

public class RedpacketRecordPrecenter extends BasePresenter<RedpacketRecordFragment> {


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.EXPENSECALENDAR://红包专场消费记录
                ExpenseCalendarModel model = (ExpenseCalendarModel) message.getData();
                Logger.i("model:" + model);
                Logger.i("model.getCode():" + model.getCode());
                if (model.getCode().equals("100")) {
                    mView.red_packet_money.setText(model.getData().getVippoint());
                    Logger.i("money：" + model.getData().getVippoint());
                    mView.mlvAdapter.setData(model.getData().getList());
                }
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    protected void onCreate() {
        CommonRequestUtils.getRequestUtils().expenseCalendar(APP.sUserid);
        Logger.i("APP.sUserid:" + APP.sUserid);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}

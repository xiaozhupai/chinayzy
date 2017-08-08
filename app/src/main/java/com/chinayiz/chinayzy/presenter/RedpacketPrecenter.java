package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.widget.ImageView;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeDetailModel;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.mine.RedpacketFragment;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 红包专场
 * Created by Administrator on 2017/7/31.
 */

public class RedpacketPrecenter extends BasePresenter<RedpacketFragment> {
    public ShowClassifyCodeDetailModel model1;
    //请求页数
    private int page=1;
    //每页显示的数据
    private int size=6;

    @Override
    protected void onCreate() {
        CommonRequestUtils.getRequestUtils().getShowClassifyCode();
    }

    //获取自营商品
    public void getGoods(String itemcode){
        CommonRequestUtils.getRequestUtils().showClassifyCodeDetail(page, size, itemcode);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

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
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.SHOWCLASSIFYCODE://红包专场分类
                ShowClassifyCodeModel model = (ShowClassifyCodeModel) message.getData();
                if (model.getCode().equals("100")){
                    mView.mLvAdapter.setList(model.getData());
                }
                break;
            case Commons.SHOWCLASSIFYCODEDETAIL://红包专场自营商品
                model1 = (ShowClassifyCodeDetailModel) message.getData();
                if (mView.rv_refresh != null) {
                    mView.rv_refresh.finishRefresh();
                    mView.rv_refresh.finishLoadmore();
                }
                if (model1.getCode().equals("100")) {
                    mView.mRvAdapter.onResult(model1.getData());
                }
                break;

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {

        }
    }

    @Override
    protected void onDestroy() {

    }

}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ResponseModel;
import com.chinayiz.chinayzy.entity.response.GoodsCollectModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.mine.GoodsKeepFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**宝贝收藏
 * Created by Administrator on 2017/1/10.
 */

public class GoodsKeepPresenter extends BasePresenter<GoodsKeepFragment> {
    @Override
    public void onCreate() {
        mView.adaphter.onGetData(1);
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
        if (message.getEventType()== EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.SHOWGOODSCOLLECT:  //展示宝贝收藏列表
                GoodsCollectModel model= (GoodsCollectModel) message.getData();
                mView.adaphter.onResult(model.getData());
                break;
            case Commons.GOODS_UNCOLLECT:  //取消宝贝收藏
                ResponseModel model1= (ResponseModel) message.getData();
                if (model1.getCode().equals("100")){
                    mView.adaphter.delete();
                }
                break;
        }

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }


}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ClassifyCodesModel;
import com.chinayiz.chinayzy.entity.response.ClassifyTypesModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.ClassifyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/27 15:54
 * Class ClassifyPresenter   生态农业首页二级分类菜单
 */
public class ClassifyPresenter extends BasePresenter<ClassifyFragment> {
    public Net mNet = Net.getNet();
    public ClassifyTypesModel typesModel;
    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestroy() {
        mNet=null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 二级菜单请求
     * @param typeCode
     */
    public void getTypeDatas(String typeCode) {
        mNet.getTypeCodes(typeCode);
    }

    /**
     * 三级菜单请求
     * @param clasCode
     */
    public void getClassDatas(String clasCode) {
        mNet.getClassCodes(clasCode);
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.TYPE_CODES://二级分类菜单
                typesModel=(ClassifyTypesModel) message.getData();
                mView.picUrl=typesModel.getData().get(0).getPic();
                mView.mListAdpter.setModel(typesModel.getData());
                break;
            case Commons.CLASS_CODES://三级分类菜单
                ClassifyCodesModel codesModel= (ClassifyCodesModel) message.getData();
                mView.mGridAdapter.setModel(codesModel,mView.picUrl);
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

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
}

package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ClassifyCodesModel;
import com.chinayiz.chinayzy.entity.response.ClassifyTypesModel;
import com.chinayiz.chinayzy.entity.response.GoodsSteModel;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.NewClassifyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/25 15:41
 * Class NewClassifyPresenter
 */

public class NewClassifyPresenter extends BasePresenter<NewClassifyFragment> {
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
            case Commons.GOODS_SET://四级商品集合
//                typesModel=(ClassifyTypesModel) message.getData();
//                mView.picUrl=typesModel.getData().get(0).getPic();
//                mView.mLisAdpter.setModel(typesModel);
                GoodsSteModel model= (GoodsSteModel) message.getData();
                List<NY_EatItemModel.DataBean> dataBeanList =new ArrayList<>();
                NY_EatItemModel.DataBean dataBean;
                for (GoodsSteModel.DataBean bean : model.getData()) {
                    dataBean=new NY_EatItemModel.DataBean(bean.getIcon(),bean.getGname()
                            ,bean.getIsself(),bean.getShopid(),bean.getPrice(),bean.getGoodsid()
                            ,bean.getBrand(),bean.getBrand(),bean.getPraise(),bean.getCommenttotal());
                    dataBeanList.add(dataBean);
                }
                mView.mGridAdapter.setDataBeanList(dataBeanList,mView.picUrl);
                break;
            case Commons.CLASS_CODES://三级分类菜单
                ClassifyCodesModel codesModel= (ClassifyCodesModel) message.getData();
                List<ClassifyTypesModel.DataBean> datas=new ArrayList<>();
                ClassifyTypesModel.DataBean data;
                for (ClassifyCodesModel.DataBean dataBeans : codesModel.getData()) {
                    data=new ClassifyTypesModel.DataBean(dataBeans.getItemcode(),0,dataBeans.getItemname()
                            ,"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0228/645aeb61-7671-48f4-8e65-1d82c99eacd6.png");
                    datas.add(data);
                }
                mView.mListAdpter.setModel(datas);
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

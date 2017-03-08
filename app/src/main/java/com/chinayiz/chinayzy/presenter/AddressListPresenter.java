package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AddressListModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.ui.fragment.mine.AddAddressFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/1/21.
 */

public class AddressListPresenter extends BasePresenter<AddressListFragment> {
    public static final String UPDATE="UPDATE";
    public static final String DELETE="DELETE";
    public static final String EDITER="EDITER";
    public static final String ISDEFAULT="ISDEFAULT";
    @Override
    public void onCreate() {
        getData();
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
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }

    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.SHOWADDRESS:
                AddressListModel model1= (AddressListModel) message.getData();
                mView.adaphter.setData(model1.getData());
                break;
            case Commons.DELETEADDRESS:
             BaseResponseModel model2= (BaseResponseModel) message.getData();
                BaseActivity.showToast(mView.getActivity(),model2.getMsg());
                if (model2.getCode().equals("100")){
                    getData();
                }
                break;
            case Commons.DEFAULTADDRESS:
                BaseResponseModel model3= (BaseResponseModel) message.getData();
                BaseActivity.showToast(mView.getActivity(),model3.getMsg());
                if (model3.getCode().equals("100")){
                    getData();
                }
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case UPDATE:
                getData();
                break;
            case DELETE:
              String addressid= (String) message.getData();
                delete(addressid);
                break;
            case EDITER:
             AddressListModel.DataBean bean= (AddressListModel.DataBean) message.getData();
                if (mView.index==0){
                    mView.startFragment(new AddAddressFragment(1,bean,mView.index),"AddAddressFragment");
                }else {
                    MineActivity activity= (MineActivity) mView.getActivity();
                    activity.addFragment(new AddAddressFragment(1,bean,mView.index));
                }
                break;
            case ISDEFAULT:
                String addressid2= (String) message.getData();
                setDefault(addressid2);
                break;
        }
    }

    private void setDefault(String addressid2) {
        UserNet.getNet().getdefaultAddress(addressid2);
    }

    private void delete(String addressid){
        UserNet.getNet().getdeleteAddress(addressid);
    }

    private void getData(){
        UserNet.getNet().getshowAddress();
    }


    public void toAdd(){
        if (mView.index==0){
            mView.startFragment(new AddAddressFragment(0,mView.index),"AddAddressFragment");
        }else {
         MineActivity activity= (MineActivity) mView.getActivity();
            activity.addFragment(new AddAddressFragment(0,mView.index));
        }
    }
}

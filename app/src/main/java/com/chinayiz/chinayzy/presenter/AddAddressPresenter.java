package com.chinayiz.chinayzy.presenter;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.AddAddressFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class AddAddressPresenter extends BasePresenter<AddAddressFragment> {
    private LocationManager locationManager;
    private String locationProvider;
    private String lng="";
    private String lat="";
    public static final String PICKVIEW="PICKVIEW";
    public String specificaddress;

    @Override
    public void onCreate() {
        initLocation();
    }

    private void initLocation(){
        locationManager= (LocationManager) mView.getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        if(providers.contains(LocationManager.GPS_PROVIDER)){
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else{
//            Toast.makeText(mView.getActivity(), "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return ;
        }
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lng=location.getLongitude()+"";
                lat=location.getLatitude()+"";
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });
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
        }else if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.ADDADDRESS:  //新增收货地址回调
                BaseResponseModel model1= (BaseResponseModel) message.getData();
                BaseActivity.showToast(mView.getActivity(),model1.getMsg());
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,AddressListPresenter.UPDATE,""));
                if (model1.getCode().equals("100")){
                    BaseActivity activity= (BaseActivity) mView.getActivity();
                    activity.onBackPressed();
                }
                break;
            case Commons.EDITADDRESS:   //收货地址编辑
                BaseResponseModel model2= (BaseResponseModel) message.getData();
                BaseActivity.showToast(mView.getActivity(),model2.getMsg());
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,AddressListPresenter.UPDATE,""));
                if (model2.getCode().equals("100")){

                    BaseActivity activity= (BaseActivity) mView.getActivity();
                    activity.onBackPressed();
                }
                break;
            case Commons.DELETEADDRESS:  //地址删除回调
                BaseResponseModel model3= (BaseResponseModel) message.getData();
                BaseActivity.showToast(mView.getActivity(),model3.getMsg());
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,AddressListPresenter.UPDATE,""));
                if (model3.getCode().equals("100")){
                    BaseActivity activity= (BaseActivity) mView.getActivity();
                    activity.onBackPressed();
                }
                break;

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case PICKVIEW:  //地区选择器回调
                specificaddress= (String) message.getData();
               String a[]= specificaddress.split(",");
                mView.tv_part.setText(a[0]+a[1]+a[2]);
                mView.tv_part.setTextColor(Color.BLACK);
                break;
        }
    }

    public void submit() {
        // validate
        String username =mView.et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(mView.getActivity(), "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = mView. et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mView.getActivity(), "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }



        String address =mView.  et_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(mView.getActivity(), "收货地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String parter=mView.tv_part.getText().toString().trim();
        if (parter.equals("请选择")) {
            Toast.makeText(mView.getActivity(), "地区不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        com.orhanobut.logger.Logger.i(specificaddress);
        if (mView.type==mView.ADD){
            UserNet.getNet().getaddAddress(username,phone,parter,address,lng,lat,specificaddress);
        }else {
            UserNet.getNet().geteditAddress(mView.bean.getAddressid()+"",username,phone,parter,address,lng,lat,specificaddress);
        }
    }

    //删除收货地址
    public void delete() {
        UserNet.getNet().getdeleteAddress(mView.bean.getAddressid()+"");
    }
}

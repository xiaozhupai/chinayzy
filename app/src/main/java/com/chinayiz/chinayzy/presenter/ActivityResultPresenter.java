package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.request.PayModel;
import com.chinayiz.chinayzy.entity.response.ActivityResultModel;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.flexible.ActivityResultFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;
import com.chinayiz.chinayzy.utils.AliPayUntil;
import com.chinayiz.chinayzy.utils.WeChatPayUntil;
import com.chinayiz.chinayzy.utils.magicindicator.AlipayHandler;
import com.chinayiz.chinayzy.widget.LoadlingDialog;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.chinayiz.chinayzy.wxapi.WXPayEntryActivity;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelbase.BaseResp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ActivityResultPresenter extends BasePresenter<ActivityResultFragment> implements AlipayHandler.AliPay  {
    public static final String ACTIVITY_REUSLT_BACK="ACTIVITY_REUSLT_BACK";
    public ActivityResultModel resultModel;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_PAY2_FLAG = 2;

    private  AlipayHandler mHandler =new AlipayHandler(mView,this);
    private MessageDialog dialog;
    public int status;
    private LoadlingDialog loadlingDialog;
    private double resulttotal;

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
        if (message.getEventType()==EventMessage .ERROR_EVENT){
            if (loadlingDialog!=null){
                if (loadlingDialog.isShowing()){
                    loadlingDialog.dismiss();
                }
            }

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
            case Commons.PREVIEWCROWDFORDER:   //结算订单
                resultModel= (ActivityResultModel) message.getData();


                resulttotal=resultModel.getData().getPrice();

                Glide.with(mView.getActivity()).load(resultModel.getData().getIcon()).into(mView.iv_pic);
                mView.tv_titel.setText(resultModel.getData().getGname());
                mView.tv_price.setText("￥"+resultModel.getData().getPrice());
                mView.tv_result_price.setText("总计:￥"+resulttotal);

            if (resultModel.getData().getStatus().equals("1")){   //亿众币 是否可用
                mView.rl_yzb.setVisibility(View.VISIBLE);
            }else {
                mView.rl_yzb.setVisibility(View.GONE);
            }
                break;
            case Commons.ALIPAYORDER:  //支付宝支付
                loadlingDialog.dismiss();
                final AlipayModel modell= (AlipayModel) message.getData();
                if (modell.getCode().equals("100")){
                    AliPayUntil.pay(mView.getActivity(),mHandler,modell);
                }else {
                    BaseActivity.showToast(mView.getActivity(),modell.getMsg());
                }
                break;
            case Commons.WXPAYORDER: //微信支付
                loadlingDialog.dismiss();
                WxpayModel model2= (WxpayModel) message.getData();
                if (model2.getCode().equals("100")){
                    WeChatPayUntil.pay(mView,model2);
                }else {
                    BaseActivity.showToast(mView.getActivity(),model2.getMsg());
                }
                break;
            case Commons.YZBPAYORDER:  //亿众币支付
                loadlingDialog.dismiss();
                StringModel model3= (StringModel) message.getData();
                BaseActivity.showToast(mView.getActivity(),model3.getMsg());
                if (model3.getCode().equals("100")){
                     success();
                }

                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case AddressListFragment.ADDRESS_BACK:
                getData();
                break;
            case WXPayEntryActivity.WECHAT_BACK:
                BaseResp resp= (BaseResp) message.getData();
                if (resp.errCode==0){
                    status=1;
                }
                break;
        }
    }

    private void getData(){
        CommonRequestUtils.getRequestUtils().getPreviewCrowdfOrder(mView.crowdfid);
    }


    //提交结算订单
    public void submit(){
        if (resultModel==null){
            return;
        }
        final String type="4";
        String orderbill=null;
        Gson gson=new Gson();
        PayModel payModel=new PayModel();

        orderbill=gson.toJson(payModel);
        Logger.i(orderbill);


        final String result=String.format("%.2f",resulttotal);
        Logger.i("实际付款金额"+result);


        if (loadlingDialog==null){
            loadlingDialog=new LoadlingDialog(mView.mActivity);
        }
        loadlingDialog.show();
        if (mView.iv_pay_ali.isCheck){  //支付宝支付
            CommonRequestUtils.getRequestUtils().getAliPay(type,resulttotal+"",mView.crowdfid );
        }else if (mView.iv_pay_wechat.isCheck){  //微信支付
            CommonRequestUtils.getRequestUtils().getWxPay(type,resulttotal+"",mView.crowdfid);
        }else {  //亿众币支付
            CommonRequestUtils.getRequestUtils().getYzbPayOrder(resulttotal+"",mView.crowdfid);
        }

    }

    //支付成功跳转到成功页面
    public void success(){
        Logger.i("支付成功");
        mView.mActivity.onBackPressed();
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,ACTIVITY_REUSLT_BACK,mView.crowdfid));
    }

    //支付宝支付成功
    @Override
    public void onAliSuccess() {
        Logger.i("onAliSuccess");
        status=1;
    }

    //支付宝支付失败
    @Override
    public void onAliFail() {

    }
}

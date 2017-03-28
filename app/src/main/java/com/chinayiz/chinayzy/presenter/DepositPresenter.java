package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.register.DepositFragment;
import com.chinayiz.chinayzy.utils.AliPayUntil;
import com.chinayiz.chinayzy.utils.WeChatPayUntil;
import com.chinayiz.chinayzy.utils.magicindicator.AlipayHandler;
import com.chinayiz.chinayzy.widget.LoadlingDialog;
import com.chinayiz.chinayzy.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseResp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/3/27.
 */

public class DepositPresenter extends BasePresenter<DepositFragment> implements AlipayHandler.AliPay {
    private AlipayHandler mHandler =new AlipayHandler(mView,this);
    public int status;
    public static final String RESULT_BACK="RESULT_BACK";
    private LoadlingDialog loadlingDialog;
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
       if (message.getEventType()==EventMessage.NET_EVENT){
           disposeNetMsg(message);
       }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
     if (message.getEventType()==EventMessage.INFORM_EVENT){
         disposeInfoMsg(message);
     }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        loadlingDialog.dismiss();
        switch (message.getDataType()){
            case Commons.ALIPAYORDER:  //支付宝支付
                final AlipayModel modell= (AlipayModel) message.getData();
                if (modell.getCode().equals("100")){
                    AliPayUntil.pay(mView.getActivity(),mHandler,modell);
                }
                break;
            case Commons.WXPAYORDER: //微信支付
                WxpayModel model2= (WxpayModel) message.getData();
                if (model2.getCode().equals("100")){
                    WeChatPayUntil.pay(mView,model2);
                }
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case WXPayEntryActivity.WECHAT_BACK:
                BaseResp resp= (BaseResp) message.getData();
                if (resp.errCode==0){
                    status=1;
                }else {
                    status=0;
                }
                break;
        }
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    //确定支付
    public void submit() {
        String type="3";
        String total="1350";
        if (loadlingDialog==null){
            loadlingDialog=new LoadlingDialog(mView.getActivity());
        }
        loadlingDialog.show();
        if (mView.iv_ali_pay.isCheck){  //支付宝支付
            CommonRequestUtils.getRequestUtils().getAliPayOrder(type,total, "");
        }else {  //微信支付
            CommonRequestUtils.getRequestUtils().getWxPayOrder(type,total, "");
        }
    }

    @Override
    public void onAliSuccess() {
        status=1;
    }

    @Override
    public void onAliFail() {
         status=0;
    }

    //支付成功跳转到成功页面
    public void success(){
        UserSeeion.setMember(mView.getActivity());
        mView.mActivity.onBackPressed();
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,RESULT_BACK,""));
    }
}

package com.chinayiz.chinayzy.ui.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.utils.AliPayUntil;
import com.chinayiz.chinayzy.utils.WeChatPayUntil;
import com.chinayiz.chinayzy.utils.magicindicator.AlipayHandler;
import com.chinayiz.chinayzy.widget.LoadlingDialog;
import com.chinayiz.chinayzy.wxapi.WXPayEntryActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelbase.BaseResp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/13 10:28
 * Class PrestoreFragment  积分充值
 */

public class PrestoreFragment extends AbsFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AlipayHandler.AliPay {
    private EditText edit_moneys;
    private Button bt_inPut;
    private RadioGroup mRadioGroup;
    private int type=1;
    private AlipayHandler alipayHandler=new AlipayHandler(this,this);
    private int status;
    private LoadlingDialog loadlingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestore, container, false);
        initView(view);
        return view;
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        loadlingDialog.dismiss();
        switch (message.getDataType()){
            case Commons.ALIPAYORDER:
               AlipayModel alipayModel= (AlipayModel) message.getData();
                AliPayUntil.pay(getActivity(),alipayHandler,alipayModel);
                break;
            case Commons.WXPAYORDER:
                WxpayModel wxpayModel= (WxpayModel) message.getData();
                WeChatPayUntil.pay(this,wxpayModel);
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
                         Logger.i("微信支付成功");
                     }else {
                         status=0;
                         Logger.i("微信支付失败");
                     }
                     break;
             }
    }

    @Override
    public void initView(View view) {
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        iv_back_button.setOnClickListener(this);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setTextColor(Color.BLACK);
        tv_actionbar_title.setText("充值");
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);

        edit_moneys = (EditText) view.findViewById(R.id.edit_moneys);
        mRadioGroup=((RadioGroup)view.findViewById(R.id.rg_payType));
        mRadioGroup.setOnCheckedChangeListener(this);
        ((RadioButton)mRadioGroup.findViewById(R.id.rb_alipay)).setChecked(true);
        bt_inPut = (Button) view.findViewById(R.id.bt_inPut);
        bt_inPut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_button:{
                getActivity().onBackPressed();
                break;
            }
            case R.id.bt_inPut:{
                Logger.i("确认充值");
                if (loadlingDialog==null){
                    loadlingDialog=new LoadlingDialog(getActivity());
                }
                loadlingDialog.show();
                String total=edit_moneys.getText().toString().trim();
                 if (type==1){  //支付宝支付
                     CommonRequestUtils.getRequestUtils().getAliPayOrder("1",total, "");
                 }else {
                    CommonRequestUtils.getRequestUtils().getWxPayOrder("1",total, "");
                 }
                break;
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(getActivity(), "未知错误，请重试");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.rb_weicpay:{
                Logger.i("微信支付");
                type=2;
                break;
            }
            case R.id.rb_alipay:{
                type=1;
                Logger.i("支付宝支付");
                break;
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    private void submit() {
        String moneys = edit_moneys.getText().toString().trim();
        if (TextUtils.isEmpty(moneys)) {
            return;
        }

    }

    @Override
    public void onAliSuccess() {
       status=1;
        Logger.i("支付宝支付成功");
    }

    @Override
    public void onAliFail() {
        status=0;
        Logger.i("支付宝支付失败");
    }
}

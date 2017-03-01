package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**  结算订单
 * Created by Administrator on 2017/1/4.
 */

public class ResultPresenter extends BasePresenter <ResultFragment>{
    private CommonRequestUtils net =CommonRequestUtils.getRequestUtils();
    @Override
    public void onCreate() {
        net.getPreviewOrder(mView.params);
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

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
           switch (message.getDataType()){
               case Commons.PREVIEWORDER:
               ResultModel model= (ResultModel) message.getData();
               mView.tv_goods_total.setText("￥"+model.getData().getTotalmoney());
                   mView.tv_result_price.setText("总计:￥"+model.getData().getTotalmoney());
                   //运费收货地址
//                   ResultModel.DataBean.AddressRecordBean addressBean=model.getData().getAddressRecord();
//                   if (addressBean!=null){
//                       mView.tv_address_name.setText(addressBean.getConsignee());
//                       mView.tv_address_phone.setText(addressBean.getPhone());
//                       mView.tv_address_text.setText(addressBean.getArea()+addressBean.getAddress());
//                   }
                   mView.adaphter.setData(model.getData().getGoodmessage());
                   break;
           }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit(){
        String type="2";
        String total=mView.tv_result_price.getText().toString();
        String orderbill=null;

        if (mView.iv_pay_ali.isCheck){  //支付宝支付
             net.getAliPayOrder(type,total,orderbill);
        }else {  //微信支付

        }
    }
}

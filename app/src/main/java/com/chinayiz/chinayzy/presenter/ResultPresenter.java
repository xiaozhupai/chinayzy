package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.request.PayModel;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
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

import java.util.ArrayList;
import java.util.List;

/**  结算订单
 * Created by Administrator on 2017/1/4.
 */

public class ResultPresenter extends BasePresenter <ResultFragment> implements AlipayHandler.AliPay {
    public static final String RESULT_BACK="RESULT_BACK";
    private ResultModel resultModel;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_PAY2_FLAG = 2;
    private double carriagestotal;
    private  AlipayHandler mHandler =new AlipayHandler(mView,this);
    private MessageDialog dialog;
    public int status;
    private LoadlingDialog loadlingDialog;

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
            case Commons.PREVIEWORDER:   //结算订单
                resultModel= (ResultModel) message.getData();
                CarriagesTotal();
               ChangeDeducpoint(false);
                String deducpoint="可用"+resultModel.getData().getDeductionpoint()+"抵积分<font color='#ff3951'> ￥"+resultModel.getData().getDeductionpoint()+"</font>";
                mView.tv_deducpoint.setText(Html.fromHtml(deducpoint));
                //运费收货地址
                if (resultModel.getData().getAddressRecord()!=null){
                    ResultModel.DataBean.AddressRecordBean addressBean=resultModel.getData().getAddressRecord();
                    mView.tv_address_name.setText(addressBean.getConsignee());
                    mView.tv_address_phone.setText(addressBean.getPhone());
                    mView.tv_address_text.setText(addressBean.getArea()+addressBean.getAddress());
                    mView.tv_no_address.setVisibility(View.INVISIBLE);
                    mView.tv_address_name.setVisibility(View.VISIBLE);
                    mView.tv_address_phone.setVisibility(View.VISIBLE);
                    mView.tv_address_text.setVisibility(View.VISIBLE);
                }else {
                    mView.tv_address_name.setVisibility(View.INVISIBLE);
                    mView.tv_address_phone.setVisibility(View.INVISIBLE);
                    mView.tv_address_text.setVisibility(View.INVISIBLE);
                    mView.tv_no_address.setVisibility(View.VISIBLE);
                }
                mView.adaphter.setData(resultModel.getData().getGoodmessage(),resultModel.getData().getCarriages());
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
        CommonRequestUtils.getRequestUtils().getPreviewOrder(mView.params);
    }

    //计算总运费
    private void CarriagesTotal(){
        for (ResultModel.DataBean.CarriagesBean bean: resultModel.getData().getCarriages()){
            carriagestotal+=bean.getCarriage();
        }
        Logger.i("carriagestotal===============总运费"+carriagestotal);
    }

    public void ChangeDeducpoint(boolean b){
        double resultTotal;
        if (b){
             resultTotal=resultModel.getData().getTotalmoney()-resultModel.getData().getDeductionpoint();
            mView.tv_result_price.setText("总计:￥"+resultTotal+"");
        }else {
            resultTotal=resultModel.getData().getTotalmoney();
            mView.tv_result_price.setText("总计:￥"+resultTotal+"");
        }
    }

    //提交结算订单
    public void submit(){
        if (resultModel==null){
            return;
        }
        final String type="2";
        String orderbill=null;
        Gson gson=new Gson();
        PayModel payModel=new PayModel();
        if (resultModel!=null)
            if (resultModel.getData().getAddressRecord()!=null){
                payModel.setAddressid(resultModel.getData().getAddressRecord().getAddressid());
            }


        List<PayModel.ShoplistBean> list=new ArrayList<>();
        for (ResultModel.DataBean.CarriagesBean bean: resultModel.getData().getCarriages()) { //运费
            PayModel.ShoplistBean shoplistbean=new PayModel.ShoplistBean();
            shoplistbean.setCarriage(bean.getCarriage());
            shoplistbean.setShopid(bean.getShopid());
            shoplistbean.setGoodstotal(bean.getShopTatalPrice());
            List<PayModel.ShoplistBean.GoodslistBean> list_goods=new ArrayList<>();
            for (ResultModel.DataBean.GoodmessageBean goodsbean: resultModel.getData().getGoodmessage()) {  //商品
                for (ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean goodslistbean:goodsbean.getGoodmessagelist()) {
                    if (bean.getShopid()==goodslistbean.getShopid()){
                        PayModel.ShoplistBean.GoodslistBean goods_bean=new PayModel.ShoplistBean.GoodslistBean();
                        goods_bean.setGoodsstandardid(goodslistbean.getGoodsstandardid());
                        goods_bean.setCount(goodslistbean.getNum());
                        list_goods.add(goods_bean);
                    }
                }
            }
            shoplistbean.setGoodslist(list_goods);
            list.add(shoplistbean);
        }

        payModel.setIntegration(resultModel.getData().getDeductionpoint());
        payModel.setShoplist(list);
        orderbill=gson.toJson(payModel);
        Logger.i(orderbill);
        final String total; //总金额=商品金额+运费-积分
        if (mView.cb_check.isChecked()){ //判断积分是否被选中
            double result=resultModel.getData().getTotalmoney()-resultModel.getData().getDeductionpoint();
          total=result+"";
        }else {
            total=resultModel.getData().getTotalmoney()+"";
        }

        if (resultModel.getData().getAddressRecord() == null) {
            BaseActivity.showToast(mView.getActivity(),"请填写收获信息");
            return;
        }
        if (dialog==null){
            dialog=new MessageDialog(mView.getActivity());
            dialog.message.setText("请核对您的收货地址");
            dialog.setButton1("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            final String finalOrderbill = orderbill;
            dialog.setButton2("确定", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (loadlingDialog==null){
                        loadlingDialog=new LoadlingDialog(mView.mActivity);
                    }
                    loadlingDialog.show();
                    if (mView.iv_pay_ali.isCheck){  //支付宝支付
                        CommonRequestUtils.getRequestUtils().getAliPayOrder(type,total, finalOrderbill);
                    }else {  //微信支付
                        CommonRequestUtils.getRequestUtils().getWxPayOrder(type,total, finalOrderbill);
                    }
                    dialog.dismiss();
                }
            });
        }
         dialog.show();

    }

    //支付成功跳转到成功页面
    public void success(){

        mView.mActivity.onBackPressed();
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,RESULT_BACK,""));
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

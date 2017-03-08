package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
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
import com.chinayiz.chinayzy.utils.PayResult;
import com.google.gson.Gson;

import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**  结算订单
 * Created by Administrator on 2017/1/4.
 */

public class ResultPresenter extends BasePresenter <ResultFragment>{

    private ResultModel resultModel;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private double carriagestotal;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mView.getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mView.getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        };
    };
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
                final AlipayModel modell= (AlipayModel) message.getData();
                if (modell.getCode().equals("100")){
                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(mView.getActivity());
                            Map<String, String> result = alipay.payV2(modell.getData(), true);
                            Log.i("msp", result.toString());

                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };

                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }
                break;
            case Commons.WXPAYORDER: //微信支付
                WxpayModel model2= (WxpayModel) message.getData();
                String json=model2.getData();
                Logger.i(json);
                try {
                    JSONObject jsonobject=new JSONObject(json);
                    String appid=jsonobject.getString("appid");
                    String  partnerid=jsonobject.getString("partnerid");
                    String prepayid=jsonobject.getString("prepayid");
                    String packagevalue=jsonobject.getString("package");
                    String sign=jsonobject.getString("sign");
                    String noncestr=jsonobject.getString("noncestr");
                    String timestamp=jsonobject.getString("timestamp");
                    IWXAPI api = null;
                    PayReq request = new PayReq();
                    request.appId = appid;
                    request.partnerId = partnerid;
                    request.prepayId= prepayid;
                    request.packageValue =packagevalue;
                    request.nonceStr= noncestr;
                    request.timeStamp=timestamp;
                    request.sign= sign;
                    api = WXAPIFactory.createWXAPI(mView.getActivity(), appid, true);
                    api.sendReq(request);
                    api.handleIntent(mView.getActivity().getIntent(),mView);
                } catch (JSONException e) {
                    e.printStackTrace();
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
        String type="2";
        String orderbill=null;
        Gson gson=new Gson();
        PayModel payModel=new PayModel();
        if (resultModel!=null)
            if (resultModel.getData().getAddressRecord()!=null){
                payModel.setAddressid(resultModel.getData().getAddressRecord().getAddressid());
            }
        payModel.setAddressid(6);

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
        String total; //总金额=商品金额+运费-积分
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
        if (mView.iv_pay_ali.isCheck){  //支付宝支付
            CommonRequestUtils.getRequestUtils().getAliPayOrder(type,total,orderbill);
        }else {  //微信支付
            CommonRequestUtils.getRequestUtils().getWxPayOrder(type,total,orderbill);
        }
    }
}

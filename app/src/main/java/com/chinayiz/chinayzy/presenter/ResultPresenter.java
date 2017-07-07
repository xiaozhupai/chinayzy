package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.request.PayModel;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.CouponModel;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;
import com.chinayiz.chinayzy.utils.AliPayUntil;
import com.chinayiz.chinayzy.utils.DoubleUntil;
import com.chinayiz.chinayzy.utils.WeChatPayUntil;
import com.chinayiz.chinayzy.utils.magicindicator.AlipayHandler;
import com.chinayiz.chinayzy.widget.CouponDialog;
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
    public ResultModel resultModel;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_PAY2_FLAG = 2;
    private double carriagestotal;
    private  AlipayHandler mHandler =new AlipayHandler(mView,this);
    private MessageDialog dialog;
    public int status;
    private LoadlingDialog loadlingDialog;
    private double resulttotal;
    private String result;
    private String finalOrderbill;
    public String couponlogid="0";
    public String couponlogids;
    public double resultTotal_coupon;

    @Override
    public void onCreate() {
        getData(couponlogid);
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
            if (loadlingDialog.isShowing()){
                loadlingDialog.dismiss();
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
            case Commons.PREVIEWORDER:   //结算订单
                resultModel= (ResultModel) message.getData();
                CarriagesTotal();//计算总运费

                resulttotal=Double.parseDouble(resultModel.getData().getTotalmoney());

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

                if (couponlogid.equals("0")){
                    mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resulttotal));
                }else {
                    mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resultTotal_coupon));
                }

                //优惠券
              ResultModel.DataBean.CouponBean couponBean= resultModel.getData().getCoupon();
                if (TextUtils.isEmpty(couponBean.getCouponlogids())){
                    couponlogids="0";
                }else {
                    couponlogids=couponBean.getCouponlogids();
                }

                Logger.i("couponlogid="+couponlogid);
                if (couponlogid.equals("0")){
                    mView.tv_luckly_money.setText("优惠券");
                    mView.tv_coupon_num.setVisibility(View.VISIBLE);
                }else {
                    String html_coupon="优惠券<font color='#FF3951'>￥"+couponBean.getCouponprice()+"</font>";
                    Logger.i("couponBean.getCouponprice()="+couponBean.getCouponprice());
                    mView.tv_luckly_money.setText(Html.fromHtml(html_coupon));
                    mView.tv_coupon_num.setVisibility(View.GONE);
                }
                if (couponBean.getCount()==0){
                    mView.tv_coupon_num.setText("无可用优惠券");
                }else {
                    mView.tv_coupon_num.setText(resultModel.getData().getCoupon().getCount()+"张可用");
                    couponlogids=couponBean.getCouponlogids();
                }
                mView.cb_luckey_money.setClickable(false);
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
            case CouponDialog.CouponDialog:     //使用优惠券的
                CouponModel.DataBean dataBean= (CouponModel.DataBean) message.getData();
                Logger.i("优惠券ID"+couponlogid);
                couponlogid=dataBean.getCouponlogid();
                resultTotal_coupon=DoubleUntil.sub(resulttotal,dataBean.getCouponprice());

                getData(couponlogid);

                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case AddressListFragment.ADDRESS_BACK:
                getData(couponlogid);
                break;
            case WXPayEntryActivity.WECHAT_BACK:
                BaseResp resp= (BaseResp) message.getData();
                if (resp.errCode==0){
                    status=1;

                }
                break;

        }
    }

    public void getData(String couponlogid){
        CommonRequestUtils.getRequestUtils().getPreviewOrder(mView.params,couponlogid);
    }

    //计算总运费
    private void CarriagesTotal(){
        for (ResultModel.DataBean.CarriagesBean bean: resultModel.getData().getCarriages()){
            carriagestotal+=bean.getCarriage();
        }
        Logger.i("carriagestotal===============总运费"+carriagestotal);
    }

    //修改积分
    public void ChangeDeducpoint(boolean b){
        if (b){
            if (couponlogid.equals("0")){
                Logger.i("抵用积分resulttotal="+resulttotal);
                resulttotal= DoubleUntil.sub(resulttotal,resultModel.getData().getDeductionpoint());
                mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resulttotal));
            }else {
                Logger.i("抵用积分resulttotal="+resultTotal_coupon);
                resultTotal_coupon= DoubleUntil.sub(resultTotal_coupon,resultModel.getData().getDeductionpoint());
                mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resultTotal_coupon));
            }
        }else {
            if (couponlogid.equals("0")){
                Logger.i("没有抵用积分resulttotal="+resulttotal);
                resulttotal=DoubleUntil.sum(resulttotal,resultModel.getData().getDeductionpoint());
                mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resulttotal));
            }else {
                Logger.i("没有抵用积分resulttotal="+resulttotal);
                resultTotal_coupon=DoubleUntil.sum(resultTotal_coupon,resultModel.getData().getDeductionpoint());
                mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resultTotal_coupon));
            }

        }
    }

    //修改优惠券
    public void ChangeLuckeyMoney(boolean b){
        if (b){
            resulttotal=DoubleUntil.sub(resulttotal,Double.parseDouble(resultModel.getData().getCoupon().getCouponprice()));
            mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resulttotal));
        }else{
//            resulttotal=resulttotal+Double.parseDouble(resultModel.getData().getCoupon().getCouponprice());
            mView.tv_result_price.setText("总计:￥"+String.format("%.2f",resulttotal));
        }
        if (resulttotal<resultModel.getData().getDeductionpoint()){
            mView.cb_check.setClickable(false);
        }else {
            mView.cb_check.setClickable(true);
        }
    }

    //提交结算订单
    public void submit(){
        if (resultModel==null){
            return;
        }
        final String type="2";

        Gson gson=new Gson();
        PayModel payModel=new PayModel();

        //设置收货地址
        if (resultModel!=null)
            if (resultModel.getData().getAddressRecord()!=null){
                payModel.setAddressid(Integer.parseInt(resultModel.getData().getAddressRecord().getAddressid()));
            }

     //设置商品列表
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

        //设置优惠券ID
        payModel.setCouponlogid(couponlogid);

        double jifen;
        if (mView.cb_check.isChecked()){
            jifen=resultModel.getData().getDeductionpoint();
        }else {
            jifen=0.00;
        }
        Logger.i("jifen="+jifen);
        payModel.setIntegration(jifen);
        payModel.setShoplist(list);
        finalOrderbill=gson.toJson(payModel);

//        double total=Double.parseDouble(resultModel.getData().getTotalmoney()); //总金额=商品金额+运费-积分
//        if (mView.cb_check.isChecked()){ //判断积分是否被选中
//           total=total-resultModel.getData().getDeductionpoint();
//        }
//        if (mView.cb_luckey_money.isChecked()){   //判断优惠券是否被选中
//            total=total-Double.parseDouble(resultModel.getData().getCoupon().getCouponprice());
//        }
     Logger.i("orderbill="+finalOrderbill);
        if (resultModel.getData().getAddressRecord() == null) {
            BaseActivity.showToast(mView.getActivity(),"请填写收获信息");
            return;
        }
        if (couponlogid.equals("0")){  //没有使用优惠券
            result=String.format("%.2f",resulttotal);
        }else {
            result=String.format("%.2f",resultTotal_coupon);
        }

        Logger.i("实际付款金额"+result);
        if (dialog==null){
            dialog=new MessageDialog(mView.getActivity());
            dialog.message.setText("请核对您的收货地址");
            dialog.setButton1("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.setButton2("确定", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (loadlingDialog==null){
                        loadlingDialog=new LoadlingDialog(mView.mActivity);
                    }
                    loadlingDialog.show();
                    if (mView.iv_pay_ali.isCheck){  //支付宝支付
                        CommonRequestUtils.getRequestUtils().getAliPayOrder(type,result, finalOrderbill);
                    }else if (mView.iv_pay_wechat.isCheck){  //微信支付
                        CommonRequestUtils.getRequestUtils().getWxPayOrder(type,result, finalOrderbill);
                    }else {  //亿众币支付

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
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,RESULT_BACK,4));
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

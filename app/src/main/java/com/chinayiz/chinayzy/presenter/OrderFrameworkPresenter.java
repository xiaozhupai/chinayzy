package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.OrderListAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AlipayModel;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.entity.response.PayModel;
import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.common.StoreActivity;
import com.chinayiz.chinayzy.ui.fragment.mine.OrderFrameworkFragment;
import com.chinayiz.chinayzy.utils.AliPayUntil;
import com.chinayiz.chinayzy.utils.WeChatPayUntil;
import com.chinayiz.chinayzy.utils.magicindicator.AlipayHandler;
import com.chinayiz.chinayzy.wxapi.WXPayEntryActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelbase.BaseResp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.chinayiz.chinayzy.ui.fragment.mine.OrderFragment.GET_DATA;


/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/15 17:07
 * Class OrderFrameworkPresenter  订单详情
 */

public class OrderFrameworkPresenter extends BasePresenter<OrderFrameworkFragment> implements AlipayHandler.AliPay {
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public int index, state;
    private AlipayHandler alipayHandler=new AlipayHandler(mView,this);
    public int status;  //1 支付成功  2 支付失败

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.ORDER_STATE: {
                OrderListModel mpdel = (OrderListModel) message.getData();
                mView.mFragments.get(index).setOrderListModel(mpdel);
                break;
            }
            case OrderListAdapter.ADAPTER_CLICK: {
                View view = (View) message.getData();
                OrderListModel.Order.Goods goods = (OrderListModel.Order.Goods) view.getTag(R.id.tag_orderInfo);

                switch (view.getTag(R.id.click_type).toString()) {
                    case OrderListAdapter.DELETE_ORDER:
                        Logger.i("删除订单");
                        mView.showDilog(0, goods);
                        break;
                    case OrderListAdapter.PAY_ORDER:
                        Logger.i("快速支付订单");
                        mRequestUtils.fastPay(String.valueOf(goods.getOrderid()), goods.getTotalmoney());
                        break;
                    case OrderListAdapter.CANCEL_ORDER:
                        mView.showDilog(1, goods);
                        Logger.i("取消订单");
                        break;
                    case OrderListAdapter.WARN_ORDER:
                        Logger.i("提醒发货");
                        BaseActivity.showToast(mView.getActivity(),"提醒成功");
                        break;
                    case OrderListAdapter.CONFIRM_ORDER:
                        Logger.i("确认收货");
                        mView.showDilog(2, goods);
                        break;
                    case OrderListAdapter.COMMENT_ORDER:
                        Logger.i("去评论订单");
                        Skip.toOrderDetail(mView.getActivity(),String.valueOf(goods.getOrderid()));
                        break;
                }
                break;
            }
            case Commons.FAST_PAY:{
                  PayModel model= (PayModel) message.getData();
                 if (model.getData().getType().equals("1")){  //支付宝
                     AlipayModel alipayModel=new AlipayModel();
                      alipayModel.setData(model.getData().getLinkString());
                     AliPayUntil.pay(mView.getActivity(),alipayHandler,alipayModel);
                 }else {  //微信
                     WxpayModel wxpayModel=new WxpayModel();
                     wxpayModel.setData(model.getData().getLinkString());
                     WeChatPayUntil.pay(mView,wxpayModel);
                 }
                break;
            }
        }
    }


    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {
            case GET_DATA: {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state = Integer.parseInt(message.getData().toString());
                doGteOrderList(message.getData().toString(), state);
                break;
            }
            case OrderListAdapter.CLICK_STORE: {
                Logger.i("进入店铺");
                Intent intent = new Intent(mView.getActivity(), StoreActivity.class);
                intent.putExtra("storeID", message.getData().toString());
                mView.getActivity().startActivity(intent);
                break;
            }
            case WXPayEntryActivity.WECHAT_BACK:
                BaseResp resp= (BaseResp) message.getData();
                if (resp.errCode==0){
                    status=1;
                    Logger.i("微信支付成功");
                }else {
                    state=0;
                    Logger.i("微信支付失败");
                }
                break;
        }
    }

    @Override
    protected void onCreate() {
        mRequestUtils.getImOrder(String.valueOf(mView.orderType));
    }


    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(mView.getActivity(), "未知错误，请重试");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    public void doGteOrderList(String code, int position) {
        index = position;
        mRequestUtils.getImOrder(code);
    }

    @Override
    public void onAliSuccess() {
         status=1;
        Logger.i("支付宝支付成功");
    }

    @Override
    public void onAliFail() {
        state=0;
        Logger.i("支付宝支付失败");
    }
}

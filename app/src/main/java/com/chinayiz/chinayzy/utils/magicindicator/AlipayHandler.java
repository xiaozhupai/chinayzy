package com.chinayiz.chinayzy.utils.magicindicator;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.utils.PayResult;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**  支付宝支付回调
 * Created by Administrator on 2017/3/18.
 */

public class AlipayHandler extends Handler {
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_PAY2_FLAG = 2;
    public static final String RESULT_BACK="RESULT_BACK";
    private BaseFragment mView;
    private AliPay listener;
    public AlipayHandler(BaseFragment mView,AliPay listener){
        this.mView=mView;
        this.listener=listener;
    }
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SDK_PAY_FLAG:
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
//                    Toast.makeText(mView.mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                    listener.onAliSuccess();
                } else {
                     listener.onAliFail();
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                    Toast.makeText(mView.mActivity, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case SDK_PAY2_FLAG:
              int code= (int) msg.obj;
                if (code==0){
                    listener.onAliSuccess();
                }
                break;

        }
    }


    public interface AliPay{
        void onAliSuccess();   //支付宝支付成功
        void onAliFail();      //支付宝支付失败
    }
}

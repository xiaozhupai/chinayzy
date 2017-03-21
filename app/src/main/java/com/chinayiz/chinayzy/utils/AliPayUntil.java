package com.chinayiz.chinayzy.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.chinayiz.chinayzy.entity.response.AlipayModel;

import java.util.Map;

/**   支付宝支付工具类
 * Created by Administrator on 2017/3/18.
 */

public class AliPayUntil {
    private static final int SDK_PAY_FLAG=1;

    /**
     *
     * @param activity
     * @param mHandler
     * @param model   支付宝数据
     */
    public static void pay(final Activity activity, final Handler mHandler, final AlipayModel model){
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(model.getData(), true);
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
}

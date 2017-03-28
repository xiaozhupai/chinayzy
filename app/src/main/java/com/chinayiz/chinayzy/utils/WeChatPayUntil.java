package com.chinayiz.chinayzy.utils;

import android.app.Fragment;

import com.chinayiz.chinayzy.entity.response.WxpayModel;
import com.chinayiz.chinayzy.wxapi.Constants;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**  微信支付工具类
 * Created by Administrator on 2017/3/18.
 */

public class WeChatPayUntil {
    /**
     *
     * @param mView  当前的fragment
     * @param model   微信支付数据源
     */
      public static void pay(Fragment mView, WxpayModel model){
          String json=model.getData();
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
              api = WXAPIFactory.createWXAPI(mView.getActivity(), Constants.APP_ID, true);
              api.registerApp(Constants.APP_ID);
              api.sendReq(request);
          } catch (JSONException e) {
              e.printStackTrace();
          }
      }
}

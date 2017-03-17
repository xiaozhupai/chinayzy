package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/17 18:22
 * Class PayModel
 */

public class PayModel extends BaseResponseModel{

    /**
     * data : {"linkString":"partner=\"2088621217489262\"&seller_id=\"chinayzy2017@163.com\"&out_trade_no=\"20170317182611381\"&subject=\"支付宝支付\"&body=\"支付宝支付\"&total_fee=\"0.01\"&notify_url=\"http://xpcoffee.imwork.net:37430/yzyProduct/v1/api/pay/aliNotify\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"90m\"&sign=\"mF04alyUVuePqQDGAI0X6WmraMtg0lBySUc6itnbrGXpiZoObcOFtbF%2FXR94bybxuG8HH4g2LxG3laQvTp26mw0GxC71mnRVgh7UkD10jn8BsFEXbWy1%2F1b6PpVUg4nHR9LjMgSZ1NTR1eQ662wdt8sAFVV91s7PHF6wzEJ0sM8%3D\"&sign_type=\"RSA\"","type":"1"}
     */

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * linkString : partner="2088621217489262"&seller_id="chinayzy2017@163.com"&out_trade_no="20170317182611381"&subject="支付宝支付"&body="支付宝支付"&total_fee="0.01"&notify_url="http://xpcoffee.imwork.net:37430/yzyProduct/v1/api/pay/aliNotify"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="90m"&sign="mF04alyUVuePqQDGAI0X6WmraMtg0lBySUc6itnbrGXpiZoObcOFtbF%2FXR94bybxuG8HH4g2LxG3laQvTp26mw0GxC71mnRVgh7UkD10jn8BsFEXbWy1%2F1b6PpVUg4nHR9LjMgSZ1NTR1eQ662wdt8sAFVV91s7PHF6wzEJ0sM8%3D"&sign_type="RSA"
         * type : 1
         */

        private String linkString;
        private String type;

        public String getLinkString() {
            return linkString;
        }

        public void setLinkString(String linkString) {
            this.linkString = linkString;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

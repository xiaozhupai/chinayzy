package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/6/17.
 */

public class DefaultAddressModel extends BaseResponseModel {

    /**
     * data : {"phone":"13871009000","area":"湖北省 武汉市 洪山区","addressid":11,"address":"东湖新技术开发区光谷大道3号未来之光1-502","consignee":"王建超","province":"湖北省"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * phone : 13871009000
         * area : 湖北省 武汉市 洪山区
         * addressid : 11
         * address : 东湖新技术开发区光谷大道3号未来之光1-502
         * consignee : 王建超
         * province : 湖北省
         */

        private String phone;
        private String area;
        private String addressid;
        private String address;
        private String consignee;
        private String province;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddressid() {
            return addressid;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}

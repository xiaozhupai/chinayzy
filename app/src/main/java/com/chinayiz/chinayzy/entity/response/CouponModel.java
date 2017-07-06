package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class CouponModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-07-01 10:22:49
         * endtime : 2064-01-01 10:22:32
         * couponname : 代金券
         * starttime : 2017-07-01 10:22:22
         * couponlogid : 26
         * couponcontent : 可购买全部商品
         * couponid : 2
         * coupontitle : 满100可用
         * couponremark : null
         * usetime : null
         * couponprice : 10.0
         * coupontype : 2
         */

        private String createtime;
        private String endtime;
        private String couponname;
        private String starttime;
        private String couponlogid;
        private String couponcontent;
        private String couponid;
        private String coupontitle;
        private String couponremark;
        private String usetime;
        private double couponprice;
        private String coupontype;

        public String getCouponlogid() {
            return couponlogid;
        }

        public void setCouponlogid(String couponlogid) {
            this.couponlogid = couponlogid;
        }

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public void setCouponremark(String couponremark) {
            this.couponremark = couponremark;
        }

        public void setUsetime(String usetime) {
            this.usetime = usetime;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getCouponname() {
            return couponname;
        }

        public void setCouponname(String couponname) {
            this.couponname = couponname;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }



        public String getCouponcontent() {
            return couponcontent;
        }

        public void setCouponcontent(String couponcontent) {
            this.couponcontent = couponcontent;
        }



        public String getCoupontitle() {
            return coupontitle;
        }

        public void setCoupontitle(String coupontitle) {
            this.coupontitle = coupontitle;
        }

        public Object getCouponremark() {
            return couponremark;
        }


        public Object getUsetime() {
            return usetime;
        }



        public double getCouponprice() {
            return couponprice;
        }

        public void setCouponprice(double couponprice) {
            this.couponprice = couponprice;
        }

        public String getCoupontype() {
            return coupontype;
        }

        public void setCoupontype(String coupontype) {
            this.coupontype = coupontype;
        }
    }
}

package com.chinayiz.chinayzy.entity.request;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class PayModel {


    /**
     * addressid : 333
     * integration : 44.5
     * couponlogid : 1
     * shoplist : [{"shopid":222,"carriage":12.33,"goodstotal":33.22,"goodslist":[{"goodsstandardid":1,"count":2},{"goodsstandardid":2,"count":2}]},{"shopid":222,"carriage":62.33,"goodstotal":83.22,"goodslist":[{"goodsstandardid":3,"count":2},{"goodsstandardid":4,"count":2}]}]
     */

    private int addressid;
    private double integration;
    private double vippoint;
    private String couponlogid;
    private List<ShoplistBean> shoplist;

    public String getCouponlogid() {
        return couponlogid;
    }

    public void setCouponlogid(String couponlogid) {
        this.couponlogid = couponlogid;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public double getIntegration() {
        return integration;
    }

    public void setIntegration(double integration) {
        this.integration = integration;
    }

    public double getVippoint() {
        return vippoint;
    }

    public void setVippoint(double vippoint) {
        this.vippoint = vippoint;
    }

    public List<ShoplistBean> getShoplist() {
        return shoplist;
    }

    public void setShoplist(List<ShoplistBean> shoplist) {
        this.shoplist = shoplist;
    }

    public static class ShoplistBean {
        /**
         * shopid : 222
         * carriage : 12.33
         * goodstotal : 33.22
         * goodslist : [{"goodsstandardid":1,"count":2},{"goodsstandardid":2,"count":2}]
         */

        private int shopid;
        private double carriage;
        private double goodstotal;
        private List<GoodslistBean> goodslist;

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public double getCarriage() {
            return carriage;
        }

        public void setCarriage(double carriage) {
            this.carriage = carriage;
        }

        public double getGoodstotal() {
            return goodstotal;
        }

        public void setGoodstotal(double goodstotal) {
            this.goodstotal = goodstotal;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * goodsstandardid : 1
             * count : 2
             */

            private int goodsstandardid;
            private int count;

            public int getGoodsstandardid() {
                return goodsstandardid;
            }

            public void setGoodsstandardid(int goodsstandardid) {
                this.goodsstandardid = goodsstandardid;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}

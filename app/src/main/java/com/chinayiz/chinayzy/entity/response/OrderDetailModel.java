package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/13 16:56
 * Class OrderDetailModel 订单详情
 */

public class OrderDetailModel extends BaseResponseModel {

    /**
     * data : {"totalmoney":54422,"phone":"","addressid":"","deliverytime":"2017-02-20 11:49:23","carriage":"","consignee":"","isself":"0","state":"","omessages":[{"orderdetailid":1,"icon":"http://chinayiz.cn/m1/2017/0218/1f1829f5-03d0-40b6-aeb0-38edd6ab839b.jpg","price":100.75,"standerpic":"http://chinayiz.cn/m1/2017/0217/f76d2886-1cd7-4593-9d3e-57ae17b98d10.jpg","gname":"云雾绿茶","goodsdesc":"好吃","standardvalue":"2只装","standardname":"2只装","goodscount":2,"iscomment":"0"}],"code":"","ordertime":"2017-02-15 11:49:17","sname":"玉树临风","paytime":"2017-02-22 11:49:20","point":"","num":2,"shopid":2,"area":"","address":"","money":"","orderid":2,"receivingtime":"2017-02-11 11:49:26"}
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
         * totalmoney : 54422
         * phone :
         * addressid :
         * deliverytime : 2017-02-20 11:49:23
         * carriage :
         * consignee :
         * isself : 0
         * state :
         * omessages : [{"orderdetailid":1,"icon":"http://chinayiz.cn/m1/2017/0218/1f1829f5-03d0-40b6-aeb0-38edd6ab839b.jpg","price":100.75,"standerpic":"http://chinayiz.cn/m1/2017/0217/f76d2886-1cd7-4593-9d3e-57ae17b98d10.jpg","gname":"云雾绿茶","goodsdesc":"好吃","standardvalue":"2只装","standardname":"2只装","goodscount":2,"iscomment":"0"}]
         * code :
         * ordertime : 2017-02-15 11:49:17
         * sname : 玉树临风
         * paytime : 2017-02-22 11:49:20
         * point :
         * num : 2
         * shopid : 2
         * area :
         * address :
         * money :
         * orderid : 2
         * receivingtime : 2017-02-11 11:49:26
         * couponprice :1350
         */

        private String totalmoney;
        private String phone;
        private String pic;
        private String addressid;
        private String deliverytime;
        private String carriage;
        private String consignee;
        private String isself;
        private String state;
        @SerializedName("code")
        private String codeX;
        private String ordertime;
        private String sname;
        private String paytime;
        private String point;
        private String couponprice;
        private int num;
        private int shopid;
        private String area;
        private String address;
        private String money;
        private int orderid;
        private String receivingtime;
        private List<OmessagesBean> omessages;
        private String type;
        private String vippoint;

        public String getCouponprice() {
            return couponprice;
        }

        public void setCouponprice(String couponprice) {
            this.couponprice = couponprice;
        }

        public String getTotalmoney() {
            return totalmoney;
        }

        public void setTotalmoney(String totalmoney) {
            this.totalmoney = totalmoney;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getAddressid() {
            return addressid;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
        }

        public String getDeliverytime() {
            return deliverytime;
        }

        public void setDeliverytime(String deliverytime) {
            this.deliverytime = deliverytime;
        }

        public String getCarriage() {
            return carriage;
        }

        public void setCarriage(String carriage) {
            this.carriage = carriage;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCodeX() {
            return codeX;
        }

        public void setCodeX(String codeX) {
            this.codeX = codeX;
        }

        public String getOrdertime() {
            return ordertime;
        }

        public void setOrdertime(String ordertime) {
            this.ordertime = ordertime;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getReceivingtime() {
            return receivingtime;
        }

        public void setReceivingtime(String receivingtime) {
            this.receivingtime = receivingtime;
        }

        public List<OmessagesBean> getOmessages() {
            return omessages;
        }

        public void setOmessages(List<OmessagesBean> omessages) {
            this.omessages = omessages;
        }

        public static class OmessagesBean {
            /**
             * orderdetailid : 1
             * icon : http://chinayiz.cn/m1/2017/0218/1f1829f5-03d0-40b6-aeb0-38edd6ab839b.jpg
             * price : 100.75
             * standerpic : http://chinayiz.cn/m1/2017/0217/f76d2886-1cd7-4593-9d3e-57ae17b98d10.jpg
             * gname : 云雾绿茶
             * goodsdesc : 好吃
             * standardvalue : 2只装
             * standardname : 2只装
             * goodsid
             * goodscount : 2
             * iscomment : 0
             */

            private int orderdetailid;
            private String icon;
            private double price;
            private String standerpic;
            private String gname;
            private String goodsdesc;
            private String standardvalue;
            private String standardname;
            private int goodscount;
            private String iscomment;
            private String goodsid;

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

            public int getOrderdetailid() {
                return orderdetailid;
            }

            public void setOrderdetailid(int orderdetailid) {
                this.orderdetailid = orderdetailid;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getStanderpic() {
                return standerpic;
            }

            public void setStanderpic(String standerpic) {
                this.standerpic = standerpic;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getGoodsdesc() {
                return goodsdesc;
            }

            public void setGoodsdesc(String goodsdesc) {
                this.goodsdesc = goodsdesc;
            }

            public String getStandardvalue() {
                return standardvalue;
            }

            public void setStandardvalue(String standardvalue) {
                this.standardvalue = standardvalue;
            }

            public String getStandardname() {
                return standardname;
            }

            public void setStandardname(String standardname) {
                this.standardname = standardname;
            }

            public int getGoodscount() {
                return goodscount;
            }

            public void setGoodscount(int goodscount) {
                this.goodscount = goodscount;
            }

            public String getIscomment() {
                return iscomment;
            }

            public void setIscomment(String iscomment) {
                this.iscomment = iscomment;
            }
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVippoint() {
            return vippoint;
        }

        public void setVippoint(String vippoint) {
            this.vippoint = vippoint;
        }
    }
}

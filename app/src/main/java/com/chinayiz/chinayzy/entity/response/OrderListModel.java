package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/13 16:50
 * Class OrderListModel 我的订单
 */

public class OrderListModel extends BaseResponseModel {

    @SerializedName("data")
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public static class Order {
        @SerializedName("orderlist")
        private List<Goods> goodsList;

        public List<Goods> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<Goods> goodsList) {
            this.goodsList = goodsList;
        }

        public static class Goods {
            /**
             * salesvolume : 500
             * state : 1
             * gname : 陈泰昌 茶叶礼盒 乌龙茶 漳平水仙茶浓香型茶饼 礼盒装240g
             * ordertime : 2017-03-14 14:52:51
             * paytime : null
             * sname : 亿众自营店
             * goodscount : 1
             * shopid : 3
             * money : 650
             * shopmessage : 3,亿众自营店
             * grade : null
             * productarea : 中国大陆
             * receivingtime : null
             * orderdetailid : 267
             * totalmoney : 650
             * icon : http://chinayiz.cn/m1/2017/0227/fbe5da42-a74c-4925-afd0-e98a9efbd742.jpg
             * deliverytime : null
             * standerpic : http://chinayiz.cn/m1/2017/0227/73fb19e7-3f56-4ba1-b288-c73e2b6d0fe6.jpg
             * pic : http://chinayiz.cn/v1/2017/0216/64aae6de-928e-46fe-8f7a-d9571fadab95.png
             * standardname : 礼盒装
             * point : 0
             * unit : g
             * price : 299
             * brand : 陳泰昌
             * orderid : 192
             * standardvalue : 180g
             */

            private int salesvolume;
            private String state;
            private String gname;
            private String ordertime;
            private Object paytime;
            private String sname;
            private int goodscount;
            private int shopid;
            private String money;
            private String shopmessage;
            private Object grade;
            private String productarea;
            private Object receivingtime;
            private int orderdetailid;
            private String totalmoney;
            private String icon;
            private Object deliverytime;
            private String standerpic;
            private String pic;
            private String standardname;
            private int point;
            private String unit;
            private String price;
            private String brand;
            private int orderid;
            private String standardvalue;

            public int getSalesvolume() {
                return salesvolume;
            }

            public void setSalesvolume(int salesvolume) {
                this.salesvolume = salesvolume;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getOrdertime() {
                return ordertime;
            }

            public void setOrdertime(String ordertime) {
                this.ordertime = ordertime;
            }

            public Object getPaytime() {
                return paytime;
            }

            public void setPaytime(Object paytime) {
                this.paytime = paytime;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public int getGoodscount() {
                return goodscount;
            }

            public void setGoodscount(int goodscount) {
                this.goodscount = goodscount;
            }

            public int getShopid() {
                return shopid;
            }

            public void setShopid(int shopid) {
                this.shopid = shopid;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getShopmessage() {
                return shopmessage;
            }

            public void setShopmessage(String shopmessage) {
                this.shopmessage = shopmessage;
            }

            public Object getGrade() {
                return grade;
            }

            public void setGrade(Object grade) {
                this.grade = grade;
            }

            public String getProductarea() {
                return productarea;
            }

            public void setProductarea(String productarea) {
                this.productarea = productarea;
            }

            public Object getReceivingtime() {
                return receivingtime;
            }

            public void setReceivingtime(Object receivingtime) {
                this.receivingtime = receivingtime;
            }

            public int getOrderdetailid() {
                return orderdetailid;
            }

            public void setOrderdetailid(int orderdetailid) {
                this.orderdetailid = orderdetailid;
            }

            public String getTotalmoney() {
                return totalmoney;
            }

            public void setTotalmoney(String totalmoney) {
                this.totalmoney = totalmoney;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getDeliverytime() {
                return deliverytime;
            }

            public void setDeliverytime(Object deliverytime) {
                this.deliverytime = deliverytime;
            }

            public String getStanderpic() {
                return standerpic;
            }

            public void setStanderpic(String standerpic) {
                this.standerpic = standerpic;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getStandardname() {
                return standardname;
            }

            public void setStandardname(String standardname) {
                this.standardname = standardname;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public int getOrderid() {
                return orderid;
            }

            public void setOrderid(int orderid) {
                this.orderid = orderid;
            }

            public String getStandardvalue() {
                return standardvalue;
            }

            public void setStandardvalue(String standardvalue) {
                this.standardvalue = standardvalue;
            }

            @Override
            public String toString() {
                return "Goods{" +
                        "salesvolume=" + salesvolume +
                        ", state='" + state + '\'' +
                        ", gname='" + gname + '\'' +
                        ", ordertime='" + ordertime + '\'' +
                        ", paytime=" + paytime +
                        ", sname='" + sname + '\'' +
                        ", goodscount=" + goodscount +
                        ", shopid=" + shopid +
                        ", money='" + money + '\'' +
                        ", shopmessage='" + shopmessage + '\'' +
                        ", grade=" + grade +
                        ", productarea='" + productarea + '\'' +
                        ", receivingtime=" + receivingtime +
                        ", orderdetailid=" + orderdetailid +
                        ", totalmoney=" + totalmoney +
                        ", icon='" + icon + '\'' +
                        ", deliverytime=" + deliverytime +
                        ", standerpic='" + standerpic + '\'' +
                        ", pic='" + pic + '\'' +
                        ", standardname='" + standardname + '\'' +
                        ", point=" + point +
                        ", unit='" + unit + '\'' +
                        ", price=" + price +
                        ", brand='" + brand + '\'' +
                        ", orderid=" + orderid +
                        ", standardvalue='" + standardvalue + '\'' +
                        '}';
            }
        }
    }
}

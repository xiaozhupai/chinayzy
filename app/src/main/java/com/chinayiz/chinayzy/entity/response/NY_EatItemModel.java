package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 11:32
 * Class NY_LoveEatResponeResult 生态农业爱吃版块
 */

public class NY_EatItemModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * icon : http://chinayiz.cn/m1/2017/0222/e1e63f48-8025-467e-aa42-e8ead41cd685.jpg
         * shopid : 3
         * price : 209.00-226.00
         * goodsid : 2
         * salesvolume : 500
         * gname : 润虎 茶叶 红茶 武夷山金骏眉 大师茶陶瓷罐礼盒装
         * brand : 润虎
         * goodsstandardid : 15
         * repertorytotal : 99
         * productarea : 福建省泉州市
         */

        private String icon;
        private int shopid;
        private String price;
        private int goodsid;
        private int salesvolume;
        private String gname;
        private String brand;
        private int goodsstandardid;
        private int repertorytotal;
        private String productarea;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public int getSalesvolume() {
            return salesvolume;
        }

        public void setSalesvolume(int salesvolume) {
            this.salesvolume = salesvolume;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(int goodsstandardid) {
            this.goodsstandardid = goodsstandardid;
        }

        public int getRepertorytotal() {
            return repertorytotal;
        }

        public void setRepertorytotal(int repertorytotal) {
            this.repertorytotal = repertorytotal;
        }

        public String getProductarea() {
            return productarea;
        }

        public void setProductarea(String productarea) {
            this.productarea = productarea;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "brand='" + brand + '\'' +
                    ", icon='" + icon + '\'' +
                    ", shopid=" + shopid +
                    ", price='" + price + '\'' +
                    ", goodsid=" + goodsid +
                    ", salesvolume=" + salesvolume +
                    ", gname='" + gname + '\'' +
                    ", goodsstandardid=" + goodsstandardid +
                    ", repertorytotal=" + repertorytotal +
                    ", productarea='" + productarea + '\'' +
                    '}';
        }
    }
}

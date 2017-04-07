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

    @Override
    public String toString() {
        return "NY_EatItemModel{" +
                "data=" + data +
                '}';
    }

    public  class DataBean {
        /**
         * icon : http://chinayiz.cn/m1/2017/0328/b981e389-300e-4b2f-85d5-370251e9c6a4.jpg
         * salesvolume : 0
         * gname : 凤头姜原味型恩施特产
         * isself : 1
         * goodsstandardid : 128
         * shopid : 3
         * price : 22.00-60.00
         * goodsid : 59
         * brand : 凤头
         * comment3 : 0
         * repertorytotal : 99
         * praise : 暂无
         * productarea : 恩施来凤
         * commenttotal : 0
         */

        private String icon;
        private String salesvolume;
        private String gname;
        private String isself;
        private String goodsstandardid;
        private String shopid;
        private String price;
        private String goodsid;
        private String brand;
        private String comment3;
        private String repertorytotal;
        private String praise;
        private String productarea;
        private String commenttotal;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSalesvolume() {
            return salesvolume;
        }

        public void setSalesvolume(String salesvolume) {
            this.salesvolume = salesvolume;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
        }

        public String getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(String goodsstandardid) {
            this.goodsstandardid = goodsstandardid;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getComment3() {
            return comment3;
        }

        public void setComment3(String comment3) {
            this.comment3 = comment3;
        }

        public String getRepertorytotal() {
            return repertorytotal;
        }

        public void setRepertorytotal(String repertorytotal) {
            this.repertorytotal = repertorytotal;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getProductarea() {
            return productarea;
        }

        public void setProductarea(String productarea) {
            this.productarea = productarea;
        }

        public String getCommenttotal() {
            return commenttotal;
        }

        public void setCommenttotal(String commenttotal) {
            this.commenttotal = commenttotal;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "icon='" + icon + '\'' +
                    ", salesvolume='" + salesvolume + '\'' +
                    ", gname='" + gname + '\'' +
                    ", isself='" + isself + '\'' +
                    ", goodsstandardid='" + goodsstandardid + '\'' +
                    ", shopid='" + shopid + '\'' +
                    ", price='" + price + '\'' +
                    ", goodsid='" + goodsid + '\'' +
                    ", brand='" + brand + '\'' +
                    ", comment3='" + comment3 + '\'' +
                    ", repertorytotal='" + repertorytotal + '\'' +
                    ", praise='" + praise + '\'' +
                    ", productarea='" + productarea + '\'' +
                    ", commenttotal='" + commenttotal + '\'' +
                    '}';
        }
    }
}

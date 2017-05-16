package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/10 15:02
 * Class HomeHotGoodsModel
 */

public class HomeHotGoodsModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * icon : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0428/c7189daa-2028-4bc5-8f2d-38487c2fea31.jpg
         * price : 3350
         * updatetime : 2017-04-20 18:46:42
         * goodsid : 149
         * isself : 1
         * gname : 益众之天乙 优质红茶/恩施富硒红茶 /利川红 /明前冷后浑   100g/盒
         * brand : 益众
         * goodsstandardid : 252
         * praise : 100%
         * commenttotal : 0
         */
        private String icon;
        private String price;
        private String updatetime;
        private String goodsid;
        private String isself;
        private String gname;
        private String brand;
        private String goodsstandardid;
        private String praise;
        private String commenttotal;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
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

        public String getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(String goodsstandardid) {
            this.goodsstandardid = goodsstandardid;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
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
                    ", price='" + price + '\'' +
                    ", updatetime='" + updatetime + '\'' +
                    ", goodsid='" + goodsid + '\'' +
                    ", isself='" + isself + '\'' +
                    ", gname='" + gname + '\'' +
                    ", brand='" + brand + '\'' +
                    ", goodsstandardid='" + goodsstandardid + '\'' +
                    ", praise='" + praise + '\'' +
                    ", commenttotal='" + commenttotal + '\'' +
                    '}';
        }
    }
}

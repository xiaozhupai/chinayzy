package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/8 10:14
 * Class StoreGoodsListModel 店铺主页相关商品
 */
public class StoreGoodsListModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * icon : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
         * unit : g
         * price : 50-100
         * goodsid : 1
         * gname : 云雾绿茶
         * brand : 英山云雾
         * goodsdesc : 好吃
         * itemcode : 001001
         */

        private String icon;
        private String unit;
        private String price;
        private int goodsid;
        private String gname;
        private String brand;
        private String goodsdesc;
        private String itemcode;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
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

        public String getGoodsdesc() {
            return goodsdesc;
        }

        public void setGoodsdesc(String goodsdesc) {
            this.goodsdesc = goodsdesc;
        }

        public String getItemcode() {
            return itemcode;
        }

        public void setItemcode(String itemcode) {
            this.itemcode = itemcode;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "brand='" + brand + '\'' +
                    ", icon='" + icon + '\'' +
                    ", unit='" + unit + '\'' +
                    ", price='" + price + '\'' +
                    ", goodsid=" + goodsid +
                    ", gname='" + gname + '\'' +
                    ", goodsdesc='" + goodsdesc + '\'' +
                    ", itemcode='" + itemcode + '\'' +
                    '}';
        }
    }
}

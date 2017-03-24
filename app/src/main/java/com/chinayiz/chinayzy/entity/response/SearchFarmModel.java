package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7. 搜索结果
 */

public class SearchFarmModel extends BaseResponseModel {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * unit : g
         * icon : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg
         * shopid : 1
         * price : 50-100
         * goodsid : 1
         * gname : 云雾绿茶
         * brand : 英山云雾
         * goodsstandardid : 1
         * productarea : 湖北
         */

        private String unit;
        private String icon;
        private int shopid;
        private String price;
        private int goodsid;
        private String gname;
        private String brand;
        private int goodsstandardid;
        private String productarea;

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
        }

        private String isself;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

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

        public String getProductarea() {
            return productarea;
        }

        public void setProductarea(String productarea) {
            this.productarea = productarea;
        }
    }
}

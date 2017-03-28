package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 10:27
 * Class NY_FeatureResponseResult 农业首页特色商品版块
 */

public class NY_FeatureModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NY_FeatureModel{" +
                "data=" + data +
                '}';
    }

    public  class DataBean {
        /**
         * themename : 补血
         * themepic : https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg
         * type : 1
         * repertorytotal : 100
         * productarea : 湖北
         * goodsid : 2
         * price : 50-100
         * icon : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
         * salesvolume : 500
         * gname : 麻油
         * brand : 金龙鱼
         */

        private String themename;
        private String themepic;
        private String type;
        private int repertorytotal;
        private String productarea;
        private int goodsid;
        private String price;
        private String icon;
        private int salesvolume;
        private String gname;
        private String brand;
        private String isself;

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
        }
        public String getThemename() {
            return themename;
        }

        public void setThemename(String themename) {
            this.themename = themename;
        }

        public String getThemepic() {
            return themepic;
        }

        public void setThemepic(String themepic) {
            this.themepic = themepic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "themename='" + themename + '\'' +
                    ", themepic='" + themepic + '\'' +
                    ", type='" + type + '\'' +
                    ", repertorytotal=" + repertorytotal +
                    ", productarea='" + productarea + '\'' +
                    ", goodsid=" + goodsid +
                    ", price='" + price + '\'' +
                    ", icon='" + icon + '\'' +
                    ", salesvolume=" + salesvolume +
                    ", gname='" + gname + '\'' +
                    ", brand='" + brand + '\'' +
                    '}';
        }
    }
}
